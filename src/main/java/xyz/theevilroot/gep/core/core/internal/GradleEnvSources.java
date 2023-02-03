package xyz.theevilroot.gep.core.core.internal;

import javax.inject.Inject;

public abstract class GradleEnvSources {

    private final String name;

    @Inject
    public GradleEnvSources(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
