package Estructuras;

public interface Nodo<T> {

	T getValor();

	/**
	 * Retorna el nodo siguiente
	 * 
	 * @return Siguiente
	 */
	Nodo<T> getSiguiente();

	/**
	 * Retorna el nodo anterior.
	 * 
	 * @return Anterior
	 */
	Nodo<T> getAnterior();

	/**
	 * Ingresa un valor en el nodo
	 * 
	 * @param valor dato a ingresar.
	 */
	void setValor(T valor);

}