package com.seuprojeto;

import java.io.IOException;
import java.net.URL;

import com.seuprojeto.Util.SceneManager; // Importando o SceneManager

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class App extends Application {

    // Removed the static 'scene' and 'setRoot' as SceneManager vai gerenciar as trocas

    @Override
    public void start(Stage stage) throws IOException {
        
        // 1. Define o Stage principal no SceneManager para que ele possa fazer as trocas.
        SceneManager.setPrimaryStage(stage);
        
        // 2. Define o título da janela.
        stage.setTitle("App de Estudos - Cadastro");
        
        // 3. Carrega a primeira cena (Cadastro) usando o SceneManager.
        // Caminho correto dentro do classpath de resources: sem "resources" no início
        SceneManager.carregarCena("/com/fxml/Cadastro.fxml");
        
        // O stage.show() será chamado dentro do SceneManager.carregarCena()
    }

    // Mantive o método loadFXML apenas por segurança ou para inicialização,
    // mas o ideal é que SceneManager faça todo o carregamento.
    // De qualquer forma, o path aqui deve ser ajustado para corresponder à estrutura.
    private static Parent loadFXML(String fxml) throws IOException {
        // Exemplo: fxml = "Cadastro" -> "/com/fxml/Cadastro.fxml"
        String fullPath = "/com/fxml/" + fxml + ".fxml"; 
        
        URL url = App.class.getResource(fullPath);
        if (url == null) {
             throw new IOException("FXML não encontrado no caminho: " + fullPath);
        }
        
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}