import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        System.out.println(numSubmatrixSumTarget(new int[][]{{0,1,0},{1,1,1},{0,1,0}},0));

    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {

        int count = 0;
        int line = matrix.length;
        int column = matrix[0].length + 1;
        int[][] sum = new int[line][column];

        for (int i = 0; i < sum.length; i++){
            for (int j = 1; j < sum[0].length; j++){
                sum[i][j] = sum[i][j - 1] + matrix[i][j - 1];
            }
        }


        for (int start = 0; start < column; start++){
            for (int end = start + 1; end < column; end++ ){

                int sumOfSubMatrix = 0;
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                map.put(0, 1);

                for(int l = 0; l < line; l++){

                    sumOfSubMatrix += sum[l][end] - sum[l][start];
                    if (map.containsKey(sumOfSubMatrix - target))
                        count += map.get(sumOfSubMatrix - target);
                    map.put(sumOfSubMatrix, map.getOrDefault(sumOfSubMatrix, 0) + 1);

                }

            }
        }

        return count;

    }

}

/*
for: [[0,1,0],[1,1,1],[0,1,0]] , 0
sum looks like:

[0,0,1,1]
[0,1,2,3]
[0,0,1,1]

note that sum size is one size bigger and first column is all zero's

some explanation:

for row 1, in matrix:
[0,1,0]
[1,1,1]
[0,1,0]

with sum matrix:
[0,0,1,1]
[0,1,2,3]
[0,0,1,1]

sum [l][end]   == 3 when l==2 and end==3
sum [l][start] == 1 when l==1 and end==3

therefore contributing sum of this row would be 2 which is simply 1+1 from original matrix means sun from 0 to end -  sum from 0 to start [start and end inclusive, since start ias added twice and subtracted once, it is include donly once in final result]

sum of all rows [all l's] for start==1 and end==3 is 4 [1-0 + 3-1 + 1-0] which simply sum of 1,0 1,1 1,0 [row 0 to 2 and col 1 to 2 from original matrix] from original matrix


solution from: https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/discuss/803353/Java-Solution-with-Detailed-Explanation

*/
