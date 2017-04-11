package Cliente;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class EnviarMensaje implements Runnable {

    private PantallaCliente pantalla;
    private final Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private String mensaje;

    public EnviarMensaje(Socket socket, Frame frame) {
        this.socket = socket;
        pantalla = (PantallaCliente) frame;
        pantalla.getTxtConversacion().addActionListener((ActionEvent e) -> {
            mensaje = e.getActionCommand();
            enviarDatos(mensaje, pantalla.getTxtNombre().getText());
        });
    }

    public void enviarDatos(String mensaje, String usuario) {
        try {
            dos.writeUTF(usuario + " : " + mensaje);
            dos.flush();
            pantalla.mostrarMensaje(usuario + " : " + mensaje);
        } catch (IOException e) {
        }
    }

    public void mostrarMensaje(String mensaje) {
        pantalla.getMensajeArea().append(mensaje);
    }

    @Override
    public void run() {
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dos.flush();
        } catch (SocketException ex) {
        } catch (IOException | NullPointerException ioException) {
        }
    }

}
