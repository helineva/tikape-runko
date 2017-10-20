/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Aine;



public class DaoAine implements Dao<Aine, Integer>{
    private Database database;
    
    public DaoAine(Database database) {
        this.database = database;
    }
    
    @Override
    public Aine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();
        if(!rs.next()) {
            return null;
        }
        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        
        Aine aine = new Aine(id, nimi);
        
        rs.close();
        statement.close();
        connection.close();
        
        return aine;
        
    }
    
        public List<Aine> findMany(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT RaakaAine.nimi AS nimi FROM RaakaAine, SmoothieAine WHERE RaakaAine.id = ?"
                + " AND SmoothieAine.aine_id = RaakaAine.id");
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();
        if(!rs.next()) {
            return null;
        }
        ArrayList<Aine> aineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            aineet.add(new Aine(id, nimi));
        }
        
        
        
        
        rs.close();
        statement.close();
        connection.close();
        
        return aineet;
        
    }
    

    @Override
    public List<Aine> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM RaakaAine");
        ResultSet rs = statement.executeQuery();
        List<Aine> aineet = new ArrayList<>();
        if(!rs.next()) {
            return null;
        }
        
        while(rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Aine aine = new Aine(id, nimi);
            aineet.add(aine);
            
            
        }
        rs.close();
        statement.close();
        connection.close();
        return aineet;
    }
    
    public void save(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO RaakaAine(nimi) VALUES(?)");
        statement.setString(1, nimi);
        statement.executeUpdate();
        statement.close();
        connection.close();

        
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM RaakaAine WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }
    
}
