package Server;

import java.awt.*;
// import javax.swing.*;
import java.io.*;
import java.net.*;

class Connect {
  ServerSocket socket;
  DataInputStream keyin;
  DataOutputStream check;
  String width;
  String height;

  Connect(int port, String key) {
    Robot robot = null;
    Rectangle rect = null;
    try {
      System.out.println("waiting for client to connect..");
      socket = new ServerSocket(port);

      GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gdev = genv.getDefaultScreenDevice();

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      width = "" + dim.getWidth();
      height = "" + dim.getHeight();
      rect = new Rectangle(dim);
      robot = new Robot(gdev);

      drawGUI();

      while(true) {
        Socket sc = socket.accept();
        keyin = new DataInputStream(sc.getInputStream());
        check = new DataOutputStream(sc.getOutputStream());
        String k = keyin.readUTF();

        System.out.println(Server.getSKey());
        if(k.equals(Server.getSKey())) {
          check.writeUTF("yes");
          check.writeUTF(width);
          check.writeUTF(height);
          new ShareScreen(sc, robot, rect);
          new GetCommands(sc, robot);
        }

        else {
          check.writeUTF("no");
        }

      }

    }
    catch (Exception e) {
      System.out.println(e);
    }
  }
  private void drawGUI() {

  }
}