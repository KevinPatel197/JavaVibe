/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Comments;
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
 * @author pooja
 */
@Named(value = "commentManagedBean")
@ApplicationScoped
public class CommentManagedBean {

    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String commentid;
    private String postid;
    private String senderid;
    private String receiverid;
    private String comment;
    private String commentdate;
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

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(String commentdate) {
        this.commentdate = commentdate;
    }

    public String getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(String isRemoved) {
        this.isRemoved = isRemoved;
    }
    
    
    /**
     * Creates a new instance of CommentManagedBean
     */
    public CommentManagedBean() {
    }
    
    //private Methods
    private void Activity(String senderMsg, String receiverMsg, String targerURL, String senderId, String receiverId) {
        
        vibeClient.activity_feed_Insert("0", "desc", senderMsg, receiverMsg, targerURL, "Comment", "false", "false", senderId, receiverId, "0");
        
    }
    
    private void clearall()
    {
        setComment("");
    }
    
    public String sendcomment(String postid){
        
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
            
            vibeClient.commentsInsert("0", comment, "false", postid, userSessions.getAttribute("UuserId").toString(), receiverid);
            clearall();
            
            String senderMsg = "You Commented on " + rName + "'s Post.";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " has commented on your Post";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), receiverid);
            return "/web/home.xhtml?faces-redirect=true";
    }
    
    public List<Comments> showcomments(String postid)
    {
            Response response = vibeClient.commentsFindByPostId(Response.class, postid);
            ArrayList<Comments> commentArrayList = new ArrayList<>();
            GenericType<List<Comments>> showAllcomments  = new GenericType<List<Comments>>() {
            };
            commentArrayList = (ArrayList<Comments>)response.readEntity(showAllcomments);
            System.out.println("now " + commentArrayList);
            return commentArrayList;
    }
}
