#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <climits>

using namespace std;

bool colourSafe(vector<vector<int>>* adjList, vector<int> vertexColours, int testingNode, int testingColour) {
	for (int v : adjList->at(testingNode)) if (vertexColours.at(v) == testingColour) return false;
	return true;
}

int backTrack(vector<vector<int>>* adjList, vector<int> vertexColours, int currentUsed, int working) {
	//Outside list == done
	if (working == adjList->size()) return 0;

	int minColours = INT_MAX;

	for (int testingColour = 1; testingColour < currentUsed; testingColour++) {
		if (colourSafe(adjList, vertexColours, working, testingColour)) {
			vertexColours[working] = testingColour;
			int tmp = backTrack(adjList, vertexColours, currentUsed, working + 1);
			minColours = min(minColours, tmp);
		}
	}

	vertexColours[working] = currentUsed;
	int tmp = 1 + backTrack(adjList, vertexColours, currentUsed + 1, working + 1);
	return min(minColours, tmp);
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	int numberNodes;
	cin >> numberNodes;
	cin.ignore();

	vector<vector<int>>* adjList = new vector<vector<int>>(numberNodes);
	vector<int>* vertexColours = new vector<int>(numberNodes, -1);

	for (int i = 0; i < numberNodes; i++) {
		string tmp;
		getline(cin, tmp);
		vector<int>* holder = new vector<int>();
		string bldr = "";
		for (auto c : tmp) {
			if (c == ' ') {
				holder->push_back(stoi(bldr));
				bldr = "";
			}
			else {
				bldr += c;
			}
		}
		holder->push_back(stoi(bldr));
		adjList->at(i) = *holder;
	}

	int res = backTrack(adjList, *vertexColours, 1, 0);
	if (res > numberNodes) throw "Faulty";
	cout << res << endl;
}