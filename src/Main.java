import java.util.Arrays;
import java.util.Scanner;

interface SortCondition<T> {
    int compare(T a, T b);
}

public class Main {

    public static <T> void sort(T[] array, SortCondition<T> condition) {
        heapify(array, condition);
        int end = array.length - 1;
        while (end > 0) {
            swap(array, end, 0);
            shiftDown(array, 0, end - 1, condition);
            end--;
        }
    }
    private static <T> void heapify(T[] array, SortCondition<T> condition) {
        int start = (array.length - 1) / 2;   /*
        для любого элемента синдексом i, его левый дочерний узел будет находиться на позиции 2*i + 1, а правый дочерний узел - на позиции 2*i + 2.
Следовательно, для любого дочернего узла с индексом j, его родительский узел будет находиться на позиции (j - 1) / 2.
Таким образом, если мы берем последний элемент массива (с индексом array.length - 1) и делим его индекс на 2, мы получим индекс его родительского узла.
        */
        while (start >= 0) {
            shiftDown(array, start, array.length - 1, condition);
            start--;
        }
    }
    private static <T> void shiftDown(T[] array, int start, int end, SortCondition<T> condition) {
        int root = start;
        while (root * 2 + 1 <= end) {
            int child = root * 2 + 1;  //здесь мы находим левый дочерний узел
            if (child + 1 <= end && condition.compare(array[child], array[child + 1]) < 0) {
                child++;
            }
            if (condition.compare(array[root], array[child]) < 0) {
                swap(array, root, child);
                root = child;
            } else {
                return;
            }
        }
    }
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество элементов: ");
        int n = scanner.nextInt();

        Integer[] array = new Integer[n];

        System.out.println("Введите элементы массива:");
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        // Сортировка по возрастанию
        sort(array, (a, b) -> a.compareTo(b));
        System.out.println("Отсортированный массив по возрастанию:");
        System.out.println(Arrays.toString(array));

        // Сортировка по убыванию
        sort(array, (a, b) -> b.compareTo(a));
        System.out.println("Отсортированный массив по убыванию:");
        System.out.println(Arrays.toString(array));
    }
}
