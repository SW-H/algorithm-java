package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/12945
public class 피보나치수 {
	public static void main(String[] args) {
		System.out.println(new Solution().solution(3));
		System.out.println(new Solution().solution(5));
	}

	static class Solution {
		private final int MAX = 100_000;
		private final int MOD = 123_456_7;
		int[] fibonaccis = new int[MAX + 1];

		public int solution(int n) {

			fibonaccis[0] = 0;
			fibonaccis[1] = 1;
			for (int i = 2; i <= n; i++) {
				fibonaccis[i] = (fibonaccis[i - 1] + fibonaccis[i - 2]) % MOD;
			}

			return fibonaccis[n];
		}
	}
}
