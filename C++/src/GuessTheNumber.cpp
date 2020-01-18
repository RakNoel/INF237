#include <string>
#include <iostream>

int main() {
    int MaxNumber = 1001;
    int MinNumber = 0;

    int CurrentNumber = 1000 / 2;

    while (true) {
        std::cout << CurrentNumber << std::endl;
        std::string answer;
        std::cin >> answer;

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