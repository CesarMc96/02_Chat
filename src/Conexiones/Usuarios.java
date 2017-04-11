package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Usuarios extends Thread {
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String idContra;
	
	public Usuarios() {
	
	}
	
	public Usuarios( Socket socket, String id ) {
		
		this.socket = socket;
		idContra = id;
		
		try {
			
			dis = new DataInputStream( this.socket.getInputStream() );
			dos = new DataOutputStream( this.socket.getOutputStream() );
			
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		
	}
	
	public void desconectarSocket() {
		
		try {
			
			socket.close();
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {

		String inicio = "";
		
		try {
			
			inicio = dis.readUTF();
			
			if ( inicio.equals("Hola") ) {
				
				JOptionPane.showMessageDialog(null, "El Cliente : " + socket.getInetAddress().getHostName() + 
					" Saluda!. \n Cliente Nuevo Detectado \n ", " Un Nuevo Miembro ha Ingresado!. \n  ", 
					JOptionPane.INFORMATION_MESSAGE);
				
				dos.close();
				
			}
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		desconectarSocket();
		
	}
	
	
	
	
	

}
