package Client;

import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

class CreateFrame extends Thread{
  String width, height;
  private JFrame frame = new JFrame();
  private JDesktopPane desktop = new JDesktopPane();
  private Socket cs = null;
  private JInternalFrame serverframe = new JInternalFrame("server screen", true, true, true);

  private JPanel panel = new JPanel();

  // Called from Client.java code, wiht parameters - client socket, server computer's screen width and height
  CreateFrame(Socket cs, String width, String height) {
    this.width = width;
    this.height = height;
    this.cs = cs;
    start();
  }

  // Setting up the frame on the client screen.
  public void drawGUI() {
    frame.add(desktop, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setExtendedState(frame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
    frame.setVisible(true);
    serverframe.setLayout(new BorderLayout());
    serverframe.getContentPane().add(panel, BorderLayout.CENTER);
    serverframe.setSize(100, 100);
    desktop.add(serverframe);

    try {
      serverframe.setMaximum(true);
    }catch(Exception e) {
      System.out.println(e);
    }

    panel.setFocusable(true);
    serverframe.setVisible(true);
  }

  public void run() {
    InputStream in = null;
    drawGUI();

    try {
      in = cs.getInputStream();
    }catch(Exception e) {
      System.out.println(e);
    }

    new GetScreen(in, panel);
    new SendCommand(cs, panel, width, height);

  }

}
