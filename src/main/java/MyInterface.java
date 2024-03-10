import java.util.Comparator;
/**
 * Интерфейс для определения функциональности листа с динамическим изменением размера.
 * Предоставляет основные операции для работы с листом, такие как добавление и удаление элементов,
 * очистку листа, получение размера листа и элементов по индексу, а также сортировку.
 *
 * @param <T> Тип элементов, которые будет хранить реализация листа.
 */
public interface MyInterface<T> extends Iterable<T> {
    boolean add(T t);
    boolean add(int index, T t);
    T remove(int index);
    T get(int index);
    void clear();
    int size();
    void sort();
    void sort(Comparator<T> comparator);
    String toString();
}
