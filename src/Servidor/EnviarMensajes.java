package Servidor;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class EnviarMensajes implements Runnable {
	
	private PantallaServidor pantalla;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String mensaje;
	
	public EnviarMensajes( Socket socket, Frame ventana ) {
		
		this.socket = socket;
		pantalla = (PantallaServidor) ventana;
		
		pantalla.getTxtConversacion().addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				mensaje = e.getActionCommand();
				enviarDatos(mensaje, pantalla.getTxtNombre().getText() );
				
			}
		});
		
	}
	
	public void enviarDatos( String mensaje, String usuario ) {
		
		try {
			
			dos.writeUTF( usuario + " : " + mensaje );
			dos.flush();
			pantalla.mostraMensaje( usuario + " : " + mensaje );
			
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public void mostrarMensaje( String mensaje ) {
		
		pantalla.getMensajeArea().append(mensaje);
		
	}

	@Override
	public void run() {

		try {
			
			dos = new DataOutputStream( socket.getOutputStream() );
			dos.flush();
			
		} catch ( SocketException ex ) {
			
			ex.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
	 
	}
	
	

}
