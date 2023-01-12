public class SelectionSort {
    public static int[] sort(int[] arr) {
        
        for (int i = 0; i < arr.length; i++) {
            int minId = i;
            for (int j = i +1; j < arr.length; j++) {
                if (arr[j] < arr[minId]) minId = j;
            }
            int temp = arr[i];
            arr[i] = arr[minId];
            arr[minId] = temp;
        }
        return arr;
    }

}