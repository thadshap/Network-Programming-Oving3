
import java.io.* ;
import java.net.* ;
import java.util.* ;

import static java.lang.Integer.parseInt;

public final class WebServer {
    public static void main(String[] args) throws Exception {
        // Start receiving messages - ready to receive messages!

        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server started.\nListening for messages.");


            while (true) {
                // Handle a new incoming message

                try {
                    Socket client = serverSocket.accept();
                    // client <-- messages queued up in it!!
                    // Read the request - listen to the message
                    InputStreamReader isr = new InputStreamReader(client.getInputStream());

                    BufferedReader br = new BufferedReader(isr);

                    OutputStream clientOutput = client.getOutputStream();

                    String enLinje = br.readLine();
                    ArrayList<String> liste = new ArrayList<>();
                    while (!enLinje.equals("")) {
                        liste.add(enLinje);
                        enLinje = br.readLine();
                    }
                    System.out.println("HTTP/1.0 200 OK\nContent-Type: text/html; charset=utf-8"); //client.toString() "HTTP/1.0 200 OK\nContent-Type: text/html; charset=utf-8"

                    clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    clientOutput.write(("\r\n").getBytes());
                    clientOutput.write(("<h1>Hilsen. Du har koblet deg opp til min enkle web-tjener</h1>").getBytes());
                    clientOutput.write(("<ul>").getBytes());
                    for (String l:liste) {
                        clientOutput.write(("<li>"+l+"</li>").getBytes());
                    }
                    clientOutput.write(("</ul>").getBytes());
                    clientOutput.flush();
                    client.close();
                }catch (IOException e){
                    System.out.println("Wrong");
                }
            }
        }
    }
}
