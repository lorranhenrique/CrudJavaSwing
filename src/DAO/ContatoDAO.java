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
        String sql = "select * from contatos order by Nome ";
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
    
    public void EditarContato(ContatoDTO objcontatodto){
        String sql = "UPDATE contatos SET Nome = ?, Telefone = ?, Email = ? WHERE Id = ?";
        conn = new ConexaoDAO().conectaBd();
        
        try {
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, objcontatodto.getNome());
            pstm.setString(2, objcontatodto.getTelefone());
            pstm.setString(3, objcontatodto.getEmail());
            pstm.setInt(4, objcontatodto.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"ContatoDAO"+ erro);
        }
    }
    
    public void DeletarContato(ContatoDTO objcontatodto){
        String sql = "DELETE from contatos WHERE id = ?";
        conn = new ConexaoDAO().conectaBd();
        
        try {
            pstm = conn.prepareStatement(sql);
            
            pstm.setInt(1,objcontatodto.getId());
            
            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"DeletarContato"+ erro);
        }
    }
    
    public ContatoDTO PesquisarContatoEspecifico(ContatoDTO objcontatodto){
        String sql = "select * from contatos WHERE id = ? ";
        conn = new ConexaoDAO().conectaBd();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objcontatodto.getId());
            
            objcontatodto.setNome(pstm.executeQuery().getString("Nome"));
            objcontatodto.setEmail(pstm.executeQuery().getString("Email"));
            objcontatodto.setTelefone(pstm.executeQuery().getString("Telefone"));
            objcontatodto.setId(Integer.parseInt(pstm.executeQuery().getString("Id")));
                
      } catch (Exception erro) {
            JOptionPane.showMessageDialog(null,"ContatoDAO"+ erro);
        }
        
              return objcontatodto;
    }
}
