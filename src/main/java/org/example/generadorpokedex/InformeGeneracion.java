package org.example.generadorpokedex;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
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

/**
 * Clase InformeGeneracion
 * Clase que genera los informes por el parámetro Generación.
 *
 * @version 1.0
 *
 * @author Unai Nieto
 *
 */

public class InformeGeneracion {

    @FXML
    private ComboBox<String> generacion; // ComboBox para seleccionar la generación.

    /**
     * Método que se ejecuta al iniciar la clase.
     * Inicializa los valores del ComboBox con las generaciones disponibles.
     */
    @FXML
    public void initialize() {
        // Se añaden las generaciones al ComboBox
        generacion.getItems().addAll("Primera", "Segunda", "Tercera", "Cuarta");
    }

    /**
     * Método asociado al botón "Generar informe".
     * Este método genera un archivo PDF con el contenido de la base de datos,
     * filtrado por la generación seleccionada en el ComboBox.
     * El informe se guarda en la carpeta de recursos del proyecto.
     *
     * @param actionEvent Evento asociado al botón "Generar informe".
     */
    public void buttonGenerar(ActionEvent actionEvent) {
        try {
            // Se carga el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se establece la conexión con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pokemondb", "unai", "1234");

            // Se preparan los parámetros para el informe según la selección del ComboBox
            Map parametros = new HashMap();
            switch (generacion.getSelectionModel().getSelectedItem()) {
                case "Primera":
                    parametros.put("Generacion", 1);
                    break;
                case "Segunda":
                    parametros.put("Generacion", 2);
                    break;
                case "Tercera":
                    parametros.put("Generacion", 3);
                    break;
                case "Cuarta":
                    parametros.put("Generacion", 4);
                    break;
                default:
                    break;
            }
            // Se genera el informe utilizando JasperReports y se exporta a PDF
            System.out.println(parametros);
            JasperPrint print = JasperFillManager.fillReport("src\\main\\resources\\Informes\\pokedexPorGeneración.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "src\\main\\resources\\InformesPDF\\Informe_pokedexGeneracion.pdf");
        } catch (Throwable e) {
            // Se captura cualquier excepción y se imprime el stack trace
            e.printStackTrace();
        }
    }

    /**
     * Método asociado al botón "Ver informe".
     * Este método abre el archivo PDF generado por el botón "Generar informe".
     *
     * @param actionEvent Evento asociado al botón "Ver informe".
     */
    public void buttonAbrir(ActionEvent actionEvent) {
        // Se obtiene la URL del archivo PDF generado
        URL url = InformeTipo.class.getResource("/InformesPDF/Informe_pokedexGeneracion.pdf");
        if (url != null) {
            try {
                // Se crea un objeto File a partir de la URL
                File file = new File(url.toURI());
                // Si el sistema soporta la apertura de archivos, se abre el PDF
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Método asociado al botón "Volver".
     * Este método cierra la ventana actual y abre la ventana principal.
     *
     * @param actionEvent Evento asociado al botón "Volver".
     * @throws IOException Si ocurre un error al cargar la nueva vista.
     */
    public void buttonVolver(ActionEvent actionEvent) throws IOException {
        // Se carga la vista principal (hello-view.fxml)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        // Se crea una nueva ventana para mostrar la vista principal
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        // Se obtiene la ventana actual y se cierra
        Stage ventanaActual = (Stage) generacion.getScene().getWindow();
        ventanaActual.close();
        // Se muestra la nueva ventana
        stage.showAndWait();
    }
}
