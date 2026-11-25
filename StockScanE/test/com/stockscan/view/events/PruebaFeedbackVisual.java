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
 *
 *  @author Álvaro López De San Román
*/

public class PruebaFeedbackVisual implements StockScanListener {

    private final JLabel resultado =
            new JLabel("Notificaciones recibidas: 0");

    private int notificaciones = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PruebaFeedbackVisual().mostrarVentana();
        });
    }

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