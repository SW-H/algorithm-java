package programmers;
/*
[지문 추가 필요]
- 다리길이 : bridge_length
- 트럭은 1초에 다리 길이 1씩 전진한다.
- 트럭은 올라갈수 있으면 1초에 한 대씩 다리에 올라갈 수 있다

 */

import java.util.ArrayDeque;
import java.util.Deque;

// https://school.programmers.co.kr/learn/courses/30/lessons/42583
public class 다리를지나는트럭 {
	public static void main(String[] args) {
		int bridgeLength = 100;
		int weight = 100;
		int[] truck_weights = {10};

		// int bridgeLength = 2;
		// int weight = 10;
		// int[] truck_weights = {7, 4, 5, 6};

		int ret = new Solution().solution(bridgeLength, weight, truck_weights);

		System.out.println(ret);
	}

	static class Solution {
		public int solution(int bridgeLength, int weight, int[] truckWeights) {
			int time = 0;

			Deque<Integer> bridge = new ArrayDeque<>();

			for (int i = 0; i < bridgeLength; i++) {
				bridge.offerLast(0);
			}

			int bridgeWeight = 0;
			int truckIndex = 0;
			while (truckIndex < truckWeights.length) {
				bridgeWeight -= bridge.poll();
				int truckWeight = truckWeights[truckIndex];
				if (bridgeWeight + truckWeight <= weight) {
					bridge.add(truckWeight);
					bridgeWeight += truckWeight;
					truckIndex++;
				} else {
					bridge.add(0);
				}
				time++;
			}
			while (bridgeWeight > 0) {
				bridgeWeight -= bridge.poll();
				time++;
			}
			return time;
/*			int totalWeight = 0;
			while (!queue.isEmpty()) {
				if ((totalWeight + queue.getFirst()) <= weight) {
					totalWeight += queue.pollFirst();
					continue;
				}
				// while(queue.size()>1){
					if((totalWeight + queue.getFirst()) > weight){
						queue.pollFirst();
					}
				// }
				answer++;
				totalWeight = 0;
			}*/
		}
	}
}
