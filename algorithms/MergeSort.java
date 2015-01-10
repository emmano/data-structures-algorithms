package algorithms;

public class MergeSort {

    public static int[] mergeSort(int[] unsortedArray){
    return divide(unsortedArray);
    }

    private static int[] divide(int[] ints) {

        int[] sortedArray = new int[ints.length];

        if (0 < ints.length - 1) {
            int middleIndex = ints.length / 2;
            int[] leftArray = new int[middleIndex];
            int[] rightArray = new int[middleIndex];

            System.arraycopy(ints, 0, leftArray, 0, middleIndex);

            int k = 0;
            for (int j = middleIndex; j < ints.length; j++) {
                rightArray[k] = ints[j];
                k++;
            }
            printSubArrays(leftArray);
            divide(leftArray);

            printSubArrays(rightArray);
            divide(rightArray);
            sortedArray = merge(leftArray, rightArray, ints);
            printSubArrays(sortedArray);

        }
        return sortedArray;
    }

    private static int[] merge(int[] leftArray, int[] rightArray, int[] input) {

        int i = 0, j = 0, k = 0;
        while (j < rightArray.length && i < leftArray.length) {

            if (leftArray[i] < rightArray[j]) {
                input[k] = leftArray[i];
                i++;
            } else {
                input[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            input[k] = leftArray[i];
            i++;
            k++;
        }
        while (j < rightArray.length) {
            input[k] = rightArray[j];
            j++;
            k++;
        }

        return input;

    }

    private static void printSubArrays(int[] subArray) {
        for (int j = 0; j < subArray.length; j++) {
            if (j == 0) {
                System.out.print("[");
            }
            System.out.print(String.format(" %s ", subArray[j]));
        }
        if (subArray.length > 0) {
            System.out.print("]");
            System.out.println();
        }

    }
}
