#include<iostream>

bool subsetSum(int k, int i, int n, int array[], int result[]) {
    if (k == 0) {
        return true;
    }
    if (i >= n) {
        return false;
    }

    if (subsetSum(k, i +1, n, array, result)) {
        return true;
    }

    if (array[i] <= k && subsetSum(k - array[i], i +1, n, array, result)) {
        result[i] = 1;
        return true;
    }

    return false;

}
int main() {
    int k = 9;
    int n = 5;
    int array[n] = {3, 34, 4, 5, 2};

    int result[n];

    // initialize result table, result[i] = 1 or 0, meaning array[i] is 
    // or is not included in subset sum
    for (int i = 0; i < n; i++) {
        result[i] = 0;
    }

    subsetSum(k, 0, n, array, result);

    std::cout<< "Subset sum is: ";
    for (int i = 0; i < n; i++) {
        if (result[i] == 1) {
            std::cout<< array[i] << ", ";
        }
    }

    return 0;
}