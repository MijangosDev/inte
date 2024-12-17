package org.ti.inte.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

public class CustomQueue <T>
{
    private static final Logger log = LoggerFactory.getLogger(CustomQueue.class);

    private int head;
    private int tail;
    private int size;
    private T[] items;
    private int count;

    //Constructor
    @SuppressWarnings("unchecked")
    public CustomQueue(int size)
    {
        this.size = size;
        this.head = 0;
        this.tail = -1;
        this.items=(T[]) new Object[size];
    }

    //Métodos de la cola
    public boolean offer(T item)
    {
        if(isFull())
        {
            log.error("La cola está llena, no puedes agregar más elementos");
            //
            // throw new IllegalStateException("La cola está llena");
        }
        tail=(tail+1)%size;
        items[tail] = item;
        count++;
        return false;
    }

    public T poll()
    {
        if (isEmpty())
        {
            log.error("La cola está vacía, no puede remover ningún elemento");
            //throw new IllegalStateException("La cola está vacía");
        }
        System.out.println(head);
        T item = items[head];
        head = (head+1)%size;
        count--;
        System.out.println(head+"Nueva cabeza");
        System.out.println(item.toString());
        return item;
    }

    public T peek()
    {
        if (isEmpty())
        {
            log.error("La cola está vacía, no puedes obtener ningún elemento");
            //throw new IllegalStateException("La cola está vacía");
        }
        return items[head];
    }

    public List<T> getElements(){
        List <T> l = new LinkedList<T>();
        for(int i=0;i<count;i++){
            l.add(getItems()[i]);
        }
        return l;
    }

    public boolean isFull(){
        return count == size;
    }

    public boolean isEmpty(){ return count == 0; }

    //Getters & Setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public T[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTail() {
        return tail;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }
}

