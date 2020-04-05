#include <string>
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> TableOfDoubleElements() {
    return {"ac", "ag", "al", "am", "ar", "as", "at", "au", "ba", "be", "bh", "bi",
            "bk", "br", "ca", "cd", "ce", "cf", "cl", "cm", "cn", "co", "cr", "cs", "cu", "db", "ds", "dy", "er",
            "es", "eu", "fe", "fl", "fm", "fr", "ga", "gd", "ge", "he", "hf", "hg", "ho", "hs", "in", "ir", "kr",
            "la", "li", "lr", "lu", "lv", "md", "mg", "mn", "mo", "mt", "na", "nb", "nd", "ne", "ni", "no", "np",
            "os", "pa", "pb", "pd", "pm", "po", "pr", "pt", "pu", "ra", "rb", "re", "rf", "rg", "rh", "rn", "ru",
            "sb", "sc", "se", "sg", "si", "sm", "sn", "sr", "ta", "tb", "tc", "te", "th", "ti", "tl", "tm", "xe",
            "yb", "zn", "zr"};
}

vector<char> TableOfSingleElements() {
    return {'b', 'c', 'f', 'h', 'i', 'k', 'n', 'o', 'p', 's', 'u', 'v', 'w', 'y'};
}

static bool
doubleSearch(vector<bool> *singles, vector<bool> *doubles, vector<bool> *working, int sp, int *n) {
    if (working->at(sp)) return false;
    for (int i = sp; i < *n;) {
        working->at(i) = true;
        if (singles->at(i) && doubles->at(i)) {
            return doubleSearch(singles, doubles, working, i + 1, n) ||
                   doubleSearch(singles, doubles, working, i + 2, n);
        } else if (doubles->at(i)) {
            i += 2;
        } else if (singles->at(i)) {
            i += 1;
        } else {
            return false;
        }
    }
    return true;
}

static bool isWritableInTableOfElements(string word) {
    int n = (int) word.length();
    bool tmp;
    auto t1 = TableOfSingleElements();
    auto t2 = TableOfDoubleElements();

    vector<bool> singles;
    vector<bool> doubles;

    for (int i = 0, j = 2; i < n; i++) {
        char a = word.at(i);
        tmp = find(t1.begin(), t1.end(), a) != t1.end();
        singles.push_back(tmp);
        if (j <= n) {
            string x = word.substr(i, 2);
            tmp = find(t2.begin(), t2.end(), x) != t2.end();
            doubles.push_back(tmp);
        }
    }
    doubles.push_back(false);
    auto *working = new vector<bool>(n + 1);
    return doubleSearch(&singles, &doubles, working, 0, &n);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    string s;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> s;
        auto res = isWritableInTableOfElements(s) ? "YES" : "NO";
        if (s.empty()) res = "NO";
        cout << res << endl;
    }
}