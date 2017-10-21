package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Smoothie;

public class DaoSmoothie implements Dao<Smoothie, Integer>  {
    private Database database;

    public DaoSmoothie(Database database) {
        this.database = database;
    }

    @Override
    public Smoothie findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Smoothie WHERE id = ?");
        statement.setInt(1, key);
        ResultSet rs = statement.executeQuery();
        if(!rs.next()) {
            return null;
        }
        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        
        Smoothie smoothie = new Smoothie(id, nimi);
        
        rs.close();
        statement.close();
        connection.close();
        
        return smoothie;
    }

    @Override
    public List<Smoothie> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Smoothie");
        ResultSet rs = statement.executeQuery();
        List<Smoothie> smoothiet = new ArrayList<>();
        
        while(rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");
            Smoothie smoothie = new Smoothie(id, nimi);
            smoothiet.add(smoothie);            
        }
        rs.close();
        statement.close();
        connection.close();
        return smoothiet;    
    }
    
    public void save(String nimi) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Smoothie (nimi) VALUES (?)");
        statement.setString(1, nimi);
        statement.executeUpdate();
        statement.close();
        connection.close();        
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Smoothie WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }   
}
