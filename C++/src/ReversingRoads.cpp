//
// Created by RakNoel on 07/02/2020.
//

#include <iostream>
#include <vector>
#include <string>

#include "Graph.cpp"

using namespace std;

const string SUCCESS_MSG = "valid";
const string FAIL_MSG = "invalid";

int itt = 0;
//int time = 0;
vector<bool> visited;
//vector<int> pre, post;

void PrintResult(const string& res) {
	cout << "Case " << ++itt << ": " << res << endl;
}

bool validVisited(int w) {
	for (int i = 0; i < w; i++)
		if (!visited.at(i))
			return false;
	return true;
}

void PrePost(Node n, Graph _g) {
	int pos = n.GetValue();
	visited.at(pos) = true;
	//pre.at(pos) = time++;

	for (unsigned int i = 0; i < n.GetEdges().size(); i++) {
		auto nbr = _g.GetNodes().at(n.GetEdges().at(i).GetValue());
		if (!visited.at(nbr.GetValue()))
			PrePost(nbr, _g);
	}

	//post.at(pos) = time++;
}

bool TestSCC(Graph _g) {
	size_t w = _g.GetNodes().size();
	visited.clear();
	visited.resize(w, false);
	//pre.clear();
	//pre.resize(w, 0);
	//post.clear();
	//post.resize(w, 0);
	//time = 1;

	PrePost(_g.GetNodes().at(0), _g);

	if (validVisited(w)) {
		_g.FlipGraph();
		visited.clear();
		visited.resize(w, false);
		//pre.clear();
		//pre.resize(w, 0);
		//post.clear();
		//post.resize(w, 0);
		PrePost(_g.GetNodes().at(0), _g);
		_g.FlipGraph();
	}
	return validVisited(w);
}

bool Brute(Graph _g, int i, int j) {
	int to = _g.FlipEdge(i, j);
	bool res = (TestSCC(_g));

	_g.FlipEdge(to, _g.EdgeAmount(to)-1);

	return res;
}

int main() {
	int w, n;
	std::ios::sync_with_stdio(false); // Speedup (do not mix with scanf/prinf)
	std::cin.tie(NULL); // Speedup (do not mix with scanf/prinf)

main: 
	while (cin >> w >> n) {
		int a, b;
		auto *g = new Graph(w);

		for (int i = 0; i < n; i++) {
			cin >> a >> b;
			g->AddEdge(a, b);
		}

		if (n < w) {
			PrintResult(FAIL_MSG);
			continue;
		}

		if (TestSCC(*g)) {
			PrintResult(SUCCESS_MSG);
			continue;
		}

		//BRUTE FORCER
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < g->EdgeAmount(i); j++) {
				if (Brute(*g, i, j)) {
					Node n = g->GetNodes().at(i);
					int toNode = n.GetEdges().at(j).GetValue();
					g->~Graph();
					PrintResult(to_string(n.GetValue()) + " " + to_string(toNode));
					goto main;
				}
			}
		}

		PrintResult(FAIL_MSG);

	}
}



