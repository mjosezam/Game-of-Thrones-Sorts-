package Estructuras;

public class NodoSimple<T> implements Nodo<T> {
	private T valor;
	private NodoSimple<T> siguiente;

	public NodoSimple(T valor) {
		this.valor = valor;
		this.siguiente = null;
	}

	public T getValor() {
		return this.valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public void setSiguiente(NodoSimple<T> siguiente) {
		this.siguiente = siguiente;
	}

	public NodoSimple<T> getSiguiente() {
		return this.siguiente;
	}

	@Override
	public Nodo<T> getAnterior() {
		return null;
	}
}