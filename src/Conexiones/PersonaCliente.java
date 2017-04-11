package Conexiones;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class PersonaCliente extends Thread {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    String respuesta;
    Integer id;

    public PersonaCliente(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            socket = new Socket("localhost", 5000);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            System.out.println(id + " Envia Saludos!. ");
            dos.writeUTF("Hola");

            respuesta = dis.readUTF();
            System.out.println(id + " Servidor Devuelve Saludo!. " + respuesta);

            dis.close();
            dos.close();
            socket.close();
        } catch (UnknownHostException e) {
        } catch (EOFException e) {
        } catch (IOException e) {
        }
    }
}
