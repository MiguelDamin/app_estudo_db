module com.seuprojeto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.seuprojeto to javafx.fxml;
    opens com.seuprojeto.Controller to javafx.fxml;  // ← ADICIONE ESTA LINHA!
    
    exports com.seuprojeto;
}