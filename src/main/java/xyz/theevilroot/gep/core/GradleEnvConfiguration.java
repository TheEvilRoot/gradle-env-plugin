package xyz.theevilroot.gep.core;

import xyz.theevilroot.gep.core.internal.GradleEnvSource;
import xyz.theevilroot.gep.core.internal.GradleEnvSourcePropertiesFile;
import xyz.theevilroot.gep.core.internal.GradleEnvSourceSystemEnvironment;
import org.gradle.api.Project;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;

public class GradleEnvConfiguration {
    private final Project project;
    private final ArrayList<GradleEnvSource> sources = new ArrayList<>();

    private static final Logger logger = Logger.getLogger("GradleEnv");

    public GradleEnvConfiguration(Project project) {
        this.project = project;
    }

    public void enableSystemEnvironment() {
        logger.info(String.format("enableSystemEnvironment for %d keys in ENV\n", System.getenv().keySet().size()));
        sources.add(new GradleEnvSourceSystemEnvironment());
    }

    public void propertiesFile(String relativeFilePath) {
        String absolutePath = project.getProjectDir().toPath().resolve(Paths.get(relativeFilePath)).toString();
        GradleEnvSourcePropertiesFile source = new GradleEnvSourcePropertiesFile(absolutePath);
        logger.info(String.format("propertiesFile %s loaded %d keys from %s\n", relativeFilePath, source.keys().size(), source.getAbsolutePath()));
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

    public String queryKey(String key, String fallback) {
        for (GradleEnvSource source : sources) {
            String value = source.queryKey(key);
            if (value != null) {
                return value;
            }
        }
        return fallback;
    }
}
