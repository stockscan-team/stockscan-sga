package com.stockscan.view.events;

import com.stockscan.view.components.ComponenteStockScan;

/**
 * Prueba manual del registro y la retirada de oyentes de
 * {@link ComponenteStockScan}.
 * El resultado esperado es una única notificación para el código {@code ART-02}.
 */
public class PruebaNotificacion {

    /**
     * Crea una instancia de la clase de prueba sin ejecutar la comprobación.
     */
    public PruebaNotificacion() {
    }

    /**
     * Ejecuta la secuencia de prueba y escribe en la consola los eventos recibidos.
     * Comprueba el disparo sin oyentes, el registro duplicado y el desregistro.
     *
     * @param args argumentos de línea de comandos, que se ignoran
     */
    public static void main(String[] args) {
        ComponenteStockScan componente = new ComponenteStockScan();

        // 1. Disparo sin observadores registrados (no debe lanzar excepciones)
        componente.notificarObservadores("ART-01", "Sin oyentes");

        // 2. Creo un observador de prueba
        StockScanListener observador = evento -> {
            System.out.println("Notificación recibida -> Código: " + evento.getCodigo()
                    + " | Mensaje: " + evento.getMensaje());
        };

        // 3. Registro dos veces seguidas para comprobar que se evitan duplicados
        componente.addStockScanListener(observador);
        componente.addStockScanListener(observador);

        // 4. Disparo evento real (solo debe imprimirse 1 vez en consola)
        componente.notificarObservadores("ART-02", "Stock actualizado");

        // 5. Desregistro al observador y vuelvo a disparar (no debe mostrarse nada)
        componente.removeStockScanListener(observador);
        componente.notificarObservadores("ART-03", "No debe verse");
    }
}
