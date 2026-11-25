package com.stockscan.view.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.function.BooleanSupplier;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

/**
 * 
 * @author Álvaro López De San Román
*/
public class FeedbackStockScan extends ComponenteStockScan {

    /**
     * Estados de interacción independientes del filtro seleccionado.
     */
    public enum Estado {
        REPOSO, HOVER, PULSADO, DESHABILITADO
    }

    /**
     *
     * @param boton botón que se consulta
     * @return estado visual actual
     */
    public static Estado estado(JButton boton) {
        if (!boton.isEnabled()) {
            return Estado.DESHABILITADO;
        }

        if (boton.getModel().isPressed()
                && boton.getModel().isArmed()) {
            return Estado.PULSADO;
        }

        if (boton.getModel().isRollover()) {
            return Estado.HOVER;
        }

        return Estado.REPOSO;
    }

    /**
     *
     * @param boton botón existente de la pantalla
     * @param codigo identificador que se enviará en el evento
     * @param seleccionado consulta del filtro seleccionado;
     * null para botones normales
     */
    public void instalar(
            JButton boton,
            String codigo,
            BooleanSupplier seleccionado) {

        Color original = boton.getBackground();
        Color texto = boton.getForeground();
        Border borde = boton.getBorder();

        boton.setRolloverEnabled(true);
        boton.setFocusable(true);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFocusPainted(false);
        boton.setBorderPainted(true);

        Runnable actualizar = () -> {
            Estado actual = estado(boton);

            boton.putClientProperty("stockscan.estado", actual);

            Color base;

            if (seleccionado == null) {
                base = original;
            } else if (seleccionado.getAsBoolean()) {
                base = new Color(27, 79, 114);
            } else {
                base = new Color(240, 242, 245);
            }

            Color fondo = base;

            switch (actual) {
                case DESHABILITADO:
                    fondo = new Color(226, 232, 240);
                    break;

                case PULSADO:
                    fondo = base.darker().darker();
                    break;

                case HOVER:
                    fondo = base.darker();
                    break;

                default:
                    break;
            }

            boton.setBackground(fondo);

            if (actual == Estado.DESHABILITADO) {
                boton.setForeground(Color.DARK_GRAY);
            } else if (seleccionado == null) {
                boton.setForeground(texto);
            } else if (seleccionado.getAsBoolean()) {
                boton.setForeground(Color.WHITE);
            } else {
                boton.setForeground(new Color(110, 110, 115));
            }

            if (boton.hasFocus() && boton.isEnabled()) {
                boton.setBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(
                                        new Color(245, 158, 11), 2
                                ),
                                borde
                        )
                );
            } else {
                boton.setBorder(borde);
            }

            boton.setCursor(
                    Cursor.getPredefinedCursor(
                            boton.isEnabled()
                                    ? Cursor.HAND_CURSOR
                                    : Cursor.DEFAULT_CURSOR
                    )
            );

            boton.repaint();
        };

        boton.putClientProperty("stockscan.actualizar", actualizar);

        boton.getModel().addChangeListener(evento -> {
            actualizar.run();
        });

        boton.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent evento) {
                actualizar.run();
            }

            @Override
            public void focusLost(FocusEvent evento) {
                actualizar.run();
            }
        });

        boton.addActionListener(evento -> {
            notificarObservadores(
                    codigo,
                    "Activación: " + boton.getText()
            );
        });

        actualizar.run();
    }

    /**
     *
     * @param botones botones del grupo que se debe actualizar
     */
    public void refrescar(JButton[] botones) {
        for (JButton boton : botones) {
            Runnable actualizar = (Runnable) boton.getClientProperty(
                    "stockscan.actualizar"
            );

            if (actualizar != null) {
                actualizar.run();
            }
        }
    }
}