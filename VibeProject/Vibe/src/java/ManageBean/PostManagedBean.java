/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Post;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LENOVO
 */
@Named(value = "postManagedBean")
@ApplicationScoped
public class PostManagedBean {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;

    private final VibeClient vibeClient = new VibeClient();

    private String postId;
    private String userId;
    private String groupId;
    private String post;
    private String caption;
    private String uploadDate;
    private String isDeleted;
    private String likeCount;
    private String postType;

    private List<Post> postList;

    Part file;

    /**
     * Creates a new instance of PostManagedBean
     */
    public PostManagedBean() {
    }

    @PostConstruct
    public void init() {
        this.likeCount = "0";
        this.isDeleted = "false";
        this.post = "0";
    }

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    
    
    //User Post

    public void postinsert() {
        System.out.println("Done");
    }

    public void postInsert() {

        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();

        try {

            if (file == null) {

                vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "Text", userSessions.getAttribute("UuserId").toString(), "0");

            } else {
                if (file.getSubmittedFileName().contains(".jpg") || file.getSubmittedFileName().contains(".jpeg") || file.getSubmittedFileName().contains(".png")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Post\\Images\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "IMG_" + temp + ".jpg";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "Image", userSessions.getAttribute("UuserId").toString(), "0");

                }

                if (file.getSubmittedFileName().contains(".mp4") || file.getSubmittedFileName().contains(".mov") || file.getSubmittedFileName().contains(".mkv") || file.getSubmittedFileName().contains(".avi")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Post\\Videos\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "VID_" + temp + ".mp4";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "Video", userSessions.getAttribute("UuserId").toString(), "0");

                }

                if (file.getSubmittedFileName().contains(".mp3") || file.getSubmittedFileName().contains(".wav") || file.getSubmittedFileName().contains(".m4a")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Post\\Audio\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "AUD_" + temp + ".mp3";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "Audio", userSessions.getAttribute("UuserId").toString(), "0");

                }
            }

            this.caption = "";

        } catch (ClientErrorException | IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Post> feedView() {

        this.caption = "";

        Response response = vibeClient.postShowAll(Response.class);

        ArrayList<Post> postArrayList = new ArrayList<>();
        GenericType<List<Post>> postGenericType = new GenericType<List<Post>>() {
        };

        postArrayList = (ArrayList<Post>) response.readEntity(postGenericType);

        return postArrayList;
    }

    public List<Post> feedByUserId() {

        this.caption = "";

        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();

        Response response = vibeClient.postShowAllByUserId(Response.class, userSessions.getAttribute("UuserId").toString());

        ArrayList<Post> postArrayList = new ArrayList<>();
        GenericType<List<Post>> postGenericType = new GenericType<List<Post>>() {
        };

        postArrayList = (ArrayList<Post>) response.readEntity(postGenericType);

        return postArrayList;
    }

    public List<Post> feedByProfileId(String Id) {

        this.caption = "";

        Response response = vibeClient.postShowAllByUserId(Response.class, Id);

        ArrayList<Post> postArrayList = new ArrayList<>();
        GenericType<List<Post>> postGenericType = new GenericType<List<Post>>() {
        };

        postArrayList = (ArrayList<Post>) response.readEntity(postGenericType);

        return postArrayList;
    }

    //Group post
    
    public void postGroupInsert(String id) {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();

        try {

            if (file == null) {

                vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "GText", userSessions.getAttribute("UuserId").toString(), id);

            } else {
                if (file.getSubmittedFileName().contains(".jpg") || file.getSubmittedFileName().contains(".jpeg") || file.getSubmittedFileName().contains(".png")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Group\\Images\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "IMG_" + temp + ".jpg";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "GImage", userSessions.getAttribute("UuserId").toString(), id);

                }

                if (file.getSubmittedFileName().contains(".mp4") || file.getSubmittedFileName().contains(".mov") || file.getSubmittedFileName().contains(".mkv") || file.getSubmittedFileName().contains(".avi")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Group\\Videos\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "VID_" + temp + ".mp4";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "GVideo", userSessions.getAttribute("UuserId").toString(), id);

                }

                if (file.getSubmittedFileName().contains(".mp3") || file.getSubmittedFileName().contains(".wav") || file.getSubmittedFileName().contains(".m4a")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\Group\\Audio\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    post = "AUD_" + temp + ".mp3";
                    Files.copy(input, new File(fullPath, post).toPath());

                    vibeClient.postInsert("0", post, caption, isDeleted, likeCount, "GAudio", userSessions.getAttribute("UuserId").toString(), id);

                }
            }

            this.caption = "";

        } catch (ClientErrorException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public List<Post> feedByGroupId(String Id) {

        this.caption = "";

        Response response = vibeClient.postShowAllByGroupId(Response.class, Id);

        ArrayList<Post> postArrayList = new ArrayList<>();
        GenericType<List<Post>> postGenericType = new GenericType<List<Post>>() {
        };

        postArrayList = (ArrayList<Post>) response.readEntity(postGenericType);

        return postArrayList;
    }

}
