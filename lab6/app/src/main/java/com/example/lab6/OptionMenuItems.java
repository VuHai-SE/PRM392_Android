package com.example.lab6;

public enum OptionMenuItems {
    OPTION1(R.id.option1),
    OPTION2(R.id.option2),
    OPTION3(R.id.option3),
    EXIT(R.id.mExit);

    private final int id;

    OptionMenuItems(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static OptionMenuItems fromId(int id) {
        for (OptionMenuItems item : values()) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + id);
    }
}
