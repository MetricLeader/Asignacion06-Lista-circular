
package ListaCircular;

import java.util.Iterator;

public class LinkedList {

    public static void main(String[] args) {
        
        Alumnito alumn1 = new Alumnito("Juan", 19, "279949", 9.9);
        Alumnito alumn2 = new Alumnito("Zayuri", 18, "216677", 9.8);
        Alumnito alumn3 = new Alumnito("Angel", 19, "212121", 9.2);
        Alumnito alumn4 = new Alumnito("Daniel", 19, "235689", 9.6);
        
        ListaDoblementeEnlazada Lista = new ListaDoblementeEnlazada<Alumnito>();
        
        Lista.append(alumn1);
        Lista.append(alumn2);
        Lista.append(alumn3);
        System.out.println(Lista.toString());
        Lista.insert(alumn4, 2);
        System.out.println(Lista.toString());
        Lista.removeObj(alumn3);
        System.out.println(Lista.indexOf(alumn1));
        
        ListaDoblementeEnlazada<Alumnito>.ListIterator<Alumnito> Iterador = Lista.new ListIterator(Lista.inicio);
        
        System.out.println(Lista.toString());
        
        Iterador.irFinal();
        System.out.println("Ir Final: "+Iterador.next());
        
        Iterador.irInicio();
        System.out.println("Ir inicio: "+Iterador.next());
               
        Iterador.previous();
        Iterador.previous();
        System.out.println("Previo: "+Iterador.next());
    }
}