package Client;

import java.awt.*;
import java.io.*;
import java.nio.ByteBuffer;

import javax.imageio.*;
import javax.swing.*;
import java.awt.image.*;

class GetScreen extends Thread{
	// private ObjectInputStream cObjectInputStream = null;
	private JPanel panel = null;
	private boolean go = true;
	InputStream is = null;
	Image serverScreen = null;

	public GetScreen(InputStream in,JPanel p){
		is = in;
		panel = p;
		start();
	}

	public void run(){
		try{
			//Read screenshots of the client and then draw them
			  byte[] sizeAr = new byte[4];
				is.read(sizeAr);
				int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

				byte[] imageAr = new byte[size];
				is.read(imageAr);

				serverScreen = serverScreen.getScaledInstance(panel.getWidth(),panel.getHeight(),Image.SCALE_FAST);

				//Draw the received screenshots
				Graphics graphics = panel.getGraphics();
				graphics.drawImage(serverScreen, 0, 0, panel.getWidth(), panel.getHeight(), panel);
			}

		} catch(Exception e) {
			System.out.println(e);
		}
	}
}