package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-11T23:09:55")
@StaticMetamodel(UserEducation.class)
public class UserEducation_ { 

    public static volatile SingularAttribute<UserEducation, Date> endingdate;
    public static volatile SingularAttribute<UserEducation, String> instituteaddress;
    public static volatile SingularAttribute<UserEducation, Date> joiningdate;
    public static volatile SingularAttribute<UserEducation, Integer> ueId;
    public static volatile SingularAttribute<UserEducation, User> userid;
    public static volatile SingularAttribute<UserEducation, String> institutename;

}