package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-03T12:40:45")
@StaticMetamodel(FriendList.class)
public class FriendList_ { 

    public static volatile SingularAttribute<FriendList, Boolean> friendStatus;
    public static volatile SingularAttribute<FriendList, User> friendid;
    public static volatile SingularAttribute<FriendList, Date> acceptedDatetime;
    public static volatile SingularAttribute<FriendList, User> userid;
    public static volatile SingularAttribute<FriendList, Integer> flId;

}