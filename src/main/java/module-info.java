module org.example.generadorpokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;
    requires java.desktop;


    opens org.example.generadorpokedex to javafx.fxml;
    exports org.example.generadorpokedex;
}