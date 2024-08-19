module sistema {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens sistema to javafx.fxml;
    exports sistema;
}
