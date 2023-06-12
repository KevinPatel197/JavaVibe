/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.User;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "loginManagedBean")
@ApplicationScoped
public class LoginManagedBean {

    // Session Bean
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    
    //VibeClient
    private VibeClient vibeClient = new VibeClient();
    
    
    //Declare Variables
    
    @NotNull(message = "Email Required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    private String email;
    
    @NotNull(message = "Password Required")
    private String password;
    
    //Declare Variables ends
    
    
    //Custom Variables
    
    private boolean remember;
    private boolean deniedUser,invalidpass;

    //Custom Variables ends
    
    
    /**
     * Creates a new instance of LoginManagedBean
     */
    public LoginManagedBean() {
        
    }
    
    //Post Construct
    
    @PostConstruct
    public void init() {
        this.deniedUser = false;
        this.invalidpass = false;
    }

    //Post Construct ends
    
    //Getters and setters
    
    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public VibeClient getVibeClient() {
        return vibeClient;
    }

    public void setVibeClient(VibeClient vibeClient) {
        this.vibeClient = vibeClient;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public boolean isDeniedUser() {
        return deniedUser;
    }

    public void setDeniedUser(boolean deniedUser) {
        this.deniedUser = deniedUser;
    }

    public boolean isInvalidpass() {
        return invalidpass;
    }

    public void setInvalidpass(boolean invalidpass) {
        this.invalidpass = invalidpass;
    }
    
    //Getters and setters
    
    //Private Methods
    
    private void setFalse() {
        this.deniedUser = false;
        this.invalidpass = false;
    }
    
    //Private Methods ends
    
    
    //ManageBeans Methods
    
    public String vibeLogin(){
        
        try {
            
            Response response = vibeClient.vibeLogin(Response.class, email, password);
            
            System.out.println(response.hasEntity());
            System.out.println(response.toString());
            
            if(!response.hasEntity()) {
                invalidpass = true;
                return null;
            }
            
            GenericType<User> vibeLogin = new GenericType<User>(){};
            
            User users = response.readEntity(vibeLogin);
            
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            
            if(users.getAccess() == false || users.getIsactive() == false) {
                deniedUser = true;
                return null;
            }
            
            if(users.getIsadmin()) {
                
                HttpSession adminSession = request.getSession();
                
                adminSession.setAttribute("AuserId", users.getUserid());
                adminSession.setAttribute("AfullName", users.getFirstname() + " " + users.getLastname());
                adminSession.setAttribute("Aonline", true);
                adminSession.setAttribute("Atype", users.getIsadmin());
                adminSession.setAttribute("AImage", users.getProfilephoto());
                
                System.out.println(adminSession.getAttribute("AuserId").toString() + " " + 
                        adminSession.getAttribute("AfullName") + " " + 
                        adminSession.getAttribute("Aonline") + " " +
                        adminSession.getAttribute("Atype"));
                
                setFalse();
                
                if(adminSession.getAttribute("AuserId") !=  null) {
                    return "/admin/dashboard.xhtml?faces-redirect=true";
                }
                
            }
            
            if(!users.getIsadmin()) {
                
                HttpSession userSession = request.getSession();
                
                userSession.setAttribute("UuserId", users.getUserid());
                userSession.setAttribute("UfullName", users.getFirstname() + " " + users.getLastname());
                userSession.setAttribute("Uonline", true);
                userSession.setAttribute("Utype", users.getIsadmin());
                userSession.setAttribute("UImage", users.getProfilephoto());
                
                System.out.println(userSession.getAttribute("UuserId").toString() + " " + 
                        userSession.getAttribute("UfullName") + " " + 
                        userSession.getAttribute("Uonline") + " " + 
                        userSession.getAttribute("Utype"));
                
                setFalse();
                return "/web/home.xhtml?faces-redirect=true";
                
            }          
                      
        } catch (ClientErrorException e) {
            
            System.out.println("Error:- " + e.getMessage());
            
            setFalse();
            return "/login.xhtml?faces-redirect=true";
            
        }
        
        System.out.println("Outside Exception");
        setFalse();
        return "/login.xhtml?faces-redirect=true";
    }
    
    public void vibeLogout() {
        
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        try {
            setFalse();
            FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");
            
        } catch (IOException e) {
            
            System.out.println(e.getMessage());
            
        }
    }
    
    //ManageBeans Methods ends
}
