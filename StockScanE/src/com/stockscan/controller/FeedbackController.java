package com.stockscan.controller;

import com.stockscan.view.events.StockScanEvent;
import com.stockscan.view.events.StockScanListener;

/**
 * 
 * @author Álvaro López De San Román
*/
public class FeedbackController implements StockScanListener {

    private int recibidos;

    /**
     * Recibe una notificación y la muestra en la salida de NetBeans.
     *
     * @param evento información de la activación
     */
    @Override
    public void alNotificarAccion(StockScanEvent evento) {
        recibidos++;

        System.out.println(
                "StockScan #" + recibidos
                + " | " + evento.getCodigo()
                + " | " + evento.getMensaje()
        );
    }

    /**
     * Consulta el número de notificaciones recibidas.
     *
     * @return cantidad de eventos recibidos
     */
    public int getRecibidos() {
        return recibidos;
    }
}