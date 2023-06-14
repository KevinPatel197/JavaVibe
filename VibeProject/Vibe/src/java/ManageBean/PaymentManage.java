/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "paymentManage")
@ApplicationScoped
public class PaymentManage {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String payid;
    
    @NotNull(message = "Carunumber Required")
    private String cardnumber;
    
    @NotNull(message = "Cardholder Name Required")
    private String cardholder;
    
    @NotNull(message = "Expiry Month Required")
    private String expmonth;
    
    @NotNull(message = "Expiry Year Require")
    private String expyear;
    
    @NotNull(message = "CVV code Required")
    private String cvv;
    
    private List<Payment> paymentList;
    
    public PaymentManage() {
    }

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getPayid() {
        return payid;
    }

    public void setPayid(String payid) {
        this.payid = payid;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getExpmonth() {
        return expmonth;
    }

    public void setExpmonth(String expmonth) {
        this.expmonth = expmonth;
    }

    public String getExpyear() {
        return expyear;
    }

    public void setExpyear(String expyear) {
        this.expyear = expyear;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }
   

    private void clearUpdate() {
        this.cardnumber = null;
        this.cardholder=null;;
        this.expmonth = null;
        this.expyear = null;
        this.cvv = null;
    }
    
    public List<Payment> paymentShowAll() {

        Response response = vibeClient.paymentShowAll(Response.class);
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        GenericType<List<Payment>> paymentGenericType = new GenericType<List<Payment>>() {
        };

        paymentArrayList = (ArrayList<Payment>) response.readEntity(paymentGenericType);
        return paymentArrayList;
    }
    
    public String paymentInsert(){
        vibeClient.paymentInsert(cardnumber, cardholder, expmonth, expyear, cvv);
        clearUpdate();
        return "/web/invoice.xhtml?faces-redirect=true";
    }
    
    public String getpaymentinfo(String id)
    {
        Response response = vibeClient.payFindById(Response.class, id);
        Payment paymentArrayList = new Payment();
        GenericType<Payment> showAllpayment  = new GenericType<Payment>() {
        };
        paymentArrayList = (Payment)response.readEntity(showAllpayment);
        payid = paymentArrayList.getPayid().toString();
        cardnumber = paymentArrayList.getCardnumber();
        cardholder = paymentArrayList.getCardholder();
        expmonth = paymentArrayList.getExpmonth();
        expyear = paymentArrayList.getExpyear();
        cvv = paymentArrayList.getCvv();
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("paymentid : ", payid);
        userSession.setAttribute("CardNumber : ", cardnumber);
        userSession.setAttribute("CardHolder : ", cardholder);
        userSession.setAttribute("expmonth : ", expmonth);
        userSession.setAttribute("expyear : ", expyear);
        userSession.setAttribute("cvv : ", cvv);
        
        return "/web/invoice.xhtml?faces-redirect=true";
    }
    
    public void getLastValue(){
        
        Payment p = new Payment();
        p = vibeSessionBean.getLastValue();
        int pi = p.getPayid();
        String ch = p.getCardholder();
    }
    
}
