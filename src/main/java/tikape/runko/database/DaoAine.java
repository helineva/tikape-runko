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
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Aine WHERE id = ?");
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

    @Override
    public List<Aine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
