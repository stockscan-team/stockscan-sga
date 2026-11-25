package com.stockscan.view.events;

import java.util.EventObject;

/**
 * Evento de StockScan que contiene el código de la acción y su mensaje.
 * Ambos valores quedan fijados al construir el evento. El oyente puede consultar
 * el origen a través de {@link #getSource()}.
 *
 * @author Álvaro López
 * @version 1.0
 * @see StockScanListener
 */
public class StockScanEvent extends EventObject {

    /** Código asociado a la acción. */
    private final String codigo;

    /** Texto de la notificación. */
    private final String mensaje;

    /**
     * Crea un evento con el origen y los datos de la notificación.
     *
     * @param source objeto que origina el evento
     * @param codigo código asociado a la acción, que puede ser {@code null}
     * @param mensaje texto de la notificación, que puede ser {@code null}
     * @throws IllegalArgumentException si {@code source} es {@code null}
     */
    public StockScanEvent(Object source, String codigo, String mensaje) {
        super(source);
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    /**
     * Devuelve el código asociado a la acción.
     *
     * @return código recibido al construir el evento, o {@code null} si no se indicó
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el texto de la notificación.
     *
     * @return mensaje recibido al construir el evento, o {@code null} si no se indicó
     */
    public String getMensaje() {
        return mensaje;
    }
}
