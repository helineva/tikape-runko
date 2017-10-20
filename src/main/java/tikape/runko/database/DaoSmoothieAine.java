
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import tikape.runko.domain.SmoothieAine;


public class DaoSmoothieAine implements Dao<SmoothieAine, Integer>  {
    private Database database;

    public DaoSmoothieAine(Database database) {
        this.database = database;
    }

    @Override
    public SmoothieAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SmoothieAine> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void save(Integer aineId, Integer smoothieId, Integer jarjestys, String maara, String ohje) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO SmoothieAine (aine_id, smoothie_id, jarjestys, maara, ohje) VALUES (?,?,?,?,?)");
        statement.setInt(1, aineId);
        statement.setInt(2, smoothieId);
        statement.setInt(3, jarjestys);
        statement.setString(4, maara);
        statement.setString(5, ohje);
        statement.executeUpdate();
        statement.close();
        connection.close();

        
    }
    
}
