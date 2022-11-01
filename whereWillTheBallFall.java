/*   LEETCODE 1706 : WHERE WILL THE BALL FALL
 * You have a 2-D grid of size m x n representing a box, and you have n balls. The box is open on the top and bottom sides.
Each cell in the box has a diagonal board spanning two corners of the cell that can redirect a ball to the right or to the left.

A board that redirects the ball to the right spans the top-left corner to the bottom-right corner and is represented in the grid as 1.
A board that redirects the ball to the left spans the top-right corner to the bottom-left corner and is represented in the grid as -1.
We drop one ball at the top of each column of the box. Each ball can get stuck in the box or fall out of the bottom. A ball gets stuck if
 it hits a "V" shaped pattern between two boards or if a board redirects the ball into either wall of the box.

Return an array answer of size n where answer[i] is the column that the ball falls out of at the bottom after dropping the ball from the ith 
column at the top, or -1 if the ball gets stuck in the box.

example 1:
Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
Output: [1,-1,-1,-1,-1]
Explanation: This example is shown in the photo.
Ball b0 is dropped at column 0 and falls out of the box at column 1.
Ball b1 is dropped at column 1 and will get stuck in the box between column 2 and 3 and row 1.
Ball b2 is dropped at column 2 and will get stuck on the box between column 2 and 3 and row 0.
Ball b3 is dropped at column 3 and will get stuck on the box between column 2 and 3 and row 0.
Ball b4 is dropped at column 4 and will get stuck on the box between column 2 and 3 and row 1.
 */

 class whereWillTheBallFall{
    public static void main(String[] args) {
        int[][] grid = {
            {1,1,1,-1,-1},
            {1,1,1,-1,-1},
            {-1,-1,-1,1,1},
            {1,1,1,1,-1},
            {-1,-1,-1,-1,-1}
        };
        
        int m = grid[0].length;
        int[] ans = new int[m];
        for(int j = 0; j < m ; j++){
            ans[j] = dfs(grid , 0 , j);
        }
        
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }

    /*
 Just following each ball of each column (for loop in main class) and path via recursion(solve function) .
Idea :-
if value of current is 1 =>then the ball tries to move right. here 2 possibilities
(i) if right val is -1, then it forms V shape(no further move, it will stuck) return -1
(ii) if right val is 1, then it can move further, now check recursively .
if curr val is -1 =>then the ball tries to move left. here 2 possibilities
(i) if left val is 1, then it forms V shape(no further move, it will stuck) return -1
(ii) if left val is -1, then it can move further, now check recursively .
dry run the first test case for better understanding.
*/
    public static int dfs(int[][] grid , int row , int col){
        if(row == grid.length){
            return col;
        }
        if(col < 0 || col > grid[0].length) return -1;
        
        if(grid[row][col] == 1 && col+1 < grid[0].length && grid[row][col + 1] == 1){
            return dfs(grid , row + 1 , col + 1);
        }
        if(grid[row][col] == -1 && col-1 >= 0 && grid[row][col-1] == -1){
            return dfs(grid , row + 1 , col - 1);
        }
        return -1;
    }
 }