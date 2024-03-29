package com.itutorix.cs;

import java.util.ArrayList;
public class PowerOfTwoMaxHeap<T extends Comparable<T>> {
    private final int childCount;
    private final ArrayList<T> data;

    public PowerOfTwoMaxHeap(int childCount) {
        this.validateChildCount(childCount);
        this.childCount = childCount;
        this.data = new ArrayList<>();
    }

    /**
     * Validates the given child count, throwing an exception if it is not valid.
     *
     * @param childCount the child count to validate
     * @throws IllegalArgumentException if the child count is not valid
     */
    public void validateChildCount(int childCount) {
        if (childCount <= 0) {
            throw new IllegalArgumentException("childCount must be greater than zero");
        }

        if ((childCount & (childCount - 1))!= 0) {
            throw new IllegalArgumentException("childCount must be a power of 2");
        }
    }

    /**
     * Inserts a new item into the heap.
     *
     * @param item the item to be inserted
     */
    public void insert(T item) {
        data.add(item);

        int itemIndex = data.size() - 1;
        while (itemIndex > 0) {
            itemIndex = swapUp(itemIndex);
        }
    }

    /**
     * Swaps a child with its parent if the child is greater than the parent,
     * ensuring the heap property is satisfied.
     *
     * @param childIndex the index of the child to be swapped
     * @return the index of the parent if a swap occurred, otherwise -1
     */
    private int swapUp(int childIndex) {
        // check a child against its parent, and swap it if necessary to satisfy heap property
        T childValue = data.get(childIndex);
        int parentIndex = (int) Math.floor((float) (childIndex - 1) / childCount);
        if (parentIndex >= 0) {
            T parentValue = data.get(parentIndex);
            if (childValue.compareTo(parentValue) > 0) {
                data.set(parentIndex, childValue);
                data.set(childIndex, parentValue);
                return parentIndex;
            }
        }
        return -1;
    }

    /**
     * Removes and returns the maximum value from the heap.
     * If the heap is empty, returns null.
     *
     * @return the maximum value in the heap, or null if the heap is empty
     */
    public T popMax() {
        // pop the max value off the heap, return null if none remain
        if (!data.isEmpty()) {
            T maxItem = data.get(0);
            if (data.size() > 1) {
                T lastItem = data.remove(data.size() - 1);
                data.set(0, lastItem);
                int itemIndex = 0;
                while (itemIndex >= 0) {
                    itemIndex = swapDown(itemIndex);
                }
            }
            return maxItem;
        } else {
            return null;
        }
    }

    /**
     * Swaps a parent with its highest child if necessary to satisfy heap property.
     *
     * @param parentIndex the index of the parent to be swapped
     * @return the index of the highest child if a swap occurred, otherwise -1
     */
    private int swapDown(int parentIndex) {
        // check a parent against all children and swap it with the highest child if necessary to satisfy heap property
        T parentValue = data.get(parentIndex);
        // determine largest child
        int largestChildIndex = 0;
        T largestChildValue = null;
        for (int i = 0; i < childCount; i++) {
            int childIndex = childCount * parentIndex + i + 1;
            if (childIndex < data.size() - 1) {
                T childValue = data.get(childIndex);
                if (largestChildValue == null || childValue.compareTo(largestChildValue) > 0) {
                    largestChildIndex = childIndex;
                    largestChildValue = childValue;
                }
            }
        }
        // perform swap if necessary
        if (largestChildValue != null && parentValue.compareTo(largestChildValue) < 0) {
            data.set(parentIndex, largestChildValue);
            data.set(largestChildIndex, parentValue);
            return largestChildIndex;
        }
        return -1;
    }
}
