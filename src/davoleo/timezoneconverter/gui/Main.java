package davoleo.timezoneconverter.gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class Main extends Application {

    private Slider slider1, slider2;
    private TextField time1, time2;
    private Label zone1, zone2;
    private Button deltaButton;
    private CheckBox lockDelta;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Time Zones Converter");

        slider1 = new Slider(0, 24, 12);
        slider2 = new Slider(0, 24, 12);

        time1 = new TextField(LocalDateTime.now().getHour() + " : " + LocalDateTime.now().getMinute());
        time2 = new TextField(LocalDateTime.now().getHour() + " : " + LocalDateTime.now().getMinute());

        GridPane layout = new GridPane();
        layout.setHgap(8);
        layout.setHgap(8);
        layout.setPadding(new Insets(8));

        primaryStage.setScene(new Scene(layout, 750, 430));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
