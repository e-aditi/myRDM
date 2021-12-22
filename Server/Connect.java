package Server;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;


class Connect {
  ServerSocket socket;
  DataInputStream keyin;
  DataOutputStream check;
  String width;
  String height;

  // Called from Server.java with parameters - port and secret key.
  Connect(int port, String key) {
    Robot robot = null;
    Rectangle rect = null;
    try {
      System.out.println("waiting for client to connect..");
      ServerSocketFactory serverSocketFactory = SSLServerSocketFactory.getDefault();
      socket =
      serverSocketFactory.createServerSocket(port);

      GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gdev = genv.getDefaultScreenDevice();

      Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
      width = "" + dim.getWidth();
      height = "" + dim.getHeight();
      rect = new Rectangle(dim);
      robot = new Robot(gdev);

      drawGUI();

      // looping to wait for the client to connect
      while(true) {
        Socket sc = socket.accept();
        keyin = new DataInputStream(sc.getInputStream());
        check = new DataOutputStream(sc.getOutputStream());
        // Getting the value entered by the user on the client side.
        String k = keyin.readUTF();

        System.out.println(k);
        // If the value matches the secret key "yes" is written to the socket, width and height of the server screen are shared.
        if(k.equals(Server.getSKey())) {
          check.writeUTF("yes");
          check.writeUTF(width);
          check.writeUTF(height);
          new ShareScreen(sc, robot, rect);
          new GetCommands(sc, robot);
        }
        // If the value does not match the secret key "no" is written to the socket
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
