// https://school.programmers.co.kr/learn/courses/30/lessons/1843
package programmers;

import java.util.Arrays;

public class 사칙연산 {

	public static void main(String[] args) {

		int answer = new 사칙연산.Solution().solution(new String[] {"1", "-", "3", "+", "5", "-", "8"}); // length : 7
		// int answer = new 사칙연산.Solution().solution(
		// 	new String[] {"5", "-", "3", "+", "1", "+", "2", "-", "4"}); // length : 7

		System.out.println(answer);
	}

	static class Solution {
		public static int[][] maxVal;
		public static int[][] minVal;

		public int solution(String[] arr) {
			maxVal = new int[arr.length + 1][arr.length + 1];
			minVal = new int[arr.length + 1][arr.length + 1];

			for (int i = 0; i < arr.length + 1; i++) {
				// 2차원 배열을 인자로 한번에 초기화 불가능 유의!!
				// ex) Arrays.fill(maxVal, Integer.MIN_VALUE);

				Arrays.fill(maxVal[i], Integer.MIN_VALUE);
				Arrays.fill(minVal[i], Integer.MAX_VALUE);
			}

			return getMax(0, arr.length, arr);
		}

		public int getMax(int start, int end, String[] arr) {
			int max = Integer.MIN_VALUE;

			if (end - start == 1) {
				return Integer.parseInt(arr[start]); // 2 -
			}

			if (maxVal[start][end] > Integer.MIN_VALUE) {
				return maxVal[start][end];
			}

			for (int i = start + 1; i < end; i += 2) {
				if (arr[i].equals("+")) {
					max = Integer.max(max, getMax(start, i, arr) + getMax(i + 1, end, arr));
				} else { // "-"
					max = Integer.max(max, getMax(start, i, arr) - getMin(i + 1, end, arr));
				}
			}

			maxVal[start][end] = max;

			return max;
		}

		public int getMin(int start, int end, String[] arr) {
			int min = Integer.MAX_VALUE;

			if (end - start == 1) {
				return Integer.parseInt(arr[start]); // 2 -
			}

			if (minVal[start][end] < Integer.MAX_VALUE) {
				return minVal[start][end];
			}

			for (int i = start + 1; i < end; i += 2) {
				if (arr[i].equals("+")) {
					min = Integer.min(min, getMin(start, i, arr) + getMin(i + 1, end, arr));
				} else { // "-"
					min = Integer.min(min, getMin(start, i, arr) - getMax(i + 1, end, arr));
				}
			}

			minVal[start][end] = min;

			return min;
		}
	}
}

/*

1 - 5 - 3

계산값 최대
+ : 최대 + 최대
- : 최대 - 최소

(1-5)-3
1-(5-3)


idx	0 1 2 3 4 5 6 7 8
num	5 - 3 + 1 + 2 - 4


1 - 3 + 5 - 8





 */
