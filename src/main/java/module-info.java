module com.example.abyssalfate_fx {
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.abyssalfate_fx to javafx.fxml;
    exports com.example.abyssalfate_fx;
}