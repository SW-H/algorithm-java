package codetree;
//https://www.codetree.ai/problems/marble-movement/description

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class 구슬의이동 {
	public static void main(String[] args) throws IOException {
		new Solver().solve();
	}

	static class Solver {
		BufferedReader br;
		StringTokenizer st;

		int n, marbleCnt, t, maxMarble;
		Map<String, Integer> directionToInt = new HashMap<>();

		// URDL
		int[] dr = {-1, 0, 1, 0};
		int[] dc = {0, 1, 0, -1};

		Deque<Marble>[][] board = new Deque[51][51];

		class Marble implements Comparable<Marble> {
			int d;
			int v;
			int num;

			Marble(int num, int d, int v) {
				this.num = num;
				this.d = d;
				this.v = v;
			}

			@Override
			public int compareTo(Marble m) { // 내림차순 정렬 **정렬 방향 유의**
				if (this.v != m.v) {
					return m.v - this.v;
				}
				return m.num - this.num;
			}
		}

		public void solve() throws IOException {
			br = new BufferedReader(new InputStreamReader(System.in));
			st = new StringTokenizer(br.readLine());

			directionToInt.put("U", 0);
			directionToInt.put("R", 1);
			directionToInt.put("D", 2);
			directionToInt.put("L", 3);

			/*
				cf. char -> int

				int[] dirMapper = new int[ASCII_NUM];
				dirMapper['U'] = 0;
				dirMapper['R'] = 1;
				dirMapper['L'] = 2;
				dirMapper['D'] = 3;

			*/

			new UserManager().input();
			continueTime();

			int result = 0;
			for (int r = 0; r < n; r++) {
				for (int c = 0; c < n; c++) {
					result += board[r][c].size();
				}
			}

			System.out.print(result);
		}

		public void continueTime() {
			Deque<Marble>[][] nextBoard;
			for (int sec = 0; sec < t; sec++) {
				nextBoard = new Deque[51][51];

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						nextBoard[r][c] = new LinkedList<>();
					}
				}

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						if ((board[r][c] != null) && (board[r][c].isEmpty() == false)) {
							for (Iterator<Marble> marbleIt = board[r][c].iterator(); marbleIt.hasNext(); ) {
								Marble marble = marbleIt.next();
								move(r, c, marble, nextBoard);
							}
						}
					}
				}

				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						while (nextBoard[r][c].size() > maxMarble) {
							removeMarble(nextBoard, r, c);
						}
					}
				}
				board = nextBoard.clone();
			}
		}

		void move(int curR, int curC, Marble marble, Deque<Marble>[][] nextBoard) {
			for (int i = 1; i <= Math.abs(marble.v); i++) {
				int nr = curR + dr[marble.d];
				int nc = curC + dc[marble.d];

				if (!isInRange(nr, nc)) {
					marble.d = (marble.d + 2) % 4;
					nr = curR + dr[marble.d];
					nc = curC + dc[marble.d];
				}
				curR = nr;
				curC = nc;
			}

			nextBoard[curR][curC].addLast(marble);

		}

		private boolean isInRange(int r, int c) {
			return ((0 <= r) && (r < n) && (0 <= c) && (c < n));
		}

		private void removeMarble(Deque<Marble>[][] board, int r, int c) {
			// 우선순위 가장낮은 마블 제거

			int toRemove = board[r][c].size() - maxMarble;

			board[r][c] = board[r][c].stream()
				.sorted()
				.collect(Collectors.toCollection(LinkedList::new));

			for (int i = 0; i < toRemove; i++) {
				board[r][c].removeLast();
			}
		}

		class UserManager {
			void input() throws IOException {
				n = Integer.parseInt(st.nextToken());
				marbleCnt = Integer.parseInt(st.nextToken());
				t = Integer.parseInt(st.nextToken());
				maxMarble = Integer.parseInt(st.nextToken());

				for (int i = 0; i < marbleCnt; i++) { // input marble info
					st = new StringTokenizer(br.readLine());

					int r = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());
					String d = st.nextToken();
					int v = Integer.parseInt(st.nextToken());

					board[r - 1][c - 1] = new LinkedList<>();
					board[r - 1][c - 1].addLast(
						new Marble(i + 1, directionToInt.get(d), v)
					);
				}
			}
		}
	}
}
