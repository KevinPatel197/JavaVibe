package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T19:58:04")
@StaticMetamodel(FriendRequest.class)
public class FriendRequest_ { 

    public static volatile SingularAttribute<FriendRequest, User> senderid;
    public static volatile SingularAttribute<FriendRequest, User> receiverid;
    public static volatile SingularAttribute<FriendRequest, Integer> frId;
    public static volatile SingularAttribute<FriendRequest, Date> requestdate;
    public static volatile SingularAttribute<FriendRequest, String> status;

}