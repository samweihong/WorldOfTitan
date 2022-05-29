package logic;

import java.util.List;

public class Utils {
    public static String formatList(List<?> list) {
        if (list.isEmpty()) return "";
        if (list.size() == 1) return list.get(0).toString();

        StringBuilder res = new StringBuilder();
        res.append(list.get(0));
        for (int i = 1; i < list.size()-1; i++)
            res.append(", ").append(list.get(i));
        res.append(" and ").append(list.get(list.size() - 1));
        return res.toString();
    }
}
