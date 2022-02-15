import java.io.*;
import java.net.*;

class SocketTjener {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    ServerSocket tjener = new ServerSocket(PORTNR);
    System.out.println("Log for the serverside. We are waiting...");
    Socket forbindelse = tjener.accept();

    InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
    BufferedReader leseren = new BufferedReader(leseforbindelse);
    PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

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

    leseren.close();
    skriveren.close();
    forbindelse.close();
  }
}
