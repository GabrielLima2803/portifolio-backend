package com.lima.portifolio.portfolio.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SkillType {
    TOOLS(1L, "Tools"),
    FRAMEWORK(2L, "Frameworks");

    private final long id;
    private final String type;

    SkillType(long id, String type) {
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static SkillType fromValue(String value) {
        for (SkillType st : SkillType.values()) {
            if (String.valueOf(st.getId()).equals(value)
                    || st.name().equalsIgnoreCase(value)
                    || st.getType().equalsIgnoreCase(value)) {
                return st;
            }
        }
        throw new IllegalArgumentException("Tipo inválido: " + value);
    }
}
