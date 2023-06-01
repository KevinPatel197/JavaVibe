/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

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
import entity.State;
import entity.User;
import entity.UserContactInfo;
import entity.UserEducation;
import entity.UserSkills;
import entity.UserWork;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import utility.HashUtility;

/**
 *
 * @author kevin
 */
@Stateless
public class VibeSessionBean implements VibeSessionBeanLocal {

    @PersistenceContext(unitName = "VibePU")
    EntityManager em;
    HashUtility hashPassword;

    @PostConstruct
    public void init() {
        hashPassword = new HashUtility();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    //DONE
    
    
    //Country
    @Override
    public String countryInsert(int countryId, String sortName, String countryName, int phoneCode, boolean isActive) {

        Object id = null;

        try {

            try {
                id = em.createNamedQuery("Country.findIdByCountryname")
                        .setParameter("countryname", countryName)
                        .getSingleResult();

                System.out.println(id);

            } catch (Exception e) {

                Country c = new Country(countryId, sortName, countryName, phoneCode, isActive);
                em.persist(c);

            }

            if (id != null) {
                Country c = em.find(Country.class, id);
                boolean active = c.getIsactive();
                if (!active) {
                    c.setIsactive(true);
                    em.merge(c);
                }
                return "Country Exists";
            }

            return "Country Inserted " + countryName;

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String countryUpdate(int countryId, String sortName, String countryName, int phoneCode, boolean isActive) {

        try {

            Country c = em.find(Country.class, countryId);

            if (c == null) {
                return "ID does not exist " + String.valueOf(countryId);
            }

            c.setCountryid(countryId);
            c.setSortname(sortName);
            c.setCountryname(countryName);
            c.setPhonecode(phoneCode);
            c.setIsactive(isActive);

            em.merge(c);
            return "Country Updated";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String countryDelete(int countryId) {

        try {

            Country c = em.find(Country.class, countryId);

            if (c == null) {
                return "ID does not exist " + String.valueOf(countryId);
            }

            c.setIsactive(false);
            em.merge(c);

            return "Country Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Country countryFindById(int countryId) {

        try {

            Country c = em.find(Country.class, countryId);
            return c;

        } catch (Exception e) {

            e.getMessage();
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Country countryFindByName(String countryName) {

        try {

            return (Country) em.createNamedQuery("Country.findByCountryname")
                    .setParameter("countryname", countryName)
                    .getSingleResult();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Country> countryShowAll() {

        try {

            return em.createNamedQuery("Country.findAll")
                    .getResultList();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Country> countryShowActive() {

        try {

            return em.createNamedQuery("Country.findByIsactive")
                    .setParameter("isactive", true)
                    .getResultList();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //State
    @Override
    public String stateInsert(int stateId, String stateName, boolean isActive, int countryId) {

        try {

            Country country = em.find(Country.class, countryId);
            Collection<State> sc = country.getStateCollection();

            State s = new State();

            s.setStateid(stateId);
            s.setStatename(stateName);
            s.setIsactive(isActive);
            s.setCountryid(country);

            sc.add(s);
            country.setStateCollection(sc);

            em.persist(s);
            em.merge(country);

            return "State Inserted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String stateUpdate(int stateId, String stateName, boolean isActive, int countryId) {

        try {

            Country c = em.find(Country.class, countryId);
            Collection<State> sc = c.getStateCollection();

            State s = em.find(State.class, stateId);

            s.setStateid(stateId);
            s.setStatename(stateName);
            s.setIsactive(isActive);
            s.setCountryid(c);

            sc.add(s);
            c.setStateCollection(sc);

            em.persist(s);
            em.merge(c);

            return "State Updated";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String stateDelete(int stateId) {

        try {

            State s = em.find(State.class, stateId);
            s.setIsactive(false);
            em.merge(s);

            return "State Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State stateFindById(int stateId) {

        try {

            return em.find(State.class, stateId);

        } catch (Exception e) {

            e.getMessage();
            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public State stateFindByName(String stateName) {

        try {

            return (State) em.createNamedQuery("State.findByStatename")
                    .setParameter("statename", stateName)
                    .getSingleResult();

        } catch (Exception e) {

            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> stateShowAll() {

        try {

            return em.createNamedQuery("State.findAll")
                    .getResultList();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<State> stateShowActive() {

        try {

            return em.createNamedQuery("State.findByIsactive")
                    .setParameter("isactive", true)
                    .getResultList();

        } catch (Exception e) {

            e.getMessage();
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //City
    @Override
    public String cityInsert(int cityId, String cityName, boolean isActive, int stateId) {

        try {

            State s = em.find(State.class, stateId);
            Collection<City> cc = s.getCityCollection();

            City c = new City();

            c.setCityid(cityId);
            c.setCityname(cityName);
            c.setIsactive(isActive);
            c.setStateid(s);

            cc.add(c);
            s.setCityCollection(cc);

            em.persist(c);
            em.merge(s);

            return "City Inserted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cityUpdate(int cityId, String cityName, boolean isActive, int stateId) {

        try {

            State s = em.find(State.class, stateId);
            Collection<City> cc = s.getCityCollection();

            City c = em.find(City.class, cityId);

            c.setCityid(cityId);
            c.setCityname(cityName);
            c.setIsactive(isActive);
            c.setStateid(s);

            cc.add(c);
            s.setCityCollection(cc);

            em.persist(c);
            em.merge(s);

            return "City Updated";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cityDelete(int cityId) {

        try {

            City c = em.find(City.class, cityId);
            em.remove(c);
            return "City Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City cityFindById(int cityId) {

        try {

            return em.find(City.class, cityId);

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public City cityFindByName(String cityName) {

        try {

            return (City) em.createNamedQuery("City.findByCityname")
                    .setParameter("cityname", cityName)
                    .getSingleResult();

        } catch (Exception e) {

            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<City> cityShowAll() {

        try {

            return em.createNamedQuery("City.findAll")
                    .getResultList();

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<City> cityShowActive() {

        try {

            return em.createNamedQuery("City.findByIsactive")
                    .setParameter("isactive", true)
                    .getResultList();

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //User
    @Override
    public String userRegister(int userId, String firstName, String lastName, String dob, String email, String password, boolean isActive, boolean isAdmin, boolean access) {

        boolean userRegister = false;

        try {

            List<User> emailList = em.createNamedQuery("User.findAllEmail")
                    .getResultList();

            if (!emailList.isEmpty()) {

                System.out.println(emailList);

                for (User userMail : emailList) {
                    if (email.equals(userMail.getEmail())) {
                        return "false";
                    } else {
                        userRegister = true;
                    }

                }
            }

            if (userRegister) {
                //parsing string-date to Date
                Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
                User user = new User(userId, firstName, lastName, DOB, email, hashPassword.getHashPassword(password), isActive, isAdmin, access);
                user.setRegDate(new Date());
                em.persist(user);
                System.out.println("User Registered");
                return "User Registered";
            }
            System.out.println("Not Registered");
            return "Not Registered";

        } catch (Exception e) {

            return "error:-   " + e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String userUpdate(int userId, String firstName, String middleName, String lastName, String gender, String dob, String email, String username, String password, long mobile, String profilePhoto, String coverPhoto, int countryId, int stateId, int cityId) {

        try {

            User user = em.find(User.class, userId);

            user.setUserid(userId);
            user.setFirstname(firstName);
            user.setMiddlename(middleName);
            user.setLastname(lastName);
            user.setGender(gender);

            Date DOB = new SimpleDateFormat("yyyy-MM-dd").parse(dob);
            user.setDob(DOB);

            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(password);
            user.setMobile(mobile);
            user.setProfilephoto(profilePhoto);
            user.setCoverphoto(coverPhoto);

            em.persist(user);

            return "User Updated";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String userDelete(int userId) {

        try {

            User user = em.find(User.class, userId);
            user.setIsactive(false);
            em.merge(user);

            return "User Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> userFindById(int userId) {

        try {

            List<User> user = em.createNamedQuery("User.findByUserid")
                    .setParameter("userid", userId)
                    .getResultList();

            if (user.isEmpty()) {
                return null;
            }

            return user;
        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> userShowAll() {

        try {

            List<User> user = em.createNamedQuery("User.findAll")
                    .getResultList();

            if (user.isEmpty()) {
                return null;
            }

            return user;

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> adminShowAll() {

        try {

            List<User> user = em.createNamedQuery("User.findByIsadmin")
                    .setParameter("isadmin", true)
                    .getResultList();
            return user;

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> userFindByName(String userName) {

        try {

            return em.createNamedQuery("User.findByFirstname")
                    .setParameter("firstname", userName)
                    .getResultList();

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> peopleYouMayKnow(int senderId, int Id) {
        try {

            List<User> fr = em.createNamedQuery("User.peopleYouMayKnow")
                    .setParameter("userid", senderId)
                    .setParameter("currentUserId", Id)
                    .getResultList();

            return fr;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    //User_Contact_Info
    @Override
    public String user_contact_info_Insert(int uciId, String website, String language, String intrested_in, String fb_link, String insta_link, String bio, int userId) {

        try {

            User u = em.find(User.class, userId);
            Collection<UserContactInfo> ucic = u.getUserContactInfoCollection();

            UserContactInfo uci = new UserContactInfo();

            uci.setUciId(uciId);
            uci.setUserid(u);
            uci.setWebsite(website);
            uci.setLanguage(language);
            uci.setIntrestedIn(intrested_in);
            uci.setFbLink(fb_link);
            uci.setInstaLink(insta_link);
            uci.setBio(bio);

            ucic.add(uci);
            u.setUserContactInfoCollection(ucic);

            em.persist(uci);
            em.merge(u);

            return "User Contact Info Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_contact_info_Update(int uciId, String website, String language, String intrested_in, String fb_link, String insta_link, String bio, int userId) {

        try {

            User u = em.find(User.class, userId);
            Collection<UserContactInfo> ucic = u.getUserContactInfoCollection();

            UserContactInfo uci = em.find(UserContactInfo.class, uciId);

            uci.setUciId(uciId);
            uci.setUserid(u);
            uci.setWebsite(website);
            uci.setLanguage(language);
            uci.setIntrestedIn(intrested_in);
            uci.setFbLink(fb_link);
            uci.setInstaLink(insta_link);
            uci.setBio(bio);

            ucic.add(uci);
            u.setUserContactInfoCollection(ucic);

            em.persist(uci);
            em.merge(u);

            return "User Contact Info Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_contact_info_Delete(int uciId) {

        try {

            UserContactInfo uci = em.find(UserContactInfo.class, uciId);
            em.remove(uci);
            return "User Contact Info Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserContactInfo user_contact_info_FindById(int uciId) {

        try {

            UserContactInfo uci = em.find(UserContactInfo.class, uciId);

            return uci;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserContactInfo> user_contact_info_ShowAll() {

        try {

            List<UserContactInfo> usercontact = em.createNamedQuery("UserContactInfo.findAll")
                    .getResultList();

            if (usercontact.isEmpty()) {
                return null;
            }

            return usercontact;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserContactInfo> user_contact_info_FindByUserId(int userId) {

        try {

            List<UserContactInfo> usercontact = em.createNamedQuery("UserContactInfo.findByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            return usercontact;

        } catch (Exception e) {

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //User_Education
    @Override
    public String user_education_Insert(int ueId, String instituteName, String joiningDate, String endingDate, String instituteAddress, int userId) {

        try {

            Date jdate = new SimpleDateFormat("yyyy-MM-dd").parse(joiningDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);

            User u = em.find(User.class, userId);
            Collection<UserEducation> uec = u.getUserEducationCollection();

            UserEducation ue = new UserEducation();

            ue.setUeId(ueId);
            ue.setUserid(u);
            ue.setInstitutename(instituteName);
            ue.setJoiningdate(jdate);
            ue.setEndingdate(edate);
            ue.setInstituteaddress(instituteAddress);

            uec.add(ue);
            u.setUserEducationCollection(uec);

            em.persist(ue);
            em.merge(u);

            return "User Education Info Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_education_Update(int ueId, String instituteName, String joiningDate, String endingDate, String instituteAddress, int userId) {

        try {

            Date jdate = new SimpleDateFormat("yyyy-MM-dd").parse(joiningDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);

            User u = em.find(User.class, userId);
            Collection<UserEducation> uec = u.getUserEducationCollection();

            UserEducation ue = em.find(UserEducation.class, ueId);

            ue.setUeId(ueId);
            ue.setUserid(u);
            ue.setInstitutename(instituteName);
            ue.setJoiningdate(jdate);
            ue.setEndingdate(edate);
            ue.setInstituteaddress(instituteAddress);

            uec.add(ue);
            u.setUserEducationCollection(uec);

            em.persist(ue);
            em.merge(u);

            return "User Education Info Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_education_Delete(int ueId) {

        try {

            UserEducation ue = em.find(UserEducation.class, ueId);
            em.remove(ue);
            return "User Education Info Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserEducation user_education_FindById(int ueId) {

        try {

            UserEducation ue = em.find(UserEducation.class, ueId);

            return ue;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserEducation> user_education_ShowAll() {

        try {

            List<UserEducation> usered = em.createNamedQuery("UserEducation.findAll")
                    .getResultList();

            if (usered.isEmpty()) {
                return null;
            }

            return usered;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserEducation> user_education_FindByUserId(int userId) {

        try {

            List<UserEducation> useredu = em.createNamedQuery("UserEducation.findByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            return useredu;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //User_Skills
    @Override
    public String user_skills_Insert(int usId, String skillName, String skillInfo, String skillPortfolio, int userId) {

        try {

            User u = em.find(User.class, userId);
            Collection<UserSkills> usc = u.getUserSkillsCollection();

            UserSkills us = new UserSkills();

            us.setUsId(usId);
            us.setUserid(u);
            us.setSkillname(skillName);
            us.setSkillinfo(skillInfo);
            us.setSkillportfolio(skillPortfolio);

            usc.add(us);
            u.setUserSkillsCollection(usc);

            em.persist(us);
            em.merge(u);

            return "User Skill Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_skills_Update(int usId, String skillName, String skillInfo, String skillPortfolio, int userId) {

        try {

            User u = em.find(User.class, userId);
            Collection<UserSkills> usc = u.getUserSkillsCollection();

            UserSkills us = em.find(UserSkills.class, usId);

            us.setUsId(usId);
            us.setUserid(u);
            us.setSkillname(skillName);
            us.setSkillinfo(skillInfo);
            us.setSkillportfolio(skillPortfolio);

            usc.add(us);
            u.setUserSkillsCollection(usc);

            em.persist(us);
            em.merge(u);

            return "User Skill Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_skills_Delete(int usId) {

        try {

            UserSkills us = em.find(UserSkills.class, usId);
            em.remove(us);
            return "User Skill Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserSkills user_skills_FindById(int usId) {

        try {

            UserSkills us = em.find(UserSkills.class, usId);

            return us;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserSkills> user_skills_ShowAll() {

        try {

            List<UserSkills> userskill = em.createNamedQuery("UserSkills.findAll")
                    .getResultList();

            if (userskill.isEmpty()) {
                return null;
            }

            return userskill;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserSkills> user_skills_FindByUserId(int userId) {

        try {

            List<UserSkills> userskill = em.createNamedQuery("UserSkills.findByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            return userskill;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //User_Work
    @Override
    public String user_work_Insert(int uwId, String companyName, String joiningDate, String endingDate, String companyAddress, int userId) {

        try {

            Date jdate = new SimpleDateFormat("yyyy-MM-dd").parse(joiningDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);

            User u = em.find(User.class, userId);
            Collection<UserWork> uwc = u.getUserWorkCollection();

            UserWork uw = new UserWork();

            uw.setUwId(uwId);
            uw.setUserid(u);
            uw.setCompanyname(companyName);
            uw.setJoiningdate(jdate);
            uw.setEndingdate(edate);
            uw.setCompanyaddress(companyAddress);

            uwc.add(uw);
            u.setUserWorkCollection(uwc);

            em.persist(uw);
            em.merge(u);

            return "User Work Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_work_Update(int uwId, String companyName, String joiningDate, String endingDate, String companyAddress, int userId) {

        try {

            Date jdate = new SimpleDateFormat("yyyy-MM-dd").parse(joiningDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(endingDate);

            User u = em.find(User.class, userId);
            Collection<UserWork> uwc = u.getUserWorkCollection();

            UserWork uw = em.find(UserWork.class, uwId);

            uw.setUwId(uwId);
            uw.setUserid(u);
            uw.setCompanyname(companyName);
            uw.setJoiningdate(jdate);
            uw.setEndingdate(edate);
            uw.setCompanyaddress(companyAddress);

            uwc.add(uw);
            u.setUserWorkCollection(uwc);

            em.persist(uw);
            em.merge(u);

            return "User Work Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String user_work_Delete(int uwId) {

        try {

            UserWork uw = em.find(UserWork.class, uwId);
            em.remove(uw);
            return "User Work Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserWork user_work_FindById(int uwId) {

        try {

            UserWork uw = em.find(UserWork.class, uwId);

            return uw;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserWork> user_work_ShowALl() {

        try {

            List<UserWork> userwork = em.createNamedQuery("UserWork.findAll")
                    .getResultList();

            if (userwork.isEmpty()) {
                return null;
            }

            return userwork;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UserWork> user_work_FindByUserId(int userId) {

        try {

            List<UserWork> userwork = em.createNamedQuery("UserWork.findByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            return userwork;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Groups
    @Override
    public Groups groupInsert(int groupId, String groupName, String description, int membersCount, boolean isDeleted, int adminId) {

        try {

            User u = em.find(User.class, adminId);
            Collection<Groups> gc = u.getGroupsCollection();

            Groups g = new Groups();

            g.setGroupid(groupId);
            g.setAdminid(u);
            g.setGroupname(groupName);
            g.setDescription(description);
            g.setMemberscount(membersCount);
            g.setIsDeleted(isDeleted);
            g.setCreateDate(new Date());

            gc.add(g);
            u.setGroupsCollection(gc);

            em.persist(g);
            em.merge(u);

            return g;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String groupUpdate(int groupId, String groupName, String description, int membersCount, boolean isDeleted, int adminId) {

        try {

            User u = em.find(User.class, adminId);
            Collection<Groups> gc = u.getGroupsCollection();

            Groups g = em.find(Groups.class, groupId);

            g.setGroupid(groupId);
            g.setAdminid(u);
            g.setGroupname(groupName);
            g.setDescription(description);
            g.setMemberscount(membersCount);
            g.setIsDeleted(isDeleted);

            gc.add(g);
            u.setGroupsCollection(gc);

            em.persist(g);
            em.merge(u);

            return "Group Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String groupDelete(int groupId) {

        try {

            Groups g = em.find(Groups.class, groupId);
            g.setIsDeleted(true);
            em.merge(g);
            return "Group Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Groups groupFindById(int groupId) {

        try {

            Groups g = em.find(Groups.class, groupId);

            return g;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Groups> groupShowAllByUser(int userId) {

        try {

            List<Groups> group = em.createNamedQuery("Groups.findAllByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            if (group.isEmpty()) {
                return null;
            }

            return group;

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public List<Groups> groupShowAll(int userId) {

        try {

            List<Groups> group = em.createNamedQuery("Groups.findAll")
                    .setParameter("userid", userId)
                    .getResultList();

            if (group.isEmpty()) {
                return null;
            }

            return group;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Groups> groupShowAll() {

        try {

            List<Groups> group = em.createNamedQuery("Groups.findAllInAdmin")
                    .getResultList();

            if (group.isEmpty()) {
                return null;
            }

            return group;

        } catch (Exception e) {

            return null;

        }
    }

    //Group_Members
    @Override
    public String group_member_Insert(int gmId, boolean isMember, String becameMember, int groupId, int memberId) {

        try {

            User u = em.find(User.class, memberId);
            Groups g = em.find(Groups.class, groupId);

            Collection<GroupMembers> gmuc = u.getGroupMembersCollection();
            Collection<GroupMembers> gmgc = g.getGroupMembersCollection();

            GroupMembers gm = new GroupMembers();

            gm.setGmId(gmId);
            gm.setIsMember(isMember);
            gm.setBecamemember(new Date());
            gm.setGroupid(g);
            gm.setMemberid(u);

            gmuc.add(gm);
            gmgc.add(gm);
            u.setGroupMembersCollection(gmuc);
            g.setGroupMembersCollection(gmgc);

            em.persist(g);
            em.merge(u);
            em.merge(g);

            return "Group Member Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String group_member_Update(int gmId, boolean isMember, String becameMember, int groupId, int memberId) {

        try {

            Date mdate = new SimpleDateFormat("yyyy-MM-dd").parse(becameMember);

            User u = em.find(User.class, memberId);
            Groups g = em.find(Groups.class, groupId);

            Collection<GroupMembers> gmuc = u.getGroupMembersCollection();
            Collection<GroupMembers> gmgc = g.getGroupMembersCollection();

            GroupMembers gm = em.find(GroupMembers.class, gmId);

            gm.setGmId(gmId);
            gm.setIsMember(isMember);
            gm.setBecamemember(mdate);
            gm.setGroupid(g);
            gm.setMemberid(u);

            gmuc.add(gm);
            gmgc.add(gm);
            u.setGroupMembersCollection(gmuc);
            g.setGroupMembersCollection(gmgc);

            em.persist(g);
            em.merge(u);
            em.merge(g);

            return "Group Member Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String group_member_Delete(int gmId) {

        try {

            GroupMembers g = em.find(GroupMembers.class, gmId);
            g.setIsMember(false);
            em.merge(g);
            return "Group Member Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GroupMembers group_member_FindById(int gmId) {

        try {

            GroupMembers g = em.find(GroupMembers.class, gmId);

            return g;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GroupMembers> group_member_FindByGroupid(int groupId) {

        try {

            List<GroupMembers> groupmember = em.createNamedQuery("GroupMembers.findAllMemberByGroupId")
                    .setParameter("groupid", groupId)
                    .getResultList();

            if (groupmember.isEmpty()) {
                return null;
            }

            return groupmember;

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public List<GroupMembers> group_member_checkGroupMember(int userId, int groupId) {

        try {

            List<GroupMembers> groupmember = em.createNamedQuery("GroupMembers.checkMember")
                    .setParameter("userid", userId)
                    .setParameter("groupid", groupId)
                    .getResultList();

            if (groupmember.isEmpty()) {
                return groupmember;
            }

            return groupmember;

        } catch (Exception e) {

            return null;

        }

    }

    @Override
    public List<GroupMembers> group_member_findGroupsByUserId(int userId) {
        try {

            List<GroupMembers> groupmember = em.createNamedQuery("GroupMembers.findGroupsByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            if (groupmember.isEmpty()) {
                return null;
            }

            return groupmember;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<GroupMembers> group_member_ShowAll() {

        try {

            List<GroupMembers> groupmember = em.createNamedQuery("GroupMembers.findAll")
                    .getResultList();

            if (groupmember.isEmpty()) {
                return null;
            }

            return groupmember;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Posts
    @Override
    public String postInsert(int postId, String post, String caption, boolean is_deleted, int likeCount, String postType, int userId, int groupId) {

        try {

            if (groupId != 0) {
                User u = em.find(User.class, userId);
                Groups g = em.find(Groups.class, groupId);

                Collection<Post> puc = u.getPostCollection();
                Collection<Post> guc = g.getPostCollection();

                Post p = new Post();

                p.setPostid(postId);
                p.setPost(post);
                p.setCaption(caption);
                p.setIsDeleted(is_deleted);
                p.setLikecount(likeCount);
                p.setPosttype(postType);
                p.setUploadDate(new Date());
                p.setUserid(u);
                p.setGroupid(g);

                puc.add(p);
                u.setPostCollection(puc);
                g.setPostCollection(guc);

                em.persist(p);
                em.merge(u);
                em.merge(g);

                return "Group Insert";

            } else {

                User u = em.find(User.class, userId);

                Collection<Post> puc = u.getPostCollection();

                Post p = new Post();

                p.setPostid(postId);
                p.setPost(post);
                p.setCaption(caption);
                p.setIsDeleted(is_deleted);
                p.setLikecount(likeCount);
                p.setPosttype(postType);
                p.setUploadDate(new Date());
                p.setUserid(u);

                puc.add(p);
                u.setPostCollection(puc);

                em.persist(p);
                em.merge(u);

                return "Post Insert";

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "false";
        }

    }

    @Override
    public String postUpdate(int postId, String post, String caption, boolean is_deleted, int likeCount, String postType, int userId, int groupId) {

        try {

            if (groupId != 0) {
                User u = em.find(User.class, userId);
                Groups g = em.find(Groups.class, groupId);

                Collection<Post> puc = u.getPostCollection();
                Collection<Post> guc = g.getPostCollection();

                Post p = em.find(Post.class, postId);

                p.setPostid(postId);
                p.setPost(post);
                p.setCaption(caption);
                p.setIsDeleted(is_deleted);
                p.setLikecount(likeCount);
                p.setPosttype(postType);
                p.setUploadDate(new Date());
                p.setUserid(u);
                p.setGroupid(g);

                puc.add(p);
                u.setPostCollection(puc);
                g.setPostCollection(guc);

                em.persist(p);
                em.merge(u);
                em.merge(g);

                return "Group Post Updated";

            } else {

                User u = em.find(User.class, userId);

                Collection<Post> puc = u.getPostCollection();

                Post p = em.find(Post.class, postId);

                p.setPostid(postId);
                p.setPost(post);
                p.setCaption(caption);
                p.setIsDeleted(is_deleted);
                p.setLikecount(likeCount);
                p.setPosttype(postType);
                p.setUploadDate(new Date());
                p.setUserid(u);

                puc.add(p);
                u.setPostCollection(puc);

                em.persist(p);
                em.merge(u);

                return "Post Updated";

            }

        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @Override
    public String postDelete(int postId) {

        try {

            Post p = em.find(Post.class, postId);
            p.setIsDeleted(true);
            em.merge(p);
            return "Post Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Post postFindById(int postId) {

        try {

            Post p = em.find(Post.class, postId);

            return p;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Post> postShowAllByUserId(int userId) {
        try {

            List<Post> posts = em.createNamedQuery("Post.postShowAllByUserId")
                    .setParameter("userid", userId)
                    .getResultList();

            if (posts.isEmpty()) {
                return null;
            }

            return posts;

        } catch (Exception e) {

            return null;

        }
    }

    @Override
    public List<Post> postShowAllByGroupId(int groupId) {
        try {

            List<Post> posts = em.createNamedQuery("Post.postShowAllByGroupId")
                    .setParameter("groupid", groupId)
                    .getResultList();

            if (posts.isEmpty()) {
                return null;
            }

            return posts;

        } catch (Exception e) {

            return null;

        }
    }

    @Override
    public List<Post> postShowAll() {

        try {

            List<Post> posts = em.createNamedQuery("Post.findAll")
                    .getResultList();

            if (posts.isEmpty()) {
                return null;
            }

            return posts;

        } catch (Exception e) {

            return null;

        }
    }

    //Likes
    @Override
    public String likeInsert(int likeId, boolean isRemoved, int postId, int senderId, int receiverId) {

        try {

            User s = em.find(User.class, senderId);
            User r = em.find(User.class, receiverId);
            Post p = em.find(Post.class, postId);

            Collection<Likes> lsc = s.getLikesCollection();
            Collection<Likes> lrc = r.getLikesCollection();
            Collection<Likes> lpc = p.getLikesCollection();

            Likes l = new Likes();

            l.setLikeid(likeId);
            l.setLikeDate(new Date());
            l.setIsRemoved(isRemoved);
            l.setPostid(p);
            l.setSenderid(s);
            l.setReceiverid(r);

            lsc.add(l);
            lrc.add(l);
            lpc.add(l);
            s.setLikesCollection(lsc);
            r.setLikesCollection(lrc);
            p.setLikesCollection(lpc);

            em.persist(l);
            em.merge(s);
            em.merge(r);
            em.merge(p);

            return "Like Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String likeUpdate(int likeId, boolean isRemoved, int postId, int senderId, int receiverId) {

        try {

            User s = em.find(User.class, senderId);
            User r = em.find(User.class, receiverId);
            Post p = em.find(Post.class, postId);

            Collection<Likes> lsc = s.getLikesCollection();
            Collection<Likes> lrc = r.getLikesCollection();
            Collection<Likes> lpc = p.getLikesCollection();

            Likes l = em.find(Likes.class, likeId);

            l.setLikeid(likeId);
            l.setIsRemoved(isRemoved);
            l.setPostid(p);
            l.setSenderid(s);
            l.setReceiverid(r);

            lsc.add(l);
            lrc.add(l);
            lpc.add(l);
            s.setLikesCollection(lsc);
            r.setLikesCollection(lrc);
            p.setLikesCollection(lpc);

            em.persist(l);
            em.merge(s);
            em.merge(r);
            em.merge(p);

            return "Like Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String likeDelete(int likeId) {

        try {

            Likes l = em.find(Likes.class, likeId);
            l.setIsRemoved(true);
            em.merge(l);
            return "Like Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Likes likeFindById(int likeId) {

        try {

            Likes l = em.find(Likes.class, likeId);

            return l;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Likes> likeShowAll() {

        try {

            List<Likes> likelist = em.createNamedQuery("Likes.findAll")
                    .getResultList();

            if (likelist.isEmpty()) {
                return null;
            }

            return likelist;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Likes> isLiked(int postId, int userId) {
        try {

            List<Likes> l = em.createNamedQuery("Likes.isliked")
                    .setParameter("postid", postId)
                    .setParameter("senderid", userId).getResultList();
            return l;

        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Likes> likeCount(int postId) {
        try {

            List<Likes> l = em.createNamedQuery("Likes.likecount")
                    .setParameter("postid", postId).getResultList();
            return l;

        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Friend_Request
    @Override
    public String friend_request_Insert(int frId, String status, int senderId, int receiverId) {

        try {

            User sender = em.find(User.class, senderId);
            User receiver = em.find(User.class, receiverId);

            Collection<FriendRequest> senderCollection = sender.getFriendRequestCollection();
            Collection<FriendRequest> receiverCollection = receiver.getFriendRequestCollection();

            FriendRequest fr = new FriendRequest();

            fr.setFrId(frId);
            // status as requested or accepted or deleted
            fr.setStatus(status);
            fr.setRequestdate(new Date());
            fr.setSenderid(sender);
            fr.setReceiverid(receiver);

            senderCollection.add(fr);
            receiverCollection.add(fr);

            sender.setFriendRequestCollection(senderCollection);
            receiver.setFriendRequestCollection(receiverCollection);

            em.persist(fr);
            em.merge(sender);
            em.merge(receiver);

//            String senderMessage = "Friend Request Send to "+ receiver.getFirstname() + " " 
//                                    + receiver.getLastname() + ".";
//            String receiverMessage = sender.getFirstname() + " " + sender.getLastname() + " " +
//                                        "has Requested to follow you.";
//            String description = "";
//            String targetURL = "null";
//            String activityType = "FriendRequest";
            return "Friend Request send ";

        } catch (Exception e) {

            return e.getMessage();

        }

    }

    @Override
    public String friend_request_Update(int frId, String status, int senderId, int receiverId) {

        try {

            User sender = em.find(User.class, senderId);
            User receiver = em.find(User.class, receiverId);

            Collection<FriendRequest> senderCollection = sender.getFriendRequestCollection();
            Collection<FriendRequest> receiverCollection = receiver.getFriendRequestCollection();

            FriendRequest fr = em.find(FriendRequest.class, frId);

            fr.setFrId(frId);
            fr.setStatus(status);
            fr.setReceiverid(receiver);
            fr.setSenderid(sender);

            senderCollection.add(fr);
            receiverCollection.add(fr);

            sender.setFriendRequestCollection(receiverCollection);
            receiver.setFriendRequestCollection(senderCollection);

            em.persist(fr);
            em.merge(sender);
            em.merge(receiver);

            return "Friend Request Updated";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String friend_request_Delete(int frId) {

        try {

            FriendRequest fr = em.find(FriendRequest.class, frId);

            fr.setStatus("deleted");

            em.merge(fr);

            return "Request Deleted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FriendRequest friend_request_FindById(int frId) {

        try {

            FriendRequest fr = em.find(FriendRequest.class, frId);

            return fr;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FriendRequest> friend_request_FindBySenderId(int senderId, String status) {

        try {

            List<FriendRequest> fr = em.createNamedQuery("FriendRequest.findBySenderId")
                    .setParameter("senderid", senderId)
                    .setParameter("status", status)
                    .getResultList();

            return fr;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<FriendRequest> friend_request_FindByReceiverId(int receiverId, String status) {

        try {

            List<FriendRequest> fr = em.createNamedQuery("FriendRequest.findByReceiverId")
                    .setParameter("receiverid", receiverId)
                    .setParameter("status", status)
                    .getResultList();

            return fr;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<FriendRequest> friend_request_ShowAll() {

        try {

            List<FriendRequest> friendRequests = em.createNamedQuery("FriendRequest.findAll")
                    .getResultList();

            if (friendRequests.isEmpty()) {
                return null;
            }

            return friendRequests;

        } catch (Exception e) {

            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<FriendRequest> friend_request_CheckStatus(int senderId, int receiverId) {
        try {

            List<FriendRequest> fr = em.createNamedQuery("FriendRequest.checkStatus")
                    .setParameter("senderid", senderId)
                    .setParameter("receiverid", receiverId)
                    .getResultList();

            return fr;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    //Friend_List
    @Override
    public String friend_list_Insert(int flId, boolean friendStatus, int userId, int friendId) {

        try {

            User user = em.find(User.class, userId);
            User friend = em.find(User.class, friendId);

            Collection<FriendList> userCollection = user.getFriendListCollection();
            Collection<FriendList> friendCollection = friend.getFriendListCollection();

            FriendList fl = new FriendList();

            fl.setFlId(flId);
            fl.setUserid(user);
            fl.setFriendid(friend);
            fl.setAcceptedDatetime(new Date());
            fl.setFriendStatus(friendStatus);

            userCollection.add(fl);
            friendCollection.add(fl);

            user.setFriendListCollection(userCollection);
            friend.setFriendListCollection(friendCollection);

            em.persist(fl);
            em.merge(user);
            em.merge(friend);

            return "Friend List Inserted";

        } catch (Exception e) {

            return e.getMessage();

        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String friend_list_Update(int flId, boolean friendStatus, int userId, int friendId) {

        try {

            User user = em.find(User.class, userId);
            User friend = em.find(User.class, friendId);

            Collection<FriendList> userCollection = user.getFriendListCollection();
            Collection<FriendList> friendCollection = friend.getFriendListCollection();

            FriendList fl = em.find(FriendList.class, flId);

            fl.setFlId(flId);
            fl.setUserid(user);
            fl.setFriendid(friend);
            fl.setFriendStatus(friendStatus);

            userCollection.add(fl);
            friendCollection.add(fl);

            user.setFriendListCollection(userCollection);
            friend.setFriendListCollection(friendCollection);

            em.persist(fl);
            em.merge(user);
            em.merge(friend);

            return "Friend List Updated";

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @Override
    public String friend_list_Delete(int flId) {

        try {

            FriendList fl = em.find(FriendList.class, flId);

            fl.setFriendStatus(false);

            em.merge(fl);

            return "Friend List deleted";

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @Override
    public FriendList friend_list_FindById(int flId) {

        try {

            return em.find(FriendList.class, flId);

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<FriendList> friend_list_ShowAllByUserId(int userId) {
        try {

            List<FriendList> fl = em.createNamedQuery("FriendList.findAllByUserId")
                    .setParameter("userId", userId)
                    .getResultList();

            return fl;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<FriendList> friend_list_ShowAll() {

        try {

            List<FriendList> fl = em.createNamedQuery("FriendList.findAll")
                    .getResultList();

            return fl;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }

    }

    //Events
    @Override
    public String eventInsert(int eventId, String eventName, String post, String eventStartDate, String eventEndDate, String eventInfo, String venue, String type, int fees, String mode, int guestCount, boolean is_removed, int hostId) {

        try {

            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(eventStartDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(eventEndDate);

            User host = em.find(User.class, hostId);

            Collection<Events> hostCollection = host.getEventsCollection();

            Events e = new Events();

            e.setEventid(eventId);
            e.setEventname(eventName);
            e.setPost(post);
            e.setEventstartdate(sdate);
            e.setEventenddate(edate);
            e.setEventinfo(eventInfo);
            e.setRegDate(new Date());
            e.setVenue(venue);
            e.setType(type);
            e.setFees(fees);
            e.setMode(mode);
            e.setGuestcount(guestCount);
            e.setIsRemoved(is_removed);
            e.setHostid(host);

            hostCollection.add(e);
            host.setEventsCollection(hostCollection);

            em.persist(e);
            em.merge(host);

            return "Event Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String eventUpdate(int eventId, String eventName, String post, String eventStartDate, String eventEndDate, String eventInfo, String venue, String type, int fees, String mode, int guestCount, boolean is_removed, int hostId) {

        try {

            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(eventStartDate);
            Date edate = new SimpleDateFormat("yyyy-MM-dd").parse(eventEndDate);

            User host = em.find(User.class, hostId);

            Collection<Events> hostCollection = host.getEventsCollection();

            Events e = em.find(Events.class, eventId);

            e.setEventid(eventId);
            e.setEventname(eventName);
            e.setPost(post);
            e.setEventstartdate(sdate);
            e.setEventenddate(edate);
            e.setEventinfo(eventInfo);
            e.setVenue(venue);
            e.setType(type);
            e.setFees(fees);
            e.setMode(mode);
            e.setGuestcount(guestCount);
            e.setIsRemoved(is_removed);
            e.setHostid(host);

            hostCollection.add(e);
            host.setEventsCollection(hostCollection);

            em.persist(e);
            em.merge(host);

            return "Event Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String eventDelete(int eventId) {

        try {

            Events e = em.find(Events.class, eventId);
            e.setIsRemoved(true);
            em.merge(e);

            return "Event Deleted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Events eventFindById(int eventId) {
        try {

            Events e = em.find(Events.class, eventId);

            return e;

        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Events> eventShowAll() {

        try {

            List<Events> events = em.createNamedQuery("Events.findAll")
                    .getResultList();

            if (events.isEmpty()) {
                return null;
            }

            return events;

        } catch (Exception e) {

            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Events> suggestedEvents(int Id) {
        try {

            List<Events> sevents = em.createNamedQuery("Events.suggestedevents")
                    .setParameter("userid", Id)
                    .getResultList();

            return sevents;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<Events> subscribedEvents(int Id) {
        try {

            List<Events> sevents = em.createNamedQuery("Events.subscribedevents")
                    .setParameter("userid", Id)
                    .getResultList();

            return sevents;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    @Override
    public List<Events> hostedEvents(int Id) {
        try {

            List<Events> hevents = em.createNamedQuery("Events.findByHostid")
                    .setParameter("hostid", Id)
                    .getResultList();

            return hevents;

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return null;

        }
    }

    //Events_UserCount
    @Override
    public String event_usercount_Insert(int euc_Id, boolean isIntrested, int eventId, int userId) {

        try {

            User u = em.find(User.class, userId);
            Events e = em.find(Events.class, eventId);

            Collection<EventUsercount> userCollection = u.getEventUsercountCollection();
            Collection<EventUsercount> eventCollection = e.getEventUsercountCollection();

            EventUsercount eu = new EventUsercount();

            eu.setEucId(euc_Id);
            eu.setIsInterested(isIntrested);
            eu.setEventid(e);
            eu.setUserid(u);

            userCollection.add(eu);
            eventCollection.add(eu);
            u.setEventUsercountCollection(userCollection);
            e.setEventUsercountCollection(eventCollection);

            em.persist(eu);
            em.merge(u);
            em.merge(e);

            return "Event User Count Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String event_usercount_Update(int euc_Id, boolean isIntrested, int eventId, int userId) {

        try {

            User u = em.find(User.class, userId);
            Events e = em.find(Events.class, eventId);

            Collection<EventUsercount> userCollection = u.getEventUsercountCollection();
            Collection<EventUsercount> eventCollection = e.getEventUsercountCollection();

            EventUsercount eu = em.find(EventUsercount.class, euc_Id);

            eu.setEucId(euc_Id);
            eu.setIsInterested(isIntrested);
            eu.setEventid(e);
            eu.setUserid(u);

            userCollection.add(eu);
            eventCollection.add(eu);
            u.setEventUsercountCollection(userCollection);
            e.setEventUsercountCollection(eventCollection);

            em.persist(eu);
            em.merge(u);
            em.merge(e);

            return "Event User Count Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String event_usercount_Delete(int euc_Id) {

        try {

            EventUsercount eu = em.find(EventUsercount.class, euc_Id);
            eu.setIsInterested(false);
            em.merge(eu);

            return "Event User Count Deleted";

        } catch (Exception e) {

            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventUsercount event_usercount_FindById(int euc_Id) {

        try {

            EventUsercount eu = em.find(EventUsercount.class, euc_Id);
            return eu;

        } catch (Exception e) {

            return null;
        }

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventUsercount> event_usercount_ShowAll() {

        try {

            List<EventUsercount> eventusers = em.createNamedQuery("EventUsercount.findAll")
                    .getResultList();

            if (eventusers.isEmpty()) {
                return null;
            }

            return eventusers;

        } catch (Exception e) {

            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EventUsercount> event_usercount_FindByEventId(int eventId) {

        try {

            List<EventUsercount> eventusers = em.createNamedQuery("EventUsercount.findByEventId")
                    .setParameter("eventid", eventId)
                    .getResultList();

            if (eventusers.isEmpty()) {
                return null;
            }

            return eventusers;

        } catch (Exception e) {

            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EventUsercount eventFindSubscribe(int eventId, int userId) {
        try {

            EventUsercount e = (EventUsercount) em.createNamedQuery("EventUsercount.findsubscribe")
                    .setParameter("eventid", eventId)
                    .setParameter("userid", userId)
                    .getSingleResult();
            return e;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Comments
    @Override
    public String commentsInsert(int commentId, String comment, boolean isRemoved, int postId, int senderId, int receiverId) {

        try {

            User s = em.find(User.class, senderId);
            User r = em.find(User.class, receiverId);
            Post p = em.find(Post.class, postId);

            Collection<Comments> csc = s.getCommentsCollection();
            Collection<Comments> crc = r.getCommentsCollection();
            Collection<Comments> cpc = p.getCommentsCollection();

            Comments c = new Comments();

            c.setCommentid(commentId);
            c.setComment(comment);
            c.setIsRemoved(isRemoved);
            c.setCommentDate(new Date());
            c.setPostid(p);
            c.setSenderid(s);
            c.setReceiverid(r);

            csc.add(c);
            crc.add(c);
            cpc.add(c);
            s.setCommentsCollection(csc);
            r.setCommentsCollection(crc);
            p.setCommentsCollection(cpc);

            em.persist(c);
            em.merge(s);
            em.merge(r);
            em.merge(p);

            return "Comment Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String commentsUpdate(int commentId, String comment, boolean isRemoved, int postId, int senderId, int receiverId) {

        try {

            User s = em.find(User.class, senderId);
            User r = em.find(User.class, receiverId);
            Post p = em.find(Post.class, postId);

            Collection<Comments> csc = s.getCommentsCollection();
            Collection<Comments> crc = r.getCommentsCollection();
            Collection<Comments> cpc = p.getCommentsCollection();

            Comments c = em.find(Comments.class, commentId);

            c.setCommentid(commentId);
            c.setComment(comment);
            c.setIsRemoved(isRemoved);
            c.setPostid(p);
            c.setSenderid(s);
            c.setReceiverid(r);

            csc.add(c);
            crc.add(c);
            cpc.add(c);
            s.setCommentsCollection(csc);
            r.setCommentsCollection(crc);
            p.setCommentsCollection(cpc);

            em.persist(c);
            em.merge(s);
            em.merge(r);
            em.merge(p);

            return "Comment Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String commentsdelete(int commentId) {

        try {

            Comments c = em.find(Comments.class, commentId);
            c.setIsRemoved(true);
            em.merge(c);
            return "Comment Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Comments commentsFindById(int commentId) {

        try {

            Comments c = em.find(Comments.class, commentId);

            return c;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comments> commentsShowAll() {

        try {

            List<Comments> commentlist = em.createNamedQuery("Comments.findAll")
                    .getResultList();

            if (commentlist.isEmpty()) {
                return null;
            }

            return commentlist;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Comments> commentsFindByPostId(int postId) {
        try {

            List<Comments> c = em.createNamedQuery("Comments.findByPostid")
                    .setParameter("postid", postId)
                    .getResultList();
            return c;

        } catch (Exception e) {
            return null;
        }
    }

    //Chats
    @Override
    public String chatInsert(int chatId, String message, boolean isDelevered, boolean isRead, boolean isDeleted, int senderId, int receiverId) {

        try {

            User sender = em.find(User.class, senderId);
            User receiver = em.find(User.class, receiverId);

            Collection<Chat> csc = sender.getChatCollection();
            Collection<Chat> crc = receiver.getChatCollection();

            Chat c = new Chat();

            c.setChatid(chatId);
            c.setMessage(message);
            c.setDatetime(new Date());
            c.setIsDeleted(isDeleted);
            c.setIsRead(isRead);
            c.setIsDelevered(isDelevered);
            c.setReceiverid(receiver);
            c.setSenderid(sender);

            csc.add(c);
            crc.add(c);
            sender.setChatCollection(csc);
            receiver.setChatCollection(crc);

            em.persist(c);
            em.merge(sender);
            em.merge(receiver);

            return "Chat Sent!";

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @Override
    public List<Chat> chatFindByReceiverId(int receiver, int sender) {

        try {

            List<Chat> c = em.createNamedQuery("Chat.findAllChatByReceiver")
                    .setParameter("senderid", sender)
                    .setParameter("receiverid", receiver)
                    .getResultList();
            return c;

        } catch (Exception e) {

            System.out.println(e);

            return null;
        }
    }

    @Override
    public List<Chat> chatFindBySenderId(int sender, int receiver) {

        try {

            List<Chat> c = em.createNamedQuery("Chat.findAllChatBySender")
                    .setParameter("sender", sender)
                    .setParameter("receiver", receiver)
                    .getResultList();
            return c;

        } catch (Exception e) {
            return null;
        }
    }

    //Ads_User
    @Override
    public String ads_user_Insert(int auId, String adsContent, String description, String link, String endDate, boolean isRemoved, boolean isExpired, int userId, int adsId) {

        try {

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//            LocalDateTime now = LocalDateTime.now();
//            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(now));
            User u = em.find(User.class, userId);
            Ads a = em.find(Ads.class, adsId);

            Collection<AdsUser> gmuc = u.getAdsUserCollection();
            Collection<AdsUser> gmac = a.getAdsUserCollection();

            AdsUser au = new AdsUser();

            au.setAuId(auId);
            au.setAdscontent(adsContent);
            au.setDescription(description);
            au.setLink(link);
            au.setStartdate(new Date());
            au.setEnddate(endDate);
            au.setIsRemoved(isRemoved);
            au.setIsExpried(isExpired);
            au.setUserid(u);
            au.setAdsid(a);

            gmuc.add(au);
            gmac.add(au);

            u.setAdsUserCollection(gmuc);
            a.setAdsUserCollection(gmac);

            em.persist(au);
            em.merge(u);
            em.merge(a);

            return "User Ads Inserted";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ads_user_Update(int auId, String adsContent, String description, String link, String endDate, boolean isRemoved, boolean isExpired, int userId, int adsId) {

        try {

//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
//            LocalDateTime now = LocalDateTime.now();
//            Date sdate = new SimpleDateFormat("yyyy-MM-dd").parse(dtf.format(now));
            User u = em.find(User.class, userId);
            Ads a = em.find(Ads.class, adsId);

            Collection<AdsUser> gmuc = u.getAdsUserCollection();
            Collection<AdsUser> gmac = a.getAdsUserCollection();

            AdsUser au = em.find(AdsUser.class, auId);

            au.setAuId(auId);
            au.setAdscontent(adsContent);
            au.setDescription(description);
            au.setLink(link);
            //au.setStartdate(sdate);
            au.setEnddate(endDate);
            au.setIsRemoved(isRemoved);
            au.setIsExpried(isExpired);
            au.setUserid(u);
            au.setAdsid(a);

            gmuc.add(au);
            gmac.add(au);

            u.setAdsUserCollection(gmuc);
            a.setAdsUserCollection(gmac);

            em.persist(au);
            em.merge(u);
            em.merge(a);

            return "User Ads Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String ads_user_Delete(int auId) {

        try {

            AdsUser au = em.find(AdsUser.class, auId);
            au.setIsRemoved(true);
            em.merge(au);
            return "User Ads Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AdsUser ads_user_FindById(int auId) {

        try {

            AdsUser au = em.find(AdsUser.class, auId);

            return au;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AdsUser> ads_user_ShowAll() {

        try {

            List<AdsUser> userads = em.createNamedQuery("AdsUser.findAll")
                    .getResultList();

            if (userads.isEmpty()) {
                return null;
            }

            return userads;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Ads
    @Override
    public void adsInsert(int adsId, String adsType, int price, String timeLimit, String description, boolean isRemoved) {

        try {

            Ads a = new Ads();

            a.setAdsId(adsId);
            a.setAdstype(adsType);
            a.setPrice(price);
            a.setTimelimit(timeLimit);
            a.setDescription(description);
            a.setIsRemoved(isRemoved);

            em.persist(a);

        } catch (Exception e) {
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String adsUpdate(int adsId, String adsType, int price, String timeLimit, String description, boolean isRemoved) {

        try {

            Ads a = em.find(Ads.class, adsId);

            a.setAdsId(adsId);
            a.setAdstype(adsType);
            a.setPrice(price);
            a.setTimelimit(timeLimit);
            a.setDescription(description);
            a.setIsRemoved(isRemoved);

            em.persist(a);

            return "Ads Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String adsDelete(int adsId) {

        try {

            Ads a = em.find(Ads.class, adsId);

            if (a.getIsRemoved() == true) {
                a.setIsRemoved(false);
                em.merge(a);

                return "Ad Added Again";
            }
            if (a.getIsRemoved() == false) {
                a.setIsRemoved(true);
                em.merge(a);

                return "Ad Removed";
            }

            return "null";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Ads adsFindById(int adsId) {

        try {

            Ads a = em.find(Ads.class, adsId);
            return a;

        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ads> adsShowAll() {

        try {
            List<Ads> ad = em.createNamedQuery("Ads.findAll")
                    .getResultList();

            if (ad.isEmpty()) {
                return null;
            }

            return ad;
        } catch (Exception e) {
            return null;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Activity_Feed
    @Override
    public String activity_feed_Insert(int afId, String description, String senderMsg, String receiverMsg, String targerURL, String activityType, boolean isRead, boolean isDeleted, int senderId, int receiverId, int groupId) {

        try {

            if (groupId != 0) {
                User s = em.find(User.class, senderId);
                User r = em.find(User.class, receiverId);
                Groups g = em.find(Groups.class, groupId);

                Collection<ActivityFeed> gmgc = g.getActivityFeedCollection();
                Collection<ActivityFeed> gmsc = s.getActivityFeedCollection();
                Collection<ActivityFeed> gmrc = r.getActivityFeedCollection();

                ActivityFeed af = new ActivityFeed();

                af.setAfId(afId);
                af.setDescription(description);
                af.setSendermessage(senderMsg);
                af.setReceivermessage(receiverMsg);
                af.setTargetUrl(targerURL);
                af.setActivityType(activityType);
                af.setActivityDate(new Date());
                af.setIsRead(isRead);
                af.setIsDeleted(isDeleted);
                af.setSenderid(s);
                af.setReceiverid(r);
                af.setGroupid(g);

                gmsc.add(af);
                gmrc.add(af);
                gmgc.add(af);
                s.setActivityFeedCollection(gmsc);
                r.setActivityFeedCollection(gmrc);
                g.setActivityFeedCollection(gmgc);

                em.persist(af);
                em.merge(s);
                em.merge(r);
                em.merge(g);

                return "Group Feed Inserted";
            } else {
                User s = em.find(User.class, senderId);
                User r = em.find(User.class, receiverId);

                Collection<ActivityFeed> gmsc = s.getActivityFeedCollection();
                Collection<ActivityFeed> gmrc = r.getActivityFeedCollection();

                ActivityFeed af = new ActivityFeed();

                af.setAfId(afId);
                af.setDescription(description);
                af.setSendermessage(senderMsg);
                af.setReceivermessage(receiverMsg);
                af.setTargetUrl(targerURL);
                af.setActivityType(activityType);
                af.setActivityDate(new Date());
                af.setIsRead(isRead);
                af.setIsDeleted(isDeleted);
                af.setSenderid(s);
                af.setReceiverid(r);

                gmsc.add(af);
                gmrc.add(af);
                s.setActivityFeedCollection(gmsc);
                r.setActivityFeedCollection(gmrc);

                em.persist(af);
                em.merge(s);
                em.merge(r);

                return "Activity Feed Inserted";
            }

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String activity_feed_Update(int afId, String description, String senderMsg, String receiverMsg, String targerURL, String activityType, boolean isRead, boolean isDeleted, int senderId, int receiverId, int groupId) {

        try {

            User s = em.find(User.class, senderId);
            User r = em.find(User.class, receiverId);
            Groups g = em.find(Groups.class, groupId);

            Collection<ActivityFeed> gmsc = s.getActivityFeedCollection();
            Collection<ActivityFeed> gmrc = r.getActivityFeedCollection();
            Collection<ActivityFeed> gmgc = g.getActivityFeedCollection();

            ActivityFeed af = em.find(ActivityFeed.class, afId);

            af.setAfId(afId);
            af.setDescription(description);
            af.setSendermessage(senderMsg);
            af.setReceivermessage(receiverMsg);
            af.setTargetUrl(targerURL);
            af.setActivityType(activityType);
            af.setIsRead(isRead);
            af.setIsDeleted(isDeleted);
            af.setSenderid(r);
            af.setReceiverid(r);
            af.setGroupid(g);

            gmsc.add(af);
            gmrc.add(af);
            gmgc.add(af);
            s.setActivityFeedCollection(gmsc);
            r.setActivityFeedCollection(gmrc);
            g.setActivityFeedCollection(gmgc);

            em.persist(af);
            em.merge(s);
            em.merge(r);
            em.merge(g);

            return "Activity Feed Updated";

        } catch (Exception e) {
            return e.getMessage();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String activity_feed_Delete(int afId) {

        try {

            ActivityFeed af = em.find(ActivityFeed.class, afId);
            af.setIsDeleted(true);
            em.merge(af);
            return "Activity Feed Removed";

        } catch (Exception e) {

            return e.getMessage();

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ActivityFeed activity_feed_FindById(int afId) {

        try {

            ActivityFeed af = em.find(ActivityFeed.class, afId);

            return af;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ActivityFeed> activity_feed_ByReceiverId(int receiverId) {
        try {

            List<ActivityFeed> feed = em.createNamedQuery("ActivityFeed.findMsgByReceiverId")
                    .setParameter("userid", receiverId)
                    .getResultList();

            if (feed.isEmpty()) {
                System.out.println("empty");
                return null;
            }

            return feed;

        } catch (Exception e) {

            return null;

        }
    }

    @Override
    public List<ActivityFeed> activity_feed_ShowAll() {

        try {

            List<ActivityFeed> feed = em.createNamedQuery("ActivityFeed.findAll")
                    .getResultList();

            if (feed.isEmpty()) {
                return null;
            }

            return feed;

        } catch (Exception e) {

            return null;

        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Login 
    @Override
    public User vibeLogin(String email, String password) {

        try {

            List<User> userList = em.createNamedQuery("User.findUserByEmail")
                    .setParameter("email", email)
                    .getResultList();

            if (!userList.isEmpty()) {

                for (User user : userList) {

                    boolean PassToHash = hashPassword.checkPassword(user.getPassword(), password);

                    if (!PassToHash) {
                        return null;
                    }

                    return user;
                }

            }

            return null;

        } catch (Exception e) {

            System.out.println(e.getMessage());
            return null;

        }
    }

   

}
