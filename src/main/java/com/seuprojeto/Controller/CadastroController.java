package com.seuprojeto.Controller;

import com.seuprojeto.DAO.UsuarioDAO;
import com.seuprojeto.Model.Usuario;
import com.seuprojeto.Util.SceneManager; 

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class CadastroController {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtSenha;
    @FXML
    private Label lblMensagem;
    
    @FXML
    private void onSalvar(){
        String nome = txtNome.getText();
        String email = txtEmail.getText();
        String senha = txtSenha.getText();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            lblMensagem.setText("Preencha todos os campos");
            lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
            return; 
        }
        Usuario usuario = new Usuario(nome, email, senha);

        UsuarioDAO dao = new UsuarioDAO();
        boolean salvou = dao.cadastrar(usuario);

        if (salvou){
            lblMensagem.setText("Usuário cadastrado (=");
            lblMensagem.setTextFill(javafx.scene.paint.Color.GREEN);
            txtNome.clear();
            txtEmail.clear();
            txtSenha.clear();
            
        }else {
              lblMensagem.setText("Erro ao cadastrar! Email pode já estar em uso.");
              lblMensagem.setTextFill(javafx.scene.paint.Color.RED);
        }

    }
    @FXML
    private void onIrParaLogin(){
        try{
            SceneManager.carregarCena("/com/fxml/Login.fxml");
            
        }catch(Exception e){
            System.out.println("não deu");
        }
    }
    

}
