import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {
    private MyList<String> list;

    @BeforeEach
    void setUp() {
        list = new MyList<>();
    }

    /**
     * Проверяет, добавляется ли элемент в лист и корректно ли возвращается с помощью метода get.
     */
    @Test
    void addAndGetString() {
        assertTrue(list.add("One"));
        assertEquals("One", list.get(0));
    }

    /**
     * Проверяет добавление элемента на определённую позицию и корректное получение этого элемента с
     * той же позиции.
     */
    @Test
    void addAtPositionAndGet() {
        list.add("One");
        list.add("Three");
        list.add(1, "Two");
        assertEquals("Two", list.get(1));
    }

    /**
     * Проверяет поведение метода add при попытке добавления элемента на позицию за пределами текущего
     * размера листа.
     */
    @Test
    void addAtPositionOutOfBounds() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, "One"));
        assertEquals("Index: 1, Size: 0", exception.getMessage());
    }

    /**
     * Проверяет удаление элемента из листа и корректность состояния листа после удаления.
     */
    @Test
    void removeElement() {
        list.add("One");
        list.add("Two");
        assertEquals("One", list.remove(0));
        assertEquals("Two", list.get(0));
        assertEquals(1, list.size());
    }

    /**
     * Проверяет поведение метода remove при попытке удаления элемента за пределами листа.
     */
    @Test
    void removeElementOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
    }

    /**
     * Проверяет очистку листа с помощью метода clear.
     */
    @Test
    void clearList() {
        list.add("One");
        list.clear();
        assertEquals(0, list.size());
    }

    /**
     * Проверяет метод size, подсчитывающий количество элементов в листе.
     */
    @Test
    void sizeOfList() {
        assertEquals(0, list.size());
        list.add("One");
        assertEquals(1, list.size());
    }

    /**
     * Проверяет сортировку листа по возрастанию с использованием Comparable.
     */
    @Test
    void testNaturalOrderSort() {
        list.add("b");
        list.add("c");
        list.add("a");
        list.sort();
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    /**
     * Проверяет сортировку листа по возрастанию с использованием Comparator.
     */
    @Test
    void sortList() {
        list.add("2");
        list.add("3");
        list.add("1");
        list.sort(Comparator.naturalOrder());
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("3", list.get(2));
    }

    /**
     * Проверяет итератор листа на его способность корректно проходить по элементам.
     */
    @Test
    void iteratorTest() {
        list.add("One");
        list.add("Two");
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("One", it.next());
        assertTrue(it.hasNext());
        assertEquals("Two", it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Проверяет, что итератор выбрасывает исключение:
     * @throws NoSuchElementException,
     * когда все элементы были пройдены и следующего элемента нет.
     */
    @Test
    void iteratorNoSuchElementException() {
        list.add("One");
        Iterator<String> it = list.iterator();
        assertEquals("One", it.next());
        assertThrows(NoSuchElementException.class, it::next);
    }

    /**
     * Проверяет метод toString на его способность возвращать строковое представление листа.
     */
    @Test
    void toStringTest() {
        list.add("One");
        list.add("Two");
        assertEquals("[One, Two]", list.toString());
    }
}

