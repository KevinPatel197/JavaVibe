/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Ads;
import entity.AdsUser;
import entity.Events;
import entity.Groups;
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
@Named(value = "adminManagedBean")
@ApplicationScoped
public class AdminManagedBean {

    // Session Bean Called
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;

    //JersyClient
    private final VibeClient vibeClient = new VibeClient();

    //Declare Variables
    private String userid;
    private String firstname;
    private String middlename;
    private String lastname;
    private String gender;
    private String dob;
    private String country;
    private String state;
    private String city;
    private String email;
    private String username;
    private String password;
    private String mobile;
    private String profilePhoto;
    private String coverPhoto;
    private String isactive;
    private String isadmin;
    private String access;
    private String req_date;
    
    private String ads_id;

    public String getAds_id() {
        return ads_id;
    }

    public void setAds_id(String ads_id) {
        this.ads_id = ads_id;
    }
    
    private String adstype;
    private String price;
    private String timelimit;
    private String description;

    public String getAdstype() {
        return adstype;
    }

    public void setAdstype(String adstype) {
        this.adstype = adstype;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    

    //Declare Variable ends
    
    
    // Declare customs Variables
    
    private String fullname;
    private boolean search;
    
    // Declare customs Variables ends
    
    // Declare Lists
    
    List<User> userlist;

    // Declare Lists ends
    
    //Session Declaration
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
    HttpSession adminSession = request.getSession();
    
    //Session Declaration ends
    
    
    /**
     * Creates a new instance of AdminManagedBean
     */
    public AdminManagedBean() {
    }

    //PostConstruct
    @PostConstruct
    public void init() {
        this.userid = adminSession.getAttribute("AuserId").toString();
        this.fullname = adminSession.getAttribute("AfullName").toString();
    }

    //PostConstruct ends
    //Getters And Setters
    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public String getIsadmin() {
        return isadmin;
    }

    public void setIsadmin(String isadmin) {
        this.isadmin = isadmin;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getReq_date() {
        return req_date;
    }

    public void setReq_date(String req_date) {
        this.req_date = req_date;
    }

    public List<User> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<User> userlist) {
        this.userlist = userlist;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    
    
    //Getters And Setters ends
    
    //Private Methods
    
    private void clearAll() {
        this.setUserid("");
        this.setFirstname("");
        this.setMiddlename("");
        this.setLastname("");
        this.setGender("");
        this.setDob("");
        this.setCountry("");
        this.setState("");
        this.setCity("");
        this.setEmail("");
        this.setUsername("");
        this.setPassword("");
        this.setMobile("");
        this.setProfilePhoto("");
        this.setCoverPhoto("");
    }
    
    private void setVariable(User user) {
        this.firstname = user.getFirstname();
        this.middlename = user.getMiddlename();
        this.lastname = user.getLastname();
        this.gender = user.getGender();
        this.dob = user.getDob().toString();
        this.country = user.getCityid().getCityname();
        this.state = user.getStateid().getStatename();
        this.city = user.getCityid().getCityname();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.profilePhoto = user.getProfilephoto();
        this.coverPhoto = user.getCoverphoto();
        this.req_date = user.getRegDate().toString();
    }
    
    //Private Methods ends
    //Public Methods
    
    //USER
    public List<User> userShowAll() {
        Response response = vibeClient.userShowAll(Response.class);
        ArrayList<User> userArrayList = new ArrayList<>();
        GenericType<List<User>> showAllUser = new GenericType<List<User>>() {
        };
        userArrayList = (ArrayList<User>) response.readEntity(showAllUser);
        return userArrayList;
    }
    
    public void showUser() {
        Response response = vibeClient.userFindById(Response.class, userid);
        GenericType<User> userById = new GenericType<User>() {};
        User user = response.readEntity(userById);
        
        setVariable(user);
    }
    
    public void showUserWork() {
        Response response = vibeClient.userFindById(Response.class, userid);
        GenericType<User> userById = new GenericType<User>() {};
        User user = response.readEntity(userById);
        
        setVariable(user);
    }
    
    public void findById(String id) {
        Response response = vibeClient.userFindById(Response.class, id);
        GenericType<User> userById = new GenericType<User>() {};
        User user = response.readEntity(userById);
        setVariable(user);
    }
    
    public List<User> findByName() {
        Response response = vibeClient.userFindByName(Response.class, firstname);
        ArrayList<User> userArrayList = new ArrayList<>();
        GenericType<List<User>> showAllUser = new GenericType<List<User>>() {
        };
        userArrayList = (ArrayList<User>) response.readEntity(showAllUser);
        return userArrayList;
    }
    
    //ADS
    
    public List<Ads> adsShowAll() {
        Response response = vibeClient.adsShowAll(Response.class);
        ArrayList<Ads> adsArrayList = new ArrayList<>();
        GenericType<List<Ads>> showAllAds = new GenericType<List<Ads>>() {
        };
        adsArrayList = (ArrayList<Ads>) response.readEntity(showAllAds);
        return adsArrayList;
    }
    
    public List<Ads> adsfindById(String id) {
        Response response = vibeClient.adsFindById(Response.class, id);
        ArrayList<Ads> adsArrayList = new ArrayList<>();
        GenericType<List<Ads>> showAllAds = new GenericType<List<Ads>>() {
        };
        adsArrayList = (ArrayList<Ads>) response.readEntity(showAllAds);
        return adsArrayList;
    }
    
    
     public void deletead(int id) {
        try {
            vibeClient.adsDelete(String.valueOf(id));
        } catch (ClientErrorException e) {
            e.getMessage();
        }
    }
     
     public String insertads(){
             vibeClient.adsInsert("0", adstype, price, timelimit, description, "false");
             return "/admin/ads.xhtml?faces-redirect=true";
    }
    
    
    
    //USER ADS
    
    
    public List<AdsUser> adsuserShowAll() {
        Response response = vibeClient.ads_user_ShowAll(Response.class);
        ArrayList<AdsUser> useradsArrayList = new ArrayList<>();
        GenericType<List<AdsUser>> showAllUserAds = new GenericType<List<AdsUser>>() {
        };
        useradsArrayList = (ArrayList<AdsUser>) response.readEntity(showAllUserAds
        );
        return useradsArrayList;
    }
    
    //EVENTS
    
    public List<Events> eventsShowAll() {
        Response response = vibeClient.eventShowAll(Response.class);
        ArrayList<Events> eventsArrayList = new ArrayList<>();
        GenericType<List<Events>> showAllEvents = new GenericType<List<Events>>() {
        };
        eventsArrayList = (ArrayList<Events>) response.readEntity(showAllEvents
        );
        return eventsArrayList;
    }
    
    //GROUPS
    
    public List<Groups> groupsShowAll() {
        Response response = vibeClient.groupShowAllInAdmin(Response.class);
        ArrayList<Groups> groupsArrayList = new ArrayList<>();
        GenericType<List<Groups>> showAllGroups = new GenericType<List<Groups>>() {
        };
        groupsArrayList = (ArrayList<Groups>) response.readEntity(showAllGroups
        );
        return groupsArrayList;
    }
    
            
    //Public Methods ends
}
