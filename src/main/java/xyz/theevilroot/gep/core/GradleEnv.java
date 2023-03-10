package xyz.theevilroot.gep.core;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class GradleEnv implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getExtensions()
                .create("gradleEnv", GradleEnvConfiguration.class, project);
    }
}
