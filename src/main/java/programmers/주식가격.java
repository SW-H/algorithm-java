package programmers;
/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42584
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class 주식가격 {
	public static void main(String[] args) {
		int[] price = {1, 2, 3, 2, 3};
		int[] res = new 주식가격.Solution().solution(price);
		for (int i : res) {
			System.out.println(res[i]);
		}

	}

	public static class Solution {
		public int[] solution(int[] prices) {
			int[] answer = new int[prices.length];
			Deque<Integer> stack = new ArrayDeque<>();

			for (int i = 0; i < prices.length; i++) {
				while ((!stack.isEmpty()) && (prices[i] < prices[stack.peekLast()])) {
					int idx = stack.peekLast();
					stack.removeLast();
					answer[idx] = i - idx;
				}
				stack.addLast(i);
			}
			while (!stack.isEmpty()) {
				int idx = stack.peekLast();
				stack.removeLast();

				answer[idx] = prices.length - 1 - idx;
			}

			return answer;
		}
	}
}


/*
*  1 2 3 2 3

값(금액) 1  2    3     4  5
인덱스 : 0  1/3  2/4
  개수  1   2     2    0  0
개수누적  1   3    5   5   5
        4   2    0   0   0
i : cum[max] - cum[i+1] + count[i]
[i]     0 1 2 3 4
[price] 1 2 3 2 3
[cum]   1 3 6 8 11
*
*
*
* *
* *
*
* */
