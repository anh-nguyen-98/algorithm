#include <iostream>
int fib (int n) {
    if (n < 2) {
        return n;
    }
    int smallerTerm = 0;
    int biggerTerm = 1;

    int result = 0;
    for (int i = 2; i <= n; i++) {
        result = biggerTerm + smallerTerm;
        smallerTerm = biggerTerm;
        biggerTerm = result;
    }
    return result;
}


int main() {

    int n = 4;

    std::cout<< fib(n);


}