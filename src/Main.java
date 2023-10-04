import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*Задача
        На входе функция получает параметр n - натуральное число.
        Необходимо сгенерировать n-массивов, заполнить их случайными числами,
        каждый массив имеет случайный размер. Размеры массивов не должны совпадать.
        Далее необходимо отсортировать массивы. Массивы с четным порядковым номером
        отсортировать по возрастанию, с нечетным порядковым номером - по убыванию.
        На выходе функция должна вернуть массив с отсортированными массивами.*/

public class Main {
    public static void main(String[] args) {

        int n = 5; // количество массивов
        ArrayList<int[]> arrays = sortingArrays(n);

        for (int i = 0; i < n; i++) {
            System.out.println("Массив " + (i+1) + " (длина " + arrays.get(i).length + "): ");
            for (int j = 0; j < arrays.get(i).length; j++) {
                System.out.print(arrays.get(i)[j] + " ");
            }
            System.out.println();
        }
    }

    public static ArrayList<int[]> createArrays(int n) {

        ArrayList<int[]> arrays = new ArrayList<>();
        ArrayList<Integer> size = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int length = random.nextInt(n * 4) + 1; // длина массива от 1 до n*4

            while(size.contains(length)) {
                length = random.nextInt(n * 4) + 1; // // длина массива от 1 до n*4
            }
            size.add(length);
            int[] newArray = new int[length];
            arrays.add(fillingArrays(newArray));
        }
        return arrays;
    }

    public static int[] fillingArrays(int[] arr) {
        Random random = new Random();

        for(int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100) - 50; // заполнение массива рандомными числами от -50 до 50
        }

        return arr;
    }

    public static ArrayList<int[]> sortingArrays(int n) {

        ArrayList<int[]> SortedArrays = createArrays(n);

        for (int i = 0; i < SortedArrays.size(); i++) {
            if(i % 2 != 0) { //Массивы с четным порядковым номером отсортировать по возрастанию
                SortedArrays.set(i, Arrays.stream(SortedArrays.get(i)).sorted().toArray());
            }
            else { // с нечетным порядковым номером - по убыванию
                SortedArrays.set(i, Arrays.stream(SortedArrays.get(i))
                        .boxed()
                        .sorted((a, b) -> b - a)
                        .mapToInt(Integer::intValue)
                        .toArray()
                );
            }
        }
        return SortedArrays;
    }
}