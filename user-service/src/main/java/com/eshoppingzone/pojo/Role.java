package com.eshoppingzone.pojo;

import javax.persistence.Embeddable;

@Embeddable
public interface Role {

    public String Customer="customer";
    public String Merchant="merchant";
    public String DeliveryAgent="deliveryagent";

}
