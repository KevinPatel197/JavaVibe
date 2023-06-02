/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.ActivityFeed;
import entity.FriendList;
import entity.FriendRequest;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "friendManagedBean")
@ApplicationScoped
public class FriendManagedBean {

    @EJB
    private VibeSessionBeanLocal vibe;
    
    private FriendRequest friendRequest = new FriendRequest();
    private FriendList friendList = new FriendList();
    private VibeClient vibeClient = new VibeClient();
    
    //friend_request Model
    
    private String fr_Id;
    private String senderId;
    private String receiverId;
    private String status;
    private String requestDate;
    
    
    //friend_request Model ends
    
    //friend_list model
    
    private String fl_Id;
    private String userId;
    private String friendId;
    private String friendStatus;
    
    //friend_list model ends
    
    //private var

            
    //private var
    
    //Declare Lists
    
    List<FriendRequest> friendRequestList;
    List<FriendList> friendsList;
    List<ActivityFeed> activityFeedList;
    
    
    //Declare Lists ends
    
    //Session Declaration
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
    HttpSession userSession = request.getSession();
    
    //Session Declaration ends
    
    /**
     * Creates a new instance of FriendManagedBean
     */
    public FriendManagedBean() {
        
    }
    
    
    @PostConstruct
    public void init() {
        
    }
    
    //Getters and setters ends

    public VibeSessionBeanLocal getVibe() {
        return vibe;
    }

    public void setVibe(VibeSessionBeanLocal vibe) {
        this.vibe = vibe;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
    }

    public FriendList getFriendList() {
        return friendList;
    }

    public void setFriendList(FriendList friendList) {
        this.friendList = friendList;
    }

    public VibeClient getVibeClient() {
        return vibeClient;
    }

    public void setVibeClient(VibeClient vibeClient) {
        this.vibeClient = vibeClient;
    }

    public String getFr_Id() {
        return fr_Id;
    }

    public void setFr_Id(String fr_Id) {
        this.fr_Id = fr_Id;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getFl_Id() {
        return fl_Id;
    }

    public void setFl_Id(String fl_Id) {
        this.fl_Id = fl_Id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public String getFriendStatus() {
        return friendStatus;
    }

    public void setFriendStatus(String friendStatus) {
        this.friendStatus = friendStatus;
    }

    public List<FriendRequest> getFriendRequestList() {
        return friendRequestList;
    }

    public void setFriendRequestList(List<FriendRequest> friendRequestList) {
        this.friendRequestList = friendRequestList;
    }

    public List<FriendList> getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(List<FriendList> friendsList) {
        this.friendsList = friendsList;
    }

    public List<ActivityFeed> getActivityFeedList() {
        return activityFeedList;
    }

    public void setActivityFeedList(List<ActivityFeed> activityFeedList) {
        this.activityFeedList = activityFeedList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpSession getUserSession() {
        return userSession;
    }

    public void setUserSession(HttpSession userSession) {
        this.userSession = userSession;
    }
    
    
    
    //Getters and setters ends

    
    //private methods
    
    private void Activity(String senderMsg, String receiverMsg, String targerURL, String senderId, String receiverId) {
        
        vibeClient.activity_feed_Insert("0", "desc", senderMsg, receiverMsg, targerURL, "Friend", "false", "false", senderId, receiverId, "0");
        
    }
    
    //private methods ends
    
    
    //public methods
    
    public List<FriendRequest> showAllFriendRequest() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.friend_request_FindByReceiverId(Response.class, userSessions.getAttribute("UuserId").toString(), "requested");
        ArrayList<FriendRequest> friendRequestArrayList = new ArrayList<>();
        GenericType<List<FriendRequest>> friendRequestGenericType = new GenericType<List<FriendRequest>>(){};
        
        friendRequestArrayList = (ArrayList<FriendRequest>)response.readEntity(friendRequestGenericType);
        
        return friendRequestArrayList;
    }
    
    public List<FriendList> showAllFriends() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.friend_list_ShowAllByUserId(Response.class, userSessions.getAttribute("UuserId").toString());
        
        ArrayList<FriendList> friendsArrayList = new ArrayList<>();
        GenericType<List<FriendList>> friendsGenericType = new GenericType<List<FriendList>>(){};
        
        friendsArrayList = (ArrayList<FriendList>)response.readEntity(friendsGenericType);
        
        return friendsArrayList;
    }
    
    public List<FriendList> showAllFriends(String Id) {
        
        Response response = vibeClient.friend_list_ShowAllByUserId(Response.class, Id);
        
        ArrayList<FriendList> friendsArrayList = new ArrayList<>();
        GenericType<List<FriendList>> friendsGenericType = new GenericType<List<FriendList>>(){};
        
        friendsArrayList = (ArrayList<FriendList>)response.readEntity(friendsGenericType);
        
        return friendsArrayList;
    }
    
    public List<User> peopleYouMayKnow() {
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.peopleYouMayKnow(Response.class, userSessions.getAttribute("UuserId").toString(), userSessions.getAttribute("UuserId").toString());
        
        ArrayList<User> userArrayList = new ArrayList<>();
        GenericType<List<User>> userGenericType = new GenericType<List<User>>(){};
        
        userArrayList = (ArrayList<User>)response.readEntity(userGenericType);
        
        return userArrayList;
    }
    
    public void sendRequest(String receiverid, String receiverName) {
        
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession(); 

            vibeClient.friend_request_Insert("0","requested",userSessions.getAttribute("UuserId").toString(),receiverid);
            
            String senderMsg = "Friend request sent to " + receiverName + ".";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " has sent a friend request.";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), receiverid);
            
            
        } catch (ClientErrorException e) {
            
            System.out.println(e.getMessage());
            
        }
        
        
    }
    
    public void confirmRequest(String senderid, String frId, String receiverName) {
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession(); 
            
            vibeClient.friend_list_Insert("0", "true", userSessions.getAttribute("UuserId").toString(), senderid);
            
            vibeClient.friend_list_Insert("0", "true", senderid, userSessions.getAttribute("UuserId").toString());
            
            vibeClient.friend_request_Update(frId, "accepted", senderid, userSessions.getAttribute("UuserId").toString());
            
            String senderMsg = "Friend request accepted of " + receiverName + ".";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " has accepted your friend request.";
            String targetUrl = "null";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), senderid);
            
            
        } catch (ClientErrorException e) {
            
            System.out.println(e.getMessage());
            
        }
    }
    
    public void deleteRequest(String frId) {
        
        try { 
            
            vibeClient.friend_request_Delete(frId);
            
        } catch (ClientErrorException e) {
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
    public String checkStatus(String senderid) {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.friend_request_CheckStatus(Response.class, senderid, userSessions.getAttribute("UuserId").toString());
        GenericType<List<FriendRequest>> friendsGenericType = new GenericType<List<FriendRequest>>(){};
        
        ArrayList<FriendRequest> frList = (ArrayList<FriendRequest>)response.readEntity(friendsGenericType);
        
        for(FriendRequest fr : frList) {
            return fr.getStatus();
        }
        return null;
    }
    
    public String checkStatus2(String senderid) {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.friend_request_CheckStatus(Response.class, userSessions.getAttribute("UuserId").toString(), senderid);
        GenericType<List<FriendRequest>> friendsGenericType = new GenericType<List<FriendRequest>>(){};
        
        ArrayList<FriendRequest> frList = (ArrayList<FriendRequest>)response.readEntity(friendsGenericType);
        
        for(FriendRequest fr : frList) {
            return fr.getStatus();
        }
        return null;
    }
    

    //public methods ends
    
    
    
    
}
