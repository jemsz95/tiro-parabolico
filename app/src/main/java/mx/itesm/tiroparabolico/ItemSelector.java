package mx.itesm.tiroparabolico;

/**
 * Autor: Racket
 * Creación: 14 de Mayo 2017
 * Última modificación: 14 de Mayo 2017
 * Descipción: Interfaz que cumple una clase que puede seleccionar elementos de una lista
 */
interface ItemSelector {
    boolean isSelected(String id);
}