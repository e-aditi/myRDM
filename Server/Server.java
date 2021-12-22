package Server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Server extends JFrame implements ActionListener{
  private static String secretKey;
  static String port = "4000";
  JButton okay;
  JPanel panel;
  JLabel info, dummy;

  Server() {
    // Generating a secret key for the session.
    secretKey = KeyGenerator.keyGenerate();
    info = new JLabel();
    info.setText("Secret key is: " + secretKey);
    dummy = new JLabel();
    dummy.setText("");
    this.setLayout(new BorderLayout());
    okay = new JButton("Okay");
    panel = new JPanel(new GridLayout(2, 1));
    panel.add(info);
    panel.add(dummy);
    panel.add(okay);

    add(panel, BorderLayout.CENTER);
    okay.addActionListener(this);

    setTitle("Secret key to connect to the server");

  }

  public static void main(String[] args) {
    Server s = new Server();
    s.setSize(300, 80);
    s.setLocation(500, 300);
    s.setVisible(true);
  }

  // when the user gives okay, Connect.java is called.
  public void actionPerformed(ActionEvent a) {
    dispose();
    new Connect(Integer.parseInt(port), secretKey);
  }

  static String getSKey() {
    return secretKey;
  } 

}