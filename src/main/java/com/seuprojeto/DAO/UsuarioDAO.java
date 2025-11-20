package com.seuprojeto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.seuprojeto.Model.Usuario;
import com.seuprojeto.Util.DatabaseConnection;

public class UsuarioDAO {
    public boolean cadastrar(Usuario usuario){
        Connection conexao = DatabaseConnection.conectar();

        if (conexao == null){
            System.out.println("Erro, não foi possivel conectar com o banco");
            return false;
        }
       try{ 
            String sql = "INSERT INTO usuarios (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            int resultado = stmt.executeUpdate();
            stmt.close();

            conexao.close();

            return resultado > 0;
        }catch(Exception e){
            System.out.println("Erro ao cadastrar usuario: " + e.getMessage());
            return false;
            
        }
    
    }
    public Usuario buscarUsuario(String email, String senha){
        Connection conexao = DatabaseConnection.conectar();

        try {
            String sql = "SELECT * FROM usuarios WHERE email = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();
            
             
            if(rs.next()){
                Usuario usuario = new Usuario(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("senha")
                );
                rs.close();
                stmt.close();
                conexao.close();
            }
            return null;

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
            return null;

        }
    }
}
