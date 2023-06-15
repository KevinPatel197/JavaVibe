package entity;

import entity.AdsUser;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-15T16:04:14")
@StaticMetamodel(Ads.class)
public class Ads_ { 

    public static volatile SingularAttribute<Ads, Boolean> isRemoved;
    public static volatile CollectionAttribute<Ads, AdsUser> adsUserCollection;
    public static volatile SingularAttribute<Ads, String> adstype;
    public static volatile SingularAttribute<Ads, Integer> price;
    public static volatile SingularAttribute<Ads, String> description;
    public static volatile SingularAttribute<Ads, Integer> adsId;
    public static volatile SingularAttribute<Ads, String> timelimit;

}