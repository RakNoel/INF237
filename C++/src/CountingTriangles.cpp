#include <iostream>
#include <vector>

using namespace std;

struct Point {
public:
    double x, y;

    Point(double x, double y) {
        this->x = x;
        this->y = y;
    }
};

struct Vector {
public:
    double dx, dy;

    Vector(Point *a, Point *b) {
        this->dx = b->x - a->x;
        this->dy = b->y - a->y;
    }
};

struct LineSegment {
public:
    Point *p;
    Vector *v;

    LineSegment(Point *a, Point *b) {
        this->p = a;
        this->v = new Vector(a, b);
    }

    bool interSects(LineSegment *other) {
        double M = (-other->v->dx * this->v->dy + this->v->dx * other->v->dy);
        double s = (-this->v->dy * (this->p->x - other->p->x) + this->v->dx * (this->p->y - other->p->y)) / M;
        double t = (other->v->dx * (this->p->y - other->p->y) - other->v->dy * (this->p->x - other->p->x)) / M;

        return 0 <= s && s <= 1 && 0 <= t && t <= 1;
    }
};

long numTriangles(vector<LineSegment> *linesegments) {
    long res = 0;
    for (int i = 0; i < linesegments->size(); i++)
        for (int j = i + 1; j < linesegments->size(); j++)
            if (linesegments->at(i).interSects(&linesegments->at(j)))
                for (int k = j + 1; k < linesegments->size(); k++)
                    if (linesegments->at(j).interSects(&linesegments->at(k)))
                        if (linesegments->at(k).interSects(&linesegments->at(i)))
                            res++;
    return res;
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int numLineSegments;
    double ax, ay, bx, by;
    auto *segments = new vector<LineSegment>();

    cin >> numLineSegments;

    while (numLineSegments > 0) {
        segments->clear();
        for (int i = 0; i < numLineSegments; i++) {
            cin >> ax >> ay >> bx >> by;
            auto *tmp = new LineSegment(new Point(ax, ay), new Point(bx, by));
            segments->push_back(*tmp);
        }

        cin >> numLineSegments;
        cout << numTriangles(segments) << endl;
    }
};