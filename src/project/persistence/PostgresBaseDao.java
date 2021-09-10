package project.persistence;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;


public class PostgresBaseDao {
    protected final Connection getConnection() {
        Connection result = null;

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("jdbc:postgresql://localhost/ovchip?user=postgres&password=admin");

            result = ds.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return result;
    }
}