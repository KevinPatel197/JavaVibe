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
import org.primefaces.model.file.UploadedFile;
import org.primefaces.shaded.commons.io.FilenameUtils;

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

    private int catid;
    private String catname;
    private List<Categories> catList;

    private int productid;
    private String productname;
    private String title;
    private String description;
    private String price;

    private String image;

    private String isActive;

    //UploadedFile  file;
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

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
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

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
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

//    public UploadedFile getFile() {
//        return file;
//    }
//
//    public void setFile(UploadedFile  file) {
//        this.file = file;
//    }
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

    private void clearUpdate() {
        this.productid = 0;
        this.productname = null;
        this.title = null;
        this.description = null;
        this.price = null;
        this.catid = 0;
        this.image = null;
    }

    public String productInsert() throws IOException {

        if (file != null) {
            InputStream input = file.getInputStream();
            String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\ProfileImage\\";

            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            //String ext = FilenameUtils.getExtension(file.getName());
            //image = temp + "_IMG." + ext;
            image = "IMG_" + temp + ".jpg";
            Files.copy(input, new File(fullPath, image).toPath());
        }

        String value = vibeSessionBean.productInsert(productid, productname, catid, title, description, price, image, true);

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("ProductImg", image);

        clearUpdate();

        return "/admin/products.xhtml?faces-redirect=true";

    }

    public List<Products> getAllProducts() {

        Response response = vibeClient.productShowAll(Response.class);
        ArrayList<Products> prodArrayList = new ArrayList<>();
        GenericType<List<Products>> showAllProd = new GenericType<List<Products>>() {
        };
        prodArrayList = (ArrayList<Products>) response.readEntity(showAllProd);
        return prodArrayList;
    }

    public List<Categories> getAllCategories() {

        Response response = vibeClient.categoryShowAll(Response.class);
        ArrayList<Categories> catArrayList = new ArrayList<>();
        GenericType<List<Categories>> showAllcat = new GenericType<List<Categories>>() {
        };
        catArrayList = (ArrayList<Categories>) response.readEntity(showAllcat);
        return catArrayList;
    }

    public void deleteProduct(String id) {

        vibeClient.productDelete(id);

    }

}
