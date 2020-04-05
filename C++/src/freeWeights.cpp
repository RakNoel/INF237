#include <set>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

static int tmpWeight;

static bool isOrdered(vector<int> list) {
    if (list.empty()) return true;
    if (list.size() % 2 != 0) return false;
    for (int i = 0; i < list.size(); i += 2)
        if (list.at(i) != list.at(i + 1)) return false;
    return true;
}

static bool testSolution(vector<int> list1, vector<int> list2) {
    vector<int> l1, l2;
    copy_if(list1.begin(), list1.end(), back_inserter(l1), [](int x) { return x > tmpWeight; });
    copy_if(list2.begin(), list2.end(), back_inserter(l2), [](int x) { return x > tmpWeight; });

    return isOrdered(l1) && isOrdered(l2);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int pairs, tmp;
    cin >> pairs;

    vector<int> row1;
    vector<int> row2;

    set<int> uniqueWeights;
    uniqueWeights.insert(0);
    for (int i = 0; i < pairs; i++) {
        cin >> tmp;
        row1.push_back(tmp);
        uniqueWeights.insert(tmp);
    }
    for (int i = 0; i < pairs; i++) {
        cin >> tmp;
        row2.push_back(tmp);
        uniqueWeights.insert(tmp);
    }

    vector<int> sortedUniqueWeights(uniqueWeights.begin(), uniqueWeights.end());
    sort(sortedUniqueWeights.begin(), sortedUniqueWeights.end());

    //BinarySearch / Bisection
    int minPointer = 0;
    int maxPointer = sortedUniqueWeights.size();

    while (minPointer < maxPointer) {
        int pointer = (maxPointer + minPointer) / 2;
        tmpWeight = sortedUniqueWeights.at(pointer);
        if (testSolution(row1, row2)) maxPointer = pointer;
        else minPointer = pointer + 1;
    }

    cout << sortedUniqueWeights.at(minPointer) << endl;
}
