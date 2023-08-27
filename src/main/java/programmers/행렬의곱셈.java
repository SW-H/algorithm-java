package programmers;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/12949
 *
 * [ok]
 * */

public class 행렬의곱셈 {
	public static void main(String[] args) {
		int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
		int[][] arr2 = {{3, 3}, {3, 3}};

		new Solution().solution(arr1, arr2);
	}

	public static class Solution {
		int[][] arr1;
		int[][] arr2;

		public int[][] solution(int[][] _arr1, int[][] _arr2) {
			arr1 = _arr1;
			arr2 = _arr2;

			int nr = arr1.length;
			int nc = arr2[0].length;
			int[][] answer = new int[nr][nc];
			for (int r = 0; r < nr; r++) {
				for (int c = 0; c < nc; c++) {
					answer[r][c] = calculate(r, c);
				}
			}

			return answer;
		}

		public int calculate(int r, int c) {
			int res = 0;
			int len = arr1[0].length;

			for (int l = 0; l < len; l++) {
				res += arr1[r][l] * arr2[l][c];
			}
			return res;
		}
	}
}
