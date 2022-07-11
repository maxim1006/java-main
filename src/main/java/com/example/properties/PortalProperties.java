package com.example.properties;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;

@StaticInitSafe
@ConfigMapping(prefix = "portal")
public interface PortalProperties {

    TestProperties test();

    interface TestProperties {
        TestPropertiesNames names();
    }

    interface TestPropertiesNames {
        String testName();
        String testName1();
        String testName2();
    }
}
