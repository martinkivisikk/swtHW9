package ee.ut.cs.swt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MinimumBinaryHeapTest {

    private MinimumBinaryHeap heap;
    private List<Integer> result;

    @BeforeEach
    public void setUp() {
        heap = new MinimumBinaryHeap();
        result = new ArrayList<>();
        result.add(10);
        result.add(5);
        result.add(4);
        result.add(8);
        result.add(12);
    }

    @Test
    public void minHeapifyTest() {
        heap.minHeapify(result);
        assertEquals(4, heap.getArray().get(0), 0);
        assertEquals(8, heap.getArray().get(1), 0);
        assertEquals(5, heap.getArray().get(2), 0);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void extractMinTest() {
        heap.add(7);
        heap.add(1);
        heap.add(6);
        int min = heap.extractMin();
        assertEquals(1, min);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void swapTest() {
        heap.add(5);
        heap.add(8);
        heap.add(13);
        heap.swap(1, 2);
        assertEquals(5, heap.getArray().get(0), 0);
        assertEquals(13, heap.getArray().get(1), 0);
        assertEquals(8, heap.getArray().get(2), 0);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void addTest() {
        heap.add(10);
        heap.add(5);
        heap.add(4);
        heap.add(8);
        heap.add(12);
        assertEquals(4, heap.getArray().get(0), 0);
        assertEquals(8, heap.getArray().get(1), 0);
        assertEquals(5, heap.getArray().get(2), 0);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void addMultipleElementsTest() {
        heap.add(1);
        heap.add(2);
        heap.add(101);
        heap.add(3);
        heap.add(102);
        heap.add(103);
        heap.add(0);
        assertEquals(0, heap.getArray().get(0));
        assertEquals(2, heap.getArray().get(1));
        assertEquals(1, heap.getArray().get(2));
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void isEmptyTest() {
        heap = new MinimumBinaryHeap();
        assertTrue(heap.isEmpty());
    }

    @Test
    public void isNotEmptyTest() {
        heap = new MinimumBinaryHeap();
        heap.add(1);
        assertFalse(heap.isEmpty());
    }

    @Test
    public void removeTest() {
        heap.add(1);
        heap.add(3);
        heap.add(2);
        heap.add(4);
        heap.add(4);
        heap.add(5);
        heap.add(5);
        heap.add(6);
        heap.add(6);
        heap.add(7);
        boolean b1 = heap.removeElement(3);
        assertTrue(b1);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void removeLastElementTest() {
        heap.minHeapify(result);
        int lastElement = heap.getArray().get(heap.getArray().size() - 1);
        boolean isRemoved = heap.removeElement(lastElement);
        assertTrue(isRemoved);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void removeNonExistingTest() {
        heap.minHeapify(result);
        boolean isRemoved = heap.removeElement(5000);
        assertFalse(isRemoved);
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    @Test
    public void swapMaintainSructureTest() {
        heap.add(1);
        heap.add(2);
        heap.add(3);
        heap.swap(0,1);
        heap.bubbleDown(0);
        assertEquals(1,heap.extractMin());
        if (isIncorrectHeap()) throw new Error(heap.getArray() + " is not a correct heap."); // For code integrity purposes
    }

    public boolean isIncorrectHeap() {
        int size = heap.getArray().size();
        for (int i = 0; i < size; i++) {
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            // Check if left child exists and is greater than current element
            if (leftChild < size && heap.getArray().get(leftChild) < heap.getArray().get(i)) {
                return true;
            }

            // Check if right child exists and is greater than current element
            if (rightChild < size && heap.getArray().get(rightChild) < heap.getArray().get(i)) {
                return true;
            }
        }
        return false;
    }
}