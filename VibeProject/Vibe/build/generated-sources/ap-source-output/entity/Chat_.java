package entity;

import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T19:58:04")
@StaticMetamodel(Chat.class)
public class Chat_ { 

    public static volatile SingularAttribute<Chat, Date> datetime;
    public static volatile SingularAttribute<Chat, User> senderid;
    public static volatile SingularAttribute<Chat, User> receiverid;
    public static volatile SingularAttribute<Chat, Integer> chatid;
    public static volatile SingularAttribute<Chat, Boolean> isDeleted;
    public static volatile SingularAttribute<Chat, Boolean> isDelevered;
    public static volatile SingularAttribute<Chat, Boolean> isRead;
    public static volatile SingularAttribute<Chat, String> message;

}