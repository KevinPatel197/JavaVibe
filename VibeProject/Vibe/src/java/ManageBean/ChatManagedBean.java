/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Chat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LENOVO
 */
@Named(value = "chatManagedBean")
@ApplicationScoped
public class ChatManagedBean {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;

    private VibeClient vibeClient = new VibeClient();
    
    private String chatId;
    private String senderId;
    private String ReceiverId;
    private String message;
    private String isDelevered;
    private String isRead;
    private String isDeleted;

    @PostConstruct
    public void init() {
        this.chatId = "0";
        this.isDeleted = "false";
        this.isDelevered = "true";
        this.isRead = "true";
    }
    
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(String ReceiverId) {
        this.ReceiverId = ReceiverId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIsDelevered() {
        return isDelevered;
    }

    public void setIsDelevered(String isDelevered) {
        this.isDelevered = isDelevered;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
    
    
    
    
    
    /**
     * Creates a new instance of ChatManagedBean
     */
    public ChatManagedBean() {
    }
    
    
    
    
    public String sendChat(String receiverid) {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        vibeClient.chatInsert(chatId, message, isDelevered, isRead, isDeleted, userSession.getAttribute("UuserId").toString(), receiverid);
        
        this.message = "";
        
        return "/web/chat.xhtml?faces-redirect=true";
        
    }
    
    
    public List<Chat> chatList(String receiverid) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.chatFindBySenderId(Response.class, userSession.getAttribute("UuserId").toString(), receiverid);
        
        ArrayList<Chat> friendsArrayList = new ArrayList<>();
        GenericType<List<Chat>> friendsGenericType = new GenericType<List<Chat>>(){};
        
        friendsArrayList = (ArrayList<Chat>)response.readEntity(friendsGenericType);
        
        return friendsArrayList;
    }
    
    
    public List<Chat> listByReceiver(String receiverid) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.chatFindByReceiverId(Response.class, receiverid, userSession.getAttribute("UuserId").toString());
        
        ArrayList<Chat> friendsArrayList = new ArrayList<>();
        GenericType<List<Chat>> friendsGenericType = new GenericType<List<Chat>>(){};
        
        friendsArrayList = (ArrayList<Chat>)response.readEntity(friendsGenericType);
        
        return friendsArrayList;
    }
    
}
