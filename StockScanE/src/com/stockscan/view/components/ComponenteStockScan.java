package com.stockscan.view.components;

import com.stockscan.view.events.StockScanEvent;
import com.stockscan.view.events.StockScanListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Componente base de StockScan con soporte de notificaciones.
 *
 * Hereda de BasePanel la validación de dimensiones
 * y las copias defensivas implementadas por Carmen.
 *
 * Debe utilizarse desde el hilo de eventos de Swing.
 *
 * @author Álvaro López
 */
public class ComponenteStockScan extends BasePanel {

    /** Observadores registrados para recibir notificaciones. */
    private final List<StockScanListener> observadores =
            new ArrayList<>();

    /**
     * Crea el componente sin observadores registrados.
     */
    public ComponenteStockScan() {
        super();
    }

    /**
     * Registra un observador.
     * Ignora valores nulos y observadores ya registrados.
     *
     * @param listener observador que recibirá las notificaciones
     */
    public void addStockScanListener(StockScanListener listener) {
        if (listener != null && !observadores.contains(listener)) {
            observadores.add(listener);
        }
    }

    /**
     * Retira un observador.
     * Si no estaba registrado, no realiza cambios.
     *
     * @param listener observador que dejará de recibir notificaciones
     */
    public void removeStockScanListener(StockScanListener listener) {
        observadores.remove(listener);
    }

    /**
     * Notifica una acción a los observadores registrados.
     *
     * El componente es el origen del evento.
     * Los observadores se ejecutan en el hilo de la llamada.
     * Durante la notificación no deben modificar la lista
     * de observadores.
     *
     * @param codigo código asociado a la acción
     * @param mensaje descripción de la acción
     */
    public void notificarObservadores(String codigo, String mensaje) {
        if (observadores.isEmpty()) {
            return;
        }

        StockScanEvent evento = new StockScanEvent(
                this,
                codigo,
                mensaje
        );

        for (StockScanListener listener : observadores) {
            listener.alNotificarAccion(evento);
        }
    }
}