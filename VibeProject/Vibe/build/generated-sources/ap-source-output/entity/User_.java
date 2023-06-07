package entity;

import entity.ActivityFeed;
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
import entity.State;
import entity.UserContactInfo;
import entity.UserEducation;
import entity.UserSkills;
import entity.UserWork;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-07T23:59:43")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> firstname;
    public static volatile SingularAttribute<User, Boolean> access;
    public static volatile CollectionAttribute<User, UserSkills> userSkillsCollection;
    public static volatile SingularAttribute<User, String> gender;
    public static volatile CollectionAttribute<User, AdsUser> adsUserCollection;
    public static volatile SingularAttribute<User, Boolean> isactive;
    public static volatile CollectionAttribute<User, ActivityFeed> activityFeedCollection;
    public static volatile SingularAttribute<User, State> stateid;
    public static volatile SingularAttribute<User, Date> regDate;
    public static volatile CollectionAttribute<User, GroupMembers> groupMembersCollection;
    public static volatile SingularAttribute<User, Integer> userid;
    public static volatile SingularAttribute<User, String> coverphoto;
    public static volatile SingularAttribute<User, String> password;
    public static volatile CollectionAttribute<User, Post> postCollection;
    public static volatile CollectionAttribute<User, Likes> likesCollection1;
    public static volatile SingularAttribute<User, Boolean> isadmin;
    public static volatile CollectionAttribute<User, UserEducation> userEducationCollection;
    public static volatile CollectionAttribute<User, Chat> chatCollection1;
    public static volatile CollectionAttribute<User, Comments> commentsCollection;
    public static volatile CollectionAttribute<User, FriendList> friendListCollection1;
    public static volatile CollectionAttribute<User, UserWork> userWorkCollection;
    public static volatile CollectionAttribute<User, Likes> likesCollection;
    public static volatile SingularAttribute<User, String> email;
    public static volatile CollectionAttribute<User, Chat> chatCollection;
    public static volatile CollectionAttribute<User, FriendRequest> friendRequestCollection;
    public static volatile SingularAttribute<User, Long> mobile;
    public static volatile CollectionAttribute<User, Events> eventsCollection;
    public static volatile SingularAttribute<User, String> middlename;
    public static volatile CollectionAttribute<User, EventUsercount> eventUsercountCollection;
    public static volatile SingularAttribute<User, City> cityid;
    public static volatile CollectionAttribute<User, FriendRequest> friendRequestCollection1;
    public static volatile SingularAttribute<User, Country> countryid;
    public static volatile SingularAttribute<User, String> lastname;
    public static volatile SingularAttribute<User, String> profilephoto;
    public static volatile SingularAttribute<User, Date> dob;
    public static volatile CollectionAttribute<User, Comments> commentsCollection1;
    public static volatile CollectionAttribute<User, FriendList> friendListCollection;
    public static volatile CollectionAttribute<User, Groups> groupsCollection;
    public static volatile CollectionAttribute<User, ActivityFeed> activityFeedCollection1;
    public static volatile CollectionAttribute<User, UserContactInfo> userContactInfoCollection;
    public static volatile SingularAttribute<User, String> username;

}