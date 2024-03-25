package com.example.utils;

import lombok.extern.slf4j.Slf4j;

import jakarta.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static org.locationtech.jts.io.WKBWriter.toHex;

@Slf4j
@ApplicationScoped
public class LoggerUtils {
    public String getHashCode(String string) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA3-256");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
        if (digest != null) {
            final byte[] hashbytes = digest.digest(string.getBytes(StandardCharsets.UTF_8));
            return toHex(hashbytes);
        } else {
            return null;
        }

    }
}

class LoggerUsage {
    public static void main(String[] args) {
        LoggerUtils loggerUtils = new LoggerUtils();

        System.out.println(loggerUtils.getHashCode("Max")); // D66DCA628B2B327213614ADC9FB6E3802CE052497F9F89143658CAD083D7AA0C
    }
}
