package programmers;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/67257
 * */
public class 수식최대화 {
	public static void main(String[] args) {
		String expression = "50*6-3*2";
		System.out.println(new Solution().solution(expression));
	}

	static class Solution {
		public long solution(String expression) {
			long answer = 0;
			String[][] priority = {{"+", "-", "*"}, {"+", "*", "-"}, {"-", "+", "*"}, {"-", "*", "+"}, {"*", "-", "+"},
					{"*", "+", "-"}};

			for (int i = 0; i < priority.length; i++) {

				long calculationResult = Math.abs(getCandidateRes(expression, priority[i]));
				answer = Math.max(answer, calculationResult);
			}

			return answer;
		}

		// 후위연산자로 변환하는 대신 앞에서부터 우선순위에 해당하는 연산자를 찾아 계산하는 방식으로 구현하였다.
		long getCandidateRes(String expression, String[] priorities) {
			List<String> splited = split(expression);

			while (splited.size() > 1) {
				for (String operator : priorities) {
					for (int i = 0; i < splited.size(); i++) {
						if (operator.equals(splited.get(i))) {
							splited.set(i - 1,
									calculate(operator, splited.get(i - 1), splited.get(i + 1)));
							splited.remove(i);
							splited.remove(i);
							i -= 2; // 반복문 내에서 list에 대한 remove를 하는 경우 index를 제거한 요소 개수만큼 앞당겨 오는 것을 빠뜨리지 않도록 주의가 필요
						}
					}
				}
			}
			return Long.parseLong(splited.get(0));
		}

		private String calculate(String operator, String leftOperand, String rightOperand) {
			switch (operator) {
				case "+":
					return String.valueOf(Long.parseLong(leftOperand) + Long.parseLong(rightOperand));
				case "-":
					return String.valueOf(Long.parseLong(leftOperand) - Long.parseLong(rightOperand));
				case "*":
					return String.valueOf(Long.parseLong(leftOperand) * Long.parseLong(rightOperand));
				default:
					return "0";
			}
		}

		// Stringtokenizer를 이용해 파싱해도 효율적으로 가능할 것 같다

		private List<String> split(String expression) {
			List<String> splited = new ArrayList<>();
			StringBuilder num = new StringBuilder();
			for (int i = 0; i < expression.length(); i++) {
				if (Character.isDigit(expression.charAt(i))) {
					num.append(expression.charAt(i));
				} else {
					splited.add(num.toString());
					splited.add(String.valueOf(expression.charAt(i)));
					num = new StringBuilder();
				}
			}
			splited.add(num.toString());
			return splited;
		}
	}

}
