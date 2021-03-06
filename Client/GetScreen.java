package Client;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class GetScreen extends Thread{
	private JPanel panel = null;
	private boolean go = true;
	InputStream is = null;
	Image serverScreen = null;

	/* Called from CreateFrame.java program with parameters - client socket's input stream, the frame created on the client side to view the server screen.*/
	public GetScreen(InputStream in,JPanel p){
		is = in;
		panel = p;
		start();
	}

	public void run(){
		try{
			// Read screenshots of the server and then draw them
			while(go){
				byte[] bytes = new byte[1024*1024];
				int count = 0;
				do{
					count += is.read(bytes,count,bytes.length-count);
				}while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));

				serverScreen = ImageIO.read(new ByteArrayInputStream(bytes));
				serverScreen = serverScreen.getScaledInstance(panel.getWidth(),panel.getHeight(),Image.SCALE_FAST);
				
				Graphics graphics = panel.getGraphics();
				graphics.drawImage(serverScreen, 0, 0, panel.getWidth(), panel.getHeight(), panel);
			}

		} catch(Exception e) {
			System.out.println(e);
		}
	}
}