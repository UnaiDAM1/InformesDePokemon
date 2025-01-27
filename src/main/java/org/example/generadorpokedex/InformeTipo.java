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

public class InformeTipo {

    @FXML
    private ComboBox<String> tipo;

    @FXML
    private ComboBox<String> fase;


    @FXML
    public void initialize() {
        tipo.getItems().addAll("Agua", "Fuego", "Planta", "Bicho", "Dragón", "Eléctrico", "Tierra",
                "Fantasma", "Hada", "Hielo", "Lucha", "Normal", "Psíquico", "Roca", "Siniestro", "Veneno", "Volador");

        fase.getItems().addAll("Base", "Primera", "Segunda");
    }

    public void buttonGenerar(ActionEvent actionEvent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/pokemondb", "unai", "1234");

            Map parametros = new HashMap();
            parametros.put("Tipo", tipo.getSelectionModel().getSelectedItem());
            switch (fase.getSelectionModel().getSelectedItem()) {
                case "Base":
                    parametros.put("FaseEv", 1);
                    break;
                case "Primera":
                    parametros.put("FaseEv", 2);
                    break;
                case "Segunda":
                    parametros.put("FaseEv", 3);
                    break;
                default:
                    break;
            }


            System.out.println(parametros);
            JasperPrint print = JasperFillManager.fillReport("src\\main\\resources\\Informes\\pokedexPorTipo1.jasper", parametros, connection);
            JasperExportManager.exportReportToPdfFile(print, "src\\main\\resources\\InformesPDF\\Informe_pokedexTipos.pdf");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void buttonAbrir(ActionEvent actionEvent) {
        URL url = InformeTipo.class.getResource("/InformesPDF/Informe_pokedexTipos.pdf");
        if (url != null) {
            try {
                File file = new File(url.toURI());
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

    public void buttonVolver(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root, 400, 500));

        // Obtener la ventana actual y cerrarla
        Stage ventanaActual = (Stage) tipo.getScene().getWindow();
        ventanaActual.close();
        stage.showAndWait();
    }
}
