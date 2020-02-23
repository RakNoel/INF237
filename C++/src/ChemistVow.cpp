#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

std::vector<std::string> TableOfDoubleElements = { "ac", "ag", "al", "am", "ar", "as", "at", "au", "ba", "be", "bh", "bi",
			"bk", "br", "ca", "cd", "ce", "cf", "cl", "cm", "cn", "co", "cr", "cs", "cu", "db", "ds", "dy", "er",
			"es", "eu", "fe", "fl", "fm", "fr", "ga", "gd", "ge", "he", "hf", "hg", "ho", "hs", "in", "ir", "kr",
			"la", "li", "lr", "lu", "lv", "md", "mg", "mn", "mo", "mt", "na", "nb", "nd", "ne", "ni", "no", "np",
			"os", "pa", "pb", "pd", "pm", "po", "pr", "pt", "pu", "ra", "rb", "re", "rf", "rg", "rh", "rn", "ru",
			"sb", "sc", "se", "sg", "si", "sm", "sn", "sr", "ta", "tb", "tc", "te", "th", "ti", "tl", "tm", "xe",
			"yb", "zn", "zr" };

std::vector<char> TableOfSingleElements = { 'b', 'c', 'f', 'h', 'i', 'k', 'n', 'o', 'p', 's', 'u', 'v', 'w', 'y' };

static bool doubleSearch(std::vector<bool>* singles, std::vector<bool>* doubles, std::vector<bool>* working, int sp, int* n) {
	if (working->at(sp)) return false;
	for (int i = sp; i < *n; ) {
		working->at(i) = true;
		if (singles->at(i) && doubles->at(i)) {
			return doubleSearch(singles, doubles, working, i + 1, n) || doubleSearch(singles, doubles, working, i + 2, n);
		}
		else if (doubles->at(i)) {
			i += 2;
		}
		else if (singles->at(i)) {
			i += 1;
		}
		else {
			return false;
		}
	}
	return true;
}

static bool isWritableInTableOfElements(std::string word) {
	int n = (int)word.length();
	bool tmp;

	std::vector<bool> singles;
	std::vector<bool> doubles;

	for (int i = 0, j = 2; i < n; i++) {
		char a = word.at(i);
		tmp = std::find(TableOfSingleElements.begin(), TableOfSingleElements.end(), a) != TableOfSingleElements.end();
		singles.push_back(tmp);
		if (j <= n) {
			std::string x = word.substr(i, 2);
			tmp = std::find(TableOfDoubleElements.begin(), TableOfDoubleElements.end(), x) != TableOfDoubleElements.end();
			doubles.push_back(tmp);
		}
	}
	doubles.push_back(false);
	std::vector<bool>* working = new std::vector<bool>(n + 1);
	return doubleSearch(&singles, &doubles, working, 0, &n);
}

int main() {
	std::ios::sync_with_stdio(false);
	std::cin.tie(NULL);

	int n;
	std::string s;
	std::cin >> n;

	for (int i = 0; i < n; i++) {
		std::cin >> s;
		auto res = isWritableInTableOfElements(s) ? "YES" : "NO";
		if (s.size() == 0) res = "NO";
		std::cout << res << std::endl;
	}
}