import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
/**
 * Реализация листа на основе массива, поддерживающая динамическое изменение размера.
 * Класс предоставляет операции для работы с листом, такие как добавление,
 * удаление и получение элементов, а также сортировку листа с использованием
 * предоставленного компаратора или естественного порядка элементов.
 *
 * @param <T> Тип элементов, хранящихся в листе. Тип должен быть совместим с Comparable,
 *           если используется метод sort() без параметров.
 */
public class MyList<T>  implements MyInterface<T> {

    public static void main(String[] args) {

        MyList<String> list = new MyList<>();
        list.add("1");
        list.add("4");
        list.add("5");
        list.add("3");
        list.add("2");
        System.out.println(list);
        list.quickSort(0, 4, String::compareTo);
        System.out.println(list);
    }

    private T[] values;

    /**
     * Конструктор для создания пустого листа.
     */
    public MyList() {
        values = (T[]) new Object[0];
    }

    /**
     * Добавляет элемент в конец листа.
     * Этот метод расширяет массив {@code values}, добавляя в него элемент {@code t}.
     *
     * @param t элемент, который необходимо добавить в лист.
     * @return возвращает {@code true}, в случае успешного добавления элемента или
     * {@code false} при выбросе исключения:
     * @throws ClassCastException
     */
    @Override
    public boolean add(T t) {
        try {
            T[] tempArray = values;
            values = (T[]) new Object[tempArray.length + 1];
            System.arraycopy(tempArray, 0, values, 0, tempArray.length);
            values[values.length - 1] = t;
            return true;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Добавляет элемент {@code t} в лист по указанному индексу {@code index}.
     * Элементы справа от указанного индекса сдвигаются на одну позицию вправо.
     *
     * @param index индекс, по которому необходимо добавить элемент.
     * @param t элемент, который необходимо добавить в лист.
     * @return возвращает {@code true}, в случае успешного добавления элемента или
     * {@code false} при выбросе исключения:
     * @throws ClassCastException
     *
     * @throws IndexOutOfBoundsException если {@code index} вне пределов массива
     * для добавляемого элемента.
     */
    @Override
    public boolean add(int index, T t) {
        if (index < 0 || index > values.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + values.length);
        }
        try {
            T[] tempArray = (T[]) new Object[values.length + 1];
            System.arraycopy(values, 0, tempArray, 0, index);
            tempArray[index] = t;
            System.arraycopy(values, index, tempArray, index + 1, values.length - index);
            values = tempArray;
            return true;
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Удаляет элемент из списка по указанному индексу.
     * Элементы справа от удаляемого сдвигаются на одну позицию влево.
     *
     * @param index индекс удаляемого элемента.
     * @return удаляемый элемент.
     * @throws IndexOutOfBoundsException если {@code index} вне пределов массива для данного листа.
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= values.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + values.length);
        }
        T elementToRemove = values[index];
        T[] tempArray = values;
        try {
            values = (T[]) new Object[tempArray.length - 1];
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        System.arraycopy(tempArray, 0, values, 0, index);
        int elementsAfterIndex = tempArray.length - index - 1;
        System.arraycopy(tempArray, index + 1, values, index, elementsAfterIndex);
        return elementToRemove;
    }

    /**
     * Возвращает элемент по указанному индексу из листа.
     *
     * @param index индекс элемента, который необходимо получить.
     * @return элемент листа по указанному индексу.
     * @throws IndexOutOfBoundsException если {@code index} вне пределов массива для листа.
     */
    @Override
    public T get(int index) {
        return values[index];
    }

    /**
     * Очищает лист, удаляя все его элементы. После вызова этого метода размер листа будет равен 0.
     */
    @Override
    public void clear() {
        values = (T[]) new Object[0];
    }

    /**
     * Возвращает количество элементов в листе.
     *
     * @return размер листа, т.е. количество элементов в нем.
     */
    @Override
    public int size() {
        return values.length;
    }

    /**
     * Сортирует лист в естественном порядке элементов. Элементы листа должны реализовывать интерфейс {@link Comparable}.
     */
    @Override
    public void sort() {
        Arrays.sort(values, 0, size(), null);
    }

    /**
     * Сортирует лист с использованием предоставленного {@code comparator}.
     *
     * @param comparator компаратор, определяющий порядок элементов в листе.
     */
    @Override
    public void sort(Comparator<T> comparator) {
        Arrays.sort(values, 0, size(), comparator);
    }

    /**
     * Возвращает итератор для обхода элементов листа.
     *
     * @return итератор, позволяющий последовательно обходить элементы листа.
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(values);
    }

    /**
     * Сортирует текущий лист с использованием алгоритма быстрой сортировки (QuickSort).
     * Данный метод обеспечивает сортировку элементов листа в естественном порядке,
     * предполагая, что тип элементов листа реализует интерфейс {@link Comparable}.
     */
//    public void sortUsingQuickSort() {
//        if (values.length > 1) {
//            quickSort(0, values.length - 1, Comparator.naturalOrder());
//        }
//    }

    /**
     * Возвращает строковое представление листа, содержащее все его элементы в порядке добавления.
     *
     * @return строка, содержащая все элементы листа, разделенные запятыми и заключенные в квадратные скобки.
     */
    @Override
    public String toString() {
        if (values == null || values.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < values.length; i++) {
            sb.append(values[i]);
            if (i < values.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
    private void quickSort(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<T> comparator) {
        T pivot = values[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            boolean shouldSwap;
            if (comparator != null) {
                shouldSwap = comparator.compare(values[j], pivot) < 0;
            } else {
                shouldSwap = ((Comparable<T>) values[j]).compareTo(pivot) < 0;
            }
            if (shouldSwap) {
                i++;
                T temp = values[i];
                values[i] = values[j];
                values[j] = temp;
            }
        }
        T temp = values[i + 1];
        values[i + 1] = values[high];
        values[high] = temp;
        return i + 1;
    }
}
