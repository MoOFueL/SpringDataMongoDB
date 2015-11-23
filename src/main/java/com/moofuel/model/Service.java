package com.moofuel.model;

/**
 * Created by MoOFueL on 19.11.2015.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = Service.COLLECTION_NAME)
public class Service {
    public static final String COLLECTION_NAME = "services";

    @Id
    private String id;

    private int serviceCode;
    private int codeOfForm;
    private String claimantCategory;
    private List<Subservice> subServiceList;


    public Service(int serviceCode, int codeOfForm, String claimantCategory, List<Subservice> subServiceList) {
        this.serviceCode = serviceCode;
        this.codeOfForm = codeOfForm;
        this.claimantCategory = claimantCategory;
        this.subServiceList = subServiceList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(int serviceCode) {
        this.serviceCode = serviceCode;
    }

    public int getCodeOfForm() {
        return codeOfForm;
    }

    public void setCodeOfForm(int codeOfForm) {
        this.codeOfForm = codeOfForm;
    }

    public String getClaimantCategory() {
        return claimantCategory;
    }

    public void setClaimantCategory(String claimantCategory) {
        this.claimantCategory = claimantCategory;
    }

    public List<Subservice> getSubServiceList() {
        return subServiceList;
    }

    public void setSubServiceList(List<Subservice> subServiceList) {
        this.subServiceList = subServiceList;
    }
}
