/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.UserEducation;
import entity.UserWork;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author pooja
 */

@Named(value = "usereduManagedBean")
@ApplicationScoped

public class UserEduManagedBean {
    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String ue_id;
    private String userid;
    private String institutename;
    private String joiningdate;
    private String endingdate;
    private String instituteaddress;

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getUe_id() {
        return ue_id;
    }

    public void setUe_id(String ue_id) {
        this.ue_id = ue_id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getInstitutename() {
        return institutename;
    }

    public void setInstitutename(String institutename) {
        this.institutename = institutename;
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

    public String getInstituteaddress() {
        return instituteaddress;
    }

    public void setInstituteaddress(String instituteaddress) {
        this.instituteaddress = instituteaddress;
    }
    
    public UserEduManagedBean(){
        
    }
    
    public List<UserEducation> showUserEduInfo() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.user_education_FindByUserId(Response.class, userSessions.getAttribute("UuserId").toString());
        ArrayList<UserEducation> eduArrayList = new ArrayList<>();
        GenericType<List<UserEducation>> showAllwork  = new GenericType<List<UserEducation>>() {
        };
        eduArrayList = (ArrayList<UserEducation>) response.readEntity(showAllwork);
        System.out.println(eduArrayList);
        return eduArrayList;
    }
    
    public List<UserEducation> showUserEdu(String Id){
        Response response = vibeClient.user_education_FindByUserId(Response.class, Id);
        ArrayList<UserEducation> eduArrayList = new ArrayList<>();
        GenericType<List<UserEducation>> showAllwork  = new GenericType<List<UserEducation>>() {
        };
        eduArrayList = (ArrayList<UserEducation>) response.readEntity(showAllwork);
        System.out.println(eduArrayList);
        return eduArrayList;
    }
    
    public String editUserEdu(String Id) {
        
        Response response = vibeClient.user_education_FindById(Response.class, Id);
        UserEducation eduArrayList = new UserEducation();
        GenericType<UserEducation> showAlledu  = new GenericType<UserEducation>() {
        };
        eduArrayList = (UserEducation) response.readEntity(showAlledu);
        ue_id = eduArrayList.getUeId().toString();
        userid = eduArrayList.getUserid().getUserid().toString();
        institutename = eduArrayList.getInstitutename();
        joiningdate = eduArrayList.getJoiningdate().toString();
        endingdate = eduArrayList.getEndingdate().toString();
        instituteaddress = eduArrayList.getInstituteaddress();     
        
        
        return "/web/editedu.xhtml?faces-redirect=true";
    }
    
  
    public String updateUserEdu() {
        
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            if(ue_id == null)
            {
                vibeClient.user_education_Insert("0", institutename, joiningdate, endingdate, instituteaddress, userSessions.getAttribute("UuserId").toString());
            }
            else{
                vibeClient.user_education_Update(ue_id, institutename, joiningdate, endingdate, instituteaddress, userid);
            }
               
            return "/web/profile.xhtml?faces-redirect=true";
              
        } catch (ClientErrorException e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
    
}


