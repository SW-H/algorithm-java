// package programmers;
//
// /*
//  *  https://school.programmers.co.kr/learn/courses/30/lessons/87377
//  *
//  * */
//
//
// import java.util.*;
// import java.util.stream.*;
// import static java.util.Collections.*;
//
// public class 교점에별만들기 {
// 	public static void main(String[] args) {
// 		int[][] input =
// 				{{1, -1, 0}, {2, -1, 0}, {4, -1, 0}};
// 		// {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
// 		// {{2, -1, 4},
// 		// 		{-2, -1, 4},
// 		// 		{0, -1, 1},
// 		// 		{5, -8, -12},
// 		// 		{5, 8, 12}};
// 		new Solution().solution(input);
// 	}
//
// 	static class Solution {
// 		class Point {
// 			public long r;
// 			public long c;
//
// 			public Point(long r, long c) {
// 				this.r = r;
// 				this.c = c;
// 			}
// 		}
// 		List<Point> meetPoints = new ArrayList<>();
// 		int[][] line;
//
//
// 		public String[] solution(int[][] _line) {
// 			line = _line;
// 			addMeetPoints();
//
// 			adjustPoints(meetPoints);
//
// 			int height = (int)(max(meetPoints.stream().map(p -> p.r).collect(Collectors.toList())) + 1);
// 			int width = (int)(max(meetPoints.stream().map(p -> p.c).collect(Collectors.toList())) + 1);
// 			return draw(height, width, meetPoints);
// 		}
//
// 		private void addMeetPoints() {
//
// 			for (int i = 0; i < line.length; i++) {
// 				for (int j = i + 1; j < line.length; j++) {
// 					long A = line[i][0];
// 					long B = line[i][1];
// 					long E = line[i][2];
//
// 					long C = line[j][0];
// 					long D = line[j][1];
// 					long F = line[j][2];
//
// 					if ((A * D - B * C == 0) ||
// 							((E * C - A * F) % (A * D - B * C) != 0) ||
// 							((B * F - E * D) % (A * D - B * C) != 0)) {
// 						continue;
// 					}
// 					long meetR = (E * C - A * F) / (A * D - B * C); // y
// 					long meetC = (B * F - E * D) / (A * D - B * C); // x
//
// 					meetPoints.add(new Point(meetR, meetC));
//
// 				}
// 			}
// 		}
//
// 		private String[] draw(int height, int width, List<Point> meetPoints) {
// 			String oneLine = ".".repeat(width);
// 			StringBuilder[] result = new StringBuilder[height];
// 			for (int i = 0; i < height; i++) {
// 				result[i] = new StringBuilder(oneLine);
// 			}
//
// 			for (Point meetpoint : meetPoints) {
// 				result[(int)meetpoint.r].setCharAt((int)meetpoint.c, '*');
// 			}
//
// 			String[] ret = new String[height];
//
// 			for (int i = result.length - 1; i >= 0; i--) {
// 				ret[i] = result[height - i - 1].toString();
// 			}
// 			return ret;
// 		}
//
// 		private void adjustPoints(List<Point> meetPoints) {
// 			int rAdjustmentValue = (int)meetPoints.get(0).r;
// 			int cAdjustmentValue = (int)meetPoints.get(0).c;
//
// 			for (Point point : meetPoints) {
// 				rAdjustmentValue = (int)Math.min(rAdjustmentValue, point.r);
// 				cAdjustmentValue = (int)Math.min(cAdjustmentValue, point.c);
// 			}
// 			for (Point point : meetPoints) {
// 				point.r -= rAdjustmentValue;
// 				point.c -= cAdjustmentValue;
// 			}
// 		}
// 	}
// }
