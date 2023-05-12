/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.EventUsercount;
import entity.Events;
import entity.FriendList;
import entity.UserContactInfo;
import entity.UserSkills;
import java.util.ArrayList;
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
@Named(value = "eventManagedBean")
@ApplicationScoped
public class EventManagedBean {

    @EJB
    private VibeSessionBeanLocal vibe;
    private VibeClient vibeClient = new VibeClient();

    public VibeClient getVibeClient() {
        return vibeClient;
    }

    public void setVibeClient(VibeClient vibeClient) {
        this.vibeClient = vibeClient;
    }
    
    
    private String eventid;
    private String hostid;
    private String eventname;
    private String post;
    private String eventstartdate;
    private String eventenddate;
    private String eventinfo;
    private String venue;
    private String type;
    private String fees;
    private String mode;
    private String guestcount;
    private boolean is_removed;
    
    private String euc_id;
    private boolean isInterested;
    
    private String rName;

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
    

    public String getEuc_id() {
        return euc_id;
    }

    public void setEuc_id(String euc_id) {
        this.euc_id = euc_id;
    }

    public boolean isIsInterested() {
        return isInterested;
    }

    public void setIsInterested(boolean isInterested) {
        this.isInterested = isInterested;
    }

    public VibeSessionBeanLocal getVibe() {
        return vibe;
    }

    public void setVibe(VibeSessionBeanLocal vibe) {
        this.vibe = vibe;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getHostid() {
        return hostid;
    }

    public void setHostid(String hostid) {
        this.hostid = hostid;
    }

    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getEventstartdate() {
        return eventstartdate;
    }

    public void setEventstartdate(String eventstartdate) {
        this.eventstartdate = eventstartdate;
    }

    public String getEventenddate() {
        return eventenddate;
    }

    public void setEventenddate(String eventenddate) {
        this.eventenddate = eventenddate;
    }

    public String getEventinfo() {
        return eventinfo;
    }

    public void setEventinfo(String eventinfo) {
        this.eventinfo = eventinfo;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGuestcount() {
        return guestcount;
    }

    public void setGuestcount(String guestcount) {
        this.guestcount = guestcount;
    }

    public boolean getIs_removed() {
        return is_removed;
    }

    public void setIs_removed(boolean is_removed) {
        this.is_removed = is_removed;
    }
    
    //private methods
    
    private void Activity(String senderMsg, String receiverMsg, String targerURL, String senderId, String receiverId) {
        
        vibeClient.activity_feed_Insert("0", "desc", senderMsg, receiverMsg, targerURL, "Event", "false", "false", senderId, receiverId, "0");
        
    }
    
    /**
     * Creates a new instance of EventManagedBean
     */
    public EventManagedBean() {
    }
    
    public List<Events> showSubscribedEvents() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.subscribedEvents(Response.class, userSessions.getAttribute("UuserId").toString());
        
        ArrayList<Events> subeventsArrayList = new ArrayList<>();
        GenericType<List<Events>> eventsGenericType = new GenericType<List<Events>>(){};
        
        subeventsArrayList = (ArrayList<Events>)response.readEntity(eventsGenericType);
        
        return subeventsArrayList;
    }
    
    public List<Events> showSuggestedEvents() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.suggestedEvents(Response.class, userSessions.getAttribute("UuserId").toString());
        
        ArrayList<Events> sugeventsArrayList = new ArrayList<>();
        GenericType<List<Events>> eventsGenericType = new GenericType<List<Events>>(){};
        
        sugeventsArrayList = (ArrayList<Events>)response.readEntity(eventsGenericType);
        
        return sugeventsArrayList;
    }
    
    public List<EventUsercount> showeventmembers(String Id) {
        
        Response response = vibeClient.event_usercount_FindByEventId(Response.class, Id);
        
        ArrayList<EventUsercount> membersArrayList = new ArrayList<>();
        GenericType<List<EventUsercount>> membersGenericType = new GenericType<List<EventUsercount>>(){};
        
        membersArrayList = (ArrayList<EventUsercount>)response.readEntity(membersGenericType);
        
        return membersArrayList;
    }
    
    public List<Events> showHostedEvents() {
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession(); 
        
        Response response = vibeClient.hostedEvents(Response.class, userSessions.getAttribute("UuserId").toString());
        
        ArrayList<Events> sugeventsArrayList = new ArrayList<>();
        GenericType<List<Events>> eventsGenericType = new GenericType<List<Events>>(){};
        
        sugeventsArrayList = (ArrayList<Events>)response.readEntity(eventsGenericType);
        
        return sugeventsArrayList;
    }
    
    public String getEventInfo(String eventId) {
         
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSessions = requests.getSession();
        
        Response response = vibeClient.eventFindById(Response.class, eventId);
        Events eventArrayList = new Events();
        GenericType<Events> showAllinfo  = new GenericType<Events>() {
        };
        eventArrayList = (Events) response.readEntity(showAllinfo);
        eventid = eventArrayList.getEventid().toString();
        hostid = eventArrayList.getHostid().getFirstname() + " " + eventArrayList.getHostid().getLastname();
        eventname = eventArrayList.getEventname();
        post = eventArrayList.getPost();
        eventstartdate = eventArrayList.getEventstartdate().toString();
        eventenddate = eventArrayList.getEventenddate().toString();
        eventinfo = eventArrayList.getEventinfo();
        venue = eventArrayList.getVenue();
        type = eventArrayList.getType();
        fees = String.valueOf(eventArrayList.getFees());
        mode = eventArrayList.getMode();
        guestcount = String.valueOf(eventArrayList.getGuestcount());
        is_removed = eventArrayList.getIsRemoved();
        
        //this.isInterested = true;
        Response res = vibeClient.eventFindSubscribe(Response.class, eventId, userSessions.getAttribute("UuserId").toString());
        EventUsercount ucountArrayList = new EventUsercount();
        GenericType<EventUsercount> showAllcount  = new GenericType<EventUsercount>() {
        };
        ucountArrayList = (EventUsercount) res.readEntity(showAllcount);
        if(ucountArrayList == null)
        {
            euc_id = null;
            isInterested = false;
        }
        else
        {
            euc_id = ucountArrayList.getEucId().toString();
            isInterested = ucountArrayList.getIsInterested();            
        }
        
        System.out.println("isinterested : " + isInterested);
        
        return "/web/eventinfo.xhtml?faces-redirect=true";
    }
    
    public String getHostedEventInfo(String Id) {
         
        
        Response response = vibeClient.eventFindById(Response.class, Id);
        Events eventArrayList = new Events();
        GenericType<Events> showAllinfo  = new GenericType<Events>() {
        };
        eventArrayList = (Events) response.readEntity(showAllinfo);
        eventid = eventArrayList.getEventid().toString();
        hostid = eventArrayList.getHostid().getFirstname() + " " + eventArrayList.getHostid().getLastname();
        eventname = eventArrayList.getEventname();
        post = eventArrayList.getPost();
        eventstartdate = eventArrayList.getEventstartdate().toString();
        eventenddate = eventArrayList.getEventenddate().toString();
        eventinfo = eventArrayList.getEventinfo();
        venue = eventArrayList.getVenue();
        type = eventArrayList.getType();
        fees = String.valueOf(eventArrayList.getFees());
        mode = eventArrayList.getMode();
        guestcount = String.valueOf(eventArrayList.getGuestcount());
        is_removed = eventArrayList.getIsRemoved();
        
        return "/web/hosteventinfo.xhtml?faces-redirect=true";
    }
    
    public String showEventInfo(String Id) {
         
        
        Response response = vibeClient.eventFindById(Response.class, Id);
        Events eventArrayList = new Events();
        GenericType<Events> showAllinfo  = new GenericType<Events>() {
        };
        eventArrayList = (Events) response.readEntity(showAllinfo);
        eventid = eventArrayList.getEventid().toString();
        hostid = eventArrayList.getHostid().getFirstname() + " " + eventArrayList.getHostid().getLastname();
        eventname = eventArrayList.getEventname();
        post = eventArrayList.getPost();
        eventstartdate = eventArrayList.getEventstartdate().toString();
        eventenddate = eventArrayList.getEventenddate().toString();
        eventinfo = eventArrayList.getEventinfo();
        venue = eventArrayList.getVenue();
        type = eventArrayList.getType();
        fees = String.valueOf(eventArrayList.getFees());
        mode = eventArrayList.getMode();
        guestcount = String.valueOf(eventArrayList.getGuestcount());
        is_removed = eventArrayList.getIsRemoved();
        
        return "/admin/eventinfo.xhtml?faces-redirect=true";
    }
    
    
    
    
    
//    public String getInterest(String eventid){
//        
//        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
//                    .getExternalContext().getRequest();
//        HttpSession userSessions = requests.getSession();
//        
//        Response res = vibeClient.eventFindSubscribe(Response.class, eventid, userSessions.getAttribute("UuserId").toString());
//        EventUsercount ecountArrayList = new EventUsercount();
//        GenericType<EventUsercount> showAllcount  = new GenericType<EventUsercount>() {
//        };
//        ecountArrayList = (EventUsercount) res.readEntity(showAllcount);
//        euc_id = ecountArrayList.getEucId().toString();
//        isInterested = ecountArrayList.getIsInterested();
//        
//        return "/web/profile-events.xhtml?faces-redirect=true";
//    }
    
    public String subscribeEvent() {
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            Response res = vibeClient.eventFindSubscribe(Response.class, eventid, userSessions.getAttribute("UuserId").toString());
            EventUsercount ecountArrayList = new EventUsercount();
            GenericType<EventUsercount> showAllcount  = new GenericType<EventUsercount>() {
            };
            ecountArrayList = (EventUsercount) res.readEntity(showAllcount);
            if(ecountArrayList != null)
            {
                euc_id =  ecountArrayList.getEucId().toString();
                eventid = ecountArrayList.getEventid().getEventid().toString();
                vibeClient.event_usercount_Update(euc_id, "true", eventid, userSessions.getAttribute("UuserId").toString());
            }else
            {
                vibeClient.event_usercount_Insert("0", "true", eventid, userSessions.getAttribute("UuserId").toString());
            }
            
            Response response = vibeClient.eventFindById(Response.class, eventid);
            Events eventArrayList = new Events();
            GenericType<Events> showAllinfo  = new GenericType<Events>() {
            };
            eventArrayList = (Events) response.readEntity(showAllinfo);
            rName = eventArrayList.getHostid().getFirstname() + " " + eventArrayList.getHostid().getLastname();
            hostid = eventArrayList.getHostid().getUserid().toString();
        
            String senderMsg = "You joined " + rName + "'s Event.";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " joined your Event.";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), hostid);
            
            return "/web/profile-events.xhtml?faces-redirect=true";
            
            
        } catch (ClientErrorException e) {
            
            return e.getMessage();
            
        }
    }
    
    public String UnsubscribeEvent(String eventid, String euc_id) {
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            vibeClient.event_usercount_Update(euc_id, "0", eventid, userSessions.getAttribute("UuserId").toString());
            
            Response response = vibeClient.eventFindById(Response.class, eventid);
            Events eventArrayList = new Events();
            GenericType<Events> showAllinfo  = new GenericType<Events>() {
            };
            eventArrayList = (Events) response.readEntity(showAllinfo);
            rName = eventArrayList.getHostid().getFirstname() + " " + eventArrayList.getHostid().getLastname();
            hostid = eventArrayList.getHostid().getUserid().toString();
        
            String senderMsg = "You left " + rName + "'s Event.";
            String receiverMsg = userSessions.getAttribute("UfullName").toString() + 
                                " unsubscribed your Event.";
            String targetUrl = "ok";

            Activity(senderMsg, receiverMsg, targetUrl, userSessions.getAttribute("UuserId").toString(), hostid);
            
            return "/web/profile-events.xhtml?faces-redirect=true";
            
            
        } catch (ClientErrorException e) {
            
            return e.getMessage();
            
        }
    }
    
    public String updateHostEvent() {
        
        try {
            
            HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
            HttpSession userSessions = requests.getSession();
            
            if(eventid == null)
            {
                vibeClient.eventInsert("0", userSessions.getAttribute("UuserId").toString(), eventname, post, eventstartdate, eventenddate, eventinfo, venue, type, fees, mode, "1", mode);
            }
            else{
                vibeClient.eventUpdate(eventid, userSessions.getAttribute("UuserId").toString(), eventname, post, eventstartdate, eventenddate, eventinfo, venue, type, fees, mode, guestcount, mode);
            }
               
            return "/web/profile-events.xhtml?faces-redirect=true";
              
        } catch (ClientErrorException e) {
            System.out.println(e);
            return e.getMessage();
        }
    }
    
    
}
