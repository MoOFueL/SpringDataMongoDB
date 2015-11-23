package com.moofuel.core;

/**
 * Created by MoOFueL on 19.11.2015.
 */

import com.moofuel.config.SpringMongoConfig;
import com.moofuel.javaFX.BarChartHistogram;
import com.moofuel.model.Applicate;
import com.moofuel.testData.TestDataGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;


public class App extends Application {

    ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
    MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");

    public static void main(String[] args) {

        //Генераторы тестовых данных
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        MongoOperations mongoOperations = (MongoOperations) ctx.getBean("mongoTemplate");
        TestDataGenerator.claimFill(mongoOperations);
        TestDataGenerator.departmentFill(mongoOperations);
        TestDataGenerator.personFill(mongoOperations);
        TestDataGenerator.subserviceFill(mongoOperations);
        TestDataGenerator.serviceFill(mongoOperations);
        TestDataGenerator.applicationFill(mongoOperations);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Макет таблицы, которая отображает записи по заявкам
        TableView<Applicate> table;
        Scene scene;
        Stage window = primaryStage;
        window.setTitle("Отчет по полученным заявкам");

        TableColumn<Applicate, String> claimIdColumn = new TableColumn<>("Идентификатор заявки");
        claimIdColumn.setMinWidth(50);
        claimIdColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("claimId"));

        TableColumn<Applicate, String> claimDateColumn = new TableColumn<>("Дата заявки");
        claimDateColumn.setMinWidth(50);
        claimDateColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("claimDate"));

        TableColumn<Applicate, String> codeOfFormColumn = new TableColumn<>("Идентификатор формы");
        codeOfFormColumn.setMinWidth(50);
        codeOfFormColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("codeOfForm"));

        TableColumn<Applicate, String> claimStatusColumn = new TableColumn<>("Статус заявки");
        claimStatusColumn.setMinWidth(50);
        claimStatusColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("claimStatus"));

        TableColumn<Applicate, String> fioColumn = new TableColumn<>("ФИО");
        fioColumn.setMinWidth(50);
        fioColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("fio"));

        TableColumn<Applicate, String> claimantCategoryColumn = new TableColumn<>("Тип заявителя");
        claimantCategoryColumn.setMinWidth(50);
        claimantCategoryColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("claimantCategory"));

        TableColumn<Applicate, String> serviceIdColumn = new TableColumn<>("Идентификатор услуги");
        serviceIdColumn.setMinWidth(50);
        serviceIdColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("serviceCode"));

        TableColumn<Applicate, String> subserviceNameColumn = new TableColumn<>("Название подуслуги");
        subserviceNameColumn.setMinWidth(50);
        subserviceNameColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("subserviceName"));

        TableColumn<Applicate, String> departmentNameColumn = new TableColumn<>("Название ведомства");
        departmentNameColumn.setMinWidth(50);
        departmentNameColumn.setCellValueFactory(new PropertyValueFactory<Applicate, String>("departmentName"));

        //Инициализация таблицы
        table = new TableView<>();
        table.setMinHeight(550);
        table.getItems().addAll(getRecords(mongoOperations));
        table.getColumns().addAll(claimIdColumn, claimDateColumn, codeOfFormColumn, claimStatusColumn, fioColumn, claimantCategoryColumn, serviceIdColumn, subserviceNameColumn, departmentNameColumn);

        //Элементы сцены
        TextField claimantCategoryInput = new TextField();
        claimantCategoryInput.setPromptText("Тип заявителя");

        Button barChartButton = new Button("Отобразить гистограмму");
        barChartButton.setOnAction(e -> new BarChartHistogram().display(Integer.parseInt(claimantCategoryInput.getText())));

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(claimantCategoryInput, barChartButton);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(table, hBox);

        scene = new Scene(layout, 1180, 650);
        window.setScene(scene);
        window.show();
    }

    //Поиск записей для вставки в таблицу TableView<Applicate> table
    public ObservableList<Applicate> getRecords(MongoOperations mongoOperations) {
        ObservableList<Applicate> records = FXCollections.observableArrayList();
        records.addAll(mongoOperations.findAll(Applicate.class));
        return records;
    }
}

