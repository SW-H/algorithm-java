package programmers;

import static java.lang.CharSequence.compare;

import java.util.ArrayList;
import java.util.List;

/*
 *
 * https://school.programmers.co.kr/learn/courses/30/lesson S/42746
 * */
public class 가장큰수 {
	class Solution {
		List<String> numbers = new ArrayList<>();

		public String solution(int[] _numbers) {
			StringBuilder answer = new StringBuilder();
			for (int num : _numbers) {
				numbers.add(String.valueOf(num));
			}

			numbers.sort((n1, n2) -> {
				String firstArgument = String.valueOf(n1);
				String secondArgument = String.valueOf(n2);

				return (secondArgument+firstArgument).compareTo((firstArgument + secondArgument));
			});
			for (String nStr : numbers) {
				answer.append(nStr);
			}
			return answer.toString().replaceAll("^0+", "0");
		}
	}
}
