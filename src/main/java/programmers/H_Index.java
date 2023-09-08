package programmers;

import java.util.Arrays;

/*
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/42747
 * */
public class H_Index {
	class Solution {
		public int solution(int[] citations) {
			int answer = 0;

			citations = Arrays.stream(citations)
				.boxed()
				.sorted((n1, n2) -> n2 - n1)
				.mapToInt(Integer::intValue)
				.toArray();

			for (int i = 0; i < citations.length; i++) {
				answer++;
				if (i + 1 > citations[i]) {
					break;
				}
			}

			return answer;
		}
	}

}


// 테스트 케이스 :  모든 논문이 논문 개수보다 많은 횟수 만큼 인용된 경우 ex) [9,9,9,9,9]
