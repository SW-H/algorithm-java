package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/12909

class 올바른괄호 {
	public static void main(String[] args) {
		System.out.print(new Solution().solution("(())()"));
	}

	static class Solution {
		boolean solution(String s) {
			int opened = 0;
			for (int i = 0; i < s.length(); i++) {
				if (s.charAt(i) == '(') {
					opened++;
					continue;
				}
				if (opened <= 0) {
					return false;
				}
				opened--;

			}

			if (opened == 0) {
				return true;
			}
			return false;
		}
	}
}
