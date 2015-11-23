package com.moofuel.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by MoOFueL on 22.11.2015.
 */

@Document(collection = Applicate.COLLECTION_NAME)
public class Applicate {

    public static final String COLLECTION_NAME = "applicates";
    @Id
    private String id;
    private int claimId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date claimDate;
    private int codeOfForm;
    private String claimStatus;
    private String fio;
    private int claimantCategory;
    private int serviceCode;
    private String subserviceName;
    private String departmentName;

    public Applicate(int claimId, Date claimDate, int codeOfForm, String claimStatus, String fio, int claimantCategory, int serviceCode, String subserviceName, String departmentName) {
        this.claimId = claimId;
        this.claimDate = claimDate;
        this.codeOfForm = codeOfForm;
        this.claimStatus = claimStatus;
        this.fio = fio;
        this.claimantCategory = claimantCategory;
        this.serviceCode = serviceCode;
        this.subserviceName = subserviceName;
        this.departmentName = departmentName;
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

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public int getCodeOfForm() {
        return codeOfForm;
    }

    public void setCodeOfForm(int codeOfForm) {
        this.codeOfForm = codeOfForm;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getClaimantCategory() {
        return claimantCategory;
    }

    public void setClaimantCategory(int claimantCategory) {
        this.claimantCategory = claimantCategory;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getSubserviceName() {
        return subserviceName;
    }

    public void setSubserviceName(String subserviceName) {
        this.subserviceName = subserviceName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
