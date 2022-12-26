#include <iostream>
#include <math.h>
int main() {
    int n = 0;
    int dp[n];
    dp[0] = 1;
    dp[1] = 1;
    for (int i = 2; i <= n; i++) {
        dp[i] = dp[i/2] + (int) pow(-1, i) * dp[i/2 -1];
    }
    std::cout<< "Result is "<< dp[n];
    return 0;
}