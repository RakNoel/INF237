#include <set> 
#include <vector>
#include <iostream>
#include <algorithm>

static int tmpWeight;

static bool isOrdered(std::vector<int> list) {
	if (list.size() == 0) return true;
	if (list.size() % 2 != 0) return false;
	for (int i = 0; i < list.size(); i += 2)
		if (list.at(i) != list.at(i + 1)) return false;
	return true;
}

static bool testSolution(std::vector<int> list1, std::vector<int> list2) {
	std::vector<int> l1, l2;
	std::copy_if(list1.begin(), list1.end(), std::back_inserter(l1), [](int x) {return x > tmpWeight; });
	std::copy_if(list2.begin(), list2.end(), std::back_inserter(l2), [](int x) {return x > tmpWeight; });

	return isOrdered(l1) && isOrdered(l2);
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);

	int pairs, tmp;
	std::cin >> pairs;

	std::vector<int> row1;
	std::vector<int> row2;

	std::set<int> uniqueWeights;
	uniqueWeights.insert(0);
	for (int i = 0; i < pairs; i++) {
		std::cin >> tmp;
		row1.push_back(tmp);
		uniqueWeights.insert(tmp);
	}
	for (int i = 0; i < pairs; i++) {
		std::cin >> tmp;
		row2.push_back(tmp);
		uniqueWeights.insert(tmp);
	}

	std::vector<int> sortedUniqueWeights(uniqueWeights.begin(), uniqueWeights.end());
	std::sort(sortedUniqueWeights.begin(), sortedUniqueWeights.end());

	//BinarySearch / Bisection
	int minPointer = 0;
	int maxPointer = sortedUniqueWeights.size();

	while (minPointer < maxPointer) {
		int pointer = (maxPointer + minPointer) / 2;
		tmpWeight = sortedUniqueWeights.at(pointer);
		if (testSolution(row1, row2)) maxPointer = pointer;
		else minPointer = pointer + 1;
	}

	std::cout << sortedUniqueWeights.at(minPointer) << std::endl;
}
