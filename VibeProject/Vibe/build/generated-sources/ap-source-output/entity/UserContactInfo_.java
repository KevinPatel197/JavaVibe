package entity;

import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-18T22:56:37")
@StaticMetamodel(UserContactInfo.class)
public class UserContactInfo_ { 

    public static volatile SingularAttribute<UserContactInfo, String> fbLink;
    public static volatile SingularAttribute<UserContactInfo, String> website;
    public static volatile SingularAttribute<UserContactInfo, Integer> uciId;
    public static volatile SingularAttribute<UserContactInfo, String> instaLink;
    public static volatile SingularAttribute<UserContactInfo, String> bio;
    public static volatile SingularAttribute<UserContactInfo, String> language;
    public static volatile SingularAttribute<UserContactInfo, User> userid;
    public static volatile SingularAttribute<UserContactInfo, String> intrestedIn;

}