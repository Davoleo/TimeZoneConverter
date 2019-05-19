package davoleo.timezoneconverter.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Main extends Application {

    private Slider slider1, slider2;
    private TextField timeField1, timeField2;
    //TODO Add zone labels to the GUI
    private Label zone1, zone2;
    private Button deltaButton;
    private CheckBox lockDelta, legaleCheck;
    private LocalTime time1;
    private LocalTime time2;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Time Zones Converter");

        time1 = LocalTime.now(Clock.systemUTC());
        time2 = LocalTime.now(Clock.systemUTC());

        slider1 = new Slider(0, 23, time1.getHour());
        slider2 = new Slider(0, 23, time2.getHour());
        slider1.setOnDragDropped(event -> updateTimeBox());

        timeField1 = new TextField(time1.truncatedTo(ChronoUnit.MINUTES).toString());
        timeField2 = new TextField(time2.truncatedTo(ChronoUnit.MINUTES).toString());

        legaleCheck = new CheckBox("Daylight Saving Time");
        int monthNumber = LocalDate.now().getMonth().getValue();
        if (monthNumber > 3 && monthNumber <= 10)
            legaleCheck.setSelected(true);

        GridPane layout = new GridPane();
        layout.setHgap(8);
        layout.setHgap(8);
        layout.setPadding(new Insets(8));

        GridPane.setConstraints(slider1, 0, 0);
        GridPane.setConstraints(slider2, 0, 1);
        GridPane.setConstraints(timeField1, 1, 0);
        GridPane.setConstraints(timeField2, 1, 1);
        GridPane.setConstraints(legaleCheck, 2, 2);

        layout.getChildren().addAll(slider1, slider2, timeField1, timeField2, legaleCheck);

        primaryStage.setScene(new Scene(layout, 750, 430));
        primaryStage.show();
    }

    private void updateTimeBox()
    {
        //TODO Fix time box updates
        time1.withHour((int) slider1.getValue());
        time2.withHour((int) slider2.getValue());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
