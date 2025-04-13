module com.example.abyssalfate_fx {
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.media;
    requires javafx.swing;

    opens com.example.abyssalfate_fx.Controllers to javafx.fxml;
    exports com.example.abyssalfate_fx;
    opens com.example.fxml to javafx.fxml;

}