import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class MazeSolverImplTest {

    private int[][] smallWriteupMaze;
    private int[][] bigWriteupMaze;
    private int[][] middleWriteupMaze;
    private int[][] blockedMaze;
    private int[][] openPath;

    @Before
    public void setupTestMazes() {
        smallWriteupMaze = new int[][]{
                {0, 0, 0, 0},
                {1, 1, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        bigWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 1, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 1, 0, 0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        middleWriteupMaze = new int[][]{
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 1, 0, 0},
                {1, 0, 0, 0, 1}
        };

        blockedMaze = new int[][]{
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0}
        };

        openPath = new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        };

    }

    @Test
    public void testReturnsSmallMazeSolutionPathInWriteup() {
        int[][] solutionPath = {
                {1, 1, 1, 0},
                {0, 0, 1, 0},
                {1, 1, 1, 0},
                {1, 1, 0, 0}
        };
        assertArrayEquals(solutionPath, MazeSolverImpl.solveMaze(smallWriteupMaze,
                new Coordinate(0, 0), new Coordinate(0, 2)));
    }

    @Test
    public void testReturnsBigMazeSolutionPathInWriteup() {
        int[][] bigWriteupSolution = new int[][]{
                {1, 1, 1, 0, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        int[][] returnedPath = MazeSolverImpl.solveMaze(bigWriteupMaze, new Coordinate(2, 0),
                new Coordinate(4, 0));
        assertArrayEquals(bigWriteupSolution, returnedPath);
    }

    /*
      Note: the above tests are the ones included in the writeup and NOT exhaustive. The autograder
      uses other test cases not listed above. Please thoroughly read all stub files, including
      CoordinateTest.java!

      For help with creating test cases, please see this link:
      https://www.seas.upenn.edu/~cis121/current/testing_guide.html
     */

    @Test
    public void testBlockedPathThrows() {
        assertArrayEquals(null, MazeSolverImpl.solveMaze(blockedMaze,
                new Coordinate(0, 0), new Coordinate(3, 0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartOOB() {
        MazeSolverImpl.solveMaze(middleWriteupMaze,
                new Coordinate(-1, 0), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndOOB() {
        MazeSolverImpl.solveMaze(middleWriteupMaze,
                new Coordinate(0, 0), new Coordinate(100, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartWithBlocked() {
        MazeSolverImpl.solveMaze(middleWriteupMaze,
                new Coordinate(0, 1), new Coordinate(3, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEndWithBlocked() {
        MazeSolverImpl.solveMaze(middleWriteupMaze,
                new Coordinate(0, 0), new Coordinate(3, 1));
    }

    @Test
    public void testOpenPath() {
        int[][] solutionMiddlePath = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        assertArrayEquals(solutionMiddlePath, MazeSolverImpl.solveMaze(openPath,
                new Coordinate(0, 0), new Coordinate(4, 4)));
    }

}
