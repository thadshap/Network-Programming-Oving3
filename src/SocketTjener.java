import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SocketTjener {
  private static ArrayList<ClientHandler> clients = new ArrayList<>();
  private static ExecutorService pool = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;
    ServerSocket tjener = new ServerSocket(PORTNR);

    while (true) {
      System.out.println("Log for the serverside. We are waiting...");
      Socket forbindelse = tjener.accept();
      ClientHandler clientThread = new ClientHandler(forbindelse);
      clients.add(clientThread);
      pool.execute(clientThread);
    }
  }
}
