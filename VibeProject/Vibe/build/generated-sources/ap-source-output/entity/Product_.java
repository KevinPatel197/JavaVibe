package entity;

import entity.Category;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-07T23:59:43")
@StaticMetamodel(Product.class)
public class Product_ { 

    public static volatile SingularAttribute<Product, Category> catid;
    public static volatile SingularAttribute<Product, String> pimage;
    public static volatile SingularAttribute<Product, String> pname;
    public static volatile SingularAttribute<Product, String> price;
    public static volatile SingularAttribute<Product, Boolean> isactive;
    public static volatile SingularAttribute<Product, String> description;
    public static volatile SingularAttribute<Product, Integer> pid;

}