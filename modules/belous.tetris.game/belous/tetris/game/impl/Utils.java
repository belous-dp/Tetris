package belous.tetris.game.impl;

import java.util.List;

public class Utils {
    public static boolean[][] strsToBools(final List<String> arrs) {
        final boolean[][] res = new boolean[arrs.size()][];
        for (int i = 0; i < arrs.size(); i++) {
            final String str = arrs.get(i);
            res[i] = new boolean[str.length()];
            for (int j = 0; j < str.length(); j++) {
                res[i][j] = str.charAt(j) == '*';
            }
        }
        return res;
    }
}
