#include <iostream>
int routes[31];

int fall(int n) {
    if (n == 1) return 1;
    if (n == 2) return 2;
    if (n == 3) return 4;
    
    if (routes[n] == -1) {
        routes[n] = fall(n -1) + fall(n-2) + fall(n-3);
    }
    return routes[n];

}

int main() {
    for (int i = 0; i < 31; i++) {
        routes[i] = -1;
    }

    int n = 4;

    std::cout<<fall(n);
}