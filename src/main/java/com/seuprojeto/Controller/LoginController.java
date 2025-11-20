package com.seuprojeto.Controller;
import com.seuprojeto.DAO.UsuarioDAO;
import com.seuprojeto.Model.Usuario;
import com.seuprojeto.Util.SceneManager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class LoginController {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;

    @FXML
    private void onVoltar(){
        try{
            SceneManager.carregarCena("/com/fxml/Cadastro.fxml");
        }catch(Exception e){
            System.out.println("Erro");
        }
    }
    @FXML
    private void onLogin(){
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (email.isEmpty() || senha.isEmpty()){
            lblMensagem.setText("Ambos os campos são obrigatorios");
            lblMensagem.setTextFill(Color.RED);
        }

        UsuarioDAO dao = new UsuarioDAO();
        Usuario usuario = dao.buscarUsuario(email, senha);

         System.out.println("🔍 DEBUG - Usuario retornado: " + usuario);
         
        if (usuario != null){
            lblMensagem.setText("Logado");
            SceneManager.carregarCena("Cadastro.fxml");
        }else{
            lblMensagem.setText("Email ou senha incorretos!");
            lblMensagem.setTextFill(Color.RED);
        }
    }
}
