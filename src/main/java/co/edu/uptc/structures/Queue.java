package co.edu.uptc.structures;

import java.util.NoSuchElementException;

public class Queue<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Queue() {
        head = null;
        tail = null;
    }

    public void push(T t){
        Node<T> newNode = new Node<>(t);
        if (isEmpty()){
            head = tail = newNode;
            size++;
        }else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    public T poll(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        T result = head.getData();
        head = head.getNext();
        size--;
        if (head == null){
            tail = null;
        }
        return result;
    }

    public T peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return head.getData();
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return head == null;
    }

}
