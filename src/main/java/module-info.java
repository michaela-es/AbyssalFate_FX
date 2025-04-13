module com.example.abyssalfate_fx {
    requires javafx.fxml;
    requires javafx.web;
    requires java.desktop;
    requires javafx.controls;
    requires javafx.media;

    opens com.example.abyssalfate_fx.Controllers to javafx.fxml;
    opens com.example.fxml to javafx.fxml;

    opens com.example.abyssalfate_fx.main to javafx.fxml, javafx.graphics;
    exports com.example.abyssalfate_fx.main;
}
