package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/43105
public class 정수삼각형 {
	public static void main(String[] args) {
		System.out.println(
			new Solution().solution(new int[][] {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}})
		);
	}

	static class Solution {
		int sum[][];

		public int solution(int[][] triangle) {
			int answer = 0;
			int height = triangle.length;
			sum = new int[height][height];
			sum[0][0] = triangle[0][0];

			for (int i = 0; i < height - 1; i++) {
				for (int j = 0; j < i + 1; j++) {
					// index : (i,j)
					// adjacent : (i+1, j), (i+1, j+1)
					sum[i + 1][j] = Math.max(sum[i][j] + triangle[i + 1][j], sum[i + 1][j]);
					sum[i + 1][j + 1] = Math.max(sum[i][j] + triangle[i + 1][j + 1], sum[i + 1][j + 1]);
				}
			}

			for (int i = 0; i < height; i++) {
				answer = Math.max(sum[height - 1][i], answer);
			}

			return answer;
		}
	}
}

// [[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]
