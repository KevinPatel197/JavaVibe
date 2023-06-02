package entity;

import entity.Categories;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T14:06:51")
@StaticMetamodel(Products.class)
public class Products_ { 

    public static volatile SingularAttribute<Products, String> image;
    public static volatile SingularAttribute<Products, Integer> productid;
    public static volatile SingularAttribute<Products, String> price;
    public static volatile SingularAttribute<Products, String> productname;
    public static volatile SingularAttribute<Products, String> description;
    public static volatile SingularAttribute<Products, Categories> categories;
    public static volatile SingularAttribute<Products, String> title;
    public static volatile SingularAttribute<Products, Boolean> isActive;

}