package entity;

import entity.State;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-17T09:29:54")
@StaticMetamodel(City.class)
public class City_ { 

    public static volatile SingularAttribute<City, String> cityname;
    public static volatile SingularAttribute<City, Boolean> isactive;
    public static volatile SingularAttribute<City, State> stateid;
    public static volatile CollectionAttribute<City, User> userCollection;
    public static volatile SingularAttribute<City, Integer> cityid;

}