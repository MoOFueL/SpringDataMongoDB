package com.moofuel.javaFX;

import com.moofuel.config.SpringMongoConfig;
import com.moofuel.model.Applicate;
import com.moofuel.model.ClaimsAggregator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


/**
 * Created by MoOFueL on 22.11.2015.
 */
public class BarChartHistogram {

    public void display(int claimantCategory) {

        //Функция агрегации, возвращающая количество заявок по дням по указанному типу заявителя claimantCategory
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
        TypedAggregation<Applicate> aggregation = newAggregation(Applicate.class,
                match(Criteria.where("claimantCategory").is(claimantCategory)),
                project("claimDate")
                        .andExpression("dayOfMonth(claimDate)").as("day")
                        .andExpression("month(claimDate)").as("month")
                        .andExpression("year(claimDate)").as("year"),
                group(fields().and("day").and("month").and("year")).first("claimDate").as("claimDate")
                        .count().as("count"));
        AggregationResults<ClaimsAggregator> groupResults = mongoOperations.aggregate(aggregation, ClaimsAggregator.class);
        List<ClaimsAggregator> result = groupResults.getMappedResults();

        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String, Number> сlaimsAggregatorBarChart = new BarChart<>(x, y);
        сlaimsAggregatorBarChart.setTitle("Заявки c типом заявителя: " + claimantCategory);
        XYChart.Series series = new XYChart.Series();
        ObservableList<XYChart.Data> dataSet = FXCollections.observableArrayList();

        //Вставка данных из агрегации в BarChart
        for (ClaimsAggregator aResult : result) {
            dataSet.add(new XYChart.Data(aResult.getClaimDate(), aResult.getCount()));
        }
        series.setData(dataSet);
        сlaimsAggregatorBarChart.getData().add(series);

        //Инициализация сцены
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Histogram");
        window.setMinWidth(1000);
        Scene scene = new Scene(сlaimsAggregatorBarChart, 600, 600);
        window.setScene(scene);
        window.showAndWait();
    }
}

