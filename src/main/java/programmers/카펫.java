package programmers;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/42842
 *
 * */
public class 카펫 {

	class Solution {
		public int[] solution(int brown, int yellow) {
			int[] answer = new int[2];

			int width, height;
			for (height = 1; height <= yellow; height++) {
				for (width = 1; width <= yellow; width++) {
					if ((height * width == yellow) && ((height + 2) * (width + 2) == (brown + yellow))) {
						return new int[] {width + 2, height + 2};
					}
				}
			}

			return answer;
		}
	}
}
