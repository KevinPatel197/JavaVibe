/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author kevin
 */
@Local
public interface VibeSessionBeanLocal {
    
    //Login
    public User vibeLogin(String email, String password);
    
    //Products
    public String productInsert(String pname,String category,String description,String price,String pimage,boolean isactive);
    public String productUpdate(int pid,String pname,String category,String description,String price, String pimage,boolean isactive);
    public String productDelete(int pid);
    public Product productFindById(int pid);
    public Product productFindByName(String pname);
    public Product productFindByCat(String category);
    public List<Product> productShowAll(); 
    public List<Product> productShowActive();
    
    
    //Country
    public String countryInsert(int countryId, String sortName, String countryName, int phoneCode, boolean isActive);
    public String countryUpdate(int countryId, String sortName, String countryName, int phoneCode, boolean isActive);
    public String countryDelete(int countryId);
    public Country countryFindById(int countryId);
    public Country countryFindByName(String countryName); //admin
    public List<Country> countryShowAll(); //admin
    public List<Country> countryShowActive();
    
    //State
    public String stateInsert(int stateId, String stateName, boolean isActive, int countryId);
    public String stateUpdate(int stateId, String stateName, boolean isActive, int countryId);
    public String stateDelete(int stateId);
    public State stateFindById(int stateId);
    public State stateFindByName(String stateName); //admin
    public List<State> stateShowAll(); //admin
    public List<State> stateShowActive();
    
    //City
    public String cityInsert(int cityId, String cityName, boolean isActive, int stateId);
    public String cityUpdate(int cityId, String cityName, boolean isActive, int stateId);
    public String cityDelete(int cityId);
    public City cityFindById(int cityId);
    public City cityFindByName(String cityName); //admin
    public List<City> cityShowAll(); //admin
    public List<City> cityShowActive();
    
    //User
    public String userRegister(int userId, String firstName, String lastName, String dob, String email, String password, boolean isActive, boolean isAdmin, boolean access);
    public String userUpdate(int userId, String firstName, String middleName, String lastName, String gender, String dob, String email, String username, String password, long mobile, String profilePhoto, String coverPhoto, int countryId, int stateId, int cityId);
    public String userDelete(int userId); //admin
    public List<User> userFindById(int userId);
    public List<User> userFindByName(String userName);
    public List<User> userShowAll(); //admin
    public List<User> adminShowAll(); //admin
    public List<User> peopleYouMayKnow(int senderId, int Id);
    public List<User> findUserByFname(String firstName);
    public List<User> findUserByLname(String lastName);
    
    //User_Contact_Info
    public String user_contact_info_Insert(int uciId, String website, String language, String intrested_in, String fb_link, String insta_link, String bio, int userId);
    public String user_contact_info_Update(int uciId, String website, String language, String intrested_in, String fb_link, String insta_link, String bio, int userId);
    public String user_contact_info_Delete(int uciId);
    public UserContactInfo user_contact_info_FindById(int uciId);
    public List<UserContactInfo> user_contact_info_FindByUserId(int userId);
    public List<UserContactInfo> user_contact_info_ShowAll(); //admin
    
    //User_Education
    public String user_education_Insert(int ueId, String instituteName, String joiningDate, String endingDate, String instituteAddress, int userId);
    public String user_education_Update(int ueId, String instituteName, String joiningDate, String endingDate, String instituteAddress, int userId);
    public String user_education_Delete(int ueId);
    public UserEducation user_education_FindById(int ueId);
    public List<UserEducation> user_education_FindByUserId(int userId);
    public List<UserEducation> user_education_ShowAll(); //admin
    
    //User_Skills
    public String user_skills_Insert(int usId, String skillName, String skillInfo, String skillPortfolio, int userId);
    public String user_skills_Update(int usId, String skillName, String skillInfo, String skillPortfolio, int userId);
    public String user_skills_Delete(int usId);
    public UserSkills user_skills_FindById(int usId);
    public List<UserSkills> user_skills_FindByUserId(int userId);
    public List<UserSkills> user_skills_ShowAll(); //admin
    
    //User_Work
    public String user_work_Insert(int uwId, String companyName, String joiningDate, String endingDate, String companyAddress, int userId);
    public String user_work_Update(int uwId, String companyName, String joiningDate, String endingDate, String companyAddress, int userId);
    public String user_work_Delete(int uwId);
    public UserWork user_work_FindById(int uwId);
    public List<UserWork> user_work_FindByUserId(int userId);
    public List<UserWork> user_work_ShowALl();  //admin
    
    
    //Groups
    public Groups groupInsert(int groupId, String groupName, String description, int membersCount, boolean isDeleted, int adminId);
    public String groupUpdate(int groupId, String groupName, String description, int membersCount, boolean isDeleted, int adminId);
    public String groupDelete(int groupId);
    public Groups groupFindById(int groupId);
    public List<Groups> groupShowAllByUser(int userId);
    public List<Groups> groupShowAll(int userId);
    public List<Groups> groupShowAll(); //admin
    
    //Group Members
    public String group_member_Insert(int gmId, boolean isMember, String becameMember, int groupId, int memberId);
    public String group_member_Update(int gmId, boolean isMember, String becameMember, int groupId, int memberId);
    public String group_member_Delete(int gmId);
    public GroupMembers group_member_FindById(int gmId);
    public List<GroupMembers> group_member_FindByGroupid(int groupId);
    public List<GroupMembers> group_member_checkGroupMember(int userId ,int groupId);
    public List<GroupMembers> group_member_findGroupsByUserId(int userId);
    public List<GroupMembers> group_member_ShowAll(); //admin
    
    //Post
    public String postInsert(int postId, String post, String caption, boolean is_deleted, int likeCount, String postType, int userId, int groupId);
    public String postUpdate(int postId, String post, String caption, boolean is_deleted, int likeCount, String postType, int userId, int groupId);
    public String postDelete(int postId);
    public Post postFindById(int postId);
    public List<Post> postShowAllByUserId(int userId);
    public List<Post> postShowAllByGroupId(int groupId);
    public List<Post> postShowAll(); //admin
    
    //Likes
    public String likeInsert(int likeId, boolean isRemoved, int postId, int senderId, int receiverId);
    public String likeUpdate(int likeId, boolean isRemoved, int postId, int senderId, int receiverId);
    public String likeDelete(int likeId);
    public Likes likeFindById(int likeId);
    public List<Likes> isLiked(int postId, int userId);
    public List<Likes> likeCount(int postId);
    public List<Likes> likeShowAll(); //admin
    
    //Friend Request
    public String friend_request_Insert(int frId, String status, int senderId, int receiverId);
    public String friend_request_Update(int frId, String status, int senderId, int receiverId);
    public String friend_request_Delete(int frId);
    public FriendRequest friend_request_FindById(int frId);
    public List<FriendRequest> friend_request_FindBySenderId(int senderId, String status);
    public List<FriendRequest> friend_request_FindByReceiverId(int receiverId, String status);
    public List<FriendRequest> friend_request_ShowAll(); //admin
    public List<FriendRequest> friend_request_CheckStatus(int senderId, int receiverId);
    
    
    //Friend List
    public String friend_list_Insert(int flId, boolean friendStatus, int userId, int friendId);
    public String friend_list_Update(int flId, boolean friendStatus, int userId, int friendId);
    public String friend_list_Delete(int flId);
    public FriendList friend_list_FindById(int flId);
    public List<FriendList> friend_list_ShowAllByUserId(int userId);
    public List<FriendList> friend_list_ShowAll();  //admin
    public String friend_unfriend(int senderId, int receiverId);
    
    
    //Events
    public String eventInsert(int eventId, String eventName, String post, String eventStartDate, String eventEndDate, String eventInfo, String venue, String type, int fees, String mode, int guestCount, boolean is_removed, int hostId);
    public String eventUpdate(int eventId, String eventName, String post, String eventStartDate, String eventEndDate, String eventInfo, String venue, String type, int fees, String mode, int guestCount, boolean is_removed, int hostId);
    public String eventDelete(int eventId);
    public Events eventFindById(int eventId);
    public List<Events> suggestedEvents(int userId);
    public List<Events> subscribedEvents(int userId);
    public List<Events> hostedEvents(int userId);
    public List<Events> eventShowAll();  //admin
    
    //Event Usercount
    public String event_usercount_Insert(int euc_Id, boolean isIntrested, int eventId, int userId);
    public String event_usercount_Update(int euc_Id, boolean isIntrested, int eventId, int userId);
    public String event_usercount_Delete(int euc_Id);
    public EventUsercount event_usercount_FindById(int euc_Id);
    public List<EventUsercount> event_usercount_FindByEventId(int eventId);
    public EventUsercount eventFindSubscribe(int eventId, int userId);
    public List<EventUsercount> event_usercount_ShowAll();  //admin
    
    //Comments
    public String commentsInsert(int commentId, String comment, boolean isRemoved, int postId, int senderId, int receiverId);
    public String commentsUpdate(int commentId, String comment, boolean isRemoved, int postId, int senderId, int receiverId);
    public String commentsdelete(int commentId);
    public Comments commentsFindById(int commentId);
    public List<Comments> commentsFindByPostId(int postId);
    public List<Comments> commentsShowAll(); //admin
    
    //Chat
    public String chatInsert(int chatId, String message, boolean isDelevered, boolean isRead, boolean isDeleted, int senderId, int receiverId);
    public List<Chat> chatFindByReceiverId(int receiver, int sender);
    public List<Chat> chatFindBySenderId(int sender, int receiver);

    //Ads User
    public String ads_user_Insert(int auId, String adsContent, String description, String link, String endDate, boolean isRemoved, boolean isExpired, int userId, int adsId);
    public String ads_user_Update(int auId, String adsContent, String description, String link, String endDate, boolean isRemoved, boolean isExpired, int userId, int adsId);
    public String ads_user_Delete(int auId);
    public AdsUser ads_user_FindById(int auId);
    public List<AdsUser> ads_user_ShowAll();  //admin
    
    //Ads
    public void adsInsert(int adsId, String adsType, int price, String timeLimit, String description, boolean isRemoved);
    public String adsUpdate(int adsId, String adsType, int price, String timeLimit, String description, boolean isRemoved);
    public String adsDelete(int adsId);
    public Ads adsFindById(int adsId);
    public List<Ads> adsShowAll(); //admin
    
    //Activity Feed
    public String activity_feed_Insert(int afId, String description, String senderMsg, String receiverMsg, String targerURL, String activityType, boolean isRead, boolean isDeleted, int senderId, int receiverId, int groupId);
    public String activity_feed_Update(int afId, String description, String senderMsg, String receiverMsg, String targerURL, String activityType, boolean isRead, boolean isDeleted, int senderId, int receiverId, int groupId);
    public String activity_feed_Delete(int afId);
    public ActivityFeed activity_feed_FindById(int afId);
    public List<ActivityFeed> activity_feed_ByReceiverId(int receiverId);
    public List<ActivityFeed> activity_feed_ShowAll();  //admin
    
}
