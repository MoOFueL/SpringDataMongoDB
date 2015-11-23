package com.moofuel.testData;

import com.moofuel.model.*;
import org.springframework.data.mongodb.core.MongoOperations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by MoOFueL on 21.11.2015.
 */
public class TestDataGenerator {


    //Заявки
    public static void claimFill(MongoOperations mongoOperations) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 100; i++) {
            Claim claim = new Claim(i, i, i, Calendar.getInstance().getTime(), String.valueOf(i));
            mongoOperations.save(claim);
        }
    }

    //Департаменты
    public static void departmentFill(MongoOperations mongoOperations) {

        for (int i = 0; i < 100; i++) {
            Department dep = new Department(String.valueOf(i), "Department " + i);
            mongoOperations.save(dep);
        }

    }

    //Люди
    public static void personFill(MongoOperations mongoOperations) {

        for (int i = 0; i < 100; i++) {
            int claimantCategory = (int) (Math.random() * 5 + 1);
            Person person = new Person("FIO " + i, claimantCategory);
            mongoOperations.save(person);
        }
    }

    //Подуслуги
    public static void subserviceFill(MongoOperations mongoOperations) {

        for (int i = 0; i < 100; i++) {
            Subservice subService = new Subservice(i, "Subservice" + i);
            mongoOperations.save(subService);
        }
    }

    //Услуги
    public static void serviceFill(MongoOperations mongoOperations) {


        List<Subservice> listForQuery = null;
        ArrayList<Subservice> arrayListForSaving = new ArrayList<Subservice>();
        listForQuery = mongoOperations.findAll(Subservice.class);

        for (int i = 1; i <= 100; i++) {
            arrayListForSaving.add(listForQuery.get((int) (Math.random() * 99 + 1)));
            Service service = new Service(i, i, "ClaimantCategory" + i, arrayListForSaving);
            mongoOperations.save(service);
            arrayListForSaving.clear();
        }
    }

    //Записи об обращениях
    public static void applicationFill(MongoOperations mongoOperation) {

        int claimId;
        Date claimDate;
        int codeOfForm;
        String claimStatus;
        String fio;
        int claimantCategory;
        int serviceCode;
        String subserviceName;
        String departmentName;

        List<Claim> claimList = mongoOperation.findAll(Claim.class);
        List<Person> personList = mongoOperation.findAll(Person.class);
        List<Department> departmentList = mongoOperation.findAll(Department.class);
        List<Service> serviceList = mongoOperation.findAll(Service.class);
        List<Subservice> subserviceList = mongoOperation.findAll(Subservice.class);

        for (int i = 0; i < mongoOperation.findAll(Claim.class).size(); i++) {
            claimId = claimList.get(i).getClaimId();
            claimDate = claimList.get(i).getClaimDate();
            codeOfForm = serviceList.get(i).getCodeOfForm();
            claimStatus = claimList.get(i).getClaimStatus();
            fio = personList.get(i).getFio();
            claimantCategory = personList.get(i).getClaimantCategory();
            serviceCode = serviceList.get(i).getServiceCode();
            subserviceName = subserviceList.get(i).getSubserviceName();
            departmentName = departmentList.get(i).getDepartmentName();

            Applicate applicate = new Applicate(claimId, claimDate, codeOfForm, claimStatus, fio, claimantCategory, serviceCode, subserviceName, departmentName);
            mongoOperation.save(applicate);
        }
    }

}

