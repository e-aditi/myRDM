package Server;
import java.awt.*;
import java.net.*;
import java.util.*;

class GetCommands extends Thread{
  Socket socket;
  Robot robot;
  boolean go;
  
  // Called from Connect.java with the parameters Server side socket and robot object.
  GetCommands(Socket s, Robot r) {
    this.socket = s;
    this.robot = r;
    go = true;
    start();
  } 
  
  // Reading the abbreviation from the socket, expanding the abbreviation and performing the required action.
  public void run() {
    Scanner sc = null;
    try {
      sc = new Scanner(socket.getInputStream());
      while(go) {
        int command = sc.nextInt();
        switch(command) {
          case-1: robot.mousePress(sc.nextInt());
          break;
          case-2: robot.mouseRelease(sc.nextInt());
          break;
          case-3: robot.keyPress(sc.nextInt());
          break;
          case-4: robot.keyRelease(sc.nextInt());
          break;
          case-5: robot.mouseMove(sc.nextInt(), sc.nextInt());
          break;

        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
