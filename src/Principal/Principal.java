package Principal;

import Cliente.EnviarMensaje;
import Cliente.PantallaCliente;
import Cliente.RecibirMensaje;
import Servidor.EnviarMensajes;
import Servidor.PantallaServidor;
import Servidor.RecibirMensajes;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JOptionPane;

public class Principal {

    private static ServerSocket server;
    private static Socket socket;
    private static Socket socket1;

    public static void main(String[] args) {
        PantallaServidor pantalla = new PantallaServidor();
        ExecutorService correr = Executors.newCachedThreadPool();

        PantallaCliente pantalla1 = new PantallaCliente();
        ExecutorService correr1 = Executors.newCachedThreadPool();

        try {
            server = new ServerSocket(3000);
            JOptionPane.showMessageDialog(null, "Esperando Cliente . . . \n ", "", JOptionPane.INFORMATION_MESSAGE);
            while (true) {
                try {
                    socket = server.accept();
                    JOptionPane.showMessageDialog(null, " Conectado a : " + socket.getInetAddress().getHostName(), "", JOptionPane.INFORMATION_MESSAGE);
                    correr.execute(new RecibirMensajes(socket, pantalla));
                    correr.execute(new EnviarMensajes(socket, pantalla));
                } catch (IOException e) {
                }
            }
        } catch (IOException e) {
        } finally {
            correr.shutdown();
        }

        //************************
        JOptionPane.showMessageDialog(null, "Buscando Servidor . . . ", "", JOptionPane.INFORMATION_MESSAGE);

        try {
            socket1 = new Socket("localhost", 3000);
            JOptionPane.showMessageDialog(null, "Conectado a : " + socket1.getInetAddress().getHostName(),
                    "", JOptionPane.INFORMATION_MESSAGE);
            correr1.execute(new RecibirMensaje(socket1, pantalla1));
            correr1.execute(new EnviarMensaje(socket1, pantalla1));
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        } finally {
            correr1.shutdown();
        }
    }
}
