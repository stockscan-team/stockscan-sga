package com.stockscan.view.events;

import java.util.EventListener;

/**
 * Oyente que recibe las notificaciones de un componente de StockScan.
 * Se puede implementar con una expresión lambda para procesar el origen, el código
 * y el mensaje de cada evento.
 *
 * @author Álvaro López
 * @version 1.0
 * @see com.stockscan.view.components.ComponenteStockScan
 */
public interface StockScanListener extends EventListener {

    /**
     * Procesa el evento entregado por el componente al emitir una notificación.
     *
     * @param evento evento recibido, con el origen, el código y el mensaje
     */
    void alNotificarAccion(StockScanEvent evento);
}
