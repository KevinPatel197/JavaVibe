/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.GroupMembers;
import entity.Groups;
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
 * @author kevin
 */
@Named(value = "groupManagedBean")
@ApplicationScoped
public class GroupManagedBean {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private VibeClient vibeClient = new VibeClient();

    private String groupId;
    private String adminId;
    private String groupName;
    private String membersCount;
    private String description;
    private String createDate;
    private String isDeleted;

    private String gm_Id;
    private String memberId;
    private String isMember;
    private String becameMember;
    
    private List<Groups> groupList;
    private List<GroupMembers> groupMemberList;
    
    private String rName;

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
    
    

    /**
     * Creates a new instance of GroupManagedBean
     */
    public GroupManagedBean() {
    }

    @PostConstruct
    public void inti() {
        this.isDeleted = "false";
        this.groupId = "0";
        this.membersCount = "0";
        this.gm_Id = "0";
        this.isMember = "true";
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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(String membersCount) {
        this.membersCount = membersCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public List<Groups> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Groups> groupList) {
        this.groupList = groupList;
    }

    public String getGm_Id() {
        return gm_Id;
    }

    public void setGm_Id(String gm_Id) {
        this.gm_Id = gm_Id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getIsMember() {
        return isMember;
    }

    public void setIsMember(String isMember) {
        this.isMember = isMember;
    }

    public String getBecameMember() {
        return becameMember;
    }

    public void setBecameMember(String becameMember) {
        this.becameMember = becameMember;
    }

    public List<GroupMembers> getGroupMemberList() {
        return groupMemberList;
    }

    public void setGroupMemberList(List<GroupMembers> groupMemberList) {
        this.groupMemberList = groupMemberList;
    }

    //private methods
    
    private void Activity(String senderMsg, String receiverMsg, String targerURL, String senderId, String receiverId) {
        
        vibeClient.activity_feed_Insert("0", "desc", senderMsg, receiverMsg, targerURL, "Group", "false", "false", senderId, receiverId, "0");
        
    }
    
    
    public String createGroup() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.groupInsert(Response.class,groupId, groupName, description, membersCount, isDeleted, userSession.getAttribute("UuserId").toString());
        
        GenericType<Groups> groupid = new GenericType<Groups>(){};
        
        Groups g = response.readEntity(groupid);
        
        vibeClient.group_member_Insert(gm_Id, isMember, becameMember, g.getGroupid().toString(), userSession.getAttribute("UuserId").toString());
        
        this.groupName = "";
        this.description = "";
        
        return "/web/groups.xhtml?faces-redirect=true";
    }

    public List<Groups> showAllUserGroup() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.groupShowAllByUser(Response.class, userSession.getAttribute("UuserId").toString());
        
        ArrayList<Groups> groupArrayList = new ArrayList<>();
        GenericType<List<Groups>> groupGenericType = new GenericType<List<Groups>>(){};
        
        groupArrayList = (ArrayList<Groups>)response.readEntity(groupGenericType);
        
        return groupArrayList;
        
    }
    
    public List<GroupMembers> groupMembersFindByGroupid(String groupid) {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.group_member_FindByGroupid(Response.class, groupid);
        
        ArrayList<GroupMembers> groupArrayList = new ArrayList<>();
        GenericType<List<GroupMembers>> groupGenericType = new GenericType<List<GroupMembers>>(){};
        
        groupArrayList = (ArrayList<GroupMembers>)response.readEntity(groupGenericType);
        
        return groupArrayList;
        
    }
    
    public List<GroupMembers> checkMember(String groupid) {
     
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.group_member_checkGroupMember(Response.class, userSession.getAttribute("UuserId").toString(), groupid);
        
        ArrayList<GroupMembers> groupArrayList = new ArrayList<>();
        GenericType<List<GroupMembers>> groupGenericType = new GenericType<List<GroupMembers>>(){};
        
        groupArrayList = (ArrayList<GroupMembers>)response.readEntity(groupGenericType);
        
        return groupArrayList;
        
    }
    
    public List<Groups> showAllGroup() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.groupShowAll(Response.class, userSession.getAttribute("UuserId").toString());
        
        ArrayList<Groups> groupArrayList = new ArrayList<>();
        GenericType<List<Groups>> groupGenericType = new GenericType<List<Groups>>(){};
        
        groupArrayList = (ArrayList<Groups>)response.readEntity(groupGenericType);
        
        return groupArrayList;
        
    }
    
    public String joinGroup(String groupid) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        vibeClient.group_member_Insert(gm_Id, isMember, becameMember, groupid, userSession.getAttribute("UuserId").toString());
        
        Response response = vibeClient.groupFindById(Response.class, groupid);
        GenericType<Groups> gpSearch = new GenericType<Groups>(){};
        Groups g = response.readEntity(gpSearch);
        adminId = g.getAdminid().getUserid().toString();
        rName = g.getAdminid().getFirstname() + g.getAdminid().getLastname();
        
            String senderMsg = "You joined " + rName + "'s Group.";
            String receiverMsg = userSession.getAttribute("UfullName").toString() + 
                                " joined your Group.";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSession.getAttribute("UuserId").toString(), adminId);
        
        return "/web/groups.xhtml?faces-redirect=true";
    }
    
    public List<GroupMembers> joinedGroup() {
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        
        Response response = vibeClient.group_member_findGroupsByUserId(Response.class, userSession.getAttribute("UuserId").toString());
        
        ArrayList<GroupMembers> groupArrayList = new ArrayList<>();
        GenericType<List<GroupMembers>> groupGenericType = new GenericType<List<GroupMembers>>(){};
        
        groupArrayList = (ArrayList<GroupMembers>)response.readEntity(groupGenericType);
        
        return groupArrayList;
        
    }
    
    public void deleteGroup(String id) {
        
        vibeClient.groupDelete(id);
        
    }
    
    public String groupFindById(String id) {
        Response response = vibeClient.groupFindById(Response.class, id);
        GenericType<Groups> empSearch = new GenericType<Groups>(){};
        Groups g = response.readEntity(empSearch);
        
        groupId = g.getGroupid().toString();
        groupName = g.getGroupname();
        description = g.getDescription();
        adminId = g.getAdminid().getUserid().toString();
        createDate = g.getCreateDate().toString();
        
        return "/web/group-detail.xhtml?faces-redirect=true";
    }
    
    public String groupShowInfo(String id) {
        
        System.out.println("Ingroupshowinfo");
        Response response = vibeClient.groupFindById(Response.class, id);
        GenericType<Groups> empSearch = new GenericType<Groups>(){};
        Groups g = response.readEntity(empSearch);
        
        groupId = g.getGroupid().toString();
        groupName = g.getGroupname();
        description = g.getDescription();
        adminId = g.getAdminid().getFirstname() + " " + g.getAdminid().getLastname();
        createDate = g.getCreateDate().toString();
        
        return "/admin/groupinfo.xhtml?faces-redirect=true";
    }
}
