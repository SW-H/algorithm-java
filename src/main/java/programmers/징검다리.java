package programmers;

import java.util.Arrays;

public class 징검다리 {
	public static void main(String[] args) {
		int solution = new Solution().solution(25, new int[] {2, 14, 11, 21, 17}, 2);

		System.out.println(solution);
	}

	static class Solution {

		final int MIN_DISTANCE = 1;
		final int MAX_DISTANCE = 1_000_000_000;

		public int solution(int distance, int[] rocks, int n) {
			rocks = Arrays.copyOf(rocks, rocks.length + 1);
			rocks[rocks.length - 1] = distance;
			int answer = solve(MIN_DISTANCE, MAX_DISTANCE, Arrays.stream(rocks).sorted().toArray(), n);

			return answer;
		}

		private int solve(int start, int end, int[] rocks, int n) {
			while (end - start > 1) {
				int min = (start + end) / 2;
				if (isValid(min, rocks, n)) {
					start = min;
				} else {
					end = min;
				}
			}
			return start;
		}

		private boolean isValid(int minDistance, int[] rocks, int n) {
			int removed = 0;
			int last = 0;
			for (int rock : rocks) {
				if (rock - last < minDistance) {
					removed++;
					continue;
				}
				last = rock;
			}

			return removed <= n;
		}

	}

	// 시도 1. 시간 초과 및 틀린 풀이
	/*
	static class Solution {
		List<Integer> minDistance = new ArrayList<>();

		public int solution(int distance, int[] rocks, int n) {
			int answer = 0;
			List<Integer> sortedRocks = Arrays.stream(rocks).sorted().boxed().collect(Collectors.toList());
			sortedRocks.add(distance);

			getMinDistances(new ArrayList<>(), sortedRocks, n, distance);

			for (Integer dist : minDistance) {
				if (answer < dist) {
					answer = dist;
				}
			}

			return answer;
		}

		private void getMinDistances(
			List<Integer> currentlySelected,
			List<Integer> toSelect,
			int n,
			int distance
		) {
			if (n < 0) {

				currentlySelected.add(distance);
				minDistance.add(calculateMinDistance(currentlySelected));

			}

			for (int i = 0; i < toSelect.size(); i++) {
				currentlySelected.add(toSelect.get(i));
				getMinDistances(
					new ArrayList<>(currentlySelected),
					new ArrayList<>(toSelect.subList(i + 1, toSelect.size())),
					n - 1,
					distance
				);
				currentlySelected.remove(currentlySelected.size() - 1);
				// if (!currentlySelected.isEmpty()) {
				// }
			}

		}

		private int calculateMinDistance(List<Integer> rocks) {
			int ret = 1_000_000_000;

			rocks.sort(Comparator.naturalOrder());

			for (int i = 1; i < rocks.size(); i++) {
				if (rocks.get(i) - rocks.get(i - 1) < ret) {
					ret = rocks.get(i) - rocks.get(i - 1);
				}
			}

			return ret;
		}
	}
	*/
}
