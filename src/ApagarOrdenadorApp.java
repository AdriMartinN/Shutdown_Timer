import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class ApagarOrdenadorApp extends JFrame {
    private final JLabel tiempoRestanteLabel;
    private final JTextField minutosField;
    private int tiempoRestanteSegundos;
    Boolean aBoolean=false;

    public ApagarOrdenadorApp() {
        super("Apagar Ordenador");

        tiempoRestanteLabel = new JLabel("Bienvenido al temporizadp");
        minutosField = new JTextField(5);
        JButton agregarTiempoButton = new JButton("Agregar Tiempo");
        JButton detenerButton = new JButton("Detener Temporizador");

        agregarTiempoButton.addActionListener(e -> agregarTiempo());

        detenerButton.addActionListener(e -> detenerTemporizador());

        minutosField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    agregarTiempo();
                }
            }
        });

        // Layout
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(tiempoRestanteLabel);
        add(new JLabel("Agregar minutos:"));
        add(minutosField);
        add(agregarTiempoButton);
        add(detenerButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);

        tiempoRestanteSegundos = 0;
        actualizarTiempoRestante();
        iniciarTemporizador();
    }

    private void agregarTiempo() {
        try {
            int minutos = Integer.parseInt(minutosField.getText());
            tiempoRestanteSegundos += minutos * 60;
            actualizarTiempoRestante();
            minutosField.setText("");
            aBoolean=true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un número válido de minutos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void detenerTemporizador() {
        tiempoRestanteSegundos = 0;
        aBoolean=false;
        actualizarTiempoRestante();
    }

    private void actualizarTiempoRestante() {
        int horas = tiempoRestanteSegundos / 3600;
        int minutos = (tiempoRestanteSegundos % 3600) / 60;
        int segundos = tiempoRestanteSegundos % 60;
        tiempoRestanteLabel.setText(String.format("Tiempo restante: %02d:%02d:%02d", horas, minutos, segundos));
    }

    private void apagarOrdenador() {
        try {
            // Ejecuta el comando de apagado del sistema
            Process process = Runtime.getRuntime().exec("shutdown -s -t 1");
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void iniciarTemporizador() {
        Timer timer = new Timer(1000, e -> {
            if (tiempoRestanteSegundos > 0 && aBoolean) {
                tiempoRestanteSegundos--;
                actualizarTiempoRestante();
            } else if(tiempoRestanteSegundos <= 0 && aBoolean) {
                // Apagar el ordenador solo cuando el temporizador llegue a cero
                apagarOrdenador();
                detenerTemporizador();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ApagarOrdenadorApp().setVisible(true));
    }
}