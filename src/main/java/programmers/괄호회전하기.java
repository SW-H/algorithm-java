package programmers;

import java.util.Deque;
import java.util.LinkedList;

public class 괄호회전하기 {
	public static void main(String[] args) {
		String[] inputs = {"[](){}", "}]()[{", "[)(]", "}}}"};
		for (String input : inputs) {
			System.out.println(new Solution().solution(input));
		}
	}

	static class Solution {
		public int solution(String s) {
			int answer = 0;

			for (int i = 0; i < s.length(); i++) {
				String rotated = rotate(s, i);
				if (isValid(rotated)) {
					answer++;
				}
			}

			return answer;
		}

		private String rotate(String s, int amount) {
			// s를 왼쪽으로 x칸만큼 회전
			if (amount == 0) {
				return s;
			}

			String rotated = s.substring(amount).concat(s.substring(0, amount));

			return rotated;

		}

		private boolean isValid(String s) {
			Deque<Character> stack = new LinkedList<>();
			char[] pair = new char['[' + '(' + '{'];
			pair[']'] = '[';
			pair['}'] = '{';
			pair[')'] = '(';

			for (int i = 0; i < s.length(); i++) {
				if ((s.charAt(i) == '(') || (s.charAt(i) == '[') || (s.charAt(i) == '{')) {
					stack.addLast(s.charAt(i));
					continue;
				}

				if (stack.isEmpty()) {
					return false;
				}

				char parenthesis = stack.peekLast();

				if (parenthesis == pair[s.charAt(i)]) {
					stack.removeLast();
					continue;
				}
				return false;
			}

			if (stack.isEmpty()) {
				return true;
			}
			return false;

/* 오답
[반례]
input : "{(})"
expected: 0
real : 1

-> 열고-닫는 괄호의 종류도 중요! (개수만으로 판별 불가)


            int[] opened = new int['[' + '(' + '{'];
            int[] pair = new int['[' + '(' + '{'];
            pair[']'] = '[';
            pair['}'] = '{';
            pair[')'] = '(';

            for (int i = 0; i < s.length(); i++) {
                if ((s.charAt(i) == '(') || (s.charAt(i) == '[') || (s.charAt(i) == '{')) {
                    opened[s.charAt(i)]++;
                    continue;
                }

                if (opened[pair[s.charAt(i)]] <= 0) {
                    return false;
                }

                opened[pair[s.charAt(i)]]--;
            }

            if ((opened['('] == 0) && (opened['{'] == 0) && (opened['['] == 0)) {
                return true;
            }
            return false;*/
		}

	}
}
