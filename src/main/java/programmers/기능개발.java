package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/42586
public class 기능개발 {
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
			int[] answer = new int[progresses.length];
			int[] duration = new int[progresses.length];
			for (int i = 0; i < progresses.length; i++) {
				duration[i] = (int)Math.ceil((double)(100 - progresses[i]) / speeds[i]);
			}

			int idx = 0;

			for (int i = 0; i < progresses.length; i++) {
				if (duration[i] < 0) {
					continue;
				}
				answer[idx]++;
				for (int j = i + 1; j < progresses.length; j++) {
					if(duration[j] < 0 ){
						continue;
					}
					if (duration[j] <= duration[i]) {
						duration[j] = -1;
						answer[idx]++;
					}
					else{
						break;
					}
				}
				if (answer[idx] > 0) {
					idx++;
				}
				duration[i] = -1;
			}

			int[] ret = new int[idx];
			for (int i = 0; i < idx; i++) {
				ret[i] = answer[i];
			}

			return ret;
		}
	}
}

// 순서가 뒤면서 완성될 것
// = 순서가 뒤면서 소요 시간이 짧은 경우
// duration 작 -> 큰 정렬, 순서 작->큰  정렬, 단 순서를 비교
