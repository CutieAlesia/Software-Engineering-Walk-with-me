package Connection;

import java.io.*;
import java.net.*;

/**
* @author Clemens Maas
* @version 1.1
*/
public class Server {

  private final ServerSocket server;
  private InputStreamReader inputStreamReader;
  private OutputStreamWriter outputStreamWriter;
  private BufferedReader bufferedReader;
  private BufferedWriter bufferedWriter;

  /**
  * @throws IOException Stream exceptions
  */
  public Server(int PORT) throws IOException {

    System.out.format("Server wird gestartet - {%d}", PORT);
    server = new ServerSocket(PORT);

    try {
      while(true) {
        Socket socket = server.accept();
        System.out.format("New connection on {%d}", socket.getLocalPort());
        inputStreamReader = new InputStreamReader(socket.getInputStream());
        outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(inputStreamReader);
        bufferedWriter = new BufferedWriter(outputStreamWriter);

        while (true){
          String input = bufferedReader.readLine();
          System.out.println(splitter(input, 0) + splitter(input, 1));
          bufferedWriter.write(reponder(splitter(input, 1)));
          bufferedWriter.newLine();
          bufferedWriter.flush();

          if (input.equalsIgnoreCase("exit"))
            break;
        }
        socket.close();
        inputStreamReader.close();
        outputStreamWriter.close();
        bufferedReader.close();
        bufferedWriter.close();

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private String splitter(String inp, int mode) {
    String[] parse = inp.split("#");
    if (mode == 0)
      return "["+parse[0]+"]: ";
    else return parse[1];
  }

  private String reponder(String command) {
    return switch (command) {
      case "!version" -> "1.1";
      case "!github" -> "https://github.com/Kushurando/Software-Engineering-Walk-with-me";
      case "!modul" -> "Software Engineering";
      case "!help" -> "Coming soon";
      default -> "";
    };
  }
}
