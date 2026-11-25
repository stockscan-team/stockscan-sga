package com.stockscan.view.components;

import java.awt.Dimension;
import java.io.Serial;
import javax.swing.JPanel;

/**
 * Clase base abstracta para los componentes visuales
 * reutilizables de StockScan.
 *
 * Centraliza la validación de las dimensiones del componente
 * y evita modificaciones externas mediante copias defensivas.
 *
 * @author Carmen Monge Montes
 */
public abstract class BasePanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructor protegido de la clase base.
     */
    protected BasePanel() {
        super();
    }

    /**
     * Establece el tamaño mínimo del componente.
     *
     * @param size dimensión mínima o null para restaurar
     *             el comportamiento automático.
     */
    @Override
    public void setMinimumSize(Dimension size) {
        super.setMinimumSize(
                validarYCopiarDimension(size, "tamaño mínimo")
        );
    }

    /**
     * Devuelve una copia del tamaño mínimo.
     *
     * @return copia independiente de la dimensión mínima.
     */
    @Override
    public Dimension getMinimumSize() {
        Dimension resultado = copiarDimension(super.getMinimumSize());
        return resultado;
    }

    /**
     * Establece el tamaño preferido del componente.
     *
     * @param size dimensión preferida o null para restaurar
     *             el cálculo automático.
     */
    @Override
    public void setPreferredSize(Dimension size) {
        super.setPreferredSize(
                validarYCopiarDimension(size, "tamaño preferido")
        );
    }

    /**
     * Devuelve una copia del tamaño preferido.
     *
     * @return copia independiente de la dimensión preferida.
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension resultado = copiarDimension(super.getPreferredSize());
        return resultado;
    }

    /**
     * Establece el tamaño máximo del componente.
     *
     * @param size dimensión máxima o null para restaurar
     *             el comportamiento automático.
     */
    @Override
    public void setMaximumSize(Dimension size) {
        super.setMaximumSize(
                validarYCopiarDimension(size, "tamaño máximo")
        );
    }

    /**
     * Devuelve una copia del tamaño máximo.
     *
     * @return copia independiente de la dimensión máxima.
     */
    @Override
    public Dimension getMaximumSize() {
        Dimension resultado = copiarDimension(super.getMaximumSize());
        return resultado;
    }

    /**
     * Valida y copia una dimensión.
     *
     * @param size dimensión que se desea validar.
     * @param propiedad nombre de la propiedad dimensional.
     * @return copia de la dimensión validada o null.
     * @throws IllegalArgumentException si existen dimensiones negativas.
     */
    private static Dimension validarYCopiarDimension(
            Dimension size, String propiedad) {

        Dimension resultado = null;

        if (size != null) {
            if (size.width < 0 || size.height < 0) {
                throw new IllegalArgumentException(
                        "El " + propiedad
                        + " no puede contener valores negativos."
                );
            }

            resultado = new Dimension(size);
        }

        return resultado;
    }

    /**
     * Crea una copia independiente de una dimensión.
     *
     * @param size dimensión original.
     * @return copia independiente o null.
     */
    private static Dimension copiarDimension(Dimension size) {

        Dimension resultado = null;

        if (size != null) {
            resultado = new Dimension(size);
        }

        return resultado;
    }
}

