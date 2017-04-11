package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class PersonaCliente extends Thread {
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	String respuesta;
	Integer id;
	
	public PersonaCliente( Integer id ) {
		
		this.id = id;
		
	}

	@Override
	public void run() {

		try {
			
			socket = new Socket("localhost", 5000);
			dis = new DataInputStream( socket.getInputStream() );
			dos = new DataOutputStream( socket.getOutputStream() );
			System.out.println( id + " Envia Saludos!. \n " );
			dos.writeUTF("Hola");
			
			respuesta = dis.readUTF();
			System.out.println( id + " Servidor Devuelve Saludo!. \n " + respuesta );
			
			dis.close();
			dos.close();
			socket.close();
			
		} catch (UnknownHostException e) {
		
			e.printStackTrace();
			
		}  catch ( EOFException e ) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		
		}
			
		
	}

}
