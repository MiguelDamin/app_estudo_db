package com.seuprojeto.Util;

import java.io.IOException;

import com.seuprojeto.App;

import javafx.fxml.FXMLLoader; // Importa a classe App (está no pacote raiz)
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    // A referência para o Stage principal da aplicação
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        SceneManager.primaryStage = stage;
    }

    /**
     * Método simplificado para carregar uma nova cena a partir do caminho FXML.
     *
     * @param caminhoDaCena O caminho (path) para o arquivo FXML, e.g., "/com/seuprojeto/fxml/Cadastro.fxml"
     */
    public static void carregarCena(String caminhoDaCena) {
        try {
            // AQUI ESTÁ A MUDANÇA CRUCIAL:
            // Usamos App.class.getResource() para garantir que a busca comece 
            // no local correto dentro do módulo "com.seuprojeto".
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(caminhoDaCena));
            
            if (fxmlLoader.getLocation() == null) {
                // Lançar um erro mais informativo caso o recurso não seja encontrado
                throw new IOException("Recurso FXML não encontrado: " + caminhoDaCena);
            }
            
            Parent root = fxmlLoader.load();

            // Cria uma nova Scene ou usa a existente
            Scene scene;
            if (primaryStage.getScene() == null) {
                scene = new Scene(root);
            } else {
                scene = primaryStage.getScene();
                scene.setRoot(root);
            }
            
            // Define a nova cena no Stage principal
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.err.println("Erro Crítico ao carregar a cena: " + caminhoDaCena);
            // Mostrar a stack trace completa é vital para depuração
            e.printStackTrace(); 
        } catch (NullPointerException e) {
            System.err.println("Erro: Stage Principal não definido. Chame setPrimaryStage() primeiro.");
            e.printStackTrace();
        }
    }
}