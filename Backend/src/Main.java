import Connection.*;
import Database.*;

import java.io.IOException;
import java.sql.SQLException;


/**
* @version 1.2
*/
public class Main {

  private static Server server;
  private static Database database;

  public static void main(String[] args) throws SQLException, IOException {
    //server = new Server(25565);
    database = new Database();
    database.viewUsers();
  }

}
