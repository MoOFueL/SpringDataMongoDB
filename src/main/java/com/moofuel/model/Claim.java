package com.moofuel.model;

/**
 * Created by MoOFueL on 19.11.2015.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = Claim.COLLECTION_NAME)
public class Claim {
    public static final String COLLECTION_NAME = "claims";

    @Id
    private String id;
    private int claimId;
    private int serviceId;
    private int claimantId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date claimDate;

    private String claimStatus;

    public Claim(int claimId, int serviceId, int claimantId, Date claimDate, String claimStatus) {
        this.claimId = claimId;
        this.serviceId = serviceId;
        this.claimantId = claimantId;
        this.claimDate = claimDate;
        this.claimStatus = claimStatus;
    }

    @Override
    public String toString() {
        return "Claim{" +
                "claimStatus='" + claimStatus + '\'' +
                ", claimDate='" + claimDate + '\'' +
                ", claimantId='" + claimantId + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", claimId='" + claimId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getClaimId() {
        return claimId;
    }

    public void setClaimId(int claimId) {
        this.claimId = claimId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getClaimantId() {
        return claimantId;
    }

    public void setClaimantId(int claimantId) {
        this.claimantId = claimantId;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }
}
