public class MazeSolverImpl {

    /**
     * You should write your code within this method. A good rule of thumb, especially with
     * recursive problems like this, is to write your input exception handling within this
     * method and then use helper methods to carry out the actual recursion.
     * <p>
     * How you set up the recursive methods is up to you, but note that since this is a *static*
     * method, all helper methods that it calls must *also* be static. Make them package-private
     * (i.e. without private or public modifiers) so you can test them individually.
     *
     * @param maze        See the writeup for more details about the format of the input maze.
     * @param sourceCoord The source (starting) coordinate
     * @param goalCoord   The goal (ending) coordinate
     * @return a matrix of the same dimension as the input maze containing the solution
     * path marked with 1's, or null if no path exists. See the writeup for more details.
     * @throws IllegalArgumentException in the following instances:
     * 1. If the maze is null
     * 2. If m * n <= 1 where m and n are the dimensions of the maze
     * 3. If either the source coordinate OR the goal coordinate are out of the matrix bounds.
     * 4. If your source or goal coordinate are on a blocked tile.
     */
    public static int[][] solveMaze(int[][] maze, Coordinate sourceCoord, Coordinate goalCoord) {
        if (maze == null || maze.length <= 1 || maze[0].length <= 1
                || sourceCoord.getX() < 0 || sourceCoord.getX() >= maze[0].length
                || sourceCoord.getY() < 0 || sourceCoord.getY() >= maze.length
                || goalCoord.getX() < 0 || goalCoord.getX() >= maze[0].length
                || goalCoord.getY() < 0 || goalCoord.getY() >= maze.length
                || maze[sourceCoord.getY()][sourceCoord.getX()] == 1
                || maze[goalCoord.getY()][goalCoord.getX()] == 1) {
            throw new IllegalArgumentException();
        }
        int[][] sol = new int[maze.length][maze[0].length];

        if (recu(maze, sol, sourceCoord.getX(), sourceCoord.getY(),
                goalCoord.getX(), goalCoord.getY())) {
            return sol;
        }
        return null;
    }

    public static boolean recu(int[][] maze, int[][] sol, int x, int y, int gx, int gy) {

        boolean res = false;
        if (x >= 0 && x < maze[0].length && y >= 0 && y < maze.length && maze[y][x] == 0) {
            if (x == gx && y == gy) {
                sol[y][x] = 1;
                return true;
            }
            maze[y][x] = 2;

            if (!res) {
                res = recu(maze, sol, x, y + 1, gx, gy);
            }
            if (!res) {
                res = recu(maze, sol, x, y - 1, gx, gy);
            }
            if (!res) {
                res = recu(maze, sol, x - 1, y, gx, gy);
            }
            if (!res) {
                res = recu(maze, sol, x + 1, y, gx, gy);
            }
            if (res) {
                sol[y][x] = 1;
            }
        }
        return res;


    }
}
