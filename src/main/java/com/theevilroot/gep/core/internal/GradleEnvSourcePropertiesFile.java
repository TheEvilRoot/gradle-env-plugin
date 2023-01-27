package com.theevilroot.gep.core.internal;

import javax.inject.Inject;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class GradleEnvSourcePropertiesFile implements GradleEnvSource {

    private final String absolutePath;
    private final Properties properties;

    @Inject
    public GradleEnvSourcePropertiesFile(String absolutePath) {
        this.absolutePath = absolutePath;
        this.properties = new Properties();
        try {
            File file = new File(absolutePath);
            if (file.exists() && file.canRead()) {
                try (InputStream inputStream = Files.newInputStream(file.toPath())) {
                    properties.load(inputStream);
                }
            }
        } catch (Exception ignored) {
        }
    }

    public Set<Object> keys() {
        return properties.keySet();
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    @Override
    public String queryKey(String key) {
        return properties.getProperty(key);
    }
}
