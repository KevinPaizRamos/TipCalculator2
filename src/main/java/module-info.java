module com.example.tipcalculator2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tipcalculator2 to javafx.fxml;
    exports com.example.tipcalculator2;
}