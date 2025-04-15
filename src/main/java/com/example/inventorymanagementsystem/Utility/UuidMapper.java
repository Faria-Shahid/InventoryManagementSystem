package com.example.inventorymanagementsystem.Utility;

import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UuidMapper {
    @Named("uuidToString")
    public static String uuidToString(UUID uuid) {
        return uuid != null ? uuid.toString() : null;
    }

    @Named("stringToUUID")
    public static UUID stringToUUID(String uuid) {
        return uuid != null ? UUID.fromString(uuid) : null;
    }
}
