package com.colini.dao;

import com.colini.db.FabricaConexoes;
import com.colini.models.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO{

    FabricaConexoes fabrica = FabricaConexoes.getInstance();
    
    // Variáveis de conexão

    public void insertUser(Users user) {
        String sql = "INSERT INTO Users (user_name, user_email, user_password) VALUES (?,?,?)";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(Users users){
        String sql = "UPDATE Users SET user_name =?, user_email =?, user_password =? WHERE user_id =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, users.getName());
            stmt.setString(2, users.getEmail());
            stmt.setString(3, users.getPassword());
            stmt.setString(4, users.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(String id) {
        String sql = "DELETE FROM Users WHERE user_id =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Users findById(String id) {
        String sql = "SELECT * FROM Users WHERE user_id =?";
        try (Connection conn = fabrica.getConnection(); // Obtém a conexão
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Users(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<Users> findAll() {
        List<Users> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Connection conn = fabrica.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clientes.add(new Users(
                    rs.getString("user_id"),
                    rs.getString("user_name"),
                    rs.getString("user_email"),
                    rs.getString("user_password")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public String verifyUserLogin(String user_email, String user_password){
        String sql = "{CALL userLoginVerifier(?, ?, ?)}";
        try (Connection conn = fabrica.getConnection(); CallableStatement stmt = conn.prepareCall(sql)) { // Use CallableStatement aqui
            
            stmt.setString(1, user_email);
            stmt.setString(2, user_password);
            stmt.registerOutParameter(3, java.sql.Types.VARCHAR); // Registra o parâmetro de saída
            
            stmt.execute();

            return stmt.getString(3); // Obtém o valor do parâmetro de saída
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Users findUser(String user_id) {
        String sql = "SELECT user_id, user_name, user_email, user_password FROM Users WHERE user_id = ?";
        try (Connection conn = fabrica.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("user_name");
                    String email = rs.getString("user_email");
                    String password = rs.getString("user_password");
                    return new Users(user_id, name, email, password);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
       }

}
