package org.example.generadorpokedex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
    @FXML
    Button botonTipo;

    @FXML
    Button botonGeneracion;
    @FXML
    public void initialize(){

    }
    public void buttonTodos(ActionEvent actionEvent) {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pokemondb", "unai", "1234");

            JasperPrint print = JasperFillManager.fillReport("src\\main\\resources\\Informes\\pokedexTodos.jasper", null, connection);
            JasperExportManager.exportReportToPdfFile(print, "src\\main\\resources\\InformesPDF\\Informe_pokedexTodos.pdf");
        }
        catch (Throwable e){
            e.printStackTrace();
        }
    }
    public void buttonTipo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("informe-tipo.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        // Obtener la ventana actual y cerrarla
        Stage ventanaActual = (Stage) botonTipo.getScene().getWindow();
        ventanaActual.close();
        stage.showAndWait();
    }

    public void buttonGeneracion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("informe-generacion.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        // Obtener la ventana actual y cerrarla
        Stage ventanaActual = (Stage) botonGeneracion.getScene().getWindow();
        ventanaActual.close();
        stage.showAndWait();
    }

    public void buttonAbrir(ActionEvent actionEvent) {
        URL url = InformeTipo.class.getResource("/InformesPDF/Informe_pokedexTodos.pdf");
        if (url != null) {
            try {
                File file = new File(url.toURI());
                if (Desktop.isDesktopSupported()){
                    Desktop.getDesktop().open(file);
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}