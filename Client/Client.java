package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class Client extends JFrame implements ActionListener{
  static String port = "4000";
  private Socket cs;
  DataOutputStream keyout;
  DataInputStream check;
  String verify;
  JButton submit;
  JPanel panel;
  JLabel label, label1;
  String width, height;
  JTextField text;

  public void initialize (String ip, int port) {
    try {
      cs = new Socket(ip, port);
      System.out.println("Conneting to the server");
      authenticate(cs);

      }catch(Exception e) {
        System.out.println(e);
      }
  }

  public static void main(String[] args) {
    String ip = JOptionPane.showInputDialog("Server ip: ");
    Client c = new Client();
    c.initialize(ip, Integer.parseInt(port));
  }

  void authenticate(Socket cs) {
    label1 = new JLabel();
    label1.setText("Secret key");
    text = new JTextField(15);
    label = new JLabel();
    label.setText("");
    this.setLayout(new BorderLayout());

    submit = new JButton("SUBMIT");

    panel = new JPanel(new GridLayout(2, 1));
    panel.add(label1);
    panel.add(text);
    panel.add(label);
    panel.add(submit);
    add(panel, BorderLayout.CENTER);
    submit.addActionListener(this);
    setTitle("login");
    setSize(300, 80);
    setLocation(500, 300);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent ae) {
    String value1 = text.getText();
    try {
      keyout = new DataOutputStream(cs.getOutputStream());
      check = new DataInputStream(cs.getInputStream());
      keyout.writeUTF(value1);
      verify = check.readUTF();
    }catch (Exception e) {
      System.out.println(e);
    }

    System.out.println(verify);

    if(verify.equals("yes")){
      try {
        width = check.readUTF();
        height = check.readUTF();
      }catch (Exception e) {
        System.out.println(e);
      }
      new CreateFrame(cs, width, height);
      dispose();
    }
    else {
      System.out.println("enter a valid key");
      JOptionPane.showMessageDialog(this, "Incorrect key", "error", JOptionPane.ERROR_MESSAGE);
      dispose();
    }
  }
}