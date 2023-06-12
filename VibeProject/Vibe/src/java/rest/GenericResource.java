/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.VibeSessionBeanLocal;
import entity.ActivityFeed;
import entity.Ads;
import entity.AdsUser;
import entity.Chat;
import entity.City;
import entity.Comments;
import entity.Country;
import entity.EventUsercount;
import entity.Events;
import entity.FriendList;
import entity.FriendRequest;
import entity.GroupMembers;
import entity.Groups;
import entity.Likes;
import entity.Post;
import entity.Product;
import entity.State;
import entity.User;
import entity.UserContactInfo;
import entity.UserEducation;
import entity.UserSkills;
import entity.UserWork;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author kevin
 */
@Path("generic")
public class GenericResource {

    @EJB VibeSessionBeanLocal vibe; 
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    
    @Path("chatinsert/{chatId}/{message}/{isDelevered}/{isRead}/{isDeleted}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String chatInsert(@PathParam("chatId")int chatId, @PathParam("message")String message, @PathParam("isDelevered")boolean isDelevered, @PathParam("isRead")boolean isRead, @PathParam("isDeleted")boolean isDeleted, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.chatInsert(chatId, message, isDelevered, isRead, isDeleted, senderId, receiverId);
    }
    
    @Path("chatfindbyreceiver/{receiver}/{sender}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chat> chatFindByReceiverId(@PathParam("receiver")int receiver, @PathParam("sender")int sender) {
        return vibe.chatFindByReceiverId(receiver, sender);
    }
    
    @Path("chatfindbysender/{sender}/{receiver}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Chat> chatFindBySenderId(@PathParam("sender")int sender, @PathParam("receiver")int receiver) {
        return vibe.chatFindBySenderId(sender, receiver);
    }
    
    
    //country
    
    @Path("countryinsert/{countryId}/{sortName}/{countryName}/{phoneCode}/{isActive}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String countryInsert(@PathParam("countryId")int countryId, @PathParam("sortName")String sortName, @PathParam("countryName")String countryName, @PathParam("phoneCode")int phoneCode, @PathParam("isActive")boolean isActive) {
        return vibe.countryInsert(countryId, sortName, countryName, phoneCode, isActive);
    }
    
    @Path("countryupdate/{countryId}/{sortName}/{countryName}/{phoneCode}/{isActive}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String countryUpdate(@PathParam("countryId")int countryId, @PathParam("sortName")String sortName, @PathParam("countryName")String countryName, @PathParam("phoneCode")int phoneCode, @PathParam("isActive")boolean isActive) {
        return vibe.countryUpdate(countryId, sortName, countryName, phoneCode, isActive);
    }
    
    @Path("countrydelete/{countryId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String countryDelete(@PathParam("countryId")int countryId) {
        return vibe.countryDelete(countryId);
    }
    
    @Path("countryfindbyid/{countryId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Country countryFindById(@PathParam("countryId")int countryId) {
        return vibe.countryFindById(countryId);
    }
    
    @Path("countryfindbyname/{countryName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Country countryFindByName(@PathParam("countryName")String countryName) {
        return vibe.countryFindByName(countryName);
    }
    
    @Path("countryshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> countryShowAll() {
        return vibe.countryShowAll();
    }
    
    @Path("countryshowactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Country> countryShowActive() {
        return vibe.countryShowActive();
    }
    
    
    //State
    
    @Path("stateinsert/{stateId}/{stateName}/{isActive}/{countryId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String stateInsert(@PathParam("stateId")int stateId, @PathParam("stateName")String stateName, @PathParam("isActive")boolean isActive, @PathParam("countryId")int countryId) {
        return vibe.cityInsert(countryId, stateName, isActive, stateId);
    }
    
    @Path("stateupdate/{stateId}/{stateName}/{isActive}/{countryId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String stateUpdate(@PathParam("stateId")int stateId, @PathParam("stateName")String stateName, @PathParam("isActive")boolean isActive, @PathParam("countryId")int countryId) {
        return vibe.stateUpdate(stateId, stateName, isActive, countryId);
    }
    
    @Path("statedelete/{stateId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String stateDelete(@PathParam("stateId")int stateId) {
        return vibe.stateDelete(stateId);
    }
    
    @Path("statefindbyid/{stateId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public State stateFindById(@PathParam("stateId")int stateId) {
        return vibe.stateFindById(stateId);
    }
    
    @Path("statefindbyname/{stateName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public State stateFindByName(@PathParam("stateName")String stateName) {
        return vibe.stateFindByName(stateName);
    }
    
    @Path("stateshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<State> stateShowAll() {
        return vibe.stateShowAll();
    }
    
    @Path("stateshowactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<State> stateShowActive() {
        return vibe.stateShowActive();
    }
    
    
    //City
    
    @Path("cityinsert/{cityId}/{cityName}/{isActive}/{stateId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cityInsert(@PathParam("cityId")int cityId, @PathParam("cityName")String cityName, @PathParam("isActive")boolean isActive, @PathParam("stateId")int stateId) {
        return vibe.cityInsert(cityId, cityName, isActive, stateId);
    }
    
    @Path("cityupdate/{cityId}/{cityName}/{isActive}/{stateId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cityUpdate(@PathParam("cityId")int cityId, @PathParam("cityName")String cityName, @PathParam("isActive")boolean isActive, @PathParam("stateId")int stateId) {
        return vibe.cityUpdate(cityId, cityName, isActive, stateId);
    }
    
    @Path("citydelete/{cityId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String cityDelete(@PathParam("cityId")int cityId) {
        return vibe.cityDelete(cityId);
    }
    
    @Path("cityfindbyid/{cityId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public City cityFindById(@PathParam("cityId")int cityId) {
        return vibe.cityFindById(cityId);
    }
    
    @Path("cityfindbyname/{cityName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public City cityFindByName(@PathParam("cityName")String cityName) {
        return vibe.cityFindByName(cityName);
    }
    
    @Path("cityshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> cityShowAll() {
        return vibe.cityShowAll();
    }
    
    @Path("cityshowactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> cityShowActive() {
        return vibe.cityShowActive();
    }
    
    
    //User
    
    @Path("userregister/{userId}/{firstName}/{lastName}/{dob}/{email}/{password}/{isActive}/{isAdmin}/{access}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String userRegister(@PathParam("userId")int userId, @PathParam("firstName")String firstName, @PathParam("lastName")String lastName, @PathParam("dob")String dob, @PathParam("email")String email, @PathParam("password")String password, @PathParam("isActive")boolean isActive , @PathParam("isAdmin")boolean isAdmin, @PathParam("access")boolean access) {
        return vibe.userRegister(userId, firstName, lastName, dob, email, password, isActive, isAdmin, access);
    }
    
    @Path("userupdate/{userId}/{firstName}/{middleName}/{lastName}/{gender}/{dob}/{email}/{username}/{password}/{mobile}/{profilePhoto}/{coverPhoto}/{countryId}/{stateId}/{cityId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String userUpdate(@PathParam("userId")int userId, @PathParam("firstName")String firstName, @PathParam("middleName")String middleName, @PathParam("lastName")String lastName, @PathParam("gender")String gender, @PathParam("dob")String dob, @PathParam("email")String email, @PathParam("username")String username, @PathParam("password")String password, @PathParam("mobile")long mobile, @PathParam("profilePhoto")String profilePhoto, @PathParam("coverPhoto")String coverPhoto, @PathParam("countryId")int countryId, @PathParam("stateId")int stateId, @PathParam("cityId")int cityId) {
        
        
//        String directoryPath = System.getProperty("user.dir");
//        String profilePath = directoryPath + "/Posts/" + userId + "/Profile/" + new Date() + profilePhoto;
//        String coverPath = directoryPath + "/Posts/" + userId + "/Cover/" + new Date() + profilePhoto;
        
        return vibe.userUpdate(userId, firstName, middleName, lastName, gender, dob, email, username, password, mobile, profilePhoto, coverPhoto, countryId, stateId, cityId);
    }
    
    @Path("userdelete/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String userDelete(@PathParam("userId")int userId) {
        return vibe.userDelete(userId);
    }
    
    @Path("userfindbyid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> userFindById(@PathParam("userId")int userId) {
        return vibe.userFindById(userId);
    }
    
    @Path("userfindbyname/{userName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> userFindByName(@PathParam("userName")String userName) {
        return vibe.userFindByName(userName);
    }
    
    @Path("usershowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> userShowAll() {
        return vibe.userShowAll();
    }
    
    @Path("adminshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> adminShowAll() {
        return vibe.adminShowAll();
    }
    
    @Path("peopleyoumayknow/{senderId}/{Id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> peopleYouMayKnow(@PathParam("senderId")int senderId, @PathParam("Id")int Id) {
        return vibe.peopleYouMayKnow(senderId, Id);
    }
    
    @Path("finduserbyfname/{firstname}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUserByFname(@PathParam("firstname")String firstName){
        return vibe.findUserByFname(firstName);
    }
    
    @Path("finduserbylname/{lastname}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUserByLname(@PathParam("lastname")String lastName){
        return vibe.findUserByLname(lastName);
    }
    
    //User Contact Info
    
    @Path("user_contact_info_insert/{uci_id}/{website}/{language}/{intrested_in}/{fb_link}/{insta_link}/{bio}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_contact_info_Insert(@PathParam("uci_id")int uci_id, @PathParam("website")String website, @PathParam("language")String language, @PathParam("intrested_in")String intrested_in, @PathParam("fb_link")String fb_link, @PathParam("insta_link")String insta_link, @PathParam("bio")String bio, @PathParam("userid")int userid) {
        return vibe.user_contact_info_Insert(uci_id, website, language, intrested_in, fb_link, insta_link, bio, userid);
    }
    
    @Path("user_contact_info_update/{uci_id}/{website}/{language}/{intrested_in}/{fb_link}/{insta_link}/{bio}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_contact_info_Update(@PathParam("uci_id")int uci_id, @PathParam("website")String website, @PathParam("language")String language, @PathParam("intrested_in")String intrested_in, @PathParam("fb_link")String fb_link, @PathParam("insta_link")String insta_link, @PathParam("bio")String bio, @PathParam("userid")int userid) {
        return vibe.user_contact_info_Update(uci_id, website, language, intrested_in, fb_link, insta_link, bio, userid);
    }
    
    @Path("user_contact_info_delete/{uciid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_contact_info_Delete(@PathParam("uciid")int uciid) {
        return vibe.user_contact_info_Delete(uciid);
    }
    
    @Path("user_contact_info_findbyid/{uciid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserContactInfo user_contact_info_FindById(@PathParam("uciid")int uciid) {
        return vibe.user_contact_info_FindById(uciid);
    }
    
    @Path("user_contact_info_findbyuserid/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserContactInfo> user_contact_info_FindByUserId(@PathParam("userid")int userid) {
        return vibe.user_contact_info_FindByUserId(userid);
    }
    
    
    @Path("user_contact_info_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserContactInfo> user_contact_info_ShowAll() {
        return vibe.user_contact_info_ShowAll();
    }
        
    //User Education
    
    @Path("user_education_insert/{ueId}/{instituteName}/{joiningDate}/{endingDate}/{instituteAddress}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_education_Insert(@PathParam("ueId")int ueId, @PathParam("instituteName")String instituteName, @PathParam("joiningDate")String joiningDate, @PathParam("endingDate")String endingDate, @PathParam("instituteAddress")String instituteAddress, @PathParam("userid")int userid) {
        return vibe.user_education_Insert(ueId, instituteName, joiningDate, endingDate, instituteAddress, userid);
    }
    
    @Path("user_education_update/{ueId}/{instituteName}/{joiningDate}/{endingDate}/{instituteAddress}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_education_Update(@PathParam("ueId")int ueId, @PathParam("instituteName")String instituteName, @PathParam("joiningDate")String joiningDate, @PathParam("endingDate")String endingDate, @PathParam("instituteAddress")String instituteAddress, @PathParam("userid")int userid) {
        return vibe.user_education_Update(ueId, instituteName, joiningDate, endingDate, instituteAddress, userid);
    }
    
    @Path("user_education_delete/{ueId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_education_Delete(@PathParam("ueId")int ueId) {
        return vibe.user_education_Delete(ueId);
    }
    
    @Path("user_education_findbyid/{ueId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserEducation user_education_FindById(@PathParam("ueId")int ueId) {
        return vibe.user_education_FindById(ueId);
    }
    
    @Path("user_education_findbyuserid/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEducation> user_education_FindByUserId(@PathParam("userid")int userid) {
        return vibe.user_education_FindByUserId(userid);
    }
    
    
    @Path("user_education_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserEducation> user_education_ShowAll() {
        return vibe.user_education_ShowAll();
    }
    
    
    //User Skills
    
    @Path("user_skills_insert/{usId}/{skillname}/{skillinfo}/{skillportfolio}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_skills_Insert(@PathParam("usId")int usId, @PathParam("skillname")String skillname, @PathParam("skillinfo")String skillinfo, @PathParam("skillportfolio")String skillportfolio, @PathParam("userid")int userid) {
        return vibe.user_skills_Insert(usId, skillname, skillinfo, skillportfolio, userid);
    }
    
    @Path("user_skills_update/{usId}/{skillname}/{skillinfo}/{skillportfolio}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_skills_Update(@PathParam("usId")int usId, @PathParam("skillname")String skillname, @PathParam("skillinfo")String skillinfo, @PathParam("skillportfolio")String skillportfolio, @PathParam("userid")int userid) {
        return vibe.user_skills_Update(usId, skillname, skillinfo, skillportfolio, userid);
    }
    
    @Path("user_skills_delete/{usId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_skills_Delete(@PathParam("usId")int usId) {
        return vibe.user_skills_Delete(usId);
    }
    
    @Path("user_skills_findbyid/{usId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserSkills user_skills_FindById(@PathParam("usId")int usId) {
        return vibe.user_skills_FindById(usId);
    }

    @Path("user_skills_findbyuserid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSkills> user_skills_FindByUserId(@PathParam("userId")int userId) {
        return vibe.user_skills_FindByUserId(userId);
    }
    
    @Path("user_skills_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserSkills> user_skills_ShowAll() {
        return vibe.user_skills_ShowAll();
    }
    
    //User Work
    
    @Path("user_work_insert/{uwId}/{companyname}/{joiningDate}/{endingDate}/{companyaddress}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_work_Insert(@PathParam("uwId")int uwId, @PathParam("companyname")String companyname, @PathParam("joiningDate")String joiningDate, @PathParam("endingDate")String endingDate, @PathParam("companyaddress")String companyaddress, @PathParam("userid")int userid) {
        return vibe.user_work_Insert(uwId, companyname, joiningDate, endingDate, companyaddress, userid);
    }
    
    @Path("user_work_update/{uwId}/{companyname}/{joiningDate}/{endingDate}/{companyaddress}/{userid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_work_Update(@PathParam("uwId")int uwId, @PathParam("companyname")String companyname, @PathParam("joiningDate")String joiningDate, @PathParam("endingDate")String endingDate, @PathParam("companyaddress")String companyaddress, @PathParam("userid")int userid) {
        return vibe.user_work_Update(uwId, companyname, joiningDate, endingDate, companyaddress, userid);
    }
    
    @Path("user_work_delete/{uwId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String user_work_Delete(@PathParam("uwId")int uwId) {
        return vibe.user_work_Delete(uwId);
    }
    
    @Path("user_work_findbyid/{uwId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserWork user_work_FindById(@PathParam("uwId")int uwId) {
        return vibe.user_work_FindById(uwId);
    }
    
    @Path("user_work_findbyuserid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserWork> user_work_FindByUserId(@PathParam("userId")int userId) {
        return vibe.user_work_FindByUserId(userId);
    }
    
    
    @Path("user_work_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<UserWork> user_work_ShowALl() {
        return vibe.user_work_ShowALl();
    }
    
    //Groups
    
    @Path("groupinsert/{groupid}/{groupName}/{description}/{membersCount}/{isDeleted}/{adminId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Groups groupInsert(@PathParam("groupid")int groupid, @PathParam("groupName")String groupName, @PathParam("description")String description, @PathParam("membersCount")int membersCount, @PathParam("isDeleted")boolean isDeleted, @PathParam("adminId")int adminId) {
        return vibe.groupInsert(groupid, groupName, description, membersCount, isDeleted, adminId);
    }
    
    @Path("groupupdate/{groupid}/{groupName}/{description}/{membersCount}/{isDeleted}/{adminId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String groupUpdate(@PathParam("groupid")int groupid, @PathParam("groupName")String groupName, @PathParam("description")String description, @PathParam("membersCount")int membersCount, @PathParam("isDeleted")boolean isDeleted, @PathParam("adminId")int adminId) {
        return vibe.groupUpdate(groupid, groupName, description, membersCount, isDeleted, adminId);
    }
    
    @Path("groupdelete/{groupid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String groupDelete(@PathParam("groupid")int groupid) {
        return vibe.groupDelete(groupid);
    }
    
    @Path("groupfindbyid/{groupid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Groups groupFindById(@PathParam("groupid")int groupid) {
        return vibe.groupFindById(groupid);
    }
    
    @Path("groupshowallbyuser/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Groups> groupShowAllByUser(@PathParam("userId")int userId) {
        return vibe.groupShowAllByUser(userId);
    }
    
    @Path("groupshowall/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Groups> groupShowAll(@PathParam("userId")int userId) {
        return vibe.groupShowAll(userId);
    }
    
    @Path("groupshowallinadmin")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Groups> groupShowAllInAdmin() {
        return vibe.groupShowAll();
    }
    
    //Group Members
    
    
    @Path("group_member_insert/{gmId}/{isMember}/{becameMember}/{groupId}/{memberId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String group_member_Insert(@PathParam("gmId")int gmId, @PathParam("isMember")boolean isMember, @PathParam("becameMember")String becameMember, @PathParam("groupId")int groupId, @PathParam("memberId")int memberId) {
        return vibe.group_member_Insert(gmId, isMember, becameMember, groupId, memberId);
    }
    
    @Path("group_member_update/{gmId}/{isMember}/{becameMember}/{groupId}/{memberId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String group_member_Update(@PathParam("gmId")int gmId, @PathParam("isMember")boolean isMember, @PathParam("becameMember")String becameMember, @PathParam("groupId")int groupId, @PathParam("memberId")int memberId) {
        return vibe.group_member_Update(gmId, isMember, becameMember, groupId, memberId);
    }
    
    @Path("group_member_delete/{gmId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String group_member_Delete(@PathParam("gmId")int gmId) {
        return vibe.group_member_Delete(gmId);
    }
    
    @Path("group_member_findbyid/{gmId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GroupMembers group_member_FindById(@PathParam("gmId")int gmId) {
        return vibe.group_member_FindById(gmId);
    }
    
    @Path("group_member_findbygroupid/{groupId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupMembers> group_member_FindByGroupid(@PathParam("groupId")int groupId) {
        return vibe.group_member_FindByGroupid(groupId);
    }
    
    @Path("group_member_checkgroupmember/{userId}/{groupId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupMembers> group_member_checkGroupMember(@PathParam("userId")int userId , @PathParam("groupId")int groupId) {
        return vibe.group_member_checkGroupMember(userId, groupId);
    }
    
    @Path("group_member_findgroupsbyuserid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupMembers> group_member_findGroupsByUserId(@PathParam("userId")int userId) {
        return vibe.group_member_findGroupsByUserId(userId);
    }
    
    @Path("group_member_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<GroupMembers> group_member_ShowAll() {
        return vibe.group_member_ShowAll();
    }
    
    //Activity Feed
    
    @Path("activity_feed_insert/{afId}/{description}/{senderMsg}/{receiverMsg}/{targerURL}/{activityType}/{isRead}/{isDeleted}/{senderId}/{receiverId}/{groupId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String activity_feed_Insert(@PathParam("afId")int afId, @PathParam("description")String description, @PathParam("senderMsg")String senderMsg, @PathParam("receiverMsg")String receiverMsg, @PathParam("targerURL")String targerURL,  @PathParam("activityType")String activityType, @PathParam("isRead")boolean isRead, @PathParam("isDeleted")boolean isDeleted, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId, @PathParam("groupId")int groupId) {
        return vibe.activity_feed_Insert(afId, description, senderMsg, receiverMsg, targerURL, activityType, isRead, isDeleted, senderId, receiverId, groupId);
    }
    
    @Path("activity_feed_update/{afId}/{senderMsg}/{receiverMsg}/{targerURL}/{isRead}/{isDeleted}/{senderId}/{receiverId}/{groupId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String activity_feed_Update(@PathParam("afId")int afId, @PathParam("description")String description, @PathParam("senderMsg")String senderMsg, @PathParam("receiverMsg")String receiverMsg, @PathParam("targerURL")String targerURL,  @PathParam("activityType")String activityType, @PathParam("isRead")boolean isRead, @PathParam("isDeleted")boolean isDeleted, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId, @PathParam("groupId")int groupId) {
        return vibe.activity_feed_Update(afId, description, senderMsg, receiverMsg, targerURL, activityType, isRead, isDeleted, senderId, receiverId, groupId);
    }
    
    @Path("activity_feed_delete/{afId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String activity_feed_Delete(@PathParam("afId")int afId) {
        return vibe.activity_feed_Delete(afId);
    }
    
    @Path("activity_feed_findbyid/{afId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ActivityFeed activity_feed_FindById(@PathParam("afId")int afId) {
        return vibe.activity_feed_FindById(afId);
    }
    
    @Path("activity_feed_byreceiverid/{receiverId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActivityFeed> activity_feed_ByReceiverId(@PathParam("receiverId")int receiverId) {
        return vibe.activity_feed_ByReceiverId(receiverId);
    }
    
    @Path("activity_feed_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ActivityFeed> activity_feed_ShowAll() {
        return vibe.activity_feed_ShowAll();

    }
    
    
    //Ads
    
    
    @Path("adsinsert/{adsId}/{adsType}/{price}/{timeLimit}/{description}/{isRemoved}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void adsInsert(@PathParam("adsId")int adsId, @PathParam("adsType")String adsType, @PathParam("price")int price, @PathParam("timeLimit")String timeLimit, @PathParam("description")String description, @PathParam("isRemoved")boolean isRemoved) {
         vibe.adsInsert(adsId, adsType, price, timeLimit, description, isRemoved);
    }
    
    @Path("adsupdate/{adsId}/{adsType}/{price}/{timeLimit}/{description}/{isRemoved}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String adsUpdate(@PathParam("adsId")int adsId, @PathParam("adsType")String adsType, @PathParam("price")int price, @PathParam("timeLimit")String timeLimit, @PathParam("description")String description, @PathParam("isRemoved")boolean isRemoved) {
        return vibe.adsUpdate(adsId, adsType, price, timeLimit, description, isRemoved);
    }
    
    @Path("adsdelete/{adsId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String adsDelete(@PathParam("adsId")int adsId) {
        return vibe.adsDelete(adsId);
    }
    
    @Path("adsfindbyid/{adsId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Ads adsFindById(@PathParam("adsId")int adsId) {
        return vibe.adsFindById(adsId);
    }
    
    
    @Path("adsshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ads> adsShowAll() {
        return vibe.adsShowAll();

    }
    
    
    //Ads_User
    
    @Path("ads_user_insert/{auId}/{adsContent}/{description}/{link}/{endDate}/{isRemoved}/{isExpired}/{userId}/{adsId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String ads_user_Insert(@PathParam("auId")int auId, @PathParam("adsContent")String adsContent, @PathParam("description")String description, @PathParam("link")String link, @PathParam("endDate")String endDate, @PathParam("isRemoved")boolean isRemoved, @PathParam("isExpired")boolean isExpired, @PathParam("userId")int userId, @PathParam("adsId")int adsId) {
        return vibe.ads_user_Insert(auId, adsContent, description, link, endDate, isRemoved, isExpired, userId, adsId);
    }
    
    @Path("ads_user_update/{auId}/{adsContent}/{description}/{link}/{endDate}/{isRemoved}/{isExpired}/{userId}/{adsId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String ads_user_Update(@PathParam("auId")int auId, @PathParam("adsContent")String adsContent, @PathParam("description")String description, @PathParam("link")String link, @PathParam("endDate")String endDate, @PathParam("isRemoved")boolean isRemoved, @PathParam("isExpired")boolean isExpired, @PathParam("userId")int userId, @PathParam("adsId")int adsId) {
        return vibe.ads_user_Update(auId, adsContent, description, link, endDate, isRemoved, isExpired, userId, adsId);
    }
    
    @Path("ads_user_delete/{auId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String ads_user_Delete(@PathParam("auId")int auId) {
        return vibe.ads_user_Delete(auId);
    }
    
    @Path("ads_user_findbyid/{auId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AdsUser ads_user_FindById(@PathParam("auId")int auId) {
        return vibe.ads_user_FindById(auId);
    }
    
    
    @Path("ads_user_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<AdsUser> ads_user_ShowAll() {
        return vibe.ads_user_ShowAll();

    }
    
    //Posts
    
    @Path("postinsert/{postId}/{post}/{caption}/{is_deleted}/{likecount}/{postType}/{userId}/{groupId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postInsert(@PathParam("postId")int postId, @PathParam("post")String post, @PathParam("caption")String caption, @PathParam("is_deleted")boolean is_deleted, @PathParam("likecount")int likecount, @PathParam("postType")String postType, @PathParam("userId")int userId, @PathParam("groupId")int groupId) {
        return vibe.postInsert(postId, post, caption, is_deleted, likecount, postType, userId, groupId);
    }
    
    @Path("postupdate/{postId}/{post}/{caption}/{is_deleted}/{likecount}/{postType}/{userId}/{groupId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postUpdate(@PathParam("postId")int postId, @PathParam("post")String post, @PathParam("caption")String caption, @PathParam("is_deleted")boolean is_deleted, @PathParam("likecount")int likecount, @PathParam("postType")String postType, @PathParam("userId")int userId, @PathParam("groupId")int groupId) {
        return vibe.postUpdate(postId, post, caption, is_deleted, likecount, postType, userId, groupId);
    }
    
    @Path("postdelete/{postId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String postDelete(@PathParam("postId")int postId) {
        return vibe.postDelete(postId);
    }
    
    @Path("postfindbyid/{postId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Post postFindById(@PathParam("postId")int postId) {
        return vibe.postFindById(postId);
    }
    
    @Path("postshowallbyuserid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postShowAllByUserId(@PathParam("userId")int userId) {
        return vibe.postShowAllByUserId(userId);
    }
    
    @Path("postshowallbygroupid/{groupId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postShowAllByGroupId(@PathParam("groupId")int groupId) {
        return vibe.postShowAllByGroupId(groupId);
    }
    
    @Path("postshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postShowAll() {
        return vibe.postShowAll();

    }
    
    
    //Likes
    
    @Path("likeinsert/{likeId}/{isRemoved}/{postId}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String likeInsert(@PathParam("likeId")int likeId, @PathParam("isRemoved")boolean isRemoved, @PathParam("postId")int postId, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.likeInsert(likeId, isRemoved, postId, senderId, receiverId);
    }
    
    @Path("likeupdate/{likeId}/{isRemoved}/{postId}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String likeUpdate(@PathParam("likeId")int likeId, @PathParam("isRemoved")boolean isRemoved, @PathParam("postId")int postId, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.likeUpdate(likeId, isRemoved, postId, senderId, receiverId);
    }
    
    @Path("likedelete/{likeId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String likeDelete(@PathParam("likeId")int likeId) {
        return vibe.likeDelete(likeId);
    }
    
    @Path("likefindbyid/{likeId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Likes likeFindById(@PathParam("likeId")int likeId) {
        return vibe.likeFindById(likeId);
    }
    
    @Path("isliked/{postid}/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Likes> isLiked(@PathParam("postid")int postid, @PathParam("userid")int userid) {
        return vibe.isLiked(postid, userid);
    }
    
    @Path("likecount/{postid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Likes> likeCount(@PathParam("postid")int postid) {
        return vibe.likeCount(postid);
    }
    
    
    @Path("likeshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Likes> likeShowAll() {
        return vibe.likeShowAll();

    }
    
    
    //Friend Request
    
    
    @Path("friend_request_insert/{frId}/{status}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_request_Insert(@PathParam("frId")int frId, @PathParam("status")String status, @PathParam("requestDate")Date requestDate, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.friend_request_Insert(frId, status, senderId, receiverId);
    }
    
    @Path("friend_request_update/{frId}/{status}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_request_Update(@PathParam("frId")int frId, @PathParam("status")String status, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.friend_request_Update(frId, status, senderId, receiverId);
    }
    
    @Path("friend_request_delete/{frId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_request_Delete(@PathParam("frId")int frId) {
        return vibe.friend_request_Delete(frId);
    }
    
    @Path("friend_request_findbyid/{frId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FriendRequest friend_request_FindById(@PathParam("frId")int frId) {
        return vibe.friend_request_FindById(frId);
    }
    
    @Path("friend_request_findbysenderid/{senderId}/{status}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendRequest> friend_request_FindBySenderId(@PathParam("senderId")int senderId, @PathParam("status")String status) {
        return vibe.friend_request_FindBySenderId(senderId, status);
    }
    
    @Path("friend_request_findbyreceiverid/{receiverId}/{status}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendRequest> friend_request_FindByReceiverId(@PathParam("receiverId")int receiverId, @PathParam("status")String status) {
        return vibe.friend_request_FindByReceiverId(receiverId, status);
    }
    
    @Path("friend_request_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendRequest> friend_request_ShowAll() {
        return vibe.friend_request_ShowAll();
    }
    
    @Path("friend_request_checkstatus/{senderId}/{receiverId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendRequest> friend_request_CheckStatus(@PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.friend_request_CheckStatus(senderId, receiverId);
    }
    
    //Comments
    
    
    @Path("commentsinsert/{commentId}/{comment}/{isRemoved}/{postId}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String commentsInsert(@PathParam("commentId")int commentId, @PathParam("comment")String comment, @PathParam("isRemoved")boolean isRemoved, @PathParam("postId")int postId, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.commentsInsert(commentId, comment, isRemoved, postId, senderId, receiverId);
    }
    
    @Path("commentsupdate/{commentId}/{comment}/{isRemoved}/{postId}/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String commentsUpdate(@PathParam("commentId")int commentId, @PathParam("comment")String comment, @PathParam("isRemoved")boolean isRemoved, @PathParam("postId")int postId, @PathParam("senderId")int senderId, @PathParam("receiverId")int receiverId) {
        return vibe.commentsUpdate(commentId, comment, isRemoved, postId, senderId, receiverId);
    }
    
    @Path("commentsdelete/{commentId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String commentsdelete(@PathParam("commentId")int commentId) {
        return vibe.commentsdelete(commentId);
    }
    
    @Path("commentsfindbyid/{commentId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Comments commentsFindById(@PathParam("commentId")int commentId) {
        return vibe.commentsFindById(commentId);
    }
    
    
    @Path("commentsshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comments> commentsShowAll() {
        return vibe.commentsShowAll();

    }
    
    @Path("commentsfindbypostid/{postid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comments> commentsFindByPostId(@PathParam("postid")int postid) {
        return vibe.commentsFindByPostId(postid);

    }
    
    
    
    //Events
    
    
    @Path("eventinsert/{eventid}/{hostid}/{eventname}/{post}/{eventstartdate}/{eventenddate}/{eventinfo}/{venue}/{type}/{fees}/{mode}/{guestcount}/{isremoved}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String eventInsert(@PathParam("eventid")int eventid, @PathParam("hostid")int hostid, @PathParam("eventname")String eventname, @PathParam("post")String post, @PathParam("eventstartdate")String eventstartdate, @PathParam("eventenddate")String eventenddate, @PathParam("eventinfo")String eventinfo, @PathParam("venue")String venue, @PathParam("type")String type, @PathParam("fees")int fees, @PathParam("mode")String mode, @PathParam("guestcount")int guestcount, @PathParam("isremoved")boolean isremoved) {
        return vibe.eventInsert(eventid, eventname, post, eventstartdate, eventenddate, eventinfo, venue, type, fees, mode, guestcount, isremoved, hostid);
    }
    
    @Path("eventupdate/{eventid}/{hostid}/{eventname}/{post}/{eventstartdate}/{eventenddate}/{eventinfo}/{venue}/{type}/{fees}/{mode}/{guestcount}/{isremoved}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String eventUpdate(@PathParam("eventid")int eventid, @PathParam("hostid")int hostid, @PathParam("eventname")String eventname, @PathParam("post")String post, @PathParam("eventstartdate")String eventstartdate, @PathParam("eventenddate")String eventenddate, @PathParam("eventinfo")String eventinfo, @PathParam("venue")String venue, @PathParam("type")String type, @PathParam("fees")int fees, @PathParam("mode")String mode, @PathParam("guestcount")int guestcount, @PathParam("isremoved")boolean isremoved) {
        return vibe.eventUpdate(eventid, eventname, post, eventstartdate, eventenddate, eventinfo, venue, type, fees, mode, guestcount, isremoved, hostid);
    }
    
    @Path("eventdelete/{eventId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String eventDelete(@PathParam("eventId")int eventId) {
        return vibe.eventDelete(eventId);
    }
    
    @Path("eventfindbyid/{eventId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Events eventFindById(@PathParam("eventId")int eventId) {
        return vibe.eventFindById(eventId);
    }
    
    
    @Path("eventshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Events> eventShowAll() {
        return vibe.eventShowAll();

    }
    
    @Path("suggestedevents/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Events> suggestedEvents(@PathParam("userid")int userid) {
        return vibe.suggestedEvents(userid);

    }
    
    @Path("subscribedEvents/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Events> subscribedEvents(@PathParam("userid")int userid) {
        return vibe.subscribedEvents(userid);

    }
    
    @Path("hostedevents/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Events> hostedEvents(@PathParam("userid")int userid) {
        return vibe.hostedEvents(userid);

    }
    
    //FriendList
    
    @Path("friendlistinsert/{flId}/{friendStatus}/{userId}/{friendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_list_Insert(@PathParam("flId")int flId, @PathParam("friendStatus")boolean friendStatus, @PathParam("userId")int userId, @PathParam("friendId")int friendId) {
        return vibe.friend_list_Insert(flId, friendStatus, userId, friendId);
    }
    
    
    
    @Path("friendlistupdate/{flId}/{friendStatus}/{userId}/{friendId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_list_Update(@PathParam("flId")int flId, @PathParam("friendStatus")boolean friendStatus, @PathParam("userId")int userId, @PathParam("friendId")int friendId) {
        return vibe.friend_list_Update(flId, friendStatus, userId, friendId);
    }
    
    
    
    @Path("friendlistdelete/{flId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_list_Delete(@PathParam("flId")int flId) {
        return vibe.friend_list_Delete(flId);
    }
    
    
    @Path("friendlistfindbyid/{flId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FriendList friend_list_FindById(@PathParam("flId")int flId) {
        return vibe.friend_list_FindById(flId);
    }
    
    @Path("friendlistfindbyuserid/{userId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendList> friend_list_ShowAllByUserId(@PathParam("userId")int userId) {
        return vibe.friend_list_ShowAllByUserId(userId);
    }
    
    @Path("friendlistshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FriendList> friend_list_ShowAll() {
        return vibe.friend_list_ShowAll();
    }
    
    @Path("friendunfriend/{senderId}/{receiverId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String friend_unfriend(@PathParam("senderId") int senderId, @PathParam("receiverId") int receiverId) {
        return vibe.friend_unfriend(senderId, receiverId);
    }

    //Events User Counts
    
    
    @Path("event_usercount_insert/{eucId}/{isIntrested}/{eventId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String event_usercount_Insert(@PathParam("eucId")int eucId, @PathParam("isIntrested")boolean isIntrested, @PathParam("eventId")int eventId, @PathParam("userId")int userId) {
        return vibe.event_usercount_Insert(eucId, isIntrested, eventId, userId);
    }
    
    @Path("event_usercount_update/{eucId}/{isIntrested}/{eventId}/{userId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String event_usercount_Update(@PathParam("eucId")int eucId, @PathParam("isIntrested")boolean isIntrested, @PathParam("eventId")int eventId, @PathParam("userId")int userId) {
        return vibe.event_usercount_Update(eucId, isIntrested, eventId, userId);
    }
    
    @Path("event_usercount_delete/{eucId}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String event_usercount_Delete(@PathParam("eucId")int eucId) {
        return vibe.event_usercount_Delete(eucId);
    }
    
    @Path("event_usercount_findbyid/{eucId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EventUsercount event_usercount_FindById(@PathParam("eucId")int eucId) {
        return vibe.event_usercount_FindById(eucId);
    }
    
    
    @Path("event_usercount_showall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventUsercount> event_usercount_ShowAll() {
        return vibe.event_usercount_ShowAll();

    }
    
    @Path("event_usercount_findbyeventid/{eventid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<EventUsercount> event_usercount_FindByEventId(@PathParam("eventid")int eventid) {
        return vibe.event_usercount_FindByEventId(eventid);

    }
    
    @Path("eventfindsubscribe/{eventid}/{userid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public EventUsercount eventFindSubscribe(@PathParam("eventid")int eventid, @PathParam("userid")int userid) {
        return vibe.eventFindSubscribe(eventid, userid);
    }

    //Login

    @Path("vibelogin/{email}/{password}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public User vibeLogin(@PathParam("email")String email, @PathParam("password")String password) {
        return vibe.vibeLogin(email, password);
    }
    
    
    //products
    
    @Path("productinsert/{pname}/{category}/{description}/{price}/{pimage}/{isactive}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String productInsert(@PathParam("pname")String pname,@PathParam("category")String category,@PathParam("description")String description,@PathParam("price")String price,@PathParam("pimage")String pimage,@PathParam("isactive")boolean isactive){
        return vibe.productInsert(pname, category, description, price, pimage, isactive);
    }
    
    @Path("productupdate/{pid}/{pname}/{category}/{description}/{price}/{pimage}/{isactive}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String productUpdate(@PathParam("pid")int pid,@PathParam("pname")String pname,@PathParam("category")String category,@PathParam("description")String description,@PathParam("price")String price,@PathParam("pimage")String pimage,@PathParam("isactive")boolean isactive){
        return vibe.productUpdate(pid, pname, category, description, price, pimage, isactive);
    }
    
    @Path("productdelete/{pid}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String productDelete(@PathParam("pid")int pid){
        return vibe.productDelete(pid);
    }
    
    @Path("productfindbyid/{pid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product productFindById(@PathParam("pid")int pid){
        return vibe.productFindById(pid);
    }
    
    @Path("productfindbyname/{pname}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product productFindByName(@PathParam("pname")String pname){
        return vibe.productFindByName(pname);
    }
    
    
    @Path("productfindbycat/{pname}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Product productFindByCat(@PathParam("category")String category){
        return vibe.productFindByCat(category);
    }

    
    @Path("productshowall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> productShowAll(){
        return vibe.productShowAll();
    }
    
    @Path("productshowactive")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> productShowActive(){
        return vibe.productShowActive();
    }
}
