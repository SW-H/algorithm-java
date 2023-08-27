package programmers;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/81302
 * */

public class 거리두기확인하기 {
	class Solution {
		public final int N = 5;
		//상하좌우
		int[] dr = {-1, 1, 0, 0};
		int[] dc = {0, 0, -1, 1};

		//대각선 : 왼쪽위부터 시계방향
		int[] diagDr = {-1, -1, 1, 1};
		int[] diagDc = {-1, 1, 1, -1};

		public int[] solution(String[][] places) {
			int[] answer = new int[N];

			for (int waitingRoom = 0; waitingRoom < N; waitingRoom++) {
				answer[waitingRoom] = checkDistancing(places[waitingRoom]);
			}

			return answer;
		}

		public int checkDistancing(String[] place) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if ((place[r].charAt(c) == 'P') && !checkManhattenDistance(place, r, c)) {
						return 0;
					}
				}
			}
			return 1;
		}

		boolean checkManhattenDistance(String[] place, int r, int c) {
			// 1-1. 바로 인접에 사람 있음(상하좌우) : false
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (isInRange(nr, nc) && place[nr].charAt(nc) == 'P') {
					return false;
				}
			}

			// 1-2. 대각선에 사람 있음 : 파티션이 그 반대 대각선에 있으면 true
			for (int d = 0; d < 4; d++) {
				int nr = r + diagDr[d];
				int nc = c + diagDc[d];
				if (isInRange(nr, nc) &&
						place[nr].charAt(nc) == 'P') {
					if (place[nr].charAt(c) == 'X' && place[r].charAt(nc) == 'X') {
						//파티션 체크
						return true;
					}
					return false;
				}
			}

			//2. 한칸 띄고 있음 -> 그 사이에 파티션 : true
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d] * 2;
				int nc = c + dc[d] * 2;
				if (isInRange(nr, nc)) {
					if (place[nr].charAt(nc) == 'P' && place[r + dr[d]].charAt(c + dc[d]) != 'X') {
						return false;
					}
				}
			}
			return true;
		}

		boolean isInRange(int r, int c) {
			return (0 <= r && r < N && 0 <= c && c < N);
		}
	}
}
