#include <string>
#include <iostream>

using namespace std;

int main() {
    int MaxNumber = 1001;
    int MinNumber = 0;

    int CurrentNumber = 1000 / 2;

    while (true) {
        cout << CurrentNumber << endl;
        string answer;
        cin >> answer;

        if (answer == "lower") {
            MaxNumber = CurrentNumber;
            CurrentNumber -= (MaxNumber - MinNumber) / 2;
        } else if (answer == "higher") {
            MinNumber = CurrentNumber;
            CurrentNumber += (MaxNumber - MinNumber) / 2;
        } else {
            break;
        }
    }
}