package programmers;
/*
* https://school.programmers.co.kr/learn/courses/30/lessons/70129
*
* */
public class 이진변환반복하기 {
	public static void main(String[] args) {
		String s = "01110";
		int[] res = new Solution().solution(s);
		System.out.println(res[0] + " " + res[1]);
	}

	public static class Solution {
		public int[] solution(String s) {
			int round = 0;
			int zeroCount = 0;
			while (!s.equals("1")) {
				int prevLen = s.length();
				int curLen = s.replace("0", "").length();


				s = Integer.toString(curLen, 2);
				zeroCount += (prevLen - curLen);
				round++;
			}

			return new int[] {round, zeroCount};
		}
	}
}
