package com.sangeun;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ImmutableQueueTest {
    private final int[] data = {8, 2, 1, 7, 0, 3, 5};

    @Test
    public void enQueue_thenReturnNewQueue() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();

        for (int i : data) {
            Queue<Integer> afterEnQueue = immutableQueue.enQueue(i);
            // Compare the address of before and after enQueue
            assertNotSame(immutableQueue, afterEnQueue);
        }
    }

    @Test
    public void enQueue_shouldBeNotEmpty() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();
        assertTrue(immutableQueue.isEmpty());

        for (int i : data) {
            immutableQueue.enQueue(i);
            assertFalse(immutableQueue.isEmpty());
        }
    }

    @Test
    public void enQueue_shuoldHavetheSameHead() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();
        immutableQueue.enQueue(data[0]);

        for (int i = 1; i < data.length; i++) {
            Integer headBeforEnqueue = immutableQueue.head();
            Queue<Integer> afterEnQueue = immutableQueue.enQueue(i);
            assertSame(headBeforEnqueue, afterEnQueue.head());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void enQueueuNullElement_shouldThrowIllegalArgumentException() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();
        immutableQueue.enQueue(null);
    }

    @Test
    public void deQueue_thenReturnNewQueue() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();

        for (int i : data) {
            immutableQueue.enQueue(i);
        }

        while (!immutableQueue.isEmpty()) {
            Queue<Integer> afterDeQueue = immutableQueue.deQueue();
            // Compare the address of before and after deQueue
            assertNotSame(immutableQueue, afterDeQueue);
        }
    }

    @Test
    public void deQueue_shouldHaveDifferentHead() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();

        for (int i : data) {
            immutableQueue.enQueue(i);
        }

        while (!immutableQueue.isEmpty()) {
            Integer headBeforeDeQueue = immutableQueue.head();
            Queue<Integer> afterDeQueue = immutableQueue.deQueue();

            if (afterDeQueue.isEmpty()) {
                // After dequeue the last element, it doesn't have a head.
                break;
            }

            assertNotSame(headBeforeDeQueue, afterDeQueue.head());
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void deQueueEmptyQueue_shouldThrowNoSuchElementException() {
        Queue<Integer> immutableQueue = new ImmutableQueue<>();
        immutableQueue.deQueue();
    }
}