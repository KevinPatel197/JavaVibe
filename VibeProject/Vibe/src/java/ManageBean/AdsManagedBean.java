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
import entity.Likes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@Named(value = "adsManagedBean")
@ApplicationScoped
public class AdsManagedBean {
    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String adsid;
    private String adstype;
    private String price;
    private String timelimit;
    private String description;
    private String isremoved;
    
    private String auid;
    private String userid;
    private String adscontent;
    private String udescription;
    private String link;
    private String startdate;
    private String enddate;
    private String uisremoved;
    private String isexpired;

    public String getAuid() {
        return auid;
    }

    public void setAuid(String auid) {
        this.auid = auid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getAdscontent() {
        return adscontent;
    }

    public void setAdscontent(String adscontent) {
        this.adscontent = adscontent;
    }

    public String getUdescription() {
        return udescription;
    }

    public void setUdescription(String udescription) {
        this.udescription = udescription;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUisremoved() {
        return uisremoved;
    }

    public void setUisremoved(String uisremoved) {
        this.uisremoved = uisremoved;
    }

    public String getIsexpired() {
        return isexpired;
    }

    public void setIsexpired(String isexpired) {
        this.isexpired = isexpired;
    }
    
    

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getAdsid() {
        return adsid;
    }

    public void setAdsid(String adsid) {
        this.adsid = adsid;
    }

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

    public String getIsremoved() {
        return isremoved;
    }

    public void setIsremoved(String isremoved) {
        this.isremoved = isremoved;
    }
    
    

    /**
     * Creates a new instance of AdsManagedBean
     */
    public AdsManagedBean() {
    }
    
    public List<Ads> getallAds()
    {
        Response response = vibeClient.adsShowAll(Response.class);
        ArrayList<Ads> adsArrayList = new ArrayList<>();
        GenericType<List<Ads>> showAllads  = new GenericType<List<Ads>>() {
        };
        adsArrayList = (ArrayList<Ads>)response.readEntity(showAllads);
        return adsArrayList;
    }
    
    public String getadsinfo(String adsId)
    {
        Response response = vibeClient.adsFindById(Response.class, adsId);
        Ads adsArrayList = new Ads();
        GenericType<Ads> showAllads  = new GenericType<Ads>() {
        };
        adsArrayList = (Ads)response.readEntity(showAllads);
        adsid = adsArrayList.getAdsId().toString();
        adstype = adsArrayList.getAdstype();
        price = String.valueOf(adsArrayList.getPrice());
        timelimit = adsArrayList.getTimelimit().toString();
        description = adsArrayList.getDescription();
        isremoved = String.valueOf(adsArrayList.getIsRemoved());
        
        return "/web/adsform.xhtml?faces-redirect=true";
    }
    
    public String activateads() {
        
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            
//            LocalDate today = java.time.LocalDate.now();
//            System.out.println("Today : " + today);
//            
//            LocalDate newdate = today.plusMonths(Long.parseLong(timelimit));
//            
//            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
//            enddate = dateFormat.format(newdate);  
//            System.out.println("End date : " + enddate);
            
            vibeClient.ads_user_Insert("0", adscontent, udescription, link, "2022-08-30", "false", "false", userSessions.getAttribute("UuserId").toString(), adsid);
               
            return "/web/home.xhtml?faces-redirect=true";
              
        } catch (ClientErrorException e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
    
    public List<AdsUser> showallads()
    {
            Response response = vibeClient.ads_user_ShowAll(Response.class);
            ArrayList<AdsUser> adsArrayList = new ArrayList<>();
            GenericType<List<AdsUser>> showAllads  = new GenericType<List<AdsUser>>() {
            };
            adsArrayList = (ArrayList<AdsUser>)response.readEntity(showAllads);
            return adsArrayList;
    }
    
}
