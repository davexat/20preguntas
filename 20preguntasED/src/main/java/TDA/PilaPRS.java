/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TDA;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Pc
 * @param <E>
 */
public class PilaPRS<E> implements Deque<E>{
    private Node primero;
    private Node ultimo;
    private int size = 0;
    
    protected class Node{
        Node ant;
        E contenido;
        Node sig;
        
        public Node(E e){
            ant = null;
            contenido = e;
            sig = null;
        }
    }
    protected class PilaIteratorPRS<E> implements Iterator<E>{
        PilaPRS<E>.Node act;
        
        PilaIteratorPRS(PilaPRS<E> list){
            act = list.primero;
        }

        @Override
        public boolean hasNext() {
            return act != null;
        }

        @Override
        public E next() {
            E element = act.contenido;
            act = act.sig;
            return element;
        }
    }
    protected class ListIteratorPRS implements ListIterator<E>{
        private Node actual = primero;
        private Node ultimoRetornado = null;
        private int nextIndex = 0;
        
        @Override
        public boolean hasNext(){
            return nextIndex < size; 
        }
        
        @Override
        public E next(){
            if(!hasNext()) throw new NoSuchElementException();
            
            ultimoRetornado = actual;
            actual = actual.sig;
            nextIndex++;
            return ultimoRetornado.contenido;
        }
        
        @Override
        public boolean hasPrevious(){
            return nextIndex > 0;
        }
        
        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            
            if (actual == null) actual = ultimo;
            else actual = actual.ant;
            
            ultimoRetornado = actual;
            nextIndex--;
            return ultimoRetornado.contenido;
        }
        
        @Override
        public int nextIndex() {
            return nextIndex;
        }
        
        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }
        
        @Override
        public void remove(){
            if (ultimoRetornado == null) throw new IllegalStateException();
            Node nextNode = ultimoRetornado.sig;
            Node prevNode = ultimoRetornado.ant;
            
            if (prevNode == null) primero = nextNode;
            else {
                prevNode.sig = nextNode;
                ultimoRetornado.ant = null;
            }
            
            if (nextNode == null) ultimo = prevNode;
            else {
                nextNode.ant = prevNode;
                ultimoRetornado.sig = null;
            }
            
            if (actual == ultimoRetornado) actual = nextNode;
            else {
                nextIndex--;
            }
            
            ultimoRetornado = null;
            size--;
        }
        
        @Override
        public void set(E e) {
            if (ultimoRetornado == null) {
                throw new IllegalStateException();
            }
            ultimoRetornado.contenido = e;
        }
        
        @Override
        public void add(E e) {
            Node newNode = new Node(e);

            if (actual == null) {
                newNode.ant = ultimo;
                if (ultimo != null) {
                    ultimo.sig = newNode;
                }
                ultimo = newNode;
                if (primero == null) {
                    primero = newNode;
                }
            } else {
                newNode.sig = actual;
                newNode.ant = actual.ant;
                if (actual.ant != null) {
                    actual.ant.sig = newNode;
                }
                actual.ant = newNode;
                if (actual == primero) {
                    primero = newNode;
                }
            }

            nextIndex++;
            size++;
            ultimoRetornado = null;
        }
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public boolean isEmpty() {
        return this.primero == null;
    }
    @Override
    public boolean contains(Object o) {        
        Node i = this.primero;
        while ( i != null ){
            if ( i.contenido.equals(o) ) return true;
            i = i.sig;
        }
        return false;
    }
    @Override
    public Iterator<E> iterator() {
        return new PilaIteratorPRS(this);
    }
    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[this.size()];
        Iterator<E> it = this.iterator();
        int c = 0;
        while(it.hasNext()) arreglo[c++] = it.next();
        return arreglo;
    }
    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean add(E e) {
        if ( e == null ) return false;
        else if ( this.isEmpty() ){
            this.primero = new Node(e);
            this.ultimo = this.primero;
            this.size++;
            return true;
        }
        Node iNode = this.primero;
        while ( iNode.sig != null ) iNode = iNode.sig;
        Node jNode = new Node(e);
        iNode.sig = jNode;
        jNode.ant = iNode;
        this.ultimo = jNode;
        this.size++;
        return true;
    }
    @Override
    public void addFirst(E e){
        if ( e == null ) throw new NullPointerException();
        else if ( this.isEmpty() ) this.add(e);
        this.add(0, e);
    }
    @Override
    public void addLast(E e){
        this.add(e);
    }
    @Override
    public E removeFirst(){
        return remove(0);
    }
    @Override
    public E removeLast(){
        return remove(this.size()-1);
    }
    @Override
    public boolean remove(Object o) {
        if ( o == null ) return false;
        for ( int i = 0; i<this.size; i++){
            if(this.get(i).equals(o)) {
                this.remove(i);
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean addAll(Collection<? extends E> c) {
        if ( c == null ) return false;
        for ( E element: c ) this.add(element);
        return true;
    }
    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    @Override
    public void clear() {
        this.primero = null;
        this.ultimo = null;
    }
    
    private E get(int index) {
        if ( index < 0 || index >= this.size() ) throw new IndexOutOfBoundsException();
        Node iNode = this.primero;
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ) return iNode.contenido;
            iNode = iNode.sig;
        }
        return null;
    }
    
    private E set(int index, E element) {
        if ( index < 0 || index >= this.size() ) throw new IndexOutOfBoundsException();
        else if ( element == null ) throw new NullPointerException();
        Node iNode = this.primero;
        E removido = null;
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ){
                removido = iNode.contenido;
                iNode.contenido = element;
            }
            iNode = iNode.sig;
        }
        return removido;
    }

    private void add(int index, E element) {
        if ( index < 0 || index > this.size() || element == null ) throw new NullPointerException();
        Node iNode = this.primero;
        Node newNode = new Node(element);
        for ( int i = 0; i <= index; i++ ){
            if ( i == index ){
                if ( i == this.size() ) this.addLast(element);
                else if ( i == 0 ){
                    iNode.ant = newNode;
                    newNode.sig = iNode;
                    this.primero = newNode;
                    this.size++;
                } else {
                    Node befNode = iNode.ant;
                    newNode.ant = befNode;
                    befNode.sig = newNode;
                    newNode.sig = iNode;
                    iNode.ant = newNode;
                    this.size++;
                }
                break;
            }
            iNode = iNode.sig;
        }
    }
    
    private E remove(int index) {
        if ( index < 0 || index >= this.size() ) throw new NullPointerException();
        Node iNode = this.primero;
        E element = null;
        if ( index == 0 ){
            element = iNode.contenido;
            iNode = iNode.sig;
            if ( this.size() > 1 ) iNode.ant = null;
            this.primero = iNode;
        } else if ( index == this.size() - 1 ) {
            Node jNode = this.ultimo;
            element = jNode.contenido;
            jNode = jNode.ant;
            jNode.sig = null;
            this.ultimo = jNode;
        } else {
            iNode = iNode.sig;
            for ( int i = 1; i <= index; i++ ){
                if ( i == index ){
                   element = iNode.contenido;
                   Node befNode = iNode.ant;
                   Node aftNode = iNode.sig;
                   befNode.sig = aftNode;
                   aftNode.ant = befNode;
                }
                
            }
        }
        this.size--;
        return element;
    }
    
    @Override
    public boolean offerFirst(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean offerLast(E e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E pollFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E pollLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E getLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E peekFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E peekLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean offer(E e) {
        return this.add(e);
    }

    @Override
    public E remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E poll() {
        return this.remove(0);
    }

    @Override
    public E element() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E peek() {
        return this.primero.contenido;
    }

    @Override
    public void push(E e) {
        this.add(e);
    }

    @Override
    public E pop() {
        return this.remove(this.size()-1);
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public String toString(){
        if( this.isEmpty() ) return null;
        
        String[] cadena = new String[this.size()];
        int c = 0;
        
        for ( E e: this){
            cadena[c++] = String.valueOf(e);
        }
        
        return String.join(", ", cadena);
    }
}
