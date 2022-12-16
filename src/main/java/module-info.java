module com.example.workorganizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.example.workorganizer to javafx.fxml;
    exports com.example.workorganizer;
}