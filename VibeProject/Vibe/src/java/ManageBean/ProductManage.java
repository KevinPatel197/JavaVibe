/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
import entity.Category;
import entity.Product;
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
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kevin
 */
@Named(value = "productManage")
@ApplicationScoped
public class ProductManage {

    @EJB
    private VibeSessionBeanLocal vibeSessionBean;
    private final VibeClient vibeClient = new VibeClient();
   
    private String pid;
    private String pname;
    private String catid;
    private String description;
    private String price;
    private String pimage;
    private String isactive;
    private List<Product> productlist;
    private List<Category> catList;
    
    Part file;
    
    
    public ProductManage() {
    }
    
    @PostConstruct
    public void init() {
        this.isactive="true";
    }

    public VibeSessionBeanLocal getVibeSessionBean() {
        return vibeSessionBean;
    }

    public void setVibeSessionBean(VibeSessionBeanLocal vibeSessionBean) {
        this.vibeSessionBean = vibeSessionBean;
    }
    
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
    
    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
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

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public List<Product> getProductlist() {
        return productlist;
    }

    public void setProductlist(List<Product> productlist) {
        this.productlist = productlist;
    }

    public List<Category> getCatList() {
        return catList;
    }

    public void setCatList(List<Category> catList) {
        this.catList = catList;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    private void clearAll() {
        setPname("");
        setCatid("");
        setDescription("");
        setPrice("");
        setPimage("");
    }
    
    private void clearUpdate(){
        this.pname=null;
        this.catid=null;
        this.description=null;
        this.price=null;
        this.pimage=null;
    }
    
    public List<Product> showAllProduct(){
        
        Response response = vibeClient.productShowAll(Response.class);
        ArrayList<Product> productArrayList = new ArrayList<>();
        GenericType<List<Product>> productGenericType = new GenericType<List<Product>>() {
        };

        productArrayList = (ArrayList<Product>) response.readEntity(productGenericType);

        return productArrayList;
    }
    
    public String productInsert() throws IOException {
        
        if (file != null) {
            
            InputStream input = file.getInputStream();
            String fullPath = "\\C:\\Users\\kevin\\OneDrive\\Desktop\\EEProject\\VibeProject\\Vibe\\web\\Images\\ProductImage\\";

            Random random = new Random();
            StringBuilder sb = new StringBuilder();

            sb.append(random.nextInt(9) + 1);
            for (int i = 0; i < 11; i++) {
                sb.append(random.nextInt(10));
            }
            String temp = sb.toString();

            pimage = "IMG_" + temp + ".jpg";
            Files.copy(input, new File(fullPath, pimage).toPath());
        }
        
        String value = vibeClient.productInsert(String.valueOf(pid), pname, String.valueOf(catid), description, price, pimage, isactive);
        
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                    .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("ProductImage : ", pimage);
        
        clearUpdate();
        
        return "/admin/products.xhtml?faces-redirect=true";
    }
    
}
