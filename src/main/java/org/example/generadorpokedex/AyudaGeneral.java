package org.example.generadorpokedex;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class AyudaGeneral {
    public static void main(String[] args) {
        // Crear el marco principal
        JFrame frame = new JFrame("Cargar contenido en JTextPane");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear el JTextPane y configurarlo como no editable
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        // Crear un JScrollPane para permitir scroll si el contenido es extenso
        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Construcción de la URI
        URI myUri;
        try {
            myUri = new URI("https://unaidam1.github.io/InformesDePokemon/");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "La URL especificada es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cargar contenido manualmente
        try {
            URL myUrl = myUri.toURL();
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                reader.close();

                // Establecer el contenido en el JTextPane
                textPane.setContentType("text/html");
                textPane.setText(content.toString());
            } else {
                throw new Exception("Respuesta no exitosa: Código " + connection.getResponseCode());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "No se pudo cargar el contenido desde la URL.\n" + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Hacer la ventana pantalla completa
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximizar
        frame.setUndecorated(true); // Ocultar bordes y barra de título (opcional)
        frame.setVisible(true);
    }
}

