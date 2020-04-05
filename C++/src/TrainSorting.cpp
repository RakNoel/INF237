#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

static int solveProblem(vector<int> *a, int n) {
    if (n <= 2) return n;

    vector<int> lds(n);
    vector<int> lis(n);
    int res = 1;

    fill(lds.begin(), lds.end(), 1);
    fill(lis.begin(), lis.end(), 1);

    for (int i = n - 2; i >= 0; i--)
        for (int k = n - 1; k > i; k--) {
            if (a->at(k) < a->at(i) && lds.at(i) < lds.at(k) + 1)
                lds.at(i) = lds.at(k) + 1;
            else if (a->at(k) > a->at(i) && lis.at(i) < lis.at(k) + 1)
                lis.at(i) = lis.at(k) + 1;

            res = max(res, ((lis.at(i) + lds.at(i)) - 1));
        }
    return res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n, s;
    cin >> n;

    auto *arr = new vector<int>();

    for (int i = 0; i < n; i++) {
        cin >> s;
        arr->push_back(s);
    }

    cout << solveProblem(arr, n) << endl;
}