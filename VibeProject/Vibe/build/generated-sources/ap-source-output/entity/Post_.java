package entity;

import entity.Comments;
import entity.Groups;
import entity.Likes;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-17T19:41:11")
@StaticMetamodel(Post.class)
public class Post_ { 

    public static volatile SingularAttribute<Post, String> post;
    public static volatile SingularAttribute<Post, Date> uploadDate;
    public static volatile SingularAttribute<Post, Boolean> isDeleted;
    public static volatile SingularAttribute<Post, String> posttype;
    public static volatile SingularAttribute<Post, Groups> groupid;
    public static volatile SingularAttribute<Post, String> caption;
    public static volatile SingularAttribute<Post, Integer> postid;
    public static volatile CollectionAttribute<Post, Comments> commentsCollection;
    public static volatile CollectionAttribute<Post, Likes> likesCollection;
    public static volatile SingularAttribute<Post, Integer> likecount;
    public static volatile SingularAttribute<Post, User> userid;

}