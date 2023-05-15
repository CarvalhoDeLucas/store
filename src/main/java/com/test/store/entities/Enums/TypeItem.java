package com.test.store.entities.Enums;

public enum TypeItem {

    PRODUCT("PRODUCT"), SERVICE("SERVICE");

    private String code;

    private TypeItem(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static TypeItem validCode(String code) {
        for (TypeItem value : TypeItem.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid type item code");
    }

}
