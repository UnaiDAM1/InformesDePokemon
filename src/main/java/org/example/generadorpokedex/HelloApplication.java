package org.example.generadorpokedex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Clase encargada de iniciar la aplicación, establecer el título de la ventana y definir sus dimensiones.
 *
 * @version 1.0
 * @author Unai Nieto
 */
public class HelloApplication extends Application {

    /**
     * Método que inicia la aplicación, carga la interfaz gráfica desde un archivo FXML,
     * establece el tamaño de la ventana y la muestra.
     *
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Pokédex");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal de la aplicación, que lanza la aplicación JavaFX.
     *
     * @param args Argumentos de línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }
}
