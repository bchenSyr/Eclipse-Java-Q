/*
 * == Created Date ==
 * Oct 9, 2018
 * 
 * == Question - Word Searchr ==
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 *    where "adjacent" cells are those horizontally or vertically neighboring. 
 * The same letter cell may not be used more than once.
 *  
 *   
 * == Example == 
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * 
 * Given word = "ABCCED", return true
 * Given word = "SEE", return true
 * Given word = "ABCB", return false.
 * 
 * == Notes == 
 * LeetCode 79(M)
 * 
 */

package dfsRelated;

public class WordSearch {
	
	/* ------------------------ < Wrong Approach, should use DFS to solve this problem !!! >--------------------------- */
    public boolean existWrong(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (explore(board, row, col, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean explore(char[][] board, int row, int col, String word) {
                
        if (board[row][col] != word.charAt(0)) {
            return false;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < word.length() - 1; i++) {
            visited[row][col] = true;
            if (row + 1 < board.length && !visited[row + 1][col] && board[row + 1][col] == word.charAt(i + 1)) {
                row++;
                continue;
            } else if (row - 1 >= 0 && !visited[row - 1][col] && board[row - 1][col] == word.charAt(i + 1)) {
                row--;
                continue;
            } else if (col - 1 >= 0 && !visited[row][col - 1] && board[row][col - 1] == word.charAt(i + 1)) {
                col--;
                continue;
            } else if (col + 1 < board[0].length && !visited[row][col + 1] && board[row][col + 1] == word.charAt(i + 1)) {
                col++;
                continue;
            }
            return false;
        }
        return true;
    }
    
   
    /* ------------------------ < Solution 1 - DFS with boolean[][] visited >--------------------------- 
     * TC: O(4 ^ N * L) 
     * SC: O(M) 
     * 
     * N: number of elements in the board
     * L: number of characters of the given word 
     *  
     */
    public boolean existMeth1(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                if (dfs(board, row, col, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        
    		// check if the current position is valid in board
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }
        
        // check if current element of board has been visited before
        if (visited[row][col]) {
            return false;
        }
        
        // check if current element of board matched the corresponding character of the given word
        if (board[row][col] != word.charAt(index)) {
            return false;           
        } 
        
        // check if this the last character of the given word
        if (index + 1 == word.length()) {
            return true;
        }
        
        // mark this element as visited
        visited[row][col] = true;
          
        // recursively DFS the four direction of the current element
        boolean result =	 dfs(board, row + 1, col, word, index + 1, visited) || 
        					 dfs(board, row - 1, col, word, index + 1, visited) ||
        					 dfs(board, row, col + 1, word, index + 1, visited) || 
        					 dfs(board, row, col - 1, word, index + 1, visited);
        
        // extremely important!!! restore the visited state for backtracking
        visited[row][col] = false;
        
        return result;
    }
    
    
    /* ------------------------ < Solution 2 - DFS without boolean[][] visited >---------------------------
     * TC: O(4 ^ N * M) 
     * SC: O(1)
     * 
     * M: number of elements in the board
     * N: number of characters of the given word 
     *  
     */
    public boolean existMeth2(char[][] board, String word) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (dfs(board, row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int row, int col, String word, int index) {
            
    		// if the index is out of bound
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return false;
        }

        // if the word is already visited(#) or cannot match the char
        if (board[row][col] != word.charAt(index)) {
            return false;           
        } 
        
       
        if (index + 1 == word.length()) { // we found a path
            return true;
        }
        
        char temp = board[row][col];
        board[row][col] = '#'; // mark as visted before
        
        boolean existed = dfs(board, row + 1, col, word, index + 1) || 
                          dfs(board, row - 1, col, word, index + 1) ||
                          dfs(board, row, col + 1, word, index + 1) || 
                          dfs(board, row, col - 1, word, index + 1);
        
        board[row][col] = temp; // restore the element
        
        return existed;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		WordSearch testObj = new WordSearch();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		char[][] board1 = new char[][]{
			                             new char[] {'a', 'b'}, 
			                             new char[] {'c', 'd'}
			                           };
			                           
		System.out.println(testObj.existWrong(board1, "acdb")); // expected: true 
		
		System.out.println(testObj.existMeth1(board1, "acdb")); // expected: true 
		System.out.println(testObj.existMeth2(board1, "acdb")); // expected: true 
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		char[][] board2 = new char[][]{ 
										new char[] {'C', 'A', 'A'}, 
									    new char[] {'A', 'A', 'A'},
									    new char[] {'B', 'C', 'D'}
									  };
									   
		System.out.println(testObj.existWrong(board2, "AAB")); // expected: true, BUT 'false' !!!
		
		System.out.println(testObj.existMeth1(board2, "AAB")); // expected: true 
		System.out.println(testObj.existMeth2(board2, "AAB")); // expected: true 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		char[][] board3 = new char[][]{ 
									    new char[] {'A', 'B', 'C', 'E'}, 
			   						    new char[] {'S', 'F', 'E', 'S'},
			   						    new char[] {'A', 'D', 'E', 'E'},
									  };
									  
		System.out.println(testObj.existMeth1(board3, "ABCESEEEFS")); // expected: true 
		System.out.println(testObj.existMeth2(board3, "ABCESEEEFS")); // expected: true 
		
	}

}
