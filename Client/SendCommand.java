package Client;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;


class SendCommand implements KeyListener, MouseMotionListener, MouseListener{
	private Socket cs = null;
	private JPanel panel = null;
	private PrintWriter writer = null;
	String width = "", height = "";
	double w;
	double h;
	
	/* Called from CreateFrame.java program with parameters - client socket, the frame created on the client side to view the server screen, width and height of the server screen.*/
	SendCommand(Socket s, JPanel p, String width, String height){
		cs = s;
		panel = p;
		this.width = width;
		this.height = height;
		w = Double.valueOf(width.trim()).doubleValue();
		h = Double.valueOf(width.trim()).doubleValue();

		panel.addKeyListener(this);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);

		try{
			// Initializing PrintWriter to send commands to the client
			writer = new PrintWriter(cs.getOutputStream());
			} catch(Exception e) {
			System.out.println(e);
		}
	}

	// Getting the abbreviation from Commands.java file and writing them to the socket.
	public void mouseDragged(MouseEvent e){
	}

	public void mouseMoved(MouseEvent e){
		double xScale = (double)w/panel.getWidth();
		double yScale = (double)h/panel.getHeight();
		writer.println(Commands.MOVE_MOUSE.getAbbrev());
		writer.println((int)(e.getX()*xScale));
		writer.println((int)(e.getY()*yScale));
		writer.flush();
	}

	public void mouseClicked(MouseEvent e){
	}

	public void mousePressed(MouseEvent e){
		writer.println(Commands.PRESS_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	public void mouseReleased(MouseEvent e){
		writer.println(Commands.RELEASE_MOUSE.getAbbrev());
		int button = e.getButton();
		int xButton = 16;
		if(button==3){
			xButton = 4;
		}
		writer.println(xButton);
		writer.flush();
	}

	public void mouseEntered(MouseEvent e){
	}

	public void mouseExited(MouseEvent e){
	}

	public void keyTyped(KeyEvent e){
	}

	public void keyPressed(KeyEvent e){
		writer.println(Commands.PRESS_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();
	}

	public void keyReleased(KeyEvent e){
		writer.println(Commands.RELEASE_KEY.getAbbrev());
		writer.println(e.getKeyCode());
		writer.flush();
	}
}
