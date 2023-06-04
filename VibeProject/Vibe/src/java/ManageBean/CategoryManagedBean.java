/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Categories;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.servlet.http.HttpSession;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "categoryManagedBean")
@ApplicationScoped
public class CategoryManagedBean {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String catid;
    private String catname;
    private String isActive;
    
    
    public CategoryManagedBean() {
    }

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    public List<Categories> getAllCategories(){
       
        Response response = vibeClient.categoryShowAll(Response.class);
        ArrayList<Categories> catArrayList = new ArrayList<>();
        GenericType<List<Categories>> showAllcat  = new GenericType<List<Categories>>() {
        };
        catArrayList = (ArrayList<Categories>)response.readEntity(showAllcat);
        return catArrayList;
    }
    
//    public List<Categories> getAllActiveCat(){
//        
//        Response response = vibeClient.categoryShowActive(Response.class);
//         ArrayList<Categories> catArrayList = new ArrayList<>();
//        GenericType<List<Categories>> showAllcat  = new GenericType<List<Categories>>() {
//        };
//        catArrayList = (ArrayList<Categories>)response.readEntity(showAllcat);
//        return catArrayList;
//    }
    
    public String insertCategories(){
        vibeClient.categoryInsert("0", catname, "true");
        return "/admin/categories.xhtml?faces-redirect=true";
    }
    
    public void deletecat(int id){
        try {
            vibeClient.categoryDelete(String.valueOf(id));
        } catch (ClientErrorException e) {
            e.getMessage();
        }
    }
    
}
