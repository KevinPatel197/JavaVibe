/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.User;
import entity.UserContactInfo;
import entity.UserWork;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

@Named(value = "userworkManagedBean")
@ApplicationScoped

public class UserWorkManagedBean {
    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String uw_id;
    private String userid;
    private String companyname;
    private String joiningdate;
    private String endingdate;
    private String companyaddress;

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getUw_id() {
        return uw_id;
    }

    public void setUw_id(String uw_id) {
        this.uw_id = uw_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getJoiningdate() {
        return joiningdate;
    }

    public void setJoiningdate(String joiningdate) {
        this.joiningdate = joiningdate;
    }

    public String getEndingdate() {
        return endingdate;
    }

    public void setEndingdate(String endingdate) {
        this.endingdate = endingdate;
    }

    public String getCompanyaddress() {
        return companyaddress;
    }

    public void setCompanyaddress(String companyaddress) {
        this.companyaddress = companyaddress;
    }
    
    public UserWorkManagedBean()
    {
        
    }
    
    public List<UserWork> showUserWorkInfo() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.user_work_FindByUserId(Response.class, userSessions.getAttribute("UuserId").toString());
        ArrayList<UserWork> workArrayList = new ArrayList<>();
        GenericType<List<UserWork>> showAllwork  = new GenericType<List<UserWork>>() {
        };
        workArrayList = (ArrayList<UserWork>) response.readEntity(showAllwork);
        System.out.println(workArrayList);
        return workArrayList;
    }
    
    public List<UserWork> showUserWork(String Id){
        Response response = vibeClient.user_work_FindByUserId(Response.class, Id);
        ArrayList<UserWork> workArrayList = new ArrayList<>();
        GenericType<List<UserWork>> showAllwork  = new GenericType<List<UserWork>>() {
        };
        workArrayList = (ArrayList<UserWork>) response.readEntity(showAllwork);
        System.out.println(workArrayList);
        return workArrayList; 
    }
    
    public String editUserWork(String Id) {
        
        Response response = vibeClient.user_work_FindById(Response.class, Id);
        UserWork workArrayList = new UserWork();
        GenericType<UserWork> showAllwork  = new GenericType<UserWork>() {
        };
        workArrayList = (UserWork) response.readEntity(showAllwork);
        uw_id = workArrayList.getUwId().toString();
        userid = workArrayList.getUserid().getUserid().toString();
        companyname = workArrayList.getCompanyname();
        joiningdate = workArrayList.getJoiningdate().toString();
        endingdate = workArrayList.getEndingdate().toString();
        companyaddress = workArrayList.getCompanyaddress();     
        
        
        return "/web/editwork.xhtml?faces-redirect=true";
    }
    
  
    public String updateUserWork() {
        
        try {
            
                HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
                HttpSession userSessions = requests.getSession();
                
                if(uw_id == null)
                {
                    vibeClient.user_work_Insert("0", companyname, joiningdate, endingdate, companyaddress, userSessions.getAttribute("UuserId").toString());
                }
                else
                {
                    vibeClient.user_work_Update(uw_id, companyname, joiningdate, endingdate, companyaddress, userid);
                }
               return "/web/profile.xhtml?faces-redirect=true";
              
        } catch (ClientErrorException e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
    
}
