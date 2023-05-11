/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package auth;

import io.jsonwebtoken.ExpiredJwtException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.security.enterprise.AuthenticationException;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import javax.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.CredentialValidationResult.Status;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static jwtrest.Constants.AUTHORIZATION_HEADER;
import static jwtrest.Constants.BEARER;
import jwtrest.JWTCredential;
import jwtrest.TokenProvider;
import record.KeepRecord;

/**
 *
 * @author kevin
 */
@Named
@RequestScoped
public class SecureAuthentication implements HttpAuthenticationMechanism, Serializable {

    @Inject
    IdentityStoreHandler handler;
    CredentialValidationResult result;
    AuthenticationStatus status;

    @Inject
    TokenProvider tokenProvider;

    /**
     * Creates a new instance of SecureAuthentication
     */
    public SecureAuthentication() {
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {

        try {

            if (request.getRequestURI().contains("vibeLogout")) {

                request.logout();
                KeepRecord.reset();
                response.sendRedirect("login.xhtml");
                return httpMessageContext.doNothing();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        String token = extractToken(httpMessageContext);
        try {

            System.out.println("Name = " + request.getParameter("username"));

            if (token == null && request.getParameter("username") != null) {

                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println("In Auth");

                Credential credential = new UsernamePasswordCredential(username, new Password(password));
                result = handler.validate(credential);

                if (result.getStatus() == Status.VALID) {

                    KeepRecord.setErrorStatus("");
                    AuthenticationStatus status = createToken(result, httpMessageContext);

                    status = httpMessageContext.notifyContainerAboutLogin(result);

                    KeepRecord.setPrincipal(result.getCallerPrincipal());
                    //KeepRecord.setRoles(result.getCallerGroups());
                    KeepRecord.setCredential(credential);

                    if (result.getCallerGroups().contains("Admin")) {
                        request.getRequestDispatcher("admin/home.xhtml").forward(request, response);
                    }
                    if (result.getCallerGroups().contains("User")) {
                        request.getRequestDispatcher("web/home.xhtml").forward(request, response);
                    }

                    return status;

                } else {
                    KeepRecord.setErrorStatus("Either Username or Password is wrong !");
                    response.sendRedirect("login.xhtml");

                    return httpMessageContext.doNothing();
                }
            }

            if (KeepRecord.getToken() != null) {
                
                httpMessageContext.notifyContainerAboutLogin(KeepRecord.getPrincipal(), KeepRecord.getRoles());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
        
        
        if (token != null) {
            // validation of the jwt credential
            return validateToken(token, httpMessageContext);
        }else if (httpMessageContext.isProtected()) {
            // A protected resource is a resource for which a constraint has been defined.
            // if there are no credentials and the resource is protected, we response with unauthorized status
            return httpMessageContext.responseUnauthorized();
        }

        return httpMessageContext.doNothing();
    }

    private AuthenticationStatus validateToken(String token, HttpMessageContext context) {
        try {
            if (tokenProvider.validateToken(token)) {
                JWTCredential credential = tokenProvider.getCredential(token);
                // System.out.println("JWTAuthenticationMechanism-Token Validated");
                return context.notifyContainerAboutLogin(credential.getPrincipal(), credential.getAuthorities());

            }
            // if token invalid, response with unauthorized status
            return context.responseUnauthorized();
        } catch (ExpiredJwtException eje) {
            //LOGGER.log(Level.INFO, "Security exception for user {0} - {1}", new String[]{eje.getClaims().getSubject(), eje.getMessage()});
            return context.responseUnauthorized();
        }
    }

    private AuthenticationStatus createToken(CredentialValidationResult result, HttpMessageContext context) {
        // if (!isRememberMe(context)) {
        // if (true) {
        String jwt = tokenProvider.createToken(result.getCallerPrincipal().getName(), result.getCallerGroups(), false);
        //context.getRequest().getSession().setAttribute("token", jwt);
        KeepRecord.setToken(jwt);
        context.getResponse().addHeader(AUTHORIZATION_HEADER, BEARER + jwt);
        System.out.println("Token Value" + jwt);

        //}
        System.out.println("JWTAuthenticationMechanism - Token Created");

        return context.notifyContainerAboutLogin(result.getCallerPrincipal(), result.getCallerGroups());
    }

    private String extractToken(HttpMessageContext context) {
        String authorizationHeader = context.getRequest().getHeader(AUTHORIZATION_HEADER);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER)) {
            String token = authorizationHeader.substring(BEARER.length(), authorizationHeader.length());
            KeepRecord.setToken(token);
            //  System.out.println("JWTAuthenticationMechanism - Extract Tokens");
            return token;
        }
        return null;
    }

}
