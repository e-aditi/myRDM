package Server;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;

import javax.imageio.*;

class ShareScreen extends Thread{
  Socket socket;
  Robot robot;
  Rectangle rect;
  boolean go;
  OutputStream os;

  ShareScreen (Socket s, Robot r, Rectangle re) {
    this.socket = s;
    this.robot = r;
    this.rect = re;
    go = true;
    start();
  }

  public void run() {
    try {
      os = socket.getOutputStream();
    } catch (Exception e) {
      System.out.println(e);
    }

    while(go) {
      BufferedImage image = robot.createScreenCapture(rect);
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();


      try {
        ImageIO.write(image, "jpeg", baos);
        byte[] size = ByteBuffer.allocate(8).putInt(baos.size()).array();
        os.write(size);
        os.write(baos.toByteArray());
        os.flush();

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
