package codetree;

//https://www.codetree.ai/missions/2/problems/merge-marbles/description

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합쳐지는구슬들 {
	public static void main(String[] args) throws IOException {
		new Solver().solve();
	}

	static class Solver {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		Marble[][] board = new Marble[51][51];

		int dr[] = {-1, 0, 1, 0};
		int dc[] = {0, 1, 0, -1};

		int marbleCnt, n, t;

		class Marble {
			int num;
			int d;
			int w;
			boolean isRemoved = false;

			Marble(int num, int d, int w) {
				this.num = num;
				this.d = d;
				this.w = w;
			}
		}

		public void solve() throws IOException {
			input();
			for (int sec = 0; sec < t; sec++) {
				Marble[][] nextBoard = new Marble[n][n];

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						if (board[r][c] != null) {
							int nr = r + dr[board[r][c].d];
							int nc = c + dc[board[r][c].d];

							if (!isInRange(nr, nc)) {
								board[r][c].d = (board[r][c].d + 2) % 4;
								nr = r;
								nc = c;
							}

							if (nextBoard[nr][nc] == null) {
								nextBoard[nr][nc] = board[r][c];
								continue;
							}

							Marble newMarble = collide(nextBoard[nr][nc], board[r][c]);
							nextBoard[nr][nc] = newMarble;
						}
					}
				}
				board = nextBoard;
			}

			int remainedCnt = 0;
			int heaviestW = Integer.MIN_VALUE;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					if (board[r][c] != null) {
						remainedCnt++;
						heaviestW = Math.max(board[r][c].w, heaviestW);
					}

				}
			}

			System.out.println(remainedCnt + " " + heaviestW);

		}

		private Marble collide(Marble prevMarble, Marble marble) {
			int w = marble.w + prevMarble.w;

			if (marble.num > prevMarble.num) {
				return new Marble(marble.num, marble.d, w);
			}

			return new Marble(prevMarble.num, prevMarble.d, w);
		}

		private boolean isInRange(int r, int c) {
			return ((0 <= r) && (r < n) && (0 <= c) && (c < n));
		}

		public void input() throws IOException {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			n = Integer.parseInt(stringTokenizer.nextToken());
			marbleCnt = Integer.parseInt(stringTokenizer.nextToken());
			t = Integer.parseInt(stringTokenizer.nextToken());

			for (int i = 1; i <= marbleCnt; i++) {
				stringTokenizer = new StringTokenizer(bufferedReader.readLine());

				int r = Integer.parseInt(stringTokenizer.nextToken());
				int c = Integer.parseInt(stringTokenizer.nextToken());
				int d = dirToInt(stringTokenizer.nextToken());
				int w = Integer.parseInt(stringTokenizer.nextToken());

				Marble marble = new Marble(i, d, w);
				board[r - 1][c - 1] = marble; // index가 1부터인지 0부터인지 구분!!!!!!
			}

		}

		private int dirToInt(String dir) {
			int[] directions = new int['Z' + 1];

			directions['U'] = 0;
			directions['R'] = 1;
			directions['D'] = 2;
			directions['L'] = 3;

			return directions[dir.charAt(0)];
		}
	}
}
