
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.SmoothieAine;


public class DaoSmoothieAine implements Dao<SmoothieAine, Integer>  {
    private Database database;

    public DaoSmoothieAine(Database database) {
        this.database = database;
    }
    
    public List<SmoothieAine> haeResepti(Integer smoothieId) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * "
                + "FROM SmoothieAine "
                + "WHERE smoothie_id = ? "
                + "ORDER BY jarjestys;");
        statement.setInt(1, smoothieId);
        ResultSet rs = statement.executeQuery();
        
        ArrayList<SmoothieAine> lista = new ArrayList<>();
        
        while (rs.next()) {
            Integer aineId = rs.getInt("aine_id");
            Integer smoothie_Id = rs.getInt("smoothie_id");
            Integer jarjestys = rs.getInt("jarjestys");
            String maara = rs.getString("maara");
            String ohje = rs.getString("ohje");
            
            lista.add(new SmoothieAine(aineId, smoothie_Id, jarjestys, maara, ohje));
        }
        
        rs.close();
        statement.close();
        connection.close();
        
        return lista;
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

    @Override
    public SmoothieAine findOne(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
