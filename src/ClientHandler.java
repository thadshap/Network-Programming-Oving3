import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class ClientHandler implements Runnable{
    private Socket forbindelse;
    private InputStreamReader leseforbindelse;
    private PrintWriter skriveren;
    private BufferedReader leseren;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.forbindelse = clientSocket;
        leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
        leseren = new BufferedReader(leseforbindelse);
        skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
    }

    @Override
    public void run() {
        try {
            skriveren.println("Hey, you have contact with the server!");
            skriveren.println("In this program you can choose to either add or subtract two numbers.");
            skriveren.println("You can quit this program by pressing the Enter button.");
            skriveren.println("Enter a number:");

            String enLinje = leseren.readLine();
            while (enLinje != null) {
                System.out.println("\nFirst number entered: " + enLinje);
                skriveren.println("Next");
                enLinje = leseren.readLine();
                System.out.println("Second number entered: " + enLinje);
                skriveren.println("Next");
                enLinje = leseren.readLine();
                System.out.println("Addition(a) or subtraction(s): " + enLinje);

                if(enLinje.equals("a")){
                    enLinje = leseren.readLine();
                    System.out.println("The answer is: " +enLinje);
                }
                else if(enLinje.equals("s")){
                    enLinje = leseren.readLine();
                    System.out.println("The answer is: "+enLinje);
                }
                else System.out.println("The client has entered something wrong.");
                enLinje = leseren.readLine();
            }
        }catch (IOException e){
            System.err.println("IO exception in client handler.");
            System.err.println(e.getStackTrace());
        }
        finally {
            try {
                leseren.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            skriveren.close();
            try {
                forbindelse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
