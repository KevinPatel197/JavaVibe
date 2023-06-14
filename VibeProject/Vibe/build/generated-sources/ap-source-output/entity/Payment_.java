package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-14T20:51:13")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, String> cvv;
    public static volatile SingularAttribute<Payment, String> expmonth;
    public static volatile SingularAttribute<Payment, String> expyear;
    public static volatile SingularAttribute<Payment, Integer> payid;
    public static volatile SingularAttribute<Payment, String> cardnumber;
    public static volatile SingularAttribute<Payment, String> cardholder;

}