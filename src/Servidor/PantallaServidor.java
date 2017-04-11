package Servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class PantallaServidor extends JFrame {

    private JPanel pnlPrincipal;
    private JLabel lblTitulo;
    private JPanel pnlTitulo;
    private JTextArea conversacionArea;
    private JPanel pnlArea;
    private JScrollPane scroll;
    JTextArea mensajeArea;
    private JPanel pnlMensaje;
    private JPanel pnlNombre;
    private JTextField txtNombre;
    private JButton btnEnviar;
    private JButton btnAgregar;
    private JPanel pnlBotones;
    private JPanel pnlConfig;
    private JPanel pnlEnviar;
    private JPanel pnlTotal;
    private JPanel pnlOrden;
    JTextField txtConversacion;
    private static ServerSocket server;
    private static Socket socket;

    public PantallaServidor() {
        super.setSize(450, 400);
        super.setTitle("Chat. Servidor!. ");
        super.setLocationRelativeTo(null);
        super.setLayout(new BorderLayout());
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);

        lblTitulo = new JLabel(" C H A T ");
        lblTitulo.setFont(new Font("", Font.BOLD, 22));

        pnlTitulo = new JPanel();
        pnlTitulo.add(lblTitulo);

        txtNombre = new JTextField(23);
        txtNombre.setText("Ingrese Nombre ");
        txtNombre.setForeground(Color.WHITE);
        txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
        txtNombre.setFont(new Font("", Font.BOLD, 14));
        txtNombre.setBorder(new LineBorder(Color.WHITE));
        txtNombre.setBackground(Color.BLUE);

        pnlNombre = new JPanel();
        pnlNombre.add(txtNombre);

        conversacionArea = new JTextArea();
        conversacionArea.setEditable(false);

        txtConversacion = new JTextField();
        txtConversacion.setPreferredSize(new Dimension(280, 170));

        scroll = new JScrollPane(txtConversacion);
        scroll.setPreferredSize(new Dimension(280, 170));

        pnlArea = new JPanel();
        pnlArea.add(scroll);

        mensajeArea = new JTextArea();
        mensajeArea.setBorder(new LineBorder(Color.BLACK));
        mensajeArea.setPreferredSize(new Dimension(280, 70));

        pnlMensaje = new JPanel();
        pnlMensaje.add(mensajeArea);

        btnAgregar = new JButton(" + ");
        btnAgregar.setFont(new Font("", Font.BOLD, 25));
        btnAgregar.addActionListener((ActionEvent e) -> {
        });

        pnlConfig = new JPanel();
        pnlConfig.add(btnAgregar);

        btnEnviar = new JButton();
        btnEnviar.setIcon(new ImageIcon(getClass().getResource("/Send.png")));
        btnEnviar.setBackground(Color.BLUE);
        btnEnviar.addActionListener((ActionEvent e) -> {
            mostraMensaje(mensajeArea.getText());
        });

        pnlEnviar = new JPanel();
        pnlEnviar.add(btnEnviar);

        pnlBotones = new JPanel();
        pnlBotones.setLayout(new BorderLayout());
        pnlBotones.add(pnlConfig, BorderLayout.NORTH);
        pnlBotones.add(pnlEnviar, BorderLayout.SOUTH);

        pnlOrden = new JPanel();
        pnlOrden.setLayout(new BorderLayout());
        pnlOrden.add(pnlNombre, BorderLayout.NORTH);
        pnlOrden.add(pnlArea, BorderLayout.CENTER);
        pnlOrden.add(pnlMensaje, BorderLayout.SOUTH);

        pnlTotal = new JPanel();
        pnlTotal.setLayout(new BorderLayout());
        pnlTotal.add(pnlOrden, BorderLayout.CENTER);
        pnlTotal.add(pnlBotones, BorderLayout.EAST);

        super.add(pnlTitulo, BorderLayout.NORTH);
        super.add(pnlTotal, BorderLayout.CENTER);

        super.setVisible(true);
    }

    public void mostraMensaje(String mensaje) {
        mensajeArea.append(mensaje + "\n");
    }

    public JTextArea getConversacionArea() {
        return conversacionArea;
    }

    public void setConversacionArea(JTextArea conversacionArea) {
        this.conversacionArea = conversacionArea;
    }

    public JTextArea getMensajeArea() {
        return mensajeArea;
    }

    public void setMensajeArea(JTextArea mensajeArea) {
        this.mensajeArea = mensajeArea;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtConversacion() {
        return txtConversacion;
    }

    public void setTxtConversacion(JTextField txtConversacion) {
        this.txtConversacion = txtConversacion;
    }

    public static void main(String[] args) {
        PantallaServidor pantalla = new PantallaServidor();
        ExecutorService correr = Executors.newCachedThreadPool();

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
    }
    
}
