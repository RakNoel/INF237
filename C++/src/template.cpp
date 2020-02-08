#include <iostream>
#include <vector>
#include <array>
#include <algorithm>
#include <cmath>
#include <tuple>
#include <cstdio>
#include <cstdlib>
#include <string>
#include <cstring>
#include <climits>
#include <cfloat>
#include <set>
#include <map>
#include <queue>
#include <stdexcept>
#include <iomanip>

using namespace std;

// Shortcuts for common data types
typedef long long          LL;
typedef unsigned long long ULL;
typedef long double        LD;
typedef pair<int, int>     pii;
typedef vector<pii>        vii;
typedef vector<int>        vi;

// Various
#define rep(i,n) for(int i=0;i<(n);i++)
#define foreach(i, a) for(__typeof(a.begin()) i = a.begin(); i != a.end(); i++)
#define feq(x,y) (fabs(x-y) <= DBL_EPSILON)
#define min(a,b) ((a)<(b)?(a):(b))
#define max(a,b) ((a)>(b)?(a):(b))

// Bit operations
#define set_bit(x, i) (x) |= (1 << (i))
#define clr_bit(x, i) (x) & ~(1 << (i))
#define tog_bit(x, i) (x) ^= (1 << (i))
#define chk_bit(x, i) (((x) >> (i)) & 1)

// Quick scanning
#define sf(n)       scanf("%d", &n)
#define sff(a,b)    scanf("%d %d", &a, &b)
#define sfff(a,b,c) scanf("%d %d %d", &a, &b, &c)

// Useful constants
#define MOD 1000000007
#define INF ((int) 1e9)
#define EPS 1e-9

// Shortcuts for pair
#define x first
#define y second

// Debugging
#define DBUG(x)    std::cout<<#x<<" is "<<x<< std::endl

int main() {
    std::ios::sync_with_stdio(false); // Speedup (do not mix with scanf/prinf)
    std::cin.tie(NULL); // Speedup (do not mix with scanf/prinf)
    std::cout << std::fixed; std::cout << std::setprecision(2); //print as 1.23

    
    return 0;
}
