/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kevin
 */
@Entity
@Table(name = "user", catalog = "vibe", schema = "")
@XmlRootElement
@NamedQueries({
    
    //Find all Emails
    @NamedQuery(name = "User.findAllEmail", query = "SELECT u FROM User u"),
    
    //Find Admin By Email
    @NamedQuery(name = "User.findUserByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    
    //People You May Know
    @NamedQuery(name = "User.peopleYouMayKnow", query = "SELECT u FROM User u WHERE u.userid NOT IN(SELECT fl.friendid.userid FROM FriendList fl WHERE fl.userid.userid = :userid) AND u.userid != :currentUserId AND u.isadmin = false AND u.isactive = true"),
    
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByUserid", query = "SELECT u FROM User u WHERE u.userid = :userid"),
    @NamedQuery(name = "User.findByFirstname", query = "SELECT u FROM User u WHERE u.firstname = :firstname"),
    @NamedQuery(name = "User.findByMiddlename", query = "SELECT u FROM User u WHERE u.middlename = :middlename"),
    @NamedQuery(name = "User.findByLastname", query = "SELECT u FROM User u WHERE u.lastname = :lastname"),
    @NamedQuery(name = "User.findByGender", query = "SELECT u FROM User u WHERE u.gender = :gender"),
    @NamedQuery(name = "User.findByDob", query = "SELECT u FROM User u WHERE u.dob = :dob"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByMobile", query = "SELECT u FROM User u WHERE u.mobile = :mobile"),
    @NamedQuery(name = "User.findByProfilephoto", query = "SELECT u FROM User u WHERE u.profilephoto = :profilephoto"),
    @NamedQuery(name = "User.findByCoverphoto", query = "SELECT u FROM User u WHERE u.coverphoto = :coverphoto"),
    @NamedQuery(name = "User.findByIsactive", query = "SELECT u FROM User u WHERE u.isactive = :isactive"),
    @NamedQuery(name = "User.findByIsadmin", query = "SELECT u FROM User u WHERE u.isadmin = :isadmin"),
    @NamedQuery(name = "User.findByAccess", query = "SELECT u FROM User u WHERE u.access = :access"),
    @NamedQuery(name = "User.findByRegDate", query = "SELECT u FROM User u WHERE u.regDate = :regDate")})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private Integer userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "firstname", nullable = false, length = 1000)
    private String firstname;
    @Size(max = 1000)
    @Column(name = "middlename", length = 1000)
    private String middlename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "lastname", nullable = false, length = 1000)
    private String lastname;
    @Size(max = 1000)
    @Column(name = "gender", length = 1000)
    private String gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "email", nullable = false, length = 1000)
    private String email;
    @Size(max = 1000)
    @Column(name = "username", length = 1000)
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "password", nullable = false, length = 1000)
    private String password;
    @Column(name = "mobile")
    private long mobile;
    @Size(max = 1000)
    @Column(name = "profilephoto", length = 1000)
    private String profilephoto;
    @Size(max = 1000)
    @Column(name = "coverphoto", length = 1000)
    private String coverphoto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isactive", nullable = false)
    private boolean isactive;
    @Basic(optional = false)
    @NotNull
    @Column(name = "isadmin", nullable = false)
    private boolean isadmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "access", nullable = false)
    private boolean access;
    @Column(name = "reg_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UserContactInfo> userContactInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverid")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<Comments> commentsCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendid")
    private Collection<FriendList> friendListCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<FriendList> friendListCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "memberid")
    private Collection<GroupMembers> groupMembersCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UserEducation> userEducationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "adminid")
    private Collection<Groups> groupsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<AdsUser> adsUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<Post> postCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverid")
    private Collection<Chat> chatCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<Chat> chatCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<EventUsercount> eventUsercountCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverid")
    private Collection<ActivityFeed> activityFeedCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<ActivityFeed> activityFeedCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverid")
    private Collection<FriendRequest> friendRequestCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<FriendRequest> friendRequestCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UserWork> userWorkCollection;
    @JoinColumn(name = "cityid", referencedColumnName = "cityid")
    @ManyToOne
    private City cityid;
    @JoinColumn(name = "countryid", referencedColumnName = "countryid")
    @ManyToOne
    private Country countryid;
    @JoinColumn(name = "stateid", referencedColumnName = "stateid")
    @ManyToOne
    private State stateid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userid")
    private Collection<UserSkills> userSkillsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hostid")
    private Collection<Events> eventsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverid")
    private Collection<Likes> likesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderid")
    private Collection<Likes> likesCollection1;

    public User() {
    }

    public User(Integer userid) {
        this.userid = userid;
    }

    public User(Integer userid, String firstname, String lastname, Date dob, String email, String password, boolean isactive, boolean isadmin, boolean access) {
        this.userid = userid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.isactive = isactive;
        this.isadmin = isadmin;
        this.access = access;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getProfilephoto() {
        return profilephoto;
    }

    public void setProfilephoto(String profilephoto) {
        this.profilephoto = profilephoto;
    }

    public String getCoverphoto() {
        return coverphoto;
    }

    public void setCoverphoto(String coverphoto) {
        this.coverphoto = coverphoto;
    }

    public boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(boolean isadmin) {
        this.isadmin = isadmin;
    }

    public boolean getAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<UserContactInfo> getUserContactInfoCollection() {
        return userContactInfoCollection;
    }

    public void setUserContactInfoCollection(Collection<UserContactInfo> userContactInfoCollection) {
        this.userContactInfoCollection = userContactInfoCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Comments> getCommentsCollection1() {
        return commentsCollection1;
    }

    public void setCommentsCollection1(Collection<Comments> commentsCollection1) {
        this.commentsCollection1 = commentsCollection1;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<FriendList> getFriendListCollection() {
        return friendListCollection;
    }

    public void setFriendListCollection(Collection<FriendList> friendListCollection) {
        this.friendListCollection = friendListCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<FriendList> getFriendListCollection1() {
        return friendListCollection1;
    }

    public void setFriendListCollection1(Collection<FriendList> friendListCollection1) {
        this.friendListCollection1 = friendListCollection1;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<GroupMembers> getGroupMembersCollection() {
        return groupMembersCollection;
    }

    public void setGroupMembersCollection(Collection<GroupMembers> groupMembersCollection) {
        this.groupMembersCollection = groupMembersCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<UserEducation> getUserEducationCollection() {
        return userEducationCollection;
    }

    public void setUserEducationCollection(Collection<UserEducation> userEducationCollection) {
        this.userEducationCollection = userEducationCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Groups> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<Groups> groupsCollection) {
        this.groupsCollection = groupsCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<AdsUser> getAdsUserCollection() {
        return adsUserCollection;
    }

    public void setAdsUserCollection(Collection<AdsUser> adsUserCollection) {
        this.adsUserCollection = adsUserCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Post> getPostCollection() {
        return postCollection;
    }

    public void setPostCollection(Collection<Post> postCollection) {
        this.postCollection = postCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Chat> getChatCollection() {
        return chatCollection;
    }

    public void setChatCollection(Collection<Chat> chatCollection) {
        this.chatCollection = chatCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Chat> getChatCollection1() {
        return chatCollection1;
    }

    public void setChatCollection1(Collection<Chat> chatCollection1) {
        this.chatCollection1 = chatCollection1;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<EventUsercount> getEventUsercountCollection() {
        return eventUsercountCollection;
    }

    public void setEventUsercountCollection(Collection<EventUsercount> eventUsercountCollection) {
        this.eventUsercountCollection = eventUsercountCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<ActivityFeed> getActivityFeedCollection() {
        return activityFeedCollection;
    }

    public void setActivityFeedCollection(Collection<ActivityFeed> activityFeedCollection) {
        this.activityFeedCollection = activityFeedCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<ActivityFeed> getActivityFeedCollection1() {
        return activityFeedCollection1;
    }

    public void setActivityFeedCollection1(Collection<ActivityFeed> activityFeedCollection1) {
        this.activityFeedCollection1 = activityFeedCollection1;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<FriendRequest> getFriendRequestCollection() {
        return friendRequestCollection;
    }

    public void setFriendRequestCollection(Collection<FriendRequest> friendRequestCollection) {
        this.friendRequestCollection = friendRequestCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<FriendRequest> getFriendRequestCollection1() {
        return friendRequestCollection1;
    }

    public void setFriendRequestCollection1(Collection<FriendRequest> friendRequestCollection1) {
        this.friendRequestCollection1 = friendRequestCollection1;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<UserWork> getUserWorkCollection() {
        return userWorkCollection;
    }

    public void setUserWorkCollection(Collection<UserWork> userWorkCollection) {
        this.userWorkCollection = userWorkCollection;
    }

    public City getCityid() {
        return cityid;
    }

    public void setCityid(City cityid) {
        this.cityid = cityid;
    }

    public Country getCountryid() {
        return countryid;
    }

    public void setCountryid(Country countryid) {
        this.countryid = countryid;
    }

    public State getStateid() {
        return stateid;
    }

    public void setStateid(State stateid) {
        this.stateid = stateid;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<UserSkills> getUserSkillsCollection() {
        return userSkillsCollection;
    }

    public void setUserSkillsCollection(Collection<UserSkills> userSkillsCollection) {
        this.userSkillsCollection = userSkillsCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Events> getEventsCollection() {
        return eventsCollection;
    }

    public void setEventsCollection(Collection<Events> eventsCollection) {
        this.eventsCollection = eventsCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Likes> getLikesCollection() {
        return likesCollection;
    }

    public void setLikesCollection(Collection<Likes> likesCollection) {
        this.likesCollection = likesCollection;
    }

    @XmlTransient
    @JsonbTransient
    public Collection<Likes> getLikesCollection1() {
        return likesCollection1;
    }

    public void setLikesCollection1(Collection<Likes> likesCollection1) {
        this.likesCollection1 = likesCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.User[ userid=" + userid + " ]";
    }
    
}
