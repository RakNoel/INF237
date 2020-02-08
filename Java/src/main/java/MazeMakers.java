import java.util.BitSet;

public class MazeMakers {
    static final int[] cy = {0, +1, 0, -1};
    static final int[] cx = {-1, 0, +1, 0};

    static int foundEdge = 0;
    static boolean loops;

    public static void main(String[] args) {
        Kattio kattio = new Kattio(System.in, System.out);

        while (true) {
            int height = kattio.getInt();
            int length = kattio.getInt();

            //Should exit on 0 0
            if (length == 0 && height == 0)
                break;

            char[][] inn = new char[height][length];
            int[][] maze = new int[height][length];
            BinaryMatrix visited = new BinaryMatrix(length, height);

            for (int i = 0; i < height; i++)
                inn[i] = kattio.getWord().toCharArray();

            for (int i = 0; i < height; i++)
                for (int j = 0; j < length; j++)
                    maze[i][j] = Integer.parseInt(inn[i][j] + "", 16);

            int root = -1;
            int sy = 0;
            int sx = 0;
            for (int i = 0; i < height && root < 0; i++)
                for (int j = 0; j < length; j++)
                    if (inn[i][j] != 'F') {
                        root = maze[i][j];
                        sx = j;
                        sy = i;
                        break;
                    }

            dfs(sx, sy, visited, maze);

            if (foundEdge < 2) {
                System.out.println("NO SOLUTION");
            } else if (visited.cardinality() != height * length) {
                System.out.println("UNREACHABLE CELL");
            } else if (loops) {
                System.out.println("MULTIPLE PATHS");
            } else {
                System.out.println("MAZE OK");
            }
        }
    }

    static void dfs(int _x, int _y, BinaryMatrix visited, int[][] graph) {
        visited.set(_x, _y, true);
        var hold = graph[_y][_x];
        int alreadyVisited = 0;
        //System.out.printf("x:%d y:%d, value:%d, cell:%s %n", _x, _y, hold, Integer.toHexString(hold));

        for (int m = 1, i = 0; m <= 8; m *= 2, i++)
            if ((hold & m) == 0) {
                var nx = _x + cx[i];
                var ny = _y + cy[i];
                if (nx < 0 || nx > graph[0].length - 1 || ny < 0 || ny > graph.length - 1)
                    foundEdge++;
                else if (visited.get(nx, ny))
                    alreadyVisited++;
                else
                    dfs(nx, ny, visited, graph);
            }

        if (alreadyVisited > 1)
            loops = true;
    }

}

class BinaryMatrix {
    private BitSet matrix;
    private int width, height;

    public BinaryMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new BitSet(width * height);
    }

    public void set(int x, int y, boolean v) {
        this.matrix.set(convert2dTo1D(x, y), v);
    }

    public boolean get(int x, int y) {
        return this.matrix.get(convert2dTo1D(x, y));
    }

    public int cardinality() {
        return this.matrix.cardinality();
    }

    private int convert2dTo1D(int x, int y) {
        var form = (y * this.width) + x;
        if (form >= height * width)
            throw new IndexOutOfBoundsException(
                    String.format(
                            "Trying to use (x:%d y:%d) is out of bounds for width:%d and height:%d",
                            x, y,
                            width, height
                    )
            );
        return form;
    }
}