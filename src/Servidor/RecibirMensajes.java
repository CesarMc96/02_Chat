package Servidor;

import java.awt.Frame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JOptionPane;

public class RecibirMensajes implements Runnable {

    private PantallaServidor pantalla;
    private Socket socket;
    private String mensaje;
    private DataOutputStream dos;
    private DataInputStream dis;

    public RecibirMensajes(Socket socket, Frame frame) {
        this.socket = socket;
        pantalla = (PantallaServidor) frame;
    }

    public void mostrarMensaje(String mensaje) {
        pantalla.getMensajeArea().append(mensaje);
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
        }

        do {
            try {
                mensaje = dis.readUTF();
                pantalla.mostraMensaje(mensaje);
            } catch (SocketException ex) {
            } catch (EOFException eofException) {
            } catch (IOException ex) {
            }
        } while (!mensaje.equals("Adios!."));

        try {
            dis.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
        }

        JOptionPane.showMessageDialog(null, "Fin de la Conexion!. \n ",
                " Se ha Finalizado la Conversacion!. \n ", JOptionPane.INFORMATION_MESSAGE);
    }
}
