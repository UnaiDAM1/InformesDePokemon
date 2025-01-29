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

/*
 * Clase HelloController
 * Clase encargada del diseño y control de la primera vista que aparece al iniciar la app se encarga de
 * llevarte a las diferentes clases que generan los informes por parámetros, también puede generar un
 * informe general y abrirlo
 *
 * @version: 1.0
 *
 * @autor: Unai Nieto
 *
 */

public class HelloController {
    @FXML
    Button botonTipo;

    @FXML
    Button botonGeneracion;

    // Metodo initialize que arranca la clase
    @FXML
    public void initialize(){

    }

    // Metodo asociado al botón de "Informe general" el cual genera un pdf en la carpeta del mismo proyecto
    // ubicada en  resources/InformesPDF con el contenido de la base de datos en el.
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

    // Metodo asociado al botón de "Informe por tipo" que lanza la clase informeTipo y cierra HelloController
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

    // Metodo asociado al botón de "Informe por tipo" que lanza la clase informeGeneracion y cierra HelloController
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

    // Metodo asociado al botón de "Ver informe" el cual abre el informe generado por el botón "Informe general"
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