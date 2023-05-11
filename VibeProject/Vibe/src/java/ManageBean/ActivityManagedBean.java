/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.ActivityFeed;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author LENOVO
 */
@Named(value = "activityManagedBean")
@ApplicationScoped
public class ActivityManagedBean {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;

    private VibeClient vibeClient = new VibeClient();
    
    @PostConstruct
    public void init() {
        
    }
    
    
    /**
     * Creates a new instance of NotificationManagedBean
     */
    public ActivityManagedBean() {
    }

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public VibeClient getVibeClient() {
        return vibeClient;
    }

    public void setVibeClient(VibeClient vibeClient) {
        this.vibeClient = vibeClient;
    }
    
    
    public List<ActivityFeed> showAllNotification() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.activity_feed_ByReceiverId(Response.class, userSessions.getAttribute("UuserId").toString());
        ArrayList<ActivityFeed> activityFeedArrayList = new ArrayList<>();
        GenericType<List<ActivityFeed>> activityFeedGenericType = new GenericType<List<ActivityFeed>>(){};
        
        activityFeedArrayList = (ArrayList<ActivityFeed>)response.readEntity(activityFeedGenericType);
        
        return activityFeedArrayList;
        
    }
    
}
