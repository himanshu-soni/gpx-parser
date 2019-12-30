
package me.himanshusoni.gpxparser.type;

/**
 * <p>Type of GPS fix. Value comes from list: {'none'|'2d'|'3d'|'dgps'|'pps'}</p>
 * <br>
 * <ul>
 * <li>none = GPS had no fix.</li>
 * <li>pps = military signal used</li>
 * </ul>
 * <p>To signify "the fix info is unknown", leave out fixType entirely.</p>
 */
public class Fix {

    /**
     * Constant that defines 'none' Fix type
     */
    public static Fix NONE = new Fix("none");
    /**
     * Constant that defines '2d' Fix type
     */
    public static Fix TWO_D = new Fix("2d");
    /**
     * Constant that defines '3d' Fix type
     */
    public static Fix THREE_D = new Fix("3d");
    /**
     * Constant that defines 'dgps' Fix type
     */
    public static Fix DGPS = new Fix("dgps");

    //'none'|'2d'|'3d'|'dgps'|'pps'
    /**
     * Constant that defines 'pps' Fix type
     */
    public static Fix PPS = new Fix("pps");
    private String value;

    private Fix(String value) {
        this.value = value;
    }

    public static Fix returnType(String value) {
        if (NONE.getValue().equals(value)) {
            return NONE;
        } else if (TWO_D.getValue().equals(value)) {
            return TWO_D;
        } else if (THREE_D.getValue().equals(value)) {
            return THREE_D;
        } else if (DGPS.getValue().equals(value)) {
            return DGPS;
        } else if (PPS.getValue().equals(value)) {
            return PPS;
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return value;
    }
}
