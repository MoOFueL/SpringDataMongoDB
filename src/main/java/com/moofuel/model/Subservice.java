package com.moofuel.model;

/**
 * Created by MoOFueL on 19.11.2015.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Subservice.COLLECTION_NAME)
public class Subservice {
    public static final String COLLECTION_NAME = "subservices";

    @Id
    private String id;

    private int serviceCode;
    private String subserviceName;

    public Subservice(int serviceCode, String subserviceName) {
        this.serviceCode = serviceCode;
        this.subserviceName = subserviceName;
    }

    @Override
    public String toString() {
        return "Subservice{" +
                "serviceCode='" + serviceCode + '\'' +
                ", subserviceName='" + subserviceName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getserviceCode() {
        return serviceCode;
    }

    public void setserviceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSubserviceName() {
        return subserviceName;
    }

    public void setSubserviceName(String subserviceName) {
        this.subserviceName = subserviceName;
    }
}
