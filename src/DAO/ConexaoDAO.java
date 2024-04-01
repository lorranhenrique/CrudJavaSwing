package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConexaoDAO {

    public Connection conectaBd() {
        
        Connection conn = null;
        

        try {

            String url = "jdbc:mysql://localhost:3306/bancoteste?user=root&password=";
            //endereço do banco de dados contendo as informações como usuario e senha sendo 3306 a port do mysql
            conn = DriverManager.getConnection(url);

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "O erro esta na classe de conexão" + erro.getMessage());
        }
        return conn;
    }
    //Ctrl space carrega a biblioteca
}
