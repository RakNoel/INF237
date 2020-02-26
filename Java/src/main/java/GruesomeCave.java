import java.util.*;

public class GruesomeCave {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);

        int L = scn.nextInt();
        int W = scn.nextInt();
        scn.nextLine();
        char[][] chars = new char[L][W];

        for (int l = 0; l < L; l++) chars[l] = scn.nextLine().toCharArray();

        System.out.println(calculateGrueRisk(chars));
    }

    /**
     * Using djikstra to find minimum path, and calculate the risk
     * @param maze
     * @return
     */
    public static double calculateGrueRisk(char[][] maze) {
        var risksTable = getRiskTable(maze);
        var totalRisk = getCaveInfo(risksTable);
        int[][] distTable = new int[maze.length][maze[0].length];

        for (int i = 0; i < distTable.length; i++)
            for (int j = 0; j < distTable[0].length; j++)
                distTable[i][j] = Integer.MAX_VALUE;

        distTable[totalRisk.start.y][totalRisk.start.x] = 0;
        PriorityQueue<Pos> q = new PriorityQueue<>();
        totalRisk.start.value = 0;
        q.add(totalRisk.start);

        while (!q.isEmpty()) {
            var cx = q.peek().x;
            var cy = q.peek().y;
            q.poll();

            for (var p : getNeighbohurs(cx, cy, maze))
                if (risksTable[p.y][p.x] > -1)
                    if (distTable[p.y][p.x] > distTable[cy][cx] + risksTable[cy][cx]) {
                        distTable[p.y][p.x] = distTable[cy][cx] + risksTable[cy][cx];
                        p.value = distTable[p.y][p.x];
                        q.add(p);
                    }
        }


        if (totalRisk.maxRisk == 0) return 0;
        return (double) distTable[totalRisk.stop.y][totalRisk.stop.x] / (double) totalRisk.maxRisk;
    }

    public static CaveInfo getCaveInfo(int[][] risks) {
        var res = new CaveInfo();
        for (int i = 0; i < risks.length; i++)
            for (int j = 0; j < risks[0].length; j++)
                if (risks[i][j] > 0)
                    res.maxRisk += risks[i][j];
                else if (risks[i][j] == 0)
                    if (res.start == null) res.start = new Pos(j, i);
                    else res.stop = new Pos(j, i);
        return res;
    }

    public static int[][] getRiskTable(char[][] maze) {
        int[][] risks = new int[maze.length][maze[0].length];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                switch (maze[i][j]) {
                    case '#':
                        risks[i][j] = -1;
                        break;
                    case 'E':
                    case 'D':
                        risks[i][j] = 0;
                        break;
                    case ' ':
                        for (Pos p : getNeighbohurs(i, j, maze)) if (maze[p.y][p.x] == ' ') risks[p.y][p.x]++;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + maze[i][j]);
                }
            }
        }
        return risks;
    }

    public static List<Pos> getNeighbohurs(int cx, int cy, char[][] maze) {
        ArrayList<Pos> valids = new ArrayList<>();
        int[] x = {0, +1, 0, -1};
        int[] y = {+1, 0, -1, 0};
        for (int i = 0; i < 4; i++)
            try {
                if (maze[cy - y[i]][cx - x[i]] != '#')
                    valids.add(new Pos(cx - x[i], cy - y[i]));
            } catch (IndexOutOfBoundsException ignore) {
            }
        return valids;
    }
}

class Pos implements Comparable<Pos> {
    int x, y;
    long value;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pos o) {
        return Long.compare(this.value, o.value);
    }
}

class CaveInfo {
    Pos start, stop;
    long maxRisk;
}
