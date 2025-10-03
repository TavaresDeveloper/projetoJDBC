package connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import io.github.cdimascio.dotenv.Dotenv;


public class ConnectionFactory {



        private static final Dotenv dotenv = Dotenv.load();
        private static final String url = dotenv.get("DB_URL");
        private static final String user = dotenv.get("DB_USER");
        private static final String password = dotenv.get("DB_PASSWORD");


        public static Connection getConnection(){

            try {
                assert url != null;
                return DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                throw new RuntimeException("Falha ao conectar com o banco ", e);


            }




        }


}
