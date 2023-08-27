package programmers;
/*
* https://school.programmers.co.kr/learn/courses/30/lessons/60057
*
* */
public class 문자열압축 {
	public static void main(String[] args) {
		int res = new Solution().solution("aabbaccc");
		System.out.println(res);
	}

	static class Solution {
		String str;

		public int solution(String s) {
			int answer = Integer.MAX_VALUE;
			str = s;
			for (int unit = 1; unit <= str.length(); unit++) {
				answer = Math.min(answer, calculate(unit));
			}
			return answer;
		}

		private int calculate(int unit) {
			String[] parsedString = parse(unit);
			String curStr = "";
			StringBuilder compressedStr = new StringBuilder();
			int cnt = 0;
			for (String token : parsedString) {
				if (token.equals(curStr)) {
					cnt++;
				} else {
					if (cnt > 1) {
						compressedStr.append(cnt);
					}
					compressedStr.append(curStr);
					curStr = token;
					cnt = 1;
				}
			}
			if (cnt > 1) {
				compressedStr.append(cnt);
			}
			compressedStr.append(curStr);

			return compressedStr.length();
		}

		String[] parse(int len) {
			int count = (int)Math.ceil(str.length() / (double)len);
			String[] ret = new String[count];
			int idx = 0;
			for (int i = 0; idx < count; i += len) {
				int endIdx = Math.min(i + len, str.length());
				ret[idx++] = str.substring(i, endIdx);
			}
			return ret;
		}

	}

	// 처음 시도 : n개 단위로 잘라 압축할 때
	// n개 만큼씩 for문에서 증가시키면서 개수를 세려고 했는데 그보다는 미리 split 해놓고 그 이후에 압축하는 게 나은 것 같음
	// split 개수 셀때 : 올림(ceil) 함수, ceil 안의 값이 double이 될 수 있도록 int/int의 경우 한개를 double로 캐스팅 필요
	// 			아니면 list에 추가하기
	/*
	private int calculate(int unit) {
			String curStr = str.substring(0, unit);
			StringBuilder compressed = new StringBuilder();
			int count = 1;
			for (int i = unit; i <= str.length(); i += unit) {
				if(i==str.length()){
					if(count>1){
						compressed.append(String.valueOf(count));
					}
					compressed.append(curStr);
					break;
				}
				if (str.length() - i < unit) {
					if(count>1){
						compressed.append(String.valueOf(count));
					}
					compressed.append(curStr);
					compressed.append(str.substring(i, str.length()));
					break;
				}
				if(!str.substring(i, i + unit).equals(curStr) || i==str.length()-1){
					if(count>1){
						compressed.append(String.valueOf(count));
					}
					compressed.append(curStr);
					count = 1;
					curStr = str.substring(i, i + unit);
				}else{
					count++;
				}
			}
			return compressed.length();
		}
	* */
}
