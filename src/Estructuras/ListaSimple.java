package Estructuras;

public class ListaSimple<T> implements Lista<T> {

	public NodoSimple<T> primero;
	public int size;

	public void add(T valor) {
		NodoSimple<T> nuevo = new NodoSimple<T>(valor);
		if (empty() == true) {
			this.primero = nuevo;
		} else {
			NodoSimple<T> temp = this.primero;
			while (temp.getSiguiente() != null) {
				temp = temp.getSiguiente();
			}
			temp.setSiguiente(nuevo);
		}
		this.size++;
	}

	public boolean empty() {
		return this.primero == null;
	}

	public void delete(T valor) {
		if (primero.getValor() == valor) {
			this.primero = primero.getSiguiente();
		} else {
			NodoSimple<T> temp = this.primero;
			if (temp.getSiguiente() != null) {
				while (temp.getSiguiente().getValor() != valor) {
					temp = temp.getSiguiente();
				}
				if (temp.getSiguiente() != null) {
					NodoSimple<T> siguiente = temp.getSiguiente().getSiguiente();
					temp.setSiguiente(siguiente);
				}
			}
		}
		this.size--;
	}

	@Override
	public Nodo<T> getPrimero() {
		return primero;
	}

	@Override
	public Nodo<T> getUltimo() {
		NodoSimple<T> temp = this.primero;
		while (temp.getSiguiente() != null) {
			temp = temp.getSiguiente();
		}
		return temp;
	}
}