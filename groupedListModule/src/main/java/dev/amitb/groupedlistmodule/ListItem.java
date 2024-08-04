package dev.amitb.groupedlistmodule;

public class ListItem {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    private final Object data;
    private final int type;

    public ListItem(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public int getType() {
        return type;
    }
}
