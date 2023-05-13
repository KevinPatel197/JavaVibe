package entity;

import entity.Events;
import entity.User;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-13T10:27:44")
@StaticMetamodel(EventUsercount.class)
public class EventUsercount_ { 

    public static volatile SingularAttribute<EventUsercount, Events> eventid;
    public static volatile SingularAttribute<EventUsercount, Integer> eucId;
    public static volatile SingularAttribute<EventUsercount, User> userid;
    public static volatile SingularAttribute<EventUsercount, Boolean> isInterested;

}