package entity;

import entity.City;
import entity.Country;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-03T12:40:45")
@StaticMetamodel(State.class)
public class State_ { 

    public static volatile SingularAttribute<State, Integer> stateid;
    public static volatile SingularAttribute<State, Boolean> isactive;
    public static volatile CollectionAttribute<State, User> userCollection;
    public static volatile CollectionAttribute<State, City> cityCollection;
    public static volatile SingularAttribute<State, String> statename;
    public static volatile SingularAttribute<State, Country> countryid;

}