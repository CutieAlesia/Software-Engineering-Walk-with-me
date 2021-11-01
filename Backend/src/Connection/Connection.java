/**
* @author Clemens Maas
* @version 1.0
*/
public class Connection {

  private int PORT = 25565;

  /**
  * @throws UnknownHostException
  */
  public Connection() throws UnknownHostException {
    Socket socket = null;
    try {
      Socket socket = new Socket("localhost", PORT);
    } catch (UnknownHostException e) {
      System.out.println("Host could not be found");
      e.printStackTrace();
    }
  }

}
