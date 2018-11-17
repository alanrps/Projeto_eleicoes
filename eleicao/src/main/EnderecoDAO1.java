package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.DbConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Endereco;
import java.sql.PreparedStatement;
/**
 *
 * @author alanrps
 */
public class EnderecoDAO1 extends DbConnection{
    private Connection conn;
    private final String sqlInsert  = "INSERT INTO ENDERECO(nmr,cep,logradouro) VALUES (?,?,?)";
    private final String sqlUpdate  = "UPDATE ENDERECO SET  nmr = ?, cep = ?, logradouro = ?";
    private final String sqlRemove  = "DELETE FROM ENDERECO WHERE nmr = ?";
    private final String sqlList    = "SELECT nmr,cep,logradouro FROM ENDERECO ORDER BY ENDERECO.nmr";
    private final String sqlFind    = "SELECT nmr,cep,logradouro FROM ENDERECO WHERE nmr = ?";

     public void insert(Endereco endereco) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlInsert);
            ps.setInt(1, endereco.getNmr());
            ps.setInt(2, endereco.getCep());
            ps.setString(3, endereco.getLogradouro());
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }        
    }
     
    public void update(Endereco endereco) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlUpdate);
            ps.setInt(1, endereco.getNmr());
            ps.setInt(2, endereco.getCep());
            ps.setString(3, endereco.getLogradouro());
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }
    }
    
    public void remove(int nmr) throws SQLException{
        PreparedStatement ps = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlRemove);
            ps.setInt(1, nmr);
            ps.execute();
        }
        finally{
            ps.close();
            close(conn);
        }
    }

    public ArrayList<Endereco> list() throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlList);
            rs = ps.executeQuery();
            ArrayList<Endereco> list = new ArrayList<>();
            Endereco endereco;
            while (rs.next()){
                endereco = new Endereco();
                endereco.setNmr(rs.getInt("nmr"));
                endereco.setCep(rs.getInt("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
                list.add(endereco);
            }
            return list;
        }
        finally{
            rs.close();
            ps.close();
            close(conn);
        }
    }
        
        public Endereco find(int nmr)throws SQLException, ClassNotFoundException, IOException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            conn = connect();
            ps = conn.prepareStatement(sqlFind);
            ps.setInt(1, nmr);

            rs = ps.executeQuery();
            Endereco endereco = null ;
            if (rs.next()){
                endereco = new Endereco();
                endereco.setNmr(rs.getInt("nmr"));
                endereco.setCep(rs.getInt("cep"));
                endereco.setLogradouro(rs.getString("logradouro"));
            }
            return endereco;
        }
        finally{
            rs.close();
            ps.close();
            close(conn);
        }
        
    }
}
       
