package programmers;

import java.util.ArrayList;
import java.util.List;

/*
 *  https://school.programmers.co.kr/learn/courses/30/lessons/84512
 *
 * */
public class 모음사전 {
	public static void main(String[] args) {
		System.out.println(new Solution().solution("EIO"));
	}

	static class Solution {
		char[] vowels = "AEIOU".toCharArray();

		public int solution(String word) {
			List<String> words = new ArrayList<>();
			generate("", words);

			return words.indexOf(word);
		}

		void generate(String word, List<String> words) {
			words.add(word);
			if (word.length() == 5) {
				return;
			}
			for (Character vowel : vowels) {
				generate(word + vowel, words);
			}
		}
	}
}
