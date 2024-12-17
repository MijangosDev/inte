package org.ti.inte.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class CustomStack <T>
{
    private static final Logger log = LoggerFactory.getLogger(CustomStack.class);

    private int capacity;
    private int top;
    private T[] elements;

    //Constructor
    @SuppressWarnings("unchecked")
    public CustomStack(int capacity)
    {
        this.capacity = capacity;
        this.top = -1;
        this.elements = (T[]) new Object[capacity];
    }

    //Métodos de la pila
    public void push (T item)
    {
        if(isFull())
        {
            System.out.println("La pila está llena");
            log.error("La pila está llena, no puedes agregar más elementos");
            return;
        }
        elements[++top] = item;
    }

    public T pop()
    {
        if(isEmpty())
        {
            System.out.println("La pila está vacía");
            log.error("La pila está vacía, no puede remover ningún elemento");
            return null;
        }
        return elements[top--];
    }

    public T peek()
    {
        if(isEmpty())
        {
            System.out.println("La pila está vacia");
            log.error("La pila está vacía, no puedes obtener ningún elemento");
            return null;
        }
        return elements[top];
    }

    public List<T> getItems(){
        List <T> l = new LinkedList<T>();
        for(int i=0;i<capacity;i++){
            l.add(getElements()[i]);
        }
        return l;
    }

    public boolean isEmpty(){ return top == -1; }

    public boolean isFull(){
        return top==capacity-1;
    }

    //Getters & Setters
    public T[] getElements() {
        return elements;
    }

    public void setElements(T[] elements) {
        this.elements = elements;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
