package com.theevilroot.gep.core;

import com.theevilroot.gep.core.internal.GradleEnvSource;
import com.theevilroot.gep.core.internal.GradleEnvSourcePropertiesFile;
import com.theevilroot.gep.core.internal.GradleEnvSourceSystemEnvironment;
import groovy.lang.Closure;
import org.gradle.api.Project;

import java.nio.file.Paths;
import java.util.ArrayList;

public class GradleEnvConfiguration {
    private final Project project;
    private final ArrayList<GradleEnvSource> sources = new ArrayList<>();

    public GradleEnvConfiguration(Project project) {
        this.project = project;
    }

    public void enableSystemEnvironment() {
        System.out.printf("enableSystemEnvironment for %d keys in ENV\n", System.getenv().keySet().size());
        sources.add(new GradleEnvSourceSystemEnvironment());
    }

    public void propertiesFile(String relativeFilePath) {
        String absolutePath = project.getProjectDir().toPath().resolve(Paths.get(relativeFilePath)).toString();
        GradleEnvSourcePropertiesFile source = new GradleEnvSourcePropertiesFile(absolutePath);
        System.out.printf("propertiesFile %s loaded %d keys from %s\n", relativeFilePath, source.keys().size(), source.getAbsolutePath());
        sources.add(source);
    }

    public String queryKey(String key) {
        for (GradleEnvSource source : sources) {
            String value = source.queryKey(key);
            if (value != null) {
                return value;
            }
        }
        return null;
    }
}
