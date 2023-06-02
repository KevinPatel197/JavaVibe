package entity;

import entity.Groups;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-02T14:06:51")
@StaticMetamodel(GroupMembers.class)
public class GroupMembers_ { 

    public static volatile SingularAttribute<GroupMembers, Integer> gmId;
    public static volatile SingularAttribute<GroupMembers, Groups> groupid;
    public static volatile SingularAttribute<GroupMembers, Boolean> isMember;
    public static volatile SingularAttribute<GroupMembers, Date> becamemember;
    public static volatile SingularAttribute<GroupMembers, User> memberid;

}