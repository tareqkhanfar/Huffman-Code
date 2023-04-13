package com.example.huffmancodegui.BackEnd;



import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {

    private int capacity;
    private int size = 0;
    private Node[] heap;

    MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new Node[capacity];
    }


    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private Node leftChild(int parentIndex) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    private Node rightChild(int parentIndex) {
        return heap[getRightChildIndex(parentIndex)];
    }

    private Node parent(int childIndex) {
        return heap[getParentIndex(childIndex)];
    }

    private void swap(int index1, int index2) {
        Node element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    // Time Complexity : O(1)
    private Node peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    // Time Complexity : O( Log n)
    public Node poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Node element = heap[0];

        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return element;
    }

    // Time Complexity : O( Log n)
    public void add(Node item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index).freq > heap[index].freq) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index).freq < leftChild(index).freq) {
                smallestChildIndex = getRightChildIndex(index);
            }

            if (heap[index].freq < heap[smallestChildIndex].freq) {
                break;
            } else {
                swap(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
    }
}