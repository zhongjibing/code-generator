package com.icezhg.codegenerator.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.icezhg.codegenerator.domain.Column;
import com.squareup.javapoet.ArrayTypeName;

/**
 * Created by zhongjibing on 2018/12/27.
 */
public final class FieldTypeUtil {

    private static final String TYPE_BIT = "bit";

    private static final Map<String, Object> TYPE_MAP = new HashMap<String, Object>() {{
        put("varchar", String.class);
        put("text", String.class);
        put("mediumtext", String.class);
        put("char", String.class);
        put("clob", String.class);
        put("longtext", String.class);
        put("integer", Integer.class);
        put("int", Integer.class);
        put("tinyint", Integer.class);
        put("smallint", Integer.class);
        put("bigint", Long.class);
        put("double", Double.class);
        put("float", Double.class);
        put("decimal", BigDecimal.class);
        put("date", Date.class);
        put("datetime", Date.class);
        put("time", Date.class);
        put("timestamp", Timestamp.class);

        put("bit", ArrayTypeName.of(Byte.class));
        put("binary", ArrayTypeName.of(Byte.class));
        put("blob", ArrayTypeName.of(Byte.class));
        put("mediumblob", ArrayTypeName.of(Byte.class));
        put("longblob", ArrayTypeName.of(Byte.class));
    }};

    public static Object getType(Column col) {
        if (TYPE_BIT.equals(col.getDataType().toLowerCase()) && Objects.equals(col.getNumericPrecision(), 1L)) {
            return Boolean.class;
        } else {
            Object type = TYPE_MAP.get(col.getDataType().toLowerCase());
            if (type == null) {
                type = String.class;
            }
            return type;
        }
    }
}
