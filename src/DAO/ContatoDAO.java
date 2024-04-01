package DAO;

import DTO.ContatoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContatoDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ContatoDTO> lista = new ArrayList<>();

    public void cadastrarContato(ContatoDTO objcontatodto) {
        String sql = "insert into contatos (Nome,Email,Telefone) values (?,?,?)";

        conn = new ConexaoDAO().conectaBd();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objcontatodto.getNome());
            pstm.setString(2, objcontatodto.getEmail());
            pstm.setString(3, objcontatodto.getTelefone());
            
            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ContatoDAO" + erro);
        }

    }
    
    
    public ArrayList<ContatoDTO> PesquisarContato(){
        String sql = "select * from contatos ";
        conn = new ConexaoDAO().conectaBd();
        
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                ContatoDTO objcontatodto = new ContatoDTO();
                objcontatodto.setId(rs.getInt("Id"));
                objcontatodto.setNome(rs.getString("Nome"));
                objcontatodto.setEmail(rs.getString("Email"));
                objcontatodto.setTelefone(rs.getString("Telefone"));
                
                lista.add(objcontatodto);
            }
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"ContatoDAO"+ erro);
        }
        
              return lista;
    }
}
