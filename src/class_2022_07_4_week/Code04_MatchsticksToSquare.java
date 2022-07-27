package class_2022_07_4_week;

// 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
// 你要用 所有的火柴棍 拼成一个正方形。
// 你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
// 如果你能拼出正方形，则返回 true ，否则返回 false 。
// 测试链接 : https://leetcode.cn/problems/matchsticks-to-square/
public class Code04_MatchsticksToSquare {

	public static boolean makesquare(int[] matchsticks) {
		int sum = 0;
		for (int num : matchsticks) {
			sum += num;
		}
		if ((sum & 3) != 0) {
			return false;
		}
		int[] dp = new int[1 << matchsticks.length];
		return process(matchsticks, 0, 0, sum >> 2, 4, dp);
	}

	public static boolean process(int[] arr, int status, int cur, int len, int edges, int[] dp) {
		if (dp[status] != 0) {
			return dp[status] == 1;
		}
		boolean ans = false;
		if (edges == 0) {
			ans = (status == (1 << arr.length) - 1) ? true : false;
		} else {
			for (int i = 0; i < arr.length && !ans; i++) {
				if (((1 << i) & status) == 0 && cur + arr[i] <= len) {
					if (cur + arr[i] == len) {
						ans |= process(arr, status | (1 << i), 0, len, edges - 1, dp);
					} else {
						ans |= process(arr, status | (1 << i), cur + arr[i], len, edges, dp);
					}
				}
			}
		}
		dp[status] = ans ? 1 : -1;
		return ans;
	}

}
