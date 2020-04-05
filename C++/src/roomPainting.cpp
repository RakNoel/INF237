#include <iostream>
#include <algorithm>
#include <vector> 

using namespace std;

int main() {
	ios::sync_with_stdio(false); // Speedup (do not mix with scanf/prinf)
	cin.tie(nullptr); // Speedup (do not mix with scanf/prinf)
	int n, m, a;
	cin >> n >> m;

	vector<int> possible;
	vector<int> wanted;

	for (int i = 0; i < n; i++) {
		cin >> a;
		possible.push_back(a);
	}
	for (int i = 0; i < m; i++) {
		cin >> a;
		wanted.push_back(a);
	}

	sort(possible.begin(), possible.end());
	sort(wanted.begin(), wanted.end());

	int lastTaken = 0;
	long waste = 0;

	for (int i = 0; i < m; i++)
		for (int j = lastTaken; j < n && n > 0; j++)
			if (wanted[i] <= possible[j]) {
				waste += possible[j] - wanted[i];
				lastTaken = j;
				break;
			}

	cout << waste << endl;
}