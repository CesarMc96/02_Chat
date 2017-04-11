package Cliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
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

public class PantallaCliente extends JFrame {

    private JPanel pnlPrincipal;
    private JLabel lblTitulo;
    private JPanel pnlTitulo;
    private JTextArea conversacionArea;
    private JPanel pnlArea;
    private JScrollPane scroll;
    private JTextArea mensajeArea;
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
    private JTextField txtConversacion;
    private JTextField txtMensajes;
    private static ServerSocket server;


    public PantallaCliente() {
        super.setSize(450, 400);
        super.setTitle("Chat. Cliente!");
        super.setLayout(new BorderLayout());
        super.setLocationRelativeTo(null);
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
        txtConversacion.setBackground(Color.WHITE);
        txtConversacion.setEditable(false);

        scroll = new JScrollPane(txtConversacion);
        scroll.setPreferredSize(new Dimension(280, 170));

        pnlArea = new JPanel();
        pnlArea.add(scroll);

        mensajeArea = new JTextArea();
        mensajeArea.setBorder(new LineBorder(Color.BLACK));
        mensajeArea.setPreferredSize(new Dimension(280, 70));

        txtMensajes = new JTextField();
        txtMensajes.setPreferredSize(new Dimension(280, 70));

        pnlMensaje = new JPanel();
        pnlMensaje.add(txtMensajes);

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
            mostrarMensaje(mensajeArea.getText());
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

    public void mostrarMensaje(String mensaje) {
        mensajeArea.append(mensaje + "\n");
    }

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        PantallaCliente.server = server;
    }

    public JTextArea getConversacionArea() {
        return conversacionArea;
    }

    public JTextArea getMensajeArea() {
        return mensajeArea;
    }

    public JTextField getTxtConversacion() {
        return txtConversacion;
    }

    public void setTxtConversacion(JTextField txtConversacion) {
        this.txtConversacion = txtConversacion;
    }

    public JTextField getTxtMensajes() {
        return txtMensajes;
    }

    public void setTxtMensajes(JTextField txtMensajes) {
        this.txtMensajes = txtMensajes;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

}
