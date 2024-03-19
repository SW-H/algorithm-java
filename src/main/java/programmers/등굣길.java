package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class 등굣길 {

	class Solution {

		int[] dr = {0, 1}; //우,하
		int[] dc = {1, 0}; //우,하
		int RPOS = 0;
		int CPOS = 0;

		public int solution(int m, int n, int[][] puddles) {
			// +) 최소길이 파악?
			return dfs(m, n, puddles);
		}

		// width = m
		// height = n

		public int dfs(int m, int n, int[][] puddles) {
			Queue<int[]> q = new LinkedList();
			q.offer(new int[] {0, 0});

			// 최종 : (n-1,m-1)

			while (!q.isEmpty()) {
				int[] cur = q.poll();

				int r = cur[RPOS];
				int c = cur[CPOS];

				for (int d = 0; d < dr.length; d++) { // 우,하 인접
					if ((0 > r + dr[d]) || (r + dr[d] >= n) || (0 > c + dc[d]) || (c + dc[d] >= m)) { // out of bound
						continue;
					}

				}

			}

			return 0;
		}
	}

}
