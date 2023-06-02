/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Categories;
import entity.Products;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
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
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "productManagedBean")
@ApplicationScoped
public class ProductManagedBean {

    
    
    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
    
    private String catid;
    private String catname;
    private List<Categories> catList;
    
    private String productid;
    private String productname;
    private String title;
    private String description;
    private String price;
    private String image;
    private String isActive;
    
    Part file;
    
    private List<Products> productlist;
    
    public ProductManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        this.isActive = "true";
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

    public List<Categories> getCatList() {
        return catList;
    }

    public void setCatList(List<Categories> catList) {
        this.catList = catList;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    

    public List<Products> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<Products> productlist) {
        this.productlist = productlist;
    }
    
    
    private void clearUpdate(){
        this.productid=null;
        this.productname=null;
        this.title=null;
        this.description=null;
        this.price=null;
        this.catid=null;
        this.image=null;
    }
    
    
    public void productInsert(){
        
        HttpServletRequest requests = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession adminSession = requests.getSession();
        
        try {
            
            if (file == null) {
                
                vibeClient.productInsert("0", productname, catid, title, description, price, image, isActive);
            }
            else
            {
                if (file.getSubmittedFileName().contains(".jpg") || file.getSubmittedFileName().contains(".jpeg") || file.getSubmittedFileName().contains(".png")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\ProductImg\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    image = "IMG_" + temp + ".jpg";
                    Files.copy(input, new File(fullPath, image).toPath());

                    vibeClient.productInsert("0", productname, catid, title, description, price, image, isActive);
                }
                
                if (file.getSubmittedFileName().contains(".mp4") || file.getSubmittedFileName().contains(".mov") || file.getSubmittedFileName().contains(".mkv") || file.getSubmittedFileName().contains(".avi")) {

                    InputStream input = file.getInputStream();
                    String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\ProductImg\\";

                    Random random = new Random();
                    StringBuilder sb = new StringBuilder();

                    sb.append(random.nextInt(9) + 1);
                    for (int i = 0; i < 11; i++) {
                        sb.append(random.nextInt(10));
                    }
                    String temp = sb.toString();

                    image = "VID_" + temp + ".mp4";
                    Files.copy(input, new File(fullPath, image).toPath());

                    vibeClient.productInsert("0", productname, catid, title, description, price, image, isActive);
                
                }
            }
        } catch (ClientErrorException | IOException e) {
            System.out.println(e.getMessage());
        }

     
    }
    
    public List<Products> getAllProducts(){
       
        Response response = vibeClient.productShowAll(Response.class);
        ArrayList<Products> prodArrayList = new ArrayList<>();
        GenericType<List<Products>> showAllProd  = new GenericType<List<Products>>() {
        };
        prodArrayList = (ArrayList<Products>)response.readEntity(showAllProd);
        return prodArrayList;
    }
    
     public List<Categories> getAllCategories(){
       
        Response response = vibeClient.categoryShowAll(Response.class);
        ArrayList<Categories> catArrayList = new ArrayList<>();
        GenericType<List<Categories>> showAllcat  = new GenericType<List<Categories>>() {
        };
        catArrayList = (ArrayList<Categories>)response.readEntity(showAllcat);
        return catArrayList;
    }
    
     
    public String ProductFindById(String id) {
        Response response = vibeClient.productFindById(Response.class, id);
        GenericType<Products> productSearch = new GenericType<Products>(){};
        Products p = response.readEntity(productSearch);
        
        productid = p.getProductid().toString();
        productname = p.getProductname();
        title = p.getTitle();
        description = p.getDescription();
        price = p.getPrice();
        image = p.getImage();
        catid = p.getCategories().toString();
        
        return "/admin/product-detail.xhtml?faces-redirect=true";
    }
    
   
    public void deleteProduct(String id) {
        
        vibeClient.productDelete(id);
        
    }
    
    
    
}
