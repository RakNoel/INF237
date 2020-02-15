#include <iostream>
#include <algorithm>
#include <vector> 


    int main() {
        std::ios::sync_with_stdio(false); // Speedup (do not mix with scanf/prinf)
        std::cin.tie(NULL); // Speedup (do not mix with scanf/prinf)
        int n, m, a;
        std::cin >> n >> m;

        std::vector<int> possible;
        std::vector<int> wanted;

        for (int i = 0; i < n; i++) {
            std::cin >> a;
            possible.push_back(a);
        }
        for (int i = 0; i < m; i++) {
            std::cin >> a;
            wanted.push_back(a);
        }

        std::sort(possible.begin(), possible.end());
        std::sort(wanted.begin(), wanted.end());

        int lastTaken = 0;
        long waste = 0;

        for (int i = 0; i < m; i++)
            for (int j = lastTaken; j < n && n > 0; j++)
                if (wanted[i] <= possible[j]) {
                    waste += possible[j] - wanted[i];
                    lastTaken = j;
                    break;
                }

        std::cout << waste << std::endl;
    }