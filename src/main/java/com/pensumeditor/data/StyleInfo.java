package com.pensumeditor.data;

public class StyleInfo {

    private String styleName;
    private String styleAuthor;
    private String id;
    private int distance_x;
    private int distance_y;
    private int space_x;
    private int space_y;
    private String version;

    public StyleInfo(String styleName, String styleAuthor, String id, int distance_x, int distance_y, int space_x, int space_y, String version) {
        this.styleName = styleName;
        this.styleAuthor = styleAuthor;
        this.id = id;
        this.distance_x = distance_x;
        this.distance_y = distance_y;
        this.space_x = space_x;
        this.space_y = space_y;
        this.version = version;
    }

    public String getStyleName() {
        return styleName;
    }

    public String getStyleAuthor() {
        return styleAuthor;
    }

    public String getId() {
        return id;
    }

    public int getDistance_x() {
        return distance_x;
    }

    public int getDistance_y() {
        return distance_y;
    }

    public int getSpace_x() {
        return space_x;
    }

    public int getSpace_y() {
        return space_y;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "StyleInfo{" +
                "styleName='" + styleName + '\'' +
                ", styleAuthor='" + styleAuthor + '\'' +
                ", id='" + id + '\'' +
                ", distance_x=" + distance_x +
                ", distance_y=" + distance_y +
                ", space_x=" + space_x +
                ", space_y=" + space_y +
                ", version='" + version + '\'' +
                '}';
    }
}
