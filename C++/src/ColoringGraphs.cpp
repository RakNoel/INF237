#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <climits>

using namespace std;

bool colourSafe(vector<vector<int>> *adjList, vector<int> *vertexColours, int testingNode, int testingColour) {
    for (int v : adjList->at(testingNode)) if (vertexColours->at(v) == testingColour) return false;
    return true;
}

int backTrack(vector<vector<int>> *adjList, vector<int> vertexColours, int currentUsed, int working) {
    //Outside list == done
    if (working == adjList->size()) return 0;

    //Set high to ensure larger
    //Could also be = n
    int minColours = INT_MAX;

    //Will skip 0th as there is only one color
    for (int testingColour = 1; testingColour < currentUsed; testingColour++)
        if (colourSafe(adjList, &vertexColours, working, testingColour)) {
            vertexColours[working] = testingColour;
            minColours = min(minColours, backTrack(adjList, vertexColours, currentUsed, working + 1));
        }

    vertexColours[working] = currentUsed;
    return min(minColours, 1 + backTrack(adjList, vertexColours, currentUsed + 1, working + 1));
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int numberNodes;
    cin >> numberNodes;
    cin.ignore();

    auto *adjList = new vector<vector<int>>(numberNodes);
    auto *vertexColours = new vector<int>(numberNodes);

    for (int i = 0; i < numberNodes; i++) {
        string tmp;
        getline(cin, tmp);
        auto *holder = new vector<int>();
        string bldr;
        for (auto c : tmp) {
            if (c == ' ') {
                holder->push_back(stoi(bldr));
                bldr = "";
            } else
                bldr += c;
        }
        holder->push_back(stoi(bldr));
        adjList->at(i) = *holder;
    }

    cout << backTrack(adjList, *vertexColours, 1, 0) << endl;
}