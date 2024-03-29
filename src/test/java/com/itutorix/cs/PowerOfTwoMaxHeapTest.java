package com.itutorix.cs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PowerOfTwoMaxHeapTest {

    @Test
    void testValidateChildCount_ValidChildCount() {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(4);
        heap.validateChildCount(4);
    }

    @Test
    void testValidateChildCount_InvalidChildCount() {
        // Given
        int childCount = -1;

        // When & Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(childCount);
            heap.validateChildCount(3);
        });
        assertEquals("childCount must be greater than zero", thrown.getMessage());
    }

    @Test
    void testValidateChildCount_NonPowerOfTwoChildCount() {
        // Given
        int childCount = 5;

        // When & Then
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(childCount);
            heap.validateChildCount(5);
        });
        assertEquals("childCount must be a power of 2", thrown.getMessage());
    }

    @Test
    void testInsertAndPopMax() {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2);
        heap.insert(10);
        heap.insert(5);
        heap.insert(15);

        assertEquals(15, heap.popMax());
        assertEquals(10, heap.popMax());
        assertEquals(5, heap.popMax());
    }


    @Test
    void testInsertAndPopMaxWithEmptyHeap() {
        PowerOfTwoMaxHeap<Integer> heap = new PowerOfTwoMaxHeap<>(2);
        assertEquals(null, heap.popMax());
    }
}