package entity;

import entity.State;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T14:06:51")
@StaticMetamodel(Country.class)
public class Country_ { 

    public static volatile SingularAttribute<Country, String> sortname;
    public static volatile SingularAttribute<Country, Boolean> isactive;
    public static volatile CollectionAttribute<Country, User> userCollection;
    public static volatile SingularAttribute<Country, String> countryname;
    public static volatile CollectionAttribute<Country, State> stateCollection;
    public static volatile SingularAttribute<Country, Integer> phonecode;
    public static volatile SingularAttribute<Country, Integer> countryid;

}