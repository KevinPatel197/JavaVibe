package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-13T10:27:44")
@StaticMetamodel(UserWork.class)
public class UserWork_ { 

    public static volatile SingularAttribute<UserWork, String> companyaddress;
    public static volatile SingularAttribute<UserWork, Date> endingdate;
    public static volatile SingularAttribute<UserWork, String> companyname;
    public static volatile SingularAttribute<UserWork, Integer> uwId;
    public static volatile SingularAttribute<UserWork, Date> joiningdate;
    public static volatile SingularAttribute<UserWork, User> userid;

}