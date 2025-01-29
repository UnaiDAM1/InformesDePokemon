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

/*
 * Clase InformeTipo
 * Clase que genera los informes por el parámetro Tipo y Fase.
 *
 * @version: 1.0
 *
 * @autor: Unai Nieto
 *
 */

public class InformeTipo {

    @FXML
    private ComboBox<String> tipo;  // ComboBox para seleccionar el tipo de Pokémon.

    @FXML
    private ComboBox<String> fase;  // ComboBox para seleccionar la fase de evolución.

    /**
     * Método que se ejecuta al iniciar la clase.
     * Inicializa los valores de los ComboBox con los tipos de Pokémon y las fases disponibles.
     */
    @FXML
    public void initialize() {
        // Se añaden los tipos de Pokémon al ComboBox 'tipo'
        tipo.getItems().addAll("Agua", "Fuego", "Planta", "Bicho", "Dragón", "Eléctrico", "Tierra",
                "Fantasma", "Hada", "Hielo", "Lucha", "Normal", "Psíquico", "Roca", "Siniestro", "Veneno", "Volador");

        // Se añaden las fases de evolución al ComboBox 'fase'
        fase.getItems().addAll("Base", "Primera", "Segunda");
    }

    /**
     * Método asociado al botón "Generar informe".
     * Este método genera un archivo PDF con el contenido de la base de datos,
     * filtrado por el tipo de Pokémon y la fase seleccionada en los ComboBox.
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

            // Se preparan los parámetros para el informe según la selección de los ComboBox
            Map parametros = new HashMap();
            // Se añade el tipo de Pokémon seleccionado
            parametros.put("Tipo", tipo.getSelectionModel().getSelectedItem());

            // Se asigna la fase de evolución según la selección
            switch (fase.getSelectionModel().getSelectedItem()) {
                case "Base":
                    parametros.put("FaseEv", 1);  // Fase base
                    break;
                case "Primera":
                    parametros.put("FaseEv", 2);  // Primera fase de evolución
                    break;
                case "Segunda":
                    parametros.put("FaseEv", 3);  // Segunda fase de evolución
                    break;
                default:
                    break;
            }

            // Se genera el informe utilizando JasperReports y se exporta a PDF
            System.out.println(parametros);
            JasperPrint print = JasperFillManager.fillReport("src\\main\\resources\\Informes\\pokedexPorTipo1.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "src\\main\\resources\\InformesPDF\\Informe_pokedexTipos.pdf");
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
        URL url = InformeTipo.class.getResource("/InformesPDF/Informe_pokedexTipos.pdf");
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
        Stage ventanaActual = (Stage) tipo.getScene().getWindow();
        ventanaActual.close();
        // Se muestra la nueva ventana
        stage.showAndWait();
    }
}
