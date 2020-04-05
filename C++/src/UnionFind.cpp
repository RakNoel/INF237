#include <vector>

using namespace std;

class UnionFind {
private:
    vector<int> pointer;
    vector<int> size;
    long total;

public:
    explicit UnionFind(int size) {
        this->total = size;
        for (int i = 0; i < size; i++) {
            pointer.push_back(i);
            this->size.push_back(0);
        }
    }

    int find(int a) {
        while (pointer.at(a) != a) a = pointer.at(a);
        return a;
    }

    bool isConnected(int a, int b) {
        return find(a) == find(b);
    }

    void connect(int a, int b) {
        int topA = find(a);
        int topB = find(b);
        if (topA == topB) return;

        this->total--;

        if (size.at(topA) > size.at(topB)) {
            pointer.at(topB) = pointer.at(topA);
            size.at(topA) += size.at(topB);
        }
        else {
            pointer.at(topA) = pointer.at(topB);
            size.at(topB) += size.at(topA);
        }
    }

    long getTotal() {
        return total;
    }
};