package com.stockscan.view.events;

import com.stockscan.view.components.ComponenteStockScan;
import java.awt.Dimension;
import java.util.function.Consumer;
import java.util.function.Supplier;
import javax.swing.SwingUtilities;

/**
 * Comprueba la integración de BasePanel con ComponenteStockScan.
 */
public class PruebaBasePanel {

    private static void comprobar(boolean condicion, String descripcion) {
        if (!condicion) {
            throw new AssertionError(descripcion);
        }

        System.out.println("PASS | " + descripcion);
    }

    /**
     * Comprueba una propiedad dimensional.
     */
    private static void probarDimension(
            String nombre,
            Consumer<Dimension> establecer,
            Supplier<Dimension> obtener) {

        Dimension original = new Dimension(300, 100);
        establecer.accept(original);

        comprobar(
                obtener.get().equals(new Dimension(300, 100)),
                nombre + ": acepta dimensiones válidas"
        );

        original.width = 900;

        comprobar(
                obtener.get().width == 300,
                nombre + ": protege frente a cambios del objeto original"
        );

        Dimension copia = obtener.get();
        copia.height = 800;

        comprobar(
                obtener.get().height == 100,
                nombre + ": devuelve una copia independiente"
        );

        boolean anchoRechazado = false;

        try {
            establecer.accept(new Dimension(-1, 100));
        } catch (IllegalArgumentException excepcion) {
            anchoRechazado = true;
        }

        comprobar(
                anchoRechazado,
                nombre + ": rechaza un ancho negativo"
        );

        boolean altoRechazado = false;

        try {
            establecer.accept(new Dimension(300, -1));
        } catch (IllegalArgumentException excepcion) {
            altoRechazado = true;
        }

        comprobar(
                altoRechazado,
                nombre + ": rechaza un alto negativo"
        );

        comprobar(
                obtener.get().equals(new Dimension(300, 100)),
                nombre + ": conserva el valor anterior tras el rechazo"
        );
    }

    /**
     * Ejecuta las pruebas en el hilo de eventos de Swing.
     *
     * @param args argumentos de ejecución
     * @throws Exception si falla una prueba o su ejecución
     */
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeAndWait(() -> {
            ComponenteStockScan componente = new ComponenteStockScan();

            probarDimension(
                    "Tamaño mínimo",
                    componente::setMinimumSize,
                    componente::getMinimumSize
            );

            probarDimension(
                    "Tamaño preferido",
                    componente::setPreferredSize,
                    componente::getPreferredSize
            );

            probarDimension(
                    "Tamaño máximo",
                    componente::setMaximumSize,
                    componente::getMaximumSize
            );

            componente.setMinimumSize(null);
            componente.setPreferredSize(null);
            componente.setMaximumSize(null);

            comprobar(
                    !componente.isMinimumSizeSet()
                    && !componente.isPreferredSizeSet()
                    && !componente.isMaximumSizeSet(),
                    "Null permite recuperar los tamaños automáticos"
            );

            int[] notificaciones = {0};

            componente.addStockScanListener(evento -> {
                notificaciones[0]++;
            });

            componente.notificarObservadores(
                    "ART-01",
                    "Prueba de integración"
            );

            comprobar(
                    notificaciones[0] == 1,
                    "Las notificaciones siguen funcionando"
            );

            System.out.println();
            System.out.println("PRUEBAS DE BASEPANEL COMPLETADAS");
        });
    }
}