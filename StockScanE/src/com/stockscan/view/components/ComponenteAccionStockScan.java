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
 * Componente visual para confirmar una acción de ejemplo.
 *
 * Representa los estados de reposo, hover, pulsado y deshabilitado.
 * Muestra un borde de foco y notifica a los observadores cuando
 * se completa una activación del botón.
 *
 * @author Álvaro López De San Román
 */
public class ComponenteAccionStockScan extends ComponenteStockScan {

    /**
     * Estados visuales del botón.
     */
    private enum EstadoVisual {
        REPOSO, HOVER, PULSADO, DESHABILITADO
    }

    /** Botón interno del componente. */
    private final JButton boton = new JButton("Confirmar artículo");

    /** Estado visual inicial. */
    private EstadoVisual estadoVisual = EstadoVisual.REPOSO;

    /**
     * Crea el componente con su configuración inicial.
     *
     * El botón comienza habilitado, muestra «Confirmar artículo»
     * y tiene un tamaño preferido de 260 por 60 píxeles.
     */
    public ComponenteAccionStockScan() {
        // El botón ocupa el espacio central del componente.
        setLayout(new BorderLayout());

        // Se configuran las dimensiones y la interacción.
        boton.setPreferredSize(new Dimension(260, 60));
        boton.setRolloverEnabled(true);
        boton.setFocusable(true);

        // El componente gestiona el fondo y el indicador de foco.
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFocusPainted(false);

        boton.getAccessibleContext().setAccessibleDescription(
                "Confirma el artículo y notifica la acción."
        );

        // Se actualiza la apariencia cuando cambia el modelo del botón.
        boton.getModel().addChangeListener(evento -> {
            actualizarEstadoVisual();
        });

        // Se actualiza el borde al ganar o perder el foco.
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

        // Se notifica una activación válida con los datos de ejemplo.
        // Esta acción no modifica productos ni guarda en la base de datos.
        boton.addActionListener(evento -> {
            notificarObservadores("ART-01", "Artículo confirmado");
        });

        add(boton, BorderLayout.CENTER);

        // Se aplica la apariencia inicial.
        actualizarEstadoVisual();
    }

    /**
     * Consulta el texto que muestra el botón.
     *
     * @return texto actual del botón
     */
    public String getTexto() {
        return boton.getText();
    }

    /**
     * Cambia el texto mediante la API pública del componente.
     *
     * @param texto nuevo texto; no puede ser nulo
     * @throws IllegalArgumentException si el texto es nulo
     */
    public void setTexto(String texto) {
        // Se valida antes de modificar el valor existente.
        if (texto == null) {
            throw new IllegalArgumentException(
                    "El texto del botón no puede ser nulo."
            );
        }

        boton.setText(texto);

        // El nuevo texto puede afectar al espacio necesario.
        boton.revalidate();
        revalidate();

        // Se solicita actualizar la representación visual.
        boton.repaint();
        repaint();
    }

    /**
     * Habilita o deshabilita el componente y su botón interno.
     *
     * @param habilitado true para permitir la interacción;
     * false para deshabilitarla
     */
    @Override
    public void setEnabled(boolean habilitado) {
        super.setEnabled(habilitado);

        // Protege frente a llamadas durante la construcción,
        // antes de que el botón interno esté inicializado.
        if (boton != null) {
            boton.setEnabled(habilitado);
            actualizarEstadoVisual();
        }
    }

    /**
     * Calcula el estado visual a partir del modelo del botón
     * y actualiza sus colores y su borde de foco.
     */
    private void actualizarEstadoVisual() {
        ButtonModel modelo = boton.getModel();

        // El estado deshabilitado tiene prioridad.
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

        // El borde naranja identifica el foco en un botón habilitado.
        Color borde = boton.hasFocus() && boton.isEnabled()
                ? new Color(245, 158, 11)
                : fondo;

        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borde, 3),
                BorderFactory.createEmptyBorder(10, 18, 10, 18)
        ));

        boton.repaint();
    }
}