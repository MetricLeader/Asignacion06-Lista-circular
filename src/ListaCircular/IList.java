/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ListaCircular;

import java.util.Iterator;

/**
 *
 * @author labitson
 */
public interface IList<T> {
    
    public void append(T elemento) throws ListException;
    public void insert(T elemento, int index) throws ListException;
    public T remove(int index) throws ListException;
    public boolean removeObj (T elemento) throws ListException;
    public int indexOf(T elemento);
    public T get(int index) throws ListException;
    public void set(T elemento, int index) throws ListException;
    public void clear();
    public boolean empty();
    public int size();
    public void invertir();
    public void concatenar(ListaDoblementeEnlazada<T> otraLista);
            
//    public Iterator<T> Iterator();
}