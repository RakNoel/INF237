#include <string>
#include <iostream>

using namespace std;

class Pos {
public:
    Pos() = default;

    int x{}, y{};

    Pos(int posX, int PosY) {
        x = posX;
        y = PosY;
    }

    Pos getNextMove(int direction) {
        int tx = x, ty = y;
        switch (direction) {
            case 0:
                tx++;
                break;
            case 1:
                ty--;
                break;
            case 2:
                tx--;
                break;
            case 3:
                ty++;
                break;
            default:
                exit(1);
        }
        return *new Pos(tx, ty);
    }

    bool outsideBoard() {
        return x < 0 || x > 7 || y < 0 || y > 7;
    }

    bool legalMove(char map[][9]) {
        return !this->outsideBoard() && (map[y][x] == '.' || map[y][x] == 'D');
    }

    bool isIceTower(char map[][9]) {
        return !this->outsideBoard() && (map[y][x] == 'I');
    }
};

void finish(bool success) {
    cout << ((success) ? "Diamond!" : "Bug!") << endl;
    exit(0);
}

int main() {
    char map[8][9];
    int direction = 0;
    Pos *robot = new Pos(0, 7);

    for (auto &i : map)
        cin >> i;

    map[robot->y][robot->x] = '.';

    string actions;
    cin >> actions;
    Pos holder, fire;

    for (auto &action : actions) {
        switch (action) {
            case 'F':
                holder = robot->getNextMove(direction);
                if (!holder.legalMove(map)) finish(false);
                *robot = holder;
                break;
            case 'R':
                direction = (direction - 1 < 0) ? 3 : direction - 1;
                break;
            case 'L':
                direction = (direction + 1) % 4;
                break;
            case 'X':
                fire = robot->getNextMove(direction);
                if (!fire.isIceTower(map)) finish(false);
                else map[fire.y][fire.x] = '.';
                break;
            default:
                exit(1);
        }
    }
    finish(map[robot->y][robot->x] == 'D');
}