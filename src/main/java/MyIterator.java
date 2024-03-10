import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Итератор для прохода по элементам листа {@link MyList}.
 * Поддерживает стандартный интерфейс итератора, включая операции hasNext() и next(),
 * позволяя итерироваться по элементам листа.
 *
 * @param <T> Тип элементов листа, по которым осуществляется итерация.
 */
public class MyIterator<T> implements Iterator<T> {
    private int index = 0;
    private T[] values;

    /**
     * Конструктор итератора.
     *
     * @param values Массив значений, по которым будет происходить итерация.
     *               Предполагается, что этот массив содержит элементы листа {@link MyList}.
     */
    MyIterator(T[] values) {
        this.values = values;
    }

    /**
     * Определяет, существует ли следующий элемент в листе.
     *
     * @return true, если следующий элемент существует; false, если достигнут конец листа.
     */
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    /**
     * Возвращает следующий элемент в листе и переводит итератор вперед.
     *
     * @return Следующий элемент листа.
     *
     * @throws NoSuchElementException если следующего элемента нет, т.е. итератор уже находится
     * в конце листа.
     */
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return values[index++];
    }
}
