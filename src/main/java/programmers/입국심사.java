package programmers;

/*
 * <풀이1> : 시간 초과
 * 1. times 길이 만큼 n을 쪼개는 여러 가지 수 : 이분 탐색?
 * 2. 각 시간과 쪼개진 값을 곱한 값의 최대 = 모든 사람이 심사를 받는데 걸리는 시간
 * 3. 1,2 수행한 모든 경우의 수 중 최솟값
 *
 *
 * <풀이2> : 시간을 기준으로 이진 탐색
 * */

public class 입국심사 {
	public static void main(String[] args) {

		long result = new Solution().solution(6, new int[] {7, 10});

		System.out.println(result);
	}

	public static class Solution {
		int n;
		int[] times;

		public long solution(int _n, int[] _times) {
			n = _n;
			times = _times;

			long answer = 0;

			long min = 1;
			long max = 1_000_000_000 * 1_000_000_000L;

			answer = binarySearch(min, max);

			return answer;
		}

		long binarySearch(long start, long end) {
			while (start < end) {
				long find = (start + end) / 2;
				if (isAvailable(find)) {
					end = find;
				} else {
					start = find + 1;
				}
			}

			return start;
		}

		boolean isAvailable(long targetTime) {
			long count = 0;

			for (int time : times) {
				count += targetTime / time;
			}

			return count >= n;
		}
	}
}
