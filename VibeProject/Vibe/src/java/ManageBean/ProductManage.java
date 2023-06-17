/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package ManageBean;

import client.VibeClient;
import ejb.VibeSessionBeanLocal;
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
import javax.ws.rs.ClientErrorException;
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
    private String category;
    private String description;
    private String price;
    private String pimage;
    private String isactive;
    private List<Product> productlist;

    Part file;

    public ProductManage() {
    }

    @PostConstruct
    public void init() {
        this.isactive = "true";
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    private void clearAll() {
        setPname("");
        setDescription("");
        setCategory("");
        setPrice("");
        setPimage("");
    }

    private void clearUpdate() {
        this.pname = null;
        this.category = null;;
        this.description = null;
        this.price = null;
        this.pimage = null;
    }

    public List<Product> showAllProduct() {

        Response response = vibeClient.productShowAll(Response.class);
        ArrayList<Product> productArrayList = new ArrayList<>();
        GenericType<List<Product>> productGenericType = new GenericType<List<Product>>() {
        };

        productArrayList = (ArrayList<Product>) response.readEntity(productGenericType);

        return productArrayList;
    }

    public List<Product> showAllActiveProduct() {

        Response response = vibeClient.productShowActive(Response.class);
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

        String Value = vibeClient.productInsert(pname, category, description, price, pimage, isactive);
        System.out.println("Value : " + Value);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("ProductImage : ", pimage);

        clearUpdate();
        return "/admin/products.xhtml?faces-redirect=true";

    }

    public void productDelete(int id) {
        try {
            vibeClient.productDelete(String.valueOf(id));
        } catch (ClientErrorException e) {
            e.getMessage();
        }
    }

    public String productEdit(String id) {
        Response response = vibeClient.productFindById(Response.class, id);
        Product productArrayList = new Product();
        GenericType<Product> showAllproducts = new GenericType<Product>() {
        };
        productArrayList = (Product) response.readEntity(showAllproducts);
        
        pid = productArrayList.getPid().toString();
        pname = productArrayList.getPname();
        category = productArrayList.getCategory();
        description = productArrayList.getDescription();
        price = productArrayList.getPrice();
        pimage = productArrayList.getPimage();
        isactive = String.valueOf(productArrayList.getIsactive());

        return "/admin/editproduct.xhtml?faces-redirect=true";
    }

    public String productUpdate() throws IOException {

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

        String value = vibeClient.productUpdate(String.valueOf(pid), pname, category, description, price, pimage, isactive);
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();

        userSession.setAttribute("ProductImage", pimage);

        return "/admin/products.xhtml?faces-redirect=true";
    }

    public String getproductinfo(String id) {
        Response response = vibeClient.productFindById(Response.class, id);
        Product productArrayList = new Product();
        GenericType<Product> showAllproducts = new GenericType<Product>() {
        };
        productArrayList = (Product) response.readEntity(showAllproducts);
        pimage = productArrayList.getPimage();
        pid = productArrayList.getPid().toString();
        pname = productArrayList.getPname();
        price = productArrayList.getPrice();
        category = productArrayList.getCategory();
        description = productArrayList.getDescription();
        isactive = String.valueOf(productArrayList.getIsactive());

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
        HttpSession userSession = request.getSession();
        userSession.setAttribute("Pid : ", pid);
        userSession.setAttribute("Pname : ", pname);
        userSession.setAttribute("Price : ", price);
        userSession.setAttribute("Pimage : ", pimage);
        userSession.setAttribute("Desc : ", description);
        userSession.setAttribute("category : ", category);

        return "/web/marketplaceinfo.xhtml?faces-redirect=true";
    }

}
