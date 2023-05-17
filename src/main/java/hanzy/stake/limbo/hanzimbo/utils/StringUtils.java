package hanzy.stake.limbo.hanzimbo.utils;

public class StringUtils {
    public static String CapitalizeFirstLetter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String surroundWithQuotations(String s) {
        return "\"".concat(s).concat("\"");
    }
}
