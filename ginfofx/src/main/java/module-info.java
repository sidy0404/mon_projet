module com.example.ginfofx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ginfofx to javafx.fxml;
    exports com.example.ginfofx;
}