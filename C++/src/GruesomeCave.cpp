#include <iostream>
#include <vector>
#include <algorithm>
#include <set>
#include <map>
#include <queue>
#include <unordered_set>
#include <string>
#include <climits>

using namespace std;

class Pos {
public:
	int x, y;
	long value;

	bool operator<(const Pos& a) const {
		return (value > a.value);
	}

};

class CaveInfo {
public:
	Pos start, stop;
	long maxRisk;
	bool startSet;
};


static CaveInfo* getCaveInfo(vector<vector<int>>* risks) {
	CaveInfo* res = new CaveInfo();
	Pos p;
	for (int i = 0; i < risks->size(); i++)
		for (int j = 0; j < risks->at(i).size(); j++)
			if (risks->at(i).at(j) > 0)
				res->maxRisk += risks->at(i).at(j);
			else if (risks->at(i).at(j) == 0) {
				p.x = j;
				p.y = i;
				if (!res->startSet) {
					res->start = p;
					res->startSet = true;
				}
				else res->stop = p;
			}
	return res;
}

static bool isInside(int* x, int* y, vector<vector<char>>* maze) {
	return (*x >= 0 && *y >= 0 && *x < maze->size() && *y < maze->at(*x).size());
}

static vector<Pos>* getNeighbohurs(int* cx, int* cy, vector<vector<char>>* maze) {
	vector<Pos>* valids = new vector<Pos>();
	Pos newPos;
	const int x[] = { 0, +1, 0, -1 };
	const int y[] = { +1, 0, -1, 0 };

	for (int i = 0; i < 4; i++) {
		newPos.x = *cx - x[i];
		newPos.y = *cy - y[i];

		if (!isInside(&newPos.x, &newPos.y, maze)) continue;

		if (maze->at(newPos.y).at(newPos.x) != '#')
			valids->push_back(newPos);
	}
	return valids;
}

static vector<vector<int>>* getRiskTable(vector<vector<char>>* maze) {
	vector<vector<int>>* risks = new vector<vector<int>>();

	for (int i = 0; i < maze->size(); i++) {
		vector<int> r;
		for (int j = 0; j < maze->at(i).size(); j++)
			r.push_back(0);
		risks->push_back(r);
	}

	for (int i = 0; i < maze->size(); i++) {
		for (int j = 0; j < maze->at(i).size(); j++) {
			switch (maze->at(i).at(j)) {
			case '#':
				risks->at(i).at(j) = -1;
				break;
			case 'E':
			case 'D':
				risks->at(i).at(j) = 0;
				break;
			case ' ':
				for (auto p : *getNeighbohurs(&j, &i, maze)) {
					if (maze->at(p.y).at(p.x) == ' ')
						risks->at(p.y).at(p.x)++;
				}
				break;
			default:
				throw new exception;
			}
		}
	}
	return risks;
}

/**
 * Using djikstra to find minimum path, and calculate the risk
 * @param maze
 * @return
 */
static double calculateGrueRisk(vector<vector<char>>* maze) {
	vector<vector<int>>* risksTable = getRiskTable(maze);
	CaveInfo* totalRisk = getCaveInfo(risksTable);

	vector<vector<int>> distTable;

	for (int i = 0; i < maze->size(); i++) {
		vector<int> r;
		for (int j = 0; j < maze->at(i).size(); j++)
			r.push_back(INT_MAX);
		distTable.push_back(r);
	}

	distTable.at(totalRisk->start.y).at(totalRisk->start.x) = 0;
	priority_queue<Pos> q;
	totalRisk->start.value = 0;
	q.push(totalRisk->start);

	while (!q.empty()) {
		int cx = q.top().x;
		int cy = q.top().y;
		q.pop();

		for (auto& p : *getNeighbohurs(&cx, &cy, maze))
			if (risksTable->at(p.y).at(p.x) > -1)
				if (distTable.at(p.y).at(p.x) > distTable.at(cy).at(cx) + risksTable->at(p.y).at(p.x)) {
					distTable.at(p.y).at(p.x) = distTable.at(cy).at(cx) + risksTable->at(p.y).at(p.x);
					p.value = distTable.at(p.y).at(p.x);
					q.push(p);
				}
	}

	if (totalRisk->maxRisk == 0) return 0;
	return (double)distTable.at(totalRisk->stop.y).at(totalRisk->stop.x) / (double)totalRisk->maxRisk;
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);

	int L, W;
	string tmp;
	cin >> L >> W;
	vector<vector<char>> chars;
	getline(cin, tmp);

	for (int l = 0; l < L; l++) {
		getline(cin, tmp);
		vector<char> line;
		for (int w = 0; w < W; w++) line.push_back(tmp.at(w));
		chars.push_back(line);
	}

	cout << calculateGrueRisk(&chars) << endl;
}