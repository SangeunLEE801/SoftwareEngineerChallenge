package com.sangeun;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public final class ImmutableQueue<T> implements Queue<T> {
    private final LinkedList<T> queue;
    // The number of elements in this immutable queue.
    private int size;

    /**
     * Creates an empty jp.ne.paypay.Queue.
     */
    public ImmutableQueue() {
        this.queue = new LinkedList<>();
        this.size = 0;
    }

    /**
     * Creates a queue added a new element to an original queue
     */
    private ImmutableQueue(LinkedList<T> q, T t) {
        q.add(t);
        this.queue = q;
        this.size = q.size();
    }

    /**
     * Creates a queue deleted a head element from an original queue
     */
    private ImmutableQueue(LinkedList<T> q) {
        q.removeFirst();
        this.queue = q;
        this.size = q.size();
    }

    /**
     * Add an element to the last of the queue.
     *
     * @param t an element to add
     * @return new immutable queue
     * @throws IllegalArgumentException if element doesn't be provided
     */
    @Override
    public Queue<T> enQueue(T t) {
        if (t == null) {
            throw new IllegalArgumentException();
        }
        this.size++;
        return new ImmutableQueue<>(this.queue, t);
    }

    /**
     * Delete the first element from the queue.
     *
     * @return new immutable queue
     * @throws NoSuchElementException if this queue is empty
     */
    @Override
    public Queue<T> deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        this.size--;
        return new ImmutableQueue<>(this.queue);
    }

    /**
     * Retrieve the first element of the queue
     *
     * @return the first element of the queue
     */
    @Override
    public T head() {
        return this.queue.getFirst();
    }

    /**
     * Tests if this queue is empty.
     *
     * @return true if this queue has no element, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
