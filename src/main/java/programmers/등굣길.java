package programmers;

import java.util.Arrays;

public class 등굣길 {
	class Solution {

		int MOD = 1_000_000_007;

		int dr[] = {0, -1}; //좌, 상
		int dc[] = {-1, 0}; //좌, 상

		boolean[][] isPuddle;
		int[][] mem;

		public int solution(int m, int n, int[][] puddles) {

			initialize(m, n, puddles);

			return answer(n - 1, m - 1);
		}

		public int answer(int r, int c) {
			// answer(r,c) = answer(r,c-1) + answer(r-1,c);
			if (((r < 0) || (c < 0)) || isPuddle[r][c]) {
				return 0;
			}
			if ((r == 0) && (c == 0)) {
				return 1;
			}
			if (mem[r][c] >= 0) {
				return mem[r][c];
			}

			int ret = 0;

			for (int d = 0; d < 2; d++) {
				ret = (ret + answer(r + dr[d], c + dc[d])) % MOD;
			}

			return (mem[r][c] = ret % MOD);
		}

		public void initialize(int m, int n, int[][] puddles) {
			isPuddle = new boolean[n][m];
			mem = new int[n][m];

			for (int[] row : mem) {
				Arrays.fill(row, -1);
			}

			for (boolean[] row : isPuddle) {
				Arrays.fill(row, false);
			}

			if ((puddles.length <= 0) || (puddles[0].length <= 0)) {
				return;
			}

			for (int i = 0; i < puddles.length; i++) {
				int[] puddle = puddles[i];
				isPuddle[puddle[1] - 1][puddle[0] - 1] = true;
			}

		}

	}

}
