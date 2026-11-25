package com.stockscan.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;

/**
 *
 * @author Álvaro López De San Román
 */
public class ComponenteAccionStockScan extends ComponenteStockScan {

    private enum EstadoVisual {
        REPOSO, HOVER, PULSADO, DESHABILITADO
    }

    private final JButton boton = new JButton("Confirmar artículo");
    private EstadoVisual estadoVisual = EstadoVisual.REPOSO;

    public ComponenteAccionStockScan() {
        setLayout(new BorderLayout());

        boton.setPreferredSize(new Dimension(260, 60));
        boton.setRolloverEnabled(true);
        boton.setFocusable(true);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFocusPainted(false);

        boton.getAccessibleContext().setAccessibleDescription(
                "Confirma el artículo y notifica la acción."
        );

        boton.getModel().addChangeListener(evento -> {
            actualizarEstadoVisual();
        });

        boton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evento) {
                actualizarEstadoVisual();
            }

            @Override
            public void focusLost(FocusEvent evento) {
                actualizarEstadoVisual();
            }
        });

        boton.addActionListener(evento -> {
            notificarObservadores("ART-01", "Artículo confirmado");
        });

        add(boton, BorderLayout.CENTER);
        actualizarEstadoVisual();
    }

    /**
     *
     * @param habilitado true para permitir la interacción
     */
    @Override
    public void setEnabled(boolean habilitado) {
        super.setEnabled(habilitado);

        if (boton != null) {
            boton.setEnabled(habilitado);
            actualizarEstadoVisual();
        }
    }

    private void actualizarEstadoVisual() {
        ButtonModel modelo = boton.getModel();

        if (!modelo.isEnabled()) {
            estadoVisual = EstadoVisual.DESHABILITADO;
        } else if (modelo.isPressed() && modelo.isArmed()) {
            estadoVisual = EstadoVisual.PULSADO;
        } else if (modelo.isRollover()) {
            estadoVisual = EstadoVisual.HOVER;
        } else {
            estadoVisual = EstadoVisual.REPOSO;
        }

        Color fondo;

        switch (estadoVisual) {
            case HOVER:
                fondo = new Color(37, 99, 235);
                break;

            case PULSADO:
                fondo = new Color(23, 37, 84);
                break;

            case DESHABILITADO:
                fondo = new Color(226, 232, 240);
                break;

            default:
                fondo = new Color(30, 64, 175);
                break;
        }

        boton.setBackground(fondo);

        boton.setForeground(
                boton.isEnabled() ? Color.WHITE : Color.DARK_GRAY
        );

        Color borde = boton.hasFocus() && boton.isEnabled()
                ? new Color(245, 158, 11) : fondo;

        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde, 3),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));

        boton.repaint();
    }
}