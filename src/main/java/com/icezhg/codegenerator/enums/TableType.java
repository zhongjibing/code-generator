package com.icezhg.codegenerator.enums;

/**
 * Created by zhongjibing on 2019/01/01.
 */
public enum TableType {
    LOCAL_TEMPORARY("LOCAL TEMPORARY"),
    SYSTEM_TABLE("SYSTEM TABLE"),
    SYSTEM_VIEW("SYSTEM VIEW"),
    TABLE("TABLE"),
    BASE_TABLE("BASE TABLE"),
    VIEW("VIEW"),
    UNKNOWN("UNKNOWN");

    private final String name;


    TableType(String tableTypeName) {
        this.name = tableTypeName;
    }

    public String getName() {
        return this.name;
    }

    private boolean equalsTo(String tableTypeName) {
        return this.name.equalsIgnoreCase(tableTypeName);
    }


    public static TableType getTableType(String tableTypeName) {
        for (TableType tableType : TableType.values()) {
            if (tableType.equalsTo(tableTypeName)) {
                return tableType;
            }
        }
        return UNKNOWN;
    }
}
