import java.util.*;

public class CountSubMatrices {
    public static void main(String[] args) {
	int[][] matrix = new int[][]{{0,1,1},{1,0,1},{1,1,1}};
	System.out.println("Count the sub matrices for the following matrix: " + Arrays.toString(matrix));
	System.out.println("Result: " + new Solution().result(matrix));
    }

    static class Solution {
	public int result(int[][] matrix) {
	    /*
	      [ 0, 1, 1 ]     [ 0, 2, 1 ]
	      [ 1, 0, 1 ]  => [ 1, 0, 1 ]
	      [ 1, 1, 1 ]     [ 3, 2, 1 ]
	     */
	    for(int i = 0; i < matrix.length; i++)
		for(int j = matrix[0].length-2; -1 < j; j--)
		    if(matrix[i][j] != 0)
			matrix[i][j] = matrix[i][j+1]+1;

	    int result = 0;
	    for(int i = 0; i < matrix.length; i++) {
		for(int j = 0; j < matrix[0].length; j++) {
		    if(matrix[i][j] == 0) continue;
		    int min = matrix[i][j];

		    for(int z = i; z < matrix.length; z++) {
			if(matrix[z][j] < min) {
			    min = matrix[z][j];
			}
			  
			result += min;   
		    }
		}
	    }

	    return result;
	}
    }
}
