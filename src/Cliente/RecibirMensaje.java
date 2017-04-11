package Cliente;

import java.awt.Frame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import javax.swing.JOptionPane;

public class RecibirMensaje implements Runnable {
	
	private PantallaCliente pantalla;
	private Socket socket;
	private DataOutputStream dos;
	private DataInputStream dis;
	private String mensaje;
	
	public RecibirMensaje( Socket socket, Frame frame ) {
		
		this.socket = socket;
		pantalla = (PantallaCliente) frame;
		
	}
	
	public void mostrarMensaje( String mensaje ) {
		
		pantalla.getMensajeArea().append(mensaje);
		
	}

	@Override
	public void run() {

		try {
			
			dis = new DataInputStream( socket.getInputStream() );
			
		} catch (IOException e) {

			e.printStackTrace();
		}
			
		do {
		
			try {
				
				mensaje = dis.readUTF();
				pantalla.mostrarMensaje(mensaje);
				
			} catch ( SocketException ex ) { 
				
				ex.printStackTrace();
				
			} catch ( EOFException ex ) { 
				
				ex.printStackTrace();
				
			} catch (IOException e) {
			
					e.printStackTrace();
					
			} 
		
		} while ( !mensaje.equals("Adios!. \n " ) );
		
		try {
			
			dis.close();
			socket.close();
			
			
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		
		JOptionPane.showMessageDialog(null, " fin de la Conexion!. \n ", " Se ha Finalizado a conexion!. \n ",
				JOptionPane.INFORMATION_MESSAGE);

	}
	
	

}
