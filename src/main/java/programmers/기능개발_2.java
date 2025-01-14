package programmers;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// https://school.programmers.co.kr/learn/courses/30/lessons/42586
public class 기능개발_2 {
	public static void main(String[] args) {
		int[] progresses = {93, 30, 55};
		int[] speeds = {1, 30, 5};

		int[] ret = new Solution().solution(progresses, speeds);

		for (int i = 0; i < ret.length; i++) {
			System.out.println(ret[i]);
		}
	}

	static class Solution {
		public int[] solution(int[] progresses, int[] speeds) {
			List<Integer> answer = new ArrayList<>();

			Deque<Integer> queue = new ArrayDeque<>();

			for (int i = 0; i < progresses.length; i++) {
				int duration = (int)Math.ceil((double)(100 - progresses[i]) / speeds[i]);

				queue.push(duration);
			}

			while (!queue.isEmpty()) {
				int target = queue.getFirst();
				queue.removeFirst();
				int count = 1;
				while (!queue.isEmpty()) {
					if (target < queue.getFirst()) {
						break;
					}

					count++;
					queue.removeFirst();
				}

				answer.add(count);
			}

			return answer.stream().mapToInt(Integer::intValue).toArray();
		}
	}
}
