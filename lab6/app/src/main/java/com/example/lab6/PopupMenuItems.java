package com.example.lab6;

public enum PopupMenuItems {
    POPUP1(R.id.popup1),
    POPUP2(R.id.popup2),
    POPUP3(R.id.popup3);

    private final int id;

    PopupMenuItems(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static PopupMenuItems fromId(int id) {
        for (PopupMenuItems item : values()) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + id);
    }
}
