module com.seuprojeto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.seuprojeto to javafx.fxml;
    exports com.seuprojeto;
}


