#include <iostream>
#include <set>
#include <queue>
#include <unordered_set>
#include <string>

#include "UnionFind.cpp"

using namespace std;

class Line { 
public:
	int from, to, cost;

	bool operator<(const Line& a) const {
		return (cost > a.cost);
	}
};

string solve(const int* numHouses, const int* numBadHouses, const int* possibleLines,
	priority_queue<Line>* goodLines,
	priority_queue<Line>* badLines,
	priority_queue<Line>* dumpLines) {

	const string impossible = "impossible";
	long totalCost = 0;
	Line nextLine{};

	//Edge case of 2 single bad houses
	if (*numHouses == 2 && *numBadHouses == 2 && *possibleLines > 0)
		return (!dumpLines->empty()) ? to_string(dumpLines->top().cost) : impossible;

	UnionFind uf = UnionFind(*numHouses);

	while (!goodLines->empty() && (uf.getTotal() - *numBadHouses > 1)) {
		nextLine = goodLines->top();
		goodLines->pop();

		//Continue if loop
		if (uf.isConnected(nextLine.from, nextLine.to)) continue;

		totalCost += nextLine.cost;
		uf.connect(nextLine.from, nextLine.to);
	}

	//If not connected yet, we cannot use bad houses as hubs
	if (uf.getTotal() - *numBadHouses > 1) return impossible;

	while (!badLines->empty() && (uf.getTotal() > 1)) {
		nextLine = badLines->top();
		badLines->pop();

		//Continue if loop
		if (uf.isConnected(nextLine.from, nextLine.to)) continue;

		totalCost += nextLine.cost;
		uf.connect(nextLine.from, nextLine.to);
	}

	return (uf.getTotal() > 1) ? impossible : to_string(totalCost);
}


int main() {
	ios::sync_with_stdio(false);
	cin.tie(nullptr);

	int numHouses, possibleLines, numBadHouses, tmp;
	bool hasA, hasB;
	Line tmpLine{};

	cin >> numHouses >> possibleLines >> numBadHouses;

	unordered_set<int> badHouses(numBadHouses);
	for (int i = 0; i < numBadHouses; i++) {
		cin >> tmp;
		badHouses.emplace(tmp - 1);
	}

	priority_queue<Line> goodLines;
	priority_queue<Line> badLines;
	priority_queue<Line> dumpLines;

	for (int i = 0; i < possibleLines; i++) {
		cin >> tmpLine.from; tmpLine.from -= 1;
		cin >> tmpLine.to; tmpLine.to -= 1;
		cin >> tmpLine.cost;

		hasA = (badHouses.count(tmpLine.from) > 0);
		hasB = (badHouses.count(tmpLine.to) > 0);

		if (hasA && hasB)
			dumpLines.emplace(tmpLine);
		else if (hasA || hasB)
			badLines.emplace(tmpLine);
		else
			goodLines.emplace(tmpLine);
	}

	cout << solve(&numHouses, &numBadHouses, &possibleLines, &goodLines, &badLines, &dumpLines) << endl;
}