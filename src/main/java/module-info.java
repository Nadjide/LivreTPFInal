module org.example.livretpfinal {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires commons.email;

    opens org.example.livretpfinal to javafx.fxml;
    exports org.example.livretpfinal;
}