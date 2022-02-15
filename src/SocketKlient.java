import java.io.*;
import java.net.*;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

class SocketKlient {
  public static void main(String[] args) throws IOException {
    final int PORTNR = 1250;

    Scanner leserFraKommandovindu = new Scanner(System.in);
    System.out.println("Give the name of the machine where the server site is running.");
    String tjenermaskin = leserFraKommandovindu.nextLine();

    Socket forbindelse = new Socket(tjenermaskin, PORTNR);
    System.out.println("The connection is created");

    InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
    BufferedReader leseren = new BufferedReader(leseforbindelse);
    PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

    String innledning1 = leseren.readLine();
    String innledning2 = leseren.readLine();
    String innledning3 = leseren.readLine();
    String innledning4 = leseren.readLine();
    System.out.println(innledning1 + "\n" + innledning2 + "\n" + innledning3 + "\n\n" + innledning4);

    String enLinje = leserFraKommandovindu.nextLine();
    while (!enLinje.equals("")) {
      int firstNumber = parseInt(enLinje);
      skriveren.println(enLinje);
      System.out.println("Enter a number");
      enLinje = leserFraKommandovindu.nextLine();
      skriveren.println(enLinje);
      int secondNumber = parseInt(enLinje);
      System.out.println("Write 'a' if you want to add or write 's' if you want to subtract: ");
      enLinje = leserFraKommandovindu.nextLine();
      skriveren.println(enLinje);
      if(enLinje.equals("a")){
        int resultFromAdd = firstNumber+secondNumber;
        skriveren.println(resultFromAdd);
        System.out.println(firstNumber+" + "+secondNumber+" = "+resultFromAdd);
      }
      else if(enLinje.equals("s")){
        int resultFromSub = firstNumber-secondNumber;
        skriveren.println(resultFromSub);
        System.out.println(firstNumber+" - "+secondNumber+" = "+resultFromSub);
      }
      else System.out.println("You have entered something wrong, please try again.");

      System.out.println("\nEnter a number");
      enLinje = leserFraKommandovindu.nextLine();
    }

    leseren.close();
    skriveren.close();
    forbindelse.close();
  }
}
