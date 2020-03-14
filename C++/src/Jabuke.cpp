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
	Point* a, * b;

public:
	Vector(Point* a, Point* b) {
		this->a = a;
		this->b = b;
	}

	bool pointIsLeftOrOn(Point* test) {
		return (b->x - a->x) * (test->y - a->y) >= (b->y - a->y) * (test->x - a->x);
	}
};

class Triangle {
private:
	Vector* ab, * bc, * ca;

public:
	Triangle(Point* a, Point* b, Point* c) {
		this->ab = new Vector(a, b);
		this->bc = new Vector(b, c);
		this->ca = new Vector(c, a);
	}

	bool isInside(Point* test) {
		return ab->pointIsLeftOrOn(test) && bc->pointIsLeftOrOn(test) && ca->pointIsLeftOrOn(test);
	}
};

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);
	std::cout << std::fixed; std::cout << std::setprecision(1);

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

	Triangle* t;
	if (area > 0) t = new Triangle(a, b, c);
	else t = new Triangle(c, b, a);

	cin >> n;

	for (int i = 0; i < n; i++) {
		cin >> x >> y;
		Point* tmp = new Point(x, y);
		if (t->isInside(tmp)) numTrees++;
	}

	cout << abs(area) << endl << numTrees << endl;
}