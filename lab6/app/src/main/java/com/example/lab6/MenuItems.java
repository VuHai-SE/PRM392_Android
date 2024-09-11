package com.example.lab6;

public enum MenuItems {
    MENU_VANG(R.id.menuVang),
    MENU_DO(R.id.menuDo),
    MENU_XANH(R.id.menuXanh);

    private final int id;

    MenuItems(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static MenuItems fromId(int id) {
        for (MenuItems item : values()) {
            if (item.getId() == id) {
                return item;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + id);
    }
}
