package Conexiones;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class NuevoServidor {

    /*public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        Integer id = 0;

        JOptionPane.showMessageDialog(null, " INICIALIZANDO SERVIDOR . . . \n", "", JOptionPane.INFORMATION_MESSAGE);

        try {
            server = new ServerSocket(5000);
            JOptionPane.showMessageDialog(null, " Se ha Conectado Con El Servidor!. \n",
                    " Conexion Establecida Correctammente!. \n ", JOptionPane.INFORMATION_MESSAGE);

            while (true) {
                socket = server.accept();
                JOptionPane.showMessageDialog(null, " Nuevo Miembro Entrante!. \n ", " Nueva Conexion Inciada!. \n ",
                        JOptionPane.INFORMATION_MESSAGE);
                new Usuarios(socket, String.valueOf(id)).start();
                id++;
            }
        } catch (IOException e) {
        }
    }*/
}
