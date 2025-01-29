package org.example.generadorpokedex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

/**
 * Clase HelloController
 * Controlador encargado de gestionar la primera vista de la aplicación.
 * Permite la navegación a otras vistas y la generación de informes.
 *
 * @version 1.0
 * @author Unai Nieto
 */
public class HelloController {
    @FXML
    Button botonTipo;

    @FXML
    Button botonGeneracion;

    /**
     * Método initialize que se ejecuta al iniciar la vista.
     */
    @FXML
    public void initialize() {
    }

    /**
     * Genera un informe general en formato PDF con los datos de la base de datos.
     * El archivo se guarda en la carpeta "resources/InformesPDF".
     *
     * @param actionEvent Evento que desencadena la generación del informe.
     */
    public void buttonTodos(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pokemondb", "unai", "1234");

            JasperPrint print = JasperFillManager.fillReport("src\\main\\resources\\Informes\\pokedexTodos.jasper", null, connection);
            JasperExportManager.exportReportToPdfFile(print, "src\\main\\resources\\InformesPDF\\Informe_pokedexTodos.pdf");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * Abre la vista de "Informe por tipo" y cierra la vista actual.
     *
     * @param actionEvent Evento que activa la acción.
     * @throws IOException Si ocurre un error al cargar la nueva vista.
     */
    public void buttonTipo(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("informe-tipo.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        Stage ventanaActual = (Stage) botonTipo.getScene().getWindow();
        ventanaActual.close();
        stage.showAndWait();
    }

    /**
     * Abre la vista de "Informe por generación" y cierra la vista actual.
     *
     * @param actionEvent Evento que activa la acción.
     * @throws IOException Si ocurre un error al cargar la nueva vista.
     */
    public void buttonGeneracion(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("informe-generacion.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        Stage ventanaActual = (Stage) botonGeneracion.getScene().getWindow();
        ventanaActual.close();
        stage.showAndWait();
    }

    /**
     * Abre el informe general generado en formato PDF.
     *
     * @param actionEvent Evento que activa la acción.
     */
    public void buttonAbrir(ActionEvent actionEvent) {
        URL url = InformeTipo.class.getResource("/InformesPDF/Informe_pokedexTodos.pdf");
        if (url != null) {
            try {
                File file = new File(url.toURI());
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
