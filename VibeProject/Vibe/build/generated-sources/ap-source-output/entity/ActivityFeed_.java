package entity;

import entity.Groups;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T19:58:04")
@StaticMetamodel(ActivityFeed.class)
public class ActivityFeed_ { 

    public static volatile SingularAttribute<ActivityFeed, User> senderid;
    public static volatile SingularAttribute<ActivityFeed, Date> activityDate;
    public static volatile SingularAttribute<ActivityFeed, User> receiverid;
    public static volatile SingularAttribute<ActivityFeed, Boolean> isDeleted;
    public static volatile SingularAttribute<ActivityFeed, String> sendermessage;
    public static volatile SingularAttribute<ActivityFeed, Groups> groupid;
    public static volatile SingularAttribute<ActivityFeed, String> receivermessage;
    public static volatile SingularAttribute<ActivityFeed, Boolean> isRead;
    public static volatile SingularAttribute<ActivityFeed, String> description;
    public static volatile SingularAttribute<ActivityFeed, String> activityType;
    public static volatile SingularAttribute<ActivityFeed, Integer> afId;
    public static volatile SingularAttribute<ActivityFeed, String> targetUrl;

}