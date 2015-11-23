package com.moofuel.model;

/**
 * Created by MoOFueL on 19.11.2015.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Person.COLLECTION_NAME)
public class Person {
    public static final String COLLECTION_NAME = "persons";

    @Id
    private String id;

    private String fio;
    private int claimantCategory;

    public Person(String fio, int claimantCategory) {
        this.fio = fio;
        this.claimantCategory = claimantCategory;
    }

    @Override
    public String toString() {
        return "Person{" +
                "claimantCategory='" + claimantCategory + '\'' +
                ", fio='" + fio + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
