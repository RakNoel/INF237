#include <iostream>
#include <vector>
#include <algorithm>

#define max(a,b) ((a)>(b)?(a):(b))

static int solveProblem(std::vector<int> *a, int n) {
    if (n <= 2) return n;

    std::vector<int> lds(n);
    std::vector<int> lis(n);
    int res = 1;

    std::fill(lds.begin(), lds.end(), 1);
    std::fill(lis.begin(), lis.end(), 1);
    
    for (int i = n - 2; i >= 0; i--)
        for (int k = n - 1; k > i; k--) {
            if (a->at(k) < a->at(i) && lds.at(i) < lds.at(k) + 1)
            {
                lds.at(i) = lds.at(k) + 1;
            }
            else if (a->at(k) > a->at(i) && lis.at(i) < lis.at(k) + 1)
            {
                lis.at(i) = lis.at(k) + 1;
            }

            res = max(res, ((lis.at(i) + lds.at(i)) - 1));
        }
    return res;
}

int main() {
    std::ios::sync_with_stdio(false);
    std::cin.tie(NULL);


    int n, s;
    std::cin >> n;

    std::vector<int> *arr = new std::vector<int>();

    for (int i = 0; i < n; i++) {
        std::cin >> s;
        arr->push_back(s);
    }

    std::cout << solveProblem(arr, n) << std::endl;

    return 0;
}