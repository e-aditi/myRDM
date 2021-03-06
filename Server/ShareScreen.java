package Server;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import javax.imageio.*;

class ShareScreen extends Thread{
  Socket socket;
  Robot robot;
  Rectangle rect;
  boolean go;
  OutputStream oos;

  // Called from Connect.java with parameters - server sise socket, robot object and the dimension of the server screen
  ShareScreen (Socket s, Robot r, Rectangle re) {
    this.socket = s;
    this.robot = r;
    this.rect = re;
    go = true;
    start();
  }

  public void run() {
    try {
      oos = socket.getOutputStream();
    } catch (Exception e) {
      System.out.println(e);
    }

    while(go) {
      // Image is captured and written to the socket.
      BufferedImage image = robot.createScreenCapture(rect);
      try {
        ImageIO.write(image, "jpeg", oos);
      }catch (Exception e) {
        System.out.println(e);
      }

      try {
        Thread.sleep(10);
      }catch(Exception e) {
        System.out.println(e);
      }
    }
  }

}
