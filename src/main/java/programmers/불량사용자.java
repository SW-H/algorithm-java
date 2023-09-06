package programmers;

/*
 * https://school.programmers.co.kr/learn/courses/30/lessons/64064
 *
 * */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 불량사용자 {
	class Solution {
		String[][] bannedUserIDs;

		public int solution(String[] user_id, String[] banned_id) {
			bannedUserIDs = Arrays.stream(banned_id)
					.map((id) -> id.replace('*', '.'))
					.map((banPattern) -> Arrays.stream(user_id).filter(userId -> userId.matches(banPattern))
							.toArray(String[]::new))
					.toArray(String[][]::new);

			Set<String> alreadyBanned = new HashSet<>();
			Set<Set<String>> combination = new HashSet<>();
			makeCombine(0, alreadyBanned, combination);

			return combination.size();
		}

		private void makeCombine(int patternIdx, Set<String> alreadyBanned,
				Set<Set<String>> combination) {
			if (patternIdx == bannedUserIDs.length) {
				combination.add(new HashSet<>(alreadyBanned));
				return;
			}

			for (String userId : bannedUserIDs[patternIdx]) {
				if(!alreadyBanned.contains(userId)){
					alreadyBanned.add(userId);
					makeCombine(patternIdx + 1, alreadyBanned, combination);
					alreadyBanned.remove(userId);
				}
			}

		}
	}
}





/*/// (1) 정규식 사용 : 실패
public int solution(String[] user_id, String[] banned_id) {
	int answer = 1;
	int[] numberOfCases = new int[banned_id.length];

	for (int i = 0; i < banned_id.length; i++) {
		String banned = banned_id[i];
		banned = banned.replaceAll("[*]", "\\\\S");
		for (String user : user_id) {
			if (user.matches(banned)) {
				numberOfCases[i]++;
			}
		}
	}
	for (int numberOfCase : numberOfCases) {
		System.out.println(numberOfCase);
		answer *= numberOfCase;
	}

	return answer;
}*/
