package davoleo.timezoneconverter.gui;

import davoleo.timezoneconverter.data.EnumTimeZones;
import davoleo.timezoneconverter.data.TimeHelper;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import javax.swing.*;
import java.time.LocalDate;

public class Main extends Application {

    private Slider slider1, slider2;
    private TextField timeField1, timeField2;
    private Label zone1, zone2;
    private ChoiceBox<EnumTimeZones> zoneBox1, zoneBox2;
    private Button deltaButton;
    private CheckBox lockDelta, legaleCheck;
    private LocalTime time1;
    private LocalTime time2;

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Time Zones Converter");

        time1 = new LocalTime(DateTimeZone.UTC);
        time2 = new LocalTime(DateTimeZone.UTC);

        slider1 = new Slider(0, 23, time1.getHourOfDay());
        slider2 = new Slider(0, 23, time2.getHourOfDay());

        slider1.setShowTickMarks(true);
        slider1.setBlockIncrement(1);
        slider2.setShowTickMarks(true);
        slider2.setBlockIncrement(1);

        slider1.valueProperty().addListener((observable, oldValue, newValue) -> handleSliderDrag(1));
        slider2.valueProperty().addListener((observable, oldValue, newValue) -> handleSliderDrag(2));

        timeField1 = new TextField();
        timeField2 = new TextField();
        timeField1.setEditable(false);
        timeField2.setEditable(false);
        refresh();

        legaleCheck = new CheckBox("Daylight Saving Time");
        int monthNumber = LocalDate.now().getMonth().getValue();
        if (monthNumber > 3 && monthNumber <= 10)
            legaleCheck.setSelected(true);

        lockDelta = new CheckBox("Lock Delta");
        lockDelta.setDisable(true);

        deltaButton = new Button("Delta");
        deltaButton.setOnAction(event -> JOptionPane.showMessageDialog(null,
                (time1.getHourOfDay() - time2.getHourOfDay()) + " or " + (time2.getHourOfDay() - time1.getHourOfDay()),
                "Time zones hour diff", JOptionPane.INFORMATION_MESSAGE));

        zone1 = new Label(EnumTimeZones.GMT.getLongName());
        zone2 = new Label(EnumTimeZones.GMT.getLongName());

        zoneBox1 = new ChoiceBox<>();
        zoneBox1.getItems().addAll(EnumTimeZones.values());
        zoneBox1.setValue(EnumTimeZones.GMT);
        zoneBox1.setOnAction(event -> handleChoice(1));
        zoneBox2 = new ChoiceBox<>();
        zoneBox2.getItems().addAll(EnumTimeZones.values());
        zoneBox2.setValue(EnumTimeZones.GMT);
        zoneBox1.setOnAction(event -> handleChoice(2));

        GridPane layout = new GridPane();
        layout.setHgap(25);
        layout.setVgap(25);
        layout.setPadding(new Insets(25));

        GridPane.setConstraints(slider1, 0, 0);
        GridPane.setConstraints(slider2, 0, 1);
        GridPane.setConstraints(timeField1, 1, 0);
        GridPane.setConstraints(timeField2, 1, 1);
        GridPane.setConstraints(zone1, 2, 0);
        GridPane.setConstraints(zone2, 2, 1);
        GridPane.setConstraints(zoneBox1, 3, 0);
        GridPane.setConstraints(zoneBox2, 3, 1);
        GridPane.setConstraints(legaleCheck, 0, 2);
        GridPane.setConstraints(deltaButton,1, 2);
        GridPane.setConstraints(lockDelta, 2, 2);

        layout.getChildren().addAll(slider1, slider2, timeField1, timeField2, zone1, zone2, zoneBox1, zoneBox2, legaleCheck, deltaButton, lockDelta);

        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(layout, 750, 250));
        primaryStage.show();
    }

    //Refreshes all application controls
    private void refresh()
    {
        timeField1.setText(TimeHelper.timeToString(time1));
        timeField2.setText(TimeHelper.timeToString(time2));
    }

    //FIXME
    private void handleChoice(int id)
    {
         switch (id)
         {
             case 1:
                 int m1 = zoneBox1.getSelectionModel().getSelectedItem().getModifier();
                 time1 = time1.withHourOfDay(time1.getHourOfDay() - m1);
                 refresh();
                 break;
             case 2:
                 int m2 = zoneBox2.getSelectionModel().getSelectedItem().getModifier();
                 time2 = time2.withHourOfDay(time2.getHourOfDay() - m2);
                 refresh();
                 break;
         }

    }

    private void handleSliderDrag(int id)
    {
        switch (id)
        {
            case 1:
                time1 = time1.withHourOfDay((int) slider1.getValue());
                refresh();
                break;
            case 2:
                time2 = time2.withHourOfDay((int) slider2.getValue());
                refresh();
                break;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
