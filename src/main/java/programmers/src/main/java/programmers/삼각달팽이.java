package programmers;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/68645
 *
 * */

public class 삼각달팽이 {
	public static void main(String[] args) {
		int n = 6;
		int[] res = new 삼각달팽이.Solution().solution(n);
		for (int i = 0; i < res.length; i++) {
			System.out.print(res[i] + " ");
		}
	}

	static class Solution {

		public int[] solution(int n) {
			int range = (n * (n + 1)) / 2;
			int[] ret = new int[range];
			int[][] snailMap = new int[n][n];

			// 아래, 오른쪽, 왼쪽위
			int[] dr = {1, 0, -1};
			int[] dc = {0, 1, -1};

			int posR = -1;
			int posC = 0;
			int num = 1;
			for (int direction = 0; num <= range; direction = (direction + 1) % 3) {
				posR += dr[direction];
				posC += dc[direction];
				while (true) {
					snailMap[posR][posC] = num++;
					int nr = posR + dr[direction];
					int nc = posC + dc[direction];
					if (nr >= n || nc >= n || (snailMap[nr][nc] > 0)) {
						break;
					}
					posR = nr;
					posC = nc;
				}
			}
			int index = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (snailMap[r][c] > 0) {
						ret[index++] = snailMap[r][c];
					}
				}
			}
			return ret;
		}
	}

	/*
	방법 1 : 몇칸씩 이동해야하는지 그 칸수를 계산해서 for문 이용하려 했는데
	그보단 각 방향으로 이동할 수 없을 때까지 이동하는 방법이 구현하기 쉬움


	public int[] solution(int n) {

		int range = (n * (n + 1)) / 2;
		int[] answer = new int [range];
		snailMap = new int[n][n];
		int filler = 1;

		// 0,0 -> 2,1 -> 4,2
		int startR = 0;
		int startC = 0;

		while (filler <= range) {
			for (int r = startR; r < n && filler <= range; r++) {
				snailMap[r][startC] = filler++;
			}

			for (int c = startC + 1; c < n && filler <= range; c++) {
				snailMap[n - 1][c] = filler++;
			}

			for (int r = n - startR - 2; r > startR && filler <= range; r--) {
				snailMap[r][r] = filler++;
			}
			startR += 2;
			startC++;
		}

		int index=0;
		for(int r=0;r<n;r++){
			for(int c=0;c<n;c++){
				if(snailMap[r][c] > 0){
					answer[index++] = snailMap[r][c];
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(snailMap[i][j] + " ");
			}
			System.out.println();
		}

		return answer;
	}*/
}

