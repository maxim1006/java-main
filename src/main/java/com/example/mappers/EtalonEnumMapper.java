package com.example.mappers;

import com.example.java.enums.EnumEtalon;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ValueMapping;

// пример enum с valueMapping + случай если передал null в from
@Mapper
public interface EtalonEnumMapper {
    @AllArgsConstructor
    public enum EnumEtalonDto {
        ALL("all values"),
        MANDATORY("mandatory values"),
        NONE("none values"),
        DEFAULT("Default");

        @Getter
        @JsonValue
        private final String value;

        @Override
        public String toString() {
            return value;
        }
    }

    // если передал null в from то на выходе DEFAULT
    @ValueMapping(source = MappingConstants.NULL, target = "DEFAULT")
    EnumEtalonDto from(EnumEtalon enumEtalon);
}
