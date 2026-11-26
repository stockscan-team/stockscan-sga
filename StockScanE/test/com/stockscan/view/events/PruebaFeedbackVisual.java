package com.stockscan.view.events;

import com.stockscan.view.components.ComponenteAccionStockScan;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Permite probar con el ratón y el teclado el aspecto del botón y observar sus notificaciones.
 *
 *  @author Álvaro López De San Román
*/

public class PruebaFeedbackVisual implements StockScanListener {

    /** Prepara el contador y la etiqueta que mostrará los eventos en la ventana. */
    public PruebaFeedbackVisual() {
    }

    /** Etiqueta que muestra el contador y los datos de la última activación. */
    private final JLabel resultado =
            new JLabel("Notificaciones recibidas: 0");

    /** Número de eventos recibidos desde que se creó la prueba. */
    private int notificaciones = 0;

    /**
     * Programa la apertura de la ventana en el hilo de eventos de Swing.
     * @param args argumentos de ejecución (se ignoran)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PruebaFeedbackVisual().mostrarVentana();
        });
    }

    /** Abre la ventana con el botón de prueba, la casilla para habilitarlo y el resultado. */
    private void mostrarVentana() {
        JFrame ventana = new JFrame("StockScan - Prueba de feedback visual");
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ComponenteAccionStockScan componente =
                new ComponenteAccionStockScan();

        componente.addStockScanListener(this);

        JCheckBox habilitado =
                new JCheckBox("Componente habilitado", true);

        habilitado.addActionListener(evento -> {
            componente.setEnabled(habilitado.isSelected());
        });

        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        panel.add(habilitado, BorderLayout.NORTH);
        panel.add(componente, BorderLayout.CENTER);
        panel.add(resultado, BorderLayout.SOUTH);

        ventana.setContentPane(panel);
        ventana.setSize(620, 220);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    /**
     * Actualiza la etiqueta con el contador, el código y el mensaje del evento recibido.
     *
     * @param evento información enviada por el componente
     */
    
    @Override
    public void alNotificarAccion(StockScanEvent evento) {
        notificaciones++;

        resultado.setText(
                "Notificaciones: " + notificaciones
                + " | " + evento.getCodigo()
                + " | " + evento.getMensaje()
        );
    }
}