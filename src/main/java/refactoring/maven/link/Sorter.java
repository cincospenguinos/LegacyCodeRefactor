package refactoring.maven.link;

public class Sorter {
    private int[] toSort;

    public Sorter(int[] _toSort) {
        toSort = _toSort;
    }

    public int[] sort() {
        int[] array = new int[toSort.length];

        for (int i = 0; i < toSort.length; i++) {
            array[i] = toSort[i];
        }

        return sort(array);
    }

    private int[] sort(int[] array) {
        if (array.length == 2) {
            int[] newArray = new int[2];

            if (array[0] < array[1]) {
                newArray[0] = array[0];
                newArray[1] = array[1];
                return newArray;
            } else {
                newArray[0] = array[1];
                newArray[1] = array[0];
                return newArray;
            }
        } else if (array.length == 1) {
            int[] newArray = new int[1];
            newArray[0] = array[0];
            return newArray;
        } else {
            int middle = array.length / 2;
            int leftLength = (array.length + 1) / 2;
            int rightLength = array.length - leftLength;
            int[] left = new int[leftLength];
            for (int ii = 0; ii < left.length; ii++) {
                left[ii] = array[ii];
            }
            left = sort(left);
            int[] right = new int[rightLength];
            for (int ii = 0; ii < right.length; ii++) {
                right[ii] = array[ii + middle];
            }
            right = sort(right);
            int[] newArray = new int[array.length];

            int l = 0, r = 0, i = 0;
            while(l < left.length || r < right.length) {
                if (l == left.length) {
                    newArray[i] = right[r];
                    i++;
                    r++;
                } else if(r == right.length) {
                    newArray[i] = left[l];
                    i++;
                    l++;
                } else if (left[l] < right[r]) {
                    newArray[i] = left[l];
                    i++;
                    l++;
                } else {
                    newArray[i] = right[r];
                    i++;
                    r++;
                }
            }

            return newArray;
        }
    }
}
