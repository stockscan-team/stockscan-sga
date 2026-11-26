package com.stockscan.view.events;

import com.stockscan.controller.FeedbackController;
import com.stockscan.view.components.FeedbackStockScan;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 * Comprueba el feedback y las notificaciones mediante el modelo de Swing.
 * No sustituye las pruebas visuales con ratón y teclado.
 */
public class PruebaFeedbackIntegrado {

    private static void comprobar(boolean condicion, String descripcion) {
        if (!condicion) {
            throw new AssertionError(descripcion);
        }

        System.out.println("PASS | " + descripcion);
    }

    /**
     * Ejecuta las pruebas en el hilo de eventos de Swing.
     *
     * @param args argumentos de ejecución
     * @throws Exception si falla una prueba o su ejecución
     */
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            JButton boton = new JButton("Confirmar");

            int[] accionesOriginales = {0};

            boton.addActionListener(evento -> {
                accionesOriginales[0]++;
            });

            FeedbackStockScan feedback = new FeedbackStockScan();
            FeedbackController receptor = new FeedbackController();

            feedback.addStockScanListener(receptor);

            StockScanEvent[] recibido = {null};

            feedback.addStockScanListener(evento -> {
                recibido[0] = evento;
            });

            feedback.instalar(boton, "PRUEBA", null);

            ButtonModel modelo = boton.getModel();

            comprobar(
                    FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.REPOSO,
                    "Estado inicial de reposo"
            );

            modelo.setRollover(true);

            comprobar(
                    FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.HOVER
                    && receptor.getRecibidos() == 0,
                    "Hover sin notificación"
            );

            modelo.setArmed(true);
            modelo.setPressed(true);

            comprobar(
                    FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.PULSADO
                    && receptor.getRecibidos() == 0,
                    "Pulsación sin notificación anticipada"
            );

            modelo.setPressed(false);
            modelo.setArmed(false);

            comprobar(
                    receptor.getRecibidos() == 1
                    && accionesOriginales[0] == 1,
                    "Una notificación y acción original conservada"
            );

            comprobar(
                    recibido[0] != null
                    && recibido[0].getSource() == feedback
                    && "PRUEBA".equals(recibido[0].getCodigo())
                    && "Activación: Confirmar".equals(
                            recibido[0].getMensaje()
                    ),
                    "Origen, código y mensaje correctos"
            );

            comprobar(
                    FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.HOVER,
                    "Recuperación de hover después de activar"
            );

            modelo.setArmed(true);
            modelo.setPressed(true);

            modelo.setRollover(false);
            modelo.setArmed(false);
            modelo.setPressed(false);

            comprobar(
                    receptor.getRecibidos() == 1
                    && accionesOriginales[0] == 1
                    && FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.REPOSO,
                    "Cancelación sin notificación ni acción"
            );

            boton.setEnabled(false);
            boton.doClick(0);

            comprobar(
                    receptor.getRecibidos() == 1
                    && accionesOriginales[0] == 1
                    && FeedbackStockScan.estado(boton)
                    == FeedbackStockScan.Estado.DESHABILITADO,
                    "Botón deshabilitado sin activación"
            );

            boton.setEnabled(true);
            boton.doClick(0);

            comprobar(
                    receptor.getRecibidos() == 2
                    && accionesOriginales[0] == 2,
                    "Recuperación del funcionamiento al habilitar"
            );

            feedback.removeStockScanListener(receptor);
            boton.doClick(0);

            comprobar(
                    receptor.getRecibidos() == 2
                    && accionesOriginales[0] == 3,
                    "El receptor retirado deja de recibir eventos"
            );

            Object pulsar = boton.getInputMap(
                    JComponent.WHEN_FOCUSED
            ).get(KeyStroke.getKeyStroke("pressed SPACE"));

            Object soltar = boton.getInputMap(
                    JComponent.WHEN_FOCUSED
            ).get(KeyStroke.getKeyStroke("released SPACE"));

            comprobar(
                    pulsar != null
                    && soltar != null
                    && boton.getActionMap().get(pulsar) != null
                    && boton.getActionMap().get(soltar) != null,
                    "Asociaciones y acciones de Espacio disponibles"
            );

            System.out.println();
            System.out.println("PRUEBAS DE FEEDBACK COMPLETADAS");
        });
    }
}