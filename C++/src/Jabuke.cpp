#include <iostream>
#include <iomanip>
#include <stdexcept>

using namespace std;

struct Point {
    double x, y;

    Point(double x, double y) {
        this->x = x;
        this->y = y;
    }
};

class Vector {
private:
    Point *a, *b;

public:
    Vector(Point *a, Point *b) {
        this->a = a;
        this->b = b;
    }

    bool pointIsLeftOrOn(Point *test) {
        return (b->x - a->x) * (test->y - a->y) >= (b->y - a->y) * (test->x - a->x);
    }
};

class Triangle {
private:
    Vector *ab, *bc, *ca;

public:
    Triangle(Point *one, Point *two, Point *three) {
        this->ab = new Vector(one, two);
        this->bc = new Vector(two, three);
        this->ca = new Vector(three, one);
    }

    bool isInside(Point *test) {
        return ab->pointIsLeftOrOn(test) && bc->pointIsLeftOrOn(test) && ca->pointIsLeftOrOn(test);
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout << fixed;
    cout << setprecision(1);

    Point *a, *b, *c;
    int x, y, n;

    cin >> x >> y;
    a = new Point(x, y);
    cin >> x >> y;
    b = new Point(x, y);
    cin >> x >> y;
    c = new Point(x, y);

    unsigned short numTrees = 0;
    double area = ((a->x * (b->y - c->y)) + (b->x * (c->y - a->y)) + (c->x * (a->y - b->y))) / 2.0;

    Triangle *t = area > 0
            ? new Triangle(a, b, c)
            : new Triangle(c, b, a);

    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> x >> y;
        auto *tmp = new Point(x, y);
        if (t->isInside(tmp)) numTrees++;
    }

    cout << abs(area) << endl << numTrees << endl;
}