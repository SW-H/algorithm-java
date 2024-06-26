package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 *
 * https://school.programmers.co.kr/learn/courses/30/lessons/72411
 * */
public class 메뉴리뉴얼 {
	public static void main(String[] args) {
		String[] results = new Solution().solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
			new int[] {2, 3, 4});

		for (String result : results) {
			System.out.println(result);
		}

	}

	public static class Solution {
		public String[] solution(String[] orders, int[] course) {
			List<Set<String>> orderList = Arrays.stream(orders)
				.map(String::chars)
				.map(charStream -> charStream
					.mapToObj(menu -> String.valueOf((char)menu))
					.collect(Collectors.toSet()))
				.collect(Collectors.toList());

			Map<Integer, List<Course>> courses = new HashMap<>();
			for (int length : course) {
				List<Course> list = new ArrayList<>();
				list.add(new Course("", 0));
				courses.put(length, list);
			}
			getCourses('A', new HashSet<>(), orderList, courses);

			return courses.values().stream()
				.filter(list -> list.get(0).occurrences > 0)
				.flatMap(List::stream)
				.map(c -> c.course)
				.sorted()
				.toArray(String[]::new);
		}

		class Course {
			public final String course;
			public final int occurrences;

			public Course(String course, int occurrences) {
				this.course = course;
				this.occurrences = occurrences;
			}
		}

		private void getCourses(
			char nextMenu,
			Set<String> selectedMenus,
			List<Set<String>> orderList,
			Map<Integer, List<Course>> courses
		) {
			int occurrences = (int)orderList.stream()
				.filter(order -> order.containsAll(selectedMenus))
				.count();
			if (occurrences < 2) {
				return;
			}

			int size = selectedMenus.size();
			if (courses.containsKey(size)) {
				List<Course> courseList = courses.get(size);
				Course course = new Course(
					selectedMenus.stream()
					.sorted()
					.collect(Collectors.joining("")),
					occurrences);

				Course original = courseList.get(0);
				if (original.occurrences < occurrences) {
					courseList.clear();
					courseList.add(course);
				} else if (original.occurrences == occurrences) {
					courseList.add(course);
				}
			}

			if (size >= 10)
				return;
			for (char menuChar = nextMenu; menuChar <= 'Z'; menuChar++) {
				String menu = String.valueOf(menuChar);
				selectedMenus.add(menu);
				getCourses((char)(menuChar + 1), selectedMenus, orderList,
					courses);
				selectedMenus.remove(menu);
			}
		}
	}
	/*static class Solution {

		int[] course;
		String[] orders;
		Character[] alphabets;

		public String[] solution(String[] _orders, int[] _course) {
			course = _course;
			orders = _orders;

			alphabets = Set.of(Arrays.stream(orders).map(String::toCharArray)).toArray(Character[]::new);

			List<Set<String>> combinations = new ArrayList<>();
			solve(0, new HashSet<>(), combinations);

			return combinations.stream().map(Objects::toString).toArray(String[]::new);
		}

		void solve(int index, Set<String> candidate, List<Set<String>> combinations) {
			if (Arrays.stream(course).anyMatch(c -> c == candidate.size())) {
				combinations.add(candidate);
			}
			if (candidate.size() > course[course.length - 1]) {
				return;
			}
			if (countOrdered(candidate) < 2) {
				return;
			}
			for (int i = index + 1; i < alphabets.length; i++) {
				candidate.add(alphabets[i].toString());
				solve(i, candidate, combinations);
				candidate.remove(alphabets[i]);
			}
		}

		int countOrdered(Set<String> course) {
			int count = 0;
			boolean contains;
			for (String order : orders) {
				contains = true;
				for (String c : course) {
					if (!Set.of(c.toCharArray()).contains(c)) {
						contains = false;
						break;
					}
				}
				if (contains) {
					count++;
				}
			}
			return count;
		}
	}*/
}
