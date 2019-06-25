package davoleo.timezoneconverter.gui;

import davoleo.timezoneconverter.data.EnumTimeZones;
import davoleo.timezoneconverter.data.TimeHelper;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import javax.swing.*;
import java.time.LocalDate;

public class Main extends Application {

    private TextField timeField1, timeField2;
    private Label zone1, zone2;
    private ChoiceBox<EnumTimeZones> zoneBox1, zoneBox2;
    private Button deltaButton, refreshButton;
    private CheckBox lockDelta, legaleCheck;
    private LocalTime time1;
    private LocalTime time2;
    private Property modifier1 = new SimpleIntegerProperty();
    private Property modifier2 = new SimpleIntegerProperty();

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Time Zones Converter");

        time1 = new LocalTime(DateTimeZone.UTC);
        time2 = new LocalTime(DateTimeZone.UTC);

        timeField1 = new TextField();
        timeField2 = new TextField();
        timeField1.setEditable(false);
        timeField2.setEditable(false);

        legaleCheck = new CheckBox("Daylight Saving Time");
        int monthNumber = LocalDate.now().getMonth().getValue();
        if (monthNumber > 3 && monthNumber <= 10) {
            legaleCheck.setSelected(true);
            time1 = time1.withHourOfDay(time1.getHourOfDay() + 1);
            time2 = time2.withHourOfDay(time2.getHourOfDay() + 1);
        }
        legaleCheck.setOnAction(event -> switchLegaleMode());

        lockDelta = new CheckBox("Lock Delta");
        lockDelta.setDisable(true);

        deltaButton = new Button("Delta");
        deltaButton.setOnAction(event -> JOptionPane.showMessageDialog(null,
                (time2.getHourOfDay() - time1.getHourOfDay()),
                "Time zones hour diff", JOptionPane.INFORMATION_MESSAGE));

        refreshButton = new Button("Refresh");
        refreshButton.setOnAction(event -> refresh());

        zone1 = new Label(EnumTimeZones.GMT.getLongName());
        zone2 = new Label(EnumTimeZones.GMT.getLongName());

        zoneBox1 = new ChoiceBox<>();
        zoneBox1.getItems().addAll(EnumTimeZones.values());
        zoneBox1.setValue(EnumTimeZones.GMT);
        zoneBox1.setOnAction(event -> handleChoice());
        zoneBox2 = new ChoiceBox<>();
        zoneBox2.getItems().addAll(EnumTimeZones.values());
        zoneBox2.setValue(EnumTimeZones.GMT);
        zoneBox2.setOnAction(event -> handleChoice());

        refresh();

        GridPane layout = new GridPane();
        layout.setHgap(25);
        layout.setVgap(25);
        layout.setPadding(new Insets(25));

        GridPane.setConstraints(timeField1, 0, 0);
        GridPane.setConstraints(timeField2, 0, 1);
        GridPane.setConstraints(zone1, 1, 0);
        GridPane.setConstraints(zone2, 1, 1);
        GridPane.setConstraints(zoneBox1, 2, 0);
        GridPane.setConstraints(zoneBox2, 2, 1);
        GridPane.setConstraints(legaleCheck, 0, 2);
        GridPane.setConstraints(deltaButton,1, 2);
        GridPane.setConstraints(lockDelta, 2, 2);
        GridPane.setConstraints(refreshButton, 3, 2);

        layout.getChildren().addAll(timeField1, timeField2, zone1, zone2, zoneBox1, zoneBox2, legaleCheck, deltaButton, lockDelta, refreshButton);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(layout, 750, 250));
        primaryStage.show();
    }

    //Refreshes all application controls
    private void refresh()
    {
        int modifier1 = zoneBox1.getSelectionModel().getSelectedItem().getModifier();
        int modifier2 = zoneBox2.getSelectionModel().getSelectedItem().getModifier();

        //Refresh minutes
        time1 = time1.withMinuteOfHour(DateTime.now().minuteOfHour().get());
        time2 = time2.withMinuteOfHour(DateTime.now().minuteOfHour().get());

        timeField1.setText(TimeHelper.timeToString(time1));
        timeField2.setText(TimeHelper.timeToString(time2));

        zone1.setText(EnumTimeZones.getZoneFromModifier(modifier1).getLongName());
        zone2.setText(EnumTimeZones.getZoneFromModifier(modifier2).getLongName());
    }

    private void switchLegaleMode(){
        if (!legaleCheck.isSelected())
        {
            time1 = time1.withHourOfDay(time1.getHourOfDay() - 1);
            time2 = time2.withHourOfDay(time2.getHourOfDay() - 1);
        }
        else
        {
            time1 = time1.withHourOfDay(time1.getHourOfDay() + 1);
            time2 = time2.withHourOfDay(time2.getHourOfDay() + 1);
        }
        refresh();
    }

    private void handleChoice()
    {
        int modifier = zoneBox1.getSelectionModel().getSelectedItem().getModifier();
        time1 = TimeHelper.offsetHour(DateTime.now(DateTimeZone.UTC).toLocalTime(), modifier);
        refresh();

        int modifier2 = zoneBox2.getSelectionModel().getSelectedItem().getModifier();
        time2 = TimeHelper.offsetHour(DateTime.now(DateTimeZone.UTC).toLocalTime(), modifier2);
        refresh();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
