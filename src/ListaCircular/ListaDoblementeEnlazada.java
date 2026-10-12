/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaCircular;

import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author labitson
 */
public class ListaDoblementeEnlazada<T> implements IList<T>, Iterable<T> {

    public NodoEnlazado<T> inicio;
    protected int nElementos;
    protected NodoEnlazado<T> fin;
    private NodoEnlazado<T> Actual;
    private Scanner sc;

    public ListaDoblementeEnlazada() {
        inicio = null;
        nElementos = 0;
        Actual = null;
    }

    private class NodoEnlazado<T> {

        private T dato;
        private NodoEnlazado<T> sig;
        private NodoEnlazado<T> ant;

        public NodoEnlazado(T dato) {
            this.dato = dato;
        }

    }

    public class ListIterator<T> implements Iterator<T> {

        public NodoEnlazado<T> nodoActual;

        public ListIterator(NodoEnlazado<T> inicio) {
            nodoActual = inicio;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public T next() {
            T dato = nodoActual.dato;
            nodoActual = nodoActual.sig;

            return dato;
        }
        public void irInicio() {
            nodoActual = (NodoEnlazado<T>) inicio;
        }
        public void irFinal() {
            nodoActual = (NodoEnlazado<T>) fin;
        }
        public void previous () {
            if (nodoActual != null) {
                nodoActual = nodoActual.ant;
            }
        }
     }

    @Override
    public void append(T elemento) throws ListException {
        NodoEnlazado<T> nodoNuevo = new NodoEnlazado<>(elemento);
        NodoEnlazado<T> nodo = inicio;

        if (inicio == null) {
            inicio = nodoNuevo;
            fin = nodoNuevo;
            nodoNuevo.sig = nodoNuevo;
            nodoNuevo.ant = nodoNuevo;
        } else {
            nodoNuevo.ant = fin;
            nodoNuevo.sig = inicio;
            fin.sig = nodoNuevo;
            inicio.ant = nodoNuevo;
            fin = nodoNuevo;
        }
        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        NodoEnlazado<T> nodoNuevo = new NodoEnlazado<>(elemento);

        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        if (index == 0) {
            if (inicio == null) {
                inicio = nodoNuevo;
                fin = nodoNuevo;
                nodoNuevo.sig = nodoNuevo;
                nodoNuevo.ant = nodoNuevo;
            } else {
                nodoNuevo.sig = inicio;
                nodoNuevo.ant = fin;
                inicio.ant = nodoNuevo;
                fin.sig = nodoNuevo;
                inicio = nodoNuevo;
            }
        } else {
            NodoEnlazado<T> nodo = inicio;

            for (int j = 0; j < index - 1; j++) {
                nodo = nodo.sig;
            }
            nodoNuevo.sig = nodo.sig;
            nodoNuevo.ant = nodo;
            nodo.sig.ant = nodoNuevo;
            nodo.sig = nodoNuevo;
        }
        nElementos++;
    }

    @Override
    public T remove(int index) throws ListException {
        T removed;
        if (empty()) {
            throw new ListException("Lista vacia");
        }
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }
        if (nElementos ==1) {
            removed = inicio.dato;
            inicio = null;
            fin = null;
        } else if (index == 0) {
            removed = inicio.dato;
            inicio = inicio.sig;
            inicio.ant = fin;
            fin.sig = inicio;
        } else {
            NodoEnlazado<T> nodo = inicio;
            for (int j = 0; j < index; j++) {
                nodo = nodo.sig;
            }
            removed = nodo.dato;
            nodo.ant.sig = nodo.sig;
            nodo.sig.ant = nodo.ant;
        
            if (nodo == fin) {
            fin = nodo.ant;
        }
        }
        nElementos--;
        return removed;
    }
      
    @Override
    public void invertir() {
        NodoEnlazado<T> actual = inicio;
        NodoEnlazado<T> temp;
        do {
            temp = actual.sig;
            actual.sig = actual.ant;
            actual.ant = temp;
            actual = temp;
        } while (actual != inicio);
        temp = inicio;
        inicio = fin;
        fin = temp;
    } 
    @Override
    public void concatenar(ListaDoblementeEnlazada<T> otraLista) {

        NodoEnlazado<T> nodo = otraLista.inicio;
        
        do {
            append(nodo.dato);
            nodo = nodo.sig;
        } while (nodo != otraLista.inicio);
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        NodoEnlazado<T> nodo = inicio;
        for (int index = 0; index < nElementos; index++) {
            if (nodo.dato.equals(elemento)) {
                remove(index);
                return true;
            }
            nodo = nodo.sig;
        } 
        return false;
    }

    @Override
    public int indexOf(T elemento) {
        NodoEnlazado<T> nodo = inicio;
        int index = 0;

        do {
            if (nodo.dato.equals(elemento)) {
                return index;
            }
            nodo = nodo.sig;
            index++;
        } while (nodo != inicio);

        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoEnlazado<T> nodo = inicio;
        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }
        return nodo.dato;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        if (empty()) {
            throw new ListException("Lista vacia");
        }

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de limites");
        }

        NodoEnlazado<T> nodo = inicio;

        for (int j = 0; j < index; j++) {
            nodo = nodo.sig;
        }

        nodo.dato = elemento;
    }

    @Override
    public void clear() {
        inicio = null;
        fin = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {
        return inicio == null;
    }

    @Override
    public int size() {
        return nElementos;
    }
    

    @Override
    public String toString() {
        String s = "[";
        NodoEnlazado<T> nodo = inicio;
        do {
            s += nodo.dato.toString();
            nodo = nodo.sig;
            if (nodo != inicio) {
                s += ", ";
            }
        } while (nodo != inicio);
        s += "]";
        return s;
    }

//    @Override
//    public java.util.Iterator<T> Iterator() {
//        return new ListIterator(inicio);
//    }
    @Override
    public java.util.Iterator<T> iterator() {
        return new ListIterator(inicio);
    }

}