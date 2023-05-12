/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Comments;
import entity.FriendList;
import entity.Likes;
import entity.Post;
import java.util.ArrayList;
import java.util.List;
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
 * @author kevin
 */
@Named(value = "likeManagedBean")
@ApplicationScoped
public class LikeManagedBean {

    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String likeid;
    private String postid;
    private String senderid;
    private String receiverid;
    private String likedate;
    private String isRemoved;
    
    private String rName;

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
    
    

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getLikeid() {
        return likeid;
    }

    public void setLikeid(String likeid) {
        this.likeid = likeid;
    }

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getLikedate() {
        return likedate;
    }

    public void setLikedate(String likedate) {
        this.likedate = likedate;
    }

    public String getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(String isRemoved) {
        this.isRemoved = isRemoved;
    }
    
    
    /**
     * Creates a new instance of LikeManagedBean
     */
    public LikeManagedBean() {
    }
    
    //private methods
    
    private void Activity(String senderMsg, String receiverMsg, String targerURL, String senderId, String receiverId) {
        
        vibeClient.activity_feed_Insert("0", "desc", senderMsg, receiverMsg, targerURL, "Like", "false", "false", senderId, receiverId, "0");
        
    }
    
    public String likepost(String postid){
        
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            Response response = vibeClient.postFindById(Response.class, postid);
            Post postArrayList = new Post();
            GenericType<Post> showAllpostinfo  = new GenericType<Post>() {
            };
            postArrayList = (Post) response.readEntity(showAllpostinfo);
            receiverid = postArrayList.getUserid().getUserid().toString();
            rName = postArrayList.getUserid().getFirstname() + postArrayList.getUserid().getLastname();
            
            vibeClient.likeInsert("0", "false", postid, userSessions.getAttribute("UuserId").toString(), receiverid);
            
            String senderMsg = "You liked " + rName + "'s Post.";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " has liked your Post.";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), receiverid);
            
            return "/web/home.xhtml?faces-redirect=true";
    }
    
    public List<Likes> isliked(String postid)
    {
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            Response response = vibeClient.isLiked(Response.class, postid, userSessions.getAttribute("UuserId").toString());
            ArrayList<Likes> islikeArrayList = new ArrayList<>();
            GenericType<List<Likes>> showAlllikeinfo  = new GenericType<List<Likes>>() {
            };
            islikeArrayList = (ArrayList<Likes>)response.readEntity(showAlllikeinfo);
            return islikeArrayList;
    }
    
    public List<Likes> likeCount(String postid)
    {
            Response response = vibeClient.likeCount(Response.class, postid);
            ArrayList<Likes> likecountArrayList = new ArrayList<>();
            GenericType<List<Likes>> showAllcount  = new GenericType<List<Likes>>() {
            };
            likecountArrayList = (ArrayList<Likes>)response.readEntity(showAllcount);
            return likecountArrayList;
    }
    
}
