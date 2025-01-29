package org.example.generadorpokedex;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/*
 * Clase HelloApplication
 * Clase encargada de iniciar la aplicación, ponerle el título a la ventana y las medidas
 *
 * @version: 1.0
 *
 * @autor: Unai Nieto
 *
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 500);
        stage.setTitle("Pokédex");
        stage.setScene(scene);
        stage.show();
    }

    // Clase main, lanza la app
    public static void main(String[] args) {
        launch();
    }
}