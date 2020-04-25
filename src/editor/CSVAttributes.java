package editor;

import java.util.*;

class CSVAttributes {

    private static final Map<Integer, String> registeredPositionByValue = new HashMap<Integer, String>() {
        {
            put(0, "GK");
            put(2, "SW");
            put(3, "CB");
            put(4, "SB");
            put(5, "DM");
            put(6, "WB");
            put(7, "CM");
            put(8, "SM");
            put(9, "AM");
            put(10, "WF");
            put(11, "SS");
            put(12, "CF");
        }
    };

    private static final Map<String, Integer> registeredPositionByLabel = new HashMap<String, Integer>() {
        {
            put("GK", 0);
            put("SW", 2);
            put("CB", 3);
            put("SB", 4);
            put("DM", 5);
            put("WB", 6);
            put("CM", 7);
            put("SM", 8);
            put("AM", 9);
            put("WF", 10);
            put("SS", 11);
            put("CF", 12);
        }
    };

    public static Map<String, Integer> getRegisteredPositionByLabel() {
        return registeredPositionByLabel;
    }

    public static Map<Integer, String> getRegisteredPositionByValue() {
        return registeredPositionByValue;
    }

}
