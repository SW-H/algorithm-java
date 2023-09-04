package programmers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 소수찾기 {
	class Solution {
		int maxLen;

		public int solution(String numbers) {
			maxLen = numbers.length();

			List<String> possibilities = new ArrayList<>();

			combine(possibilities, numbers, "");

			Set<Integer> primeNums = new HashSet<>();
			for (int i = 1; i < possibilities.size(); i++) {
				int candidateNumber = Integer.parseInt(possibilities.get(i));
				if (isPrime(candidateNumber)) {
					primeNums.add(candidateNumber);
				}
			}

			return primeNums.size();
		}

		private boolean isPrime(int num) {
			if (num <= 1) {
				return false;
			}

			for (int i = 2; i * i <= num; i++) {
				if (num % i == 0) {
					return false;
				}
			}
			return true;
		}

		private void combine(List<String> possibilities, String numbers, String num) {

			possibilities.add(num);

			if (num.length() == maxLen) {
				return;
			}

			for (char c : numbers.toCharArray()) {
				combine(possibilities, numbers.replaceFirst(String.valueOf(c), ""), num + c);
			}
		}
	}
}
