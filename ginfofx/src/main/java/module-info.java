module com.example.ginfofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ginfofx to javafx.fxml;
    exports com.example.ginfofx;
    exports com.example.ginfofx.controller;
    opens com.example.ginfofx.controller to javafx.fxml;
}
