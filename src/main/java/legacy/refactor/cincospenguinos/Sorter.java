package legacy.refactor.cincospenguinos;

public class Sorter {
    private int[] toSort;
    private boolean otherWay;
    private boolean dup;

    public Sorter(int[] _toSort, boolean _otherWay, boolean _dup) {
        toSort = _toSort;
        otherWay = _otherWay;
        dup = _dup;
    }

    public int[] sort() {
        int[] array;

        if (otherWay) {
            array = otherSort(toSort);
        } else {
            array = sort(toSort);
        }

        if (dup) {
            int[] newArray = new int[array.length];
            for (int i = 0; i < newArray.length; i++) {
                newArray[i] = -1;
            }

            int i = 0;
            for (int j = 0; j < array.length; j++) {
                int o = array[j];

                if (i > 0) {
                    if (newArray[i] != o && newArray[i - 1] != o) {
                        newArray[i] = o;
                        i++;
                        continue;
                    }
                } else if (newArray[i] != o) {
                    newArray[i] = o;
                    i++;
                }
            }

            int idx = 0;
            for(int j = 0; j < newArray.length; j++) {
                int n = newArray[j];
                if (n == -1) {
                    idx = j - 1;
                    break;
                }
            }

            if (idx == 0) {
                idx =  newArray.length - 1;
            }

            int[] trueArray = new int[idx + 1];
            for (int j = 0; j < trueArray.length; j++) {
                trueArray[j] = newArray[j];
            }

            return trueArray;
        }

        return array;
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

    private int[] otherSort(int[] array) {
        if (array.length == 2) {
            int[] newArray = new int[2];

            if (array[0] > array[1]) {
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
            left = otherSort(left);
            int[] right = new int[rightLength];
            for (int ii = 0; ii < right.length; ii++) {
                right[ii] = array[ii + middle];
            }
            right = otherSort(right);
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
                } else if (left[l] > right[r]) {
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
