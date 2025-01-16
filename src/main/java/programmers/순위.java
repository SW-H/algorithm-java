package programmers;

public class 순위 {
	public static void main(String[] args) {
		int n = 5;
		int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
		int ret = new Solution().solution(n, results);

		System.out.println(ret);
	}

	static class Solution {
		boolean[][] winOrNot; //[winner][opponent]
		int n;

		public int solution(int _n, int[][] results) {
			int answer = 0;
			n = _n;
			winOrNot = new boolean[n][n];

			for (int[] play : results) {
				int winner = play[0] - 1;
				int loser = play[1] - 1;

				winOrNot[winner][loser] = true;
			}

			for (int i = 0; i < n; i++) {
				if ((getWinCount(i, new boolean[n]) - 1 + getLoseCount(i, new boolean[n]) - 1) == (n - 1)) {
					answer++;
				}
			}

			return answer;
		}

		int getWinCount(int target, boolean[] isVisited) {
			int count = 1;

			for (int i = 0; i < n; i++) {
				if ((isVisited[i] == false) && (winOrNot[target][i])) {
					isVisited[i] = true;
					count += getWinCount(i, isVisited);
				}
			}
			return count;
		}

		int getLoseCount(int target, boolean[] isVisited) {
			int count = 1;

			for (int i = 0; i < n; i++) {
				if ((isVisited[i] == false) && (winOrNot[i][target])) {
					isVisited[i] = true;
					count += getLoseCount(i, isVisited);
				}
			}
			return count;
		}
	}
}
