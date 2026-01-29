import java.util.Arrays;

class Solution {
    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] dp = new long[26][26];
        long max = Long.MAX_VALUE / 2;
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dp[i], max);
        }
        for (int i = 0; i < 26; i++) {
            dp[i][i] = 0;
        }
        for (int i = 0; i < original.length; i++) {
            dp[original[i] - 'a'][changed[i] - 'a'] = Math.min(cost[i], dp[original[i] - 'a'][changed[i] - 'a']);
        }
        for (int k = 0; k < 26; k++) {
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        long res = 0;
        for (int i = 0; i < source.length(); i++) {
            int s = source.charAt(i) - 'a';
            int t = target.charAt(i) - 'a';
            if (s == t) {
                continue;
            }
            long c = dp[s][t];
            if (c != max) {
                res += c;
            } else {
                return -1;
            }
        }


        return res;
    }
}