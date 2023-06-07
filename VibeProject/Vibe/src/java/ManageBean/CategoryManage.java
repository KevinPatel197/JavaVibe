/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Category;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "categoryManage")
@ApplicationScoped
public class CategoryManage {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String catid;
    private String catname;
    private String description;
    private String isactive;
    private List<Category> catlist;
    
    public CategoryManage() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public List<Category> getCatlist() {
        return catlist;
    }

    public void setCatlist(List<Category> catlist) {
        this.catlist = catlist;
    }
    
    private void clearUpdate(){
        this.catname=null;
        this.description=null;
    }
    
    public List<Category> showAllCategory(){
       
        Response response = vibeClient.categoryShowAll(Response.class);
        ArrayList<Category> catArrayList = new ArrayList<>();
        GenericType<List<Category>> showAllcat  = new GenericType<List<Category>>() {
        };
        catArrayList = (ArrayList<Category>)response.readEntity(showAllcat);
        return catArrayList;
    }
    
    public List<Category> categoryShowActive(){
       
        Response response = vibeClient.categoryShowActive(Response.class, "true");
        ArrayList<Category> catArrayList = new ArrayList<>();
        GenericType<List<Category>> showAllcat  = new GenericType<List<Category>>() {
        };
        catArrayList = (ArrayList<Category>)response.readEntity(showAllcat);
        return catArrayList;
    }
    
    public String categoryInsert(){
        vibeClient.categoryInsert(catname, description, "true");
        clearUpdate();
        return "/admin/categories.xhtml?faces-redirect=true";
    }
    
    public void categoryDelete(int id){
        try {
            vibeClient.categoryDelete(String.valueOf(id));
        } catch (ClientErrorException e) {
            e.getMessage();
        }
    }
    
}
