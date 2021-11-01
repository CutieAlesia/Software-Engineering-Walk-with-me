package Connection;

import java.io.*;
import java.net.*;

/**
* @author Clemens Maas
* @version 1.0
*/
public class Connection {

  private int PORT = 25565;

  public Connection() throws IOException {
    Server server = new Server(PORT);
  }

}
