#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <cmath>

#define INF ((int) 1e9)

using namespace std;

struct dPoint {
public:
    dPoint(double x, double y) : x(x), y(y) {}

    double x, y;

    double distance(dPoint *b) {
        return sqrt(pow(b->x - this->x, 2) + pow(b->y - this->y, 2));
    }
};

struct Tuple {
    dPoint a, b;

    Tuple(const dPoint &a, const dPoint &b) : a(a), b(b) {}
};

Tuple getClosestPairRec(vector<dPoint> *Px, vector<dPoint> *Py, int start, int stop) {
    if (stop - start <= 3) {
        Tuple *min = nullptr;
        double minvalue = INF;
        for (int i = start; i < stop; i++)
            for (int j = i + 1; j < stop; j++)
                if (Px->at(i).distance(&Px->at(j)) < minvalue) {
                    minvalue = Px->at(i).distance(&Px->at(j));
                    min = new Tuple(Px->at(i), Px->at(j));
                }

        return *min;
    }

    int midPoint = ((stop - start) / 2) + start;
    Tuple minQ = getClosestPairRec(Px, Py, start, midPoint);
    Tuple minR = getClosestPairRec(Px, Py, midPoint, stop);

    auto delta = min(minQ.a.distance(&minQ.b), minR.a.distance(&minR.b));
    auto L = Px->at(midPoint);

    vector<dPoint> S;
    for (int i = start; i < stop; i++) {
        if (L.distance(&Py->at(i)) <= delta) S.push_back(Py->at(i));
    }

    Tuple *minS = nullptr;
    double minvalue = INF;
    for (int i = 0; i < S.size(); i++) {
        for (int j = i + 1; j <= i + 16 && j < S.size(); j++)
            if (S.at(i).distance(&S.at(j)) < minvalue) {
                minvalue = S.at(i).distance(&S.at(j));
                minS = new Tuple(S.at(i), S.at(j));
            }
    }

    if (minS != nullptr && minS->a.distance(&minS->b) < delta)
        return *minS;
    else if (minQ.a.distance(&minQ.b) < minR.a.distance(&minR.b))
        return minQ;
    else return minR;
}

Tuple getClosestPair(vector<dPoint> P) {
    vector<dPoint> Px = P;
    vector<dPoint> Py = P;
    sort(Px.begin(), Px.end(), [](const dPoint &a, const dPoint &b) { return (a.x < b.x); });
    sort(Py.begin(), Py.end(), [](const dPoint &a, const dPoint &b) { return (a.y < b.y); });

    return getClosestPairRec(&Px, &Py, 0, Px.size());
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int testCase;
    while (true) {
        cin >> testCase;
        if (testCase == 0) break;

        vector<dPoint> P;
        double a, b;
        for (int i = 0; i < testCase; i++) {
            cin >> a >> b;
            P.push_back(*new dPoint(a, b));
        }
        auto r = getClosestPair(P);
        cout << r.a.x << " " << r.a.y << " " << r.b.x << " " << r.b.y << endl;
    }
}