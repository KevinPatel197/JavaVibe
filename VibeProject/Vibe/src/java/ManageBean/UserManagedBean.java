/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.City;
import entity.Country;
import entity.State;
import entity.User;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "userManagedBean")
@ApplicationScoped
public class UserManagedBean {

    @EJB
    private VibeSessionBeanLocal vibe;

    private User user = new User();
    private final VibeClient vibeClient = new VibeClient();
    
    //User model
    private String userId;
    private String pincode;
    private String countryId;
    private String stateId;
    private String cityId;
    private String mobile;
    
    @NotNull(message = "FirstName Required")
    @Pattern(regexp = "[a-zA-Z]{3,30}", message = "Invalid First Name")
    private String firstName;
    private String middleName;
    
    @NotNull(message = "LastName Required")
    @Pattern(regexp = "[a-zA-Z]{3,30}", message = "Invalid Last Name")
    private String lastName;
    private String gender;
    
    @NotNull(message = "Email Required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid Email")
    private String email;
    
    Part file;
    
    private String userName;
    
    @NotNull(message = "Password Required")
    private String password;
    
    private String profilePhoto;
    
    
    private String coverPhoto;
    
    @NotNull(message = "Date Required")
    @Past(message = "Invalid DOB")
    private Date dob;
    
    private String birth;
    
    private String isActive;
    private String isAdmin;
    private String access;
    
    @AssertTrue(message = "Check Terms and Condition")
    private boolean accept;
    
    private String address;
    //User model
    
    //checking value
    private boolean register,notRegister,usedEmail;
    

    //private var
            
    //private var
    
    // Declare Lists
    
    private List<Country> countryList;
    private List<State> stateList;
    private List<City> cityList;
    private List<User> userList;
    
    // Declare Lists Ends
    
    //Session Declaration
    
    HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
    HttpSession userSession = request.getSession();
    
    //Session Declaration ends
    
    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
        
    }
    
    
    @PostConstruct
    public void init() {
        this.isActive = "true";
        this.isAdmin = "false";
        this.access = "true";
        this.userId = "0";
        this.accept = false;
        this.register = false;
        this.notRegister = false;
        this.usedEmail = false;
        this.userList = vibe.userShowAll();
        this.countryList = vibe.countryShowActive();
        this.stateList = vibe.stateShowActive();
        this.cityList = vibe.cityShowActive();
    }
    
    //Getters And Setters

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    
    
    public VibeSessionBeanLocal getVibe() {
        return vibe;
    }

    public void setVibe(VibeSessionBeanLocal vibe) {
        this.vibe = vibe;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<State> getStateList() {
        return stateList;
    }

    public void setStateList(List<State> stateList) {
        this.stateList = stateList;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public boolean isRegister() {
        return register;
    }

    public void setRegister(boolean register) {
        this.register = register;
    }

    public boolean isNotRegister() {
        return notRegister;
    }

    public void setNotRegister(boolean notRegister) {
        this.notRegister = notRegister;
    }

    public boolean isUsedEmail() {
        return usedEmail;
    }

    public void setUsedEmail(boolean usedEmail) {
        this.usedEmail = usedEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
    //Getters And Setters
    
    //Private Methods
    
    private void showAllUserVar() {
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);
        System.out.println(isActive);
        System.out.println(access);
        System.out.println(isAdmin);
        System.out.println(dob);
        System.out.println(userId);
        System.out.println(accept);
    }
    
    private void clearAll() {
        setFirstName("");
        setLastName("");
        setEmail("");
        setPassword("");
        setDob(null);
        setAccept(false);
    }
    
    private void clearUpdate() {
        this.userId = null;
        this.mobile = null;
        this.dob = null;
        this.gender = null;
        this.firstName = null;
        this.middleName = null;
        this.lastName = null;
        this.email = null;
        this.userName = null;
        this.password = null;
        this.profilePhoto = null;
        this.coverPhoto = null;
        this.countryId = null;
        this.stateId = null;
        this.cityId = null;
    }
    
    private void setFalse() {
        this.accept = false;
        this.register = false;
        this.notRegister = false;
        this.usedEmail = false;
    }
    
    //ManageBeans Methods
    
    public List<Country> countryShowActive() {
        Response response = vibeClient.countryShowActive(Response.class);
        ArrayList<Country> countryArrayList = new ArrayList<>();
        
        GenericType<List<Country>> countryGenericType = new GenericType<List<Country>>(){};
        countryArrayList = (ArrayList<Country>)response.readEntity(countryGenericType);
        
        return countryArrayList;
    }
    
    public List<State> stateShowActive() {
        Response response = vibeClient.stateShowActive(Response.class);
        ArrayList<State> stateArrayList = new ArrayList<>();
        
        GenericType<List<State>> stateGenericType = new GenericType<List<State>>(){};
        stateArrayList = (ArrayList<State>)response.readEntity(stateGenericType);
        
        return stateArrayList;
    }
    
    public List<City> cityShowActive() {
        Response response = vibeClient.cityShowActive(Response.class);
        ArrayList<City> cityArrayList = new ArrayList<>();
        
        GenericType<List<City>> cityGenericType = new GenericType<List<City>>(){};
        cityArrayList = (ArrayList<City>)response.readEntity(cityGenericType);
        
        return cityArrayList;
    }
    
    public boolean userRegister() {
        try {
            
            //showAllVar();
            
            if(accept) {
                
                //Parsing Date to String
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String DOB = dateFormat.format(dob);
                
                String Register = vibeClient.userRegister(userId, firstName, lastName, DOB, email, password, isActive, isAdmin, access);
                if(Register.equals("false")) {
                    setEmail("");
                    return this.usedEmail = true;
                }
                clearAll();
                setFalse();
                return this.register = true;
                
            } else {
                setFalse();
                return this.accept = false;
            }
            
        } catch (ClientErrorException e) {
            
            System.out.println("Error:- " + e.getMessage());
            setFalse();
            return this.notRegister = true;
        }
    }
    
    public void findUserById() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.userFindById(Response.class, userSessions.getAttribute("UuserId").toString());
        GenericType<User> userDetails = new GenericType<User>(){};
        User Users = response.readEntity(userDetails);
        
        email = Users.getEmail();
        mobile = String.valueOf(Users.getMobile());
        address = Users.getStateid().getStatename();
        dob = Users.getDob();
        gender = Users.getGender();
        
        
    }
    
    public List<User> showUserInfo() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.userFindById(Response.class, userSessions.getAttribute("UuserId").toString());
        ArrayList<User> infoArrayList = new ArrayList<>();
        GenericType<List<User>> showAllinfo  = new GenericType<List<User>>() {
        };
        infoArrayList = (ArrayList<User>) response.readEntity(showAllinfo);
        return infoArrayList;
    }
    
    public List<User> showUserInfoByProfileId(String Id) {
        
        Response response = vibeClient.userFindById(Response.class, Id);
        ArrayList<User> infoArrayList = new ArrayList<>();
        GenericType<List<User>> showAllinfo  = new GenericType<List<User>>() {
        };
        infoArrayList = (ArrayList<User>) response.readEntity(showAllinfo);
        return infoArrayList;
    }
    
    public String editUserInfo(String id) {
        
        Response response = vibeClient.userFindById(Response.class, id);
        ArrayList<User> infoArrayList = new ArrayList<>();
        GenericType<List<User>> showAllinfo  = new GenericType<List<User>>() {
        };
        infoArrayList = (ArrayList<User>) response.readEntity(showAllinfo);
        
        for(User u : infoArrayList) {
            this.userId = u.getUserid().toString();
            this.mobile = String.valueOf(u.getMobile());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String DOB = dateFormat.format(u.getDob());
            this.birth = DOB;
            this.gender = u.getGender();
            this.firstName = u.getFirstname();
            this.middleName = u.getMiddlename();
            this.lastName = u.getLastname();
            this.email = u.getEmail();
            this.userName = u.getUsername();
            this.password = u.getPassword();
            this.profilePhoto = u.getProfilephoto();
            this.coverPhoto = u.getCoverphoto();
            this.countryId = "101";
            this.stateId = "12";
            this.cityId = "1041";
        }
        
        
        return "/web/user-edit.xhtml?faces-redirect=true";
        
    }
    
    public String updateUserInfo() throws IOException {
        
        if(file != null) {
            InputStream input = file.getInputStream();
            String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\ProfileImage\\";

            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            profilePhoto = "IMG_" + temp + ".jpg";
            Files.copy(input, new File(fullPath, profilePhoto).toPath());
        }
        
        String value = vibeClient.userUpdate(userId, firstName, middleName, lastName, gender, birth, email, userName, password, mobile, profilePhoto, coverPhoto, countryId, stateId, cityId);
                
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("UImage", profilePhoto);
        
        clearUpdate();
        
        return "/web/profile.xhtml?faces-redirect=true";
        
    }
    
    //Page Profile
    
    public String showProfileInfo(String Id) {
       
        Response response = vibeClient.userFindById(Response.class, Id);
        ArrayList<User> infoArrayList = new ArrayList<>();
        GenericType<List<User>> showAllinfo  = new GenericType<List<User>>() {
        };
        System.out.println("Profile info " + showAllinfo);
        infoArrayList = (ArrayList<User>) response.readEntity(showAllinfo);
        
        for(User ui : infoArrayList)
        {
            userId = ui.getUserid().toString();
            firstName = ui.getFirstname();
            lastName = ui.getLastname();
            email = ui.getEmail();
            dob = ui.getDob();
            profilePhoto = ui.getProfilephoto();
            
        }
        return "/web/profileinfo.xhtml?faces-redirect=true";
    }
}
