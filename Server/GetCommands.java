package Server;
import java.awt.*;
// import java.io.*;
import java.net.*;
import java.util.*;

class GetCommands extends Thread{
  Socket socket;
  Robot robot;
  boolean go;
  
  GetCommands(Socket s, Robot r) {
    this.socket = s;
    this.robot = r;
    go = true;
    start();
  } 
  
  public void run() {
    Scanner sc = null;
    try {
      sc = new Scanner(socket.getInputStream());
      while(go) {
        int command = sc.nextInt();
        switch(command) {
          case-10: robot.mousePress(sc.nextInt());
          break;
          case-12: robot.mouseRelease(sc.nextInt());
          break;
          case-13: robot.keyPress(sc.nextInt());
          break;
          case-14: robot.keyRelease(sc.nextInt());
          break;
          case-15: robot.mouseMove(sc.nextInt(), sc.nextInt());
          break;

        }
      }
    } catch (Exception e) {
      System.out.println(e);
    }

  }
}
