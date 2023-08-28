package programmers;
/*
* https://school.programmers.co.kr/learn/courses/30/lessons/68936
*
* */

import java.util.*;

public class 쿼드압축후개수세기 {

	public static void main(String[] args) {
		int[][] arr = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
		int[] res = new Solution().solution(arr);
		System.out.println(res[0] + " " + res[1]);

	}

	public static class Solution {
		List<Integer> compressed = new ArrayList<>();

		public int[] solution(int[][] arr) {
			int zeros = 0;
			int ones = 0;

			compress(arr, 0, 0, arr.length);
			for (Integer element : compressed) {
				if (element == 0) {
					zeros++;
				} else {
					ones++;
				}
			}
			return new int[] {zeros, ones};
		}

		private void compress(int[][] arr, int startR, int startC, int width) {
			for (int r = 0; r < width; r++) {
				for (int c = 0; c < width; c++) {
					if (arr[startR][startC] != arr[startR + r][startC + c]) {
						compress(arr, startR, startC, width / 2);
						compress(arr, startR, startC + width / 2, width / 2);
						compress(arr, startR + width / 2, startC, width / 2);
						compress(arr, startR + width / 2, startC + width / 2, width / 2);
						return;
					}
				}
			}
			compressed.add(arr[startR][startC]);
		}
	}
}
