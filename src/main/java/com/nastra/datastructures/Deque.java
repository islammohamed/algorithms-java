package com.nastra.datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A deque, also known as a double-ended queue, is an ordered collection of items similar to the queue. It has two ends, a front and a rear, and the
 * items remain positioned in the collection. What makes a deque different is the unrestrictive nature of adding and removing items. New items can be
 * added at either the front or the rear. Likewise, existing items can be removed from either end. In a sense, this hybrid linear structure provides
 * all the capabilities of stacks and queues in a single data structure.
 * 
 * @author Eduard Tudenhoefner - nastra
 * 
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Node prev;
        Node next;
        Item value;

        public Node(Item value) {
            super();
            this.value = value;
        }
    }

    public Deque() {
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (null == item) {
            throw new NullPointerException("Item must not be null");
        }
        Node x = new Node(item);
        x.prev = first;
        if (null == first) {
            first = last = x;
        } else {
            first.next = x;
            first = x;
        }
        size++;
    }

    public void addLast(Item item) {
        if (null == item) {
            throw new NullPointerException("Item must not be null!");
        }

        Node x = new Node(item);
        x.next = last;
        if (null == last) {
            last = first = x;
        } else {
            last.prev = x;
            last = x;
        }
        size++;
    }

    public Item removeFirst() {
        if (null == first) {
            throw new NoSuchElementException();
        }
        Node tmp = first;
        first = first.prev;
        if (null != first) {
            first.next = null;
        }
        tmp.prev = null;
        size--;
        return tmp.value;
    }

    public Item removeLast() {
        if (null == first) {
            throw new NoSuchElementException();
        }
        Node tmp = last;
        last = last.next;
        if (null != last) {
            last.prev = null;
        }
        tmp.next = null;
        size--;
        return tmp.value;
    }

    public Iterator<Item> iterator() {
        return new DequeFirstToLastIterator();
    }

    public static void main(String[] args) {

    }

    private class DequeFirstToLastIterator implements Iterator<Item> {
        Node x = first;

        public boolean hasNext() {
            return null != x;
        }

        public Item next() {
            if (null == x) {
                throw new NoSuchElementException();
            }
            Item item = x.value;
            x = x.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
