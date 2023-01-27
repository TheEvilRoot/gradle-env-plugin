package com.theevilroot.gep.core.internal;

public class GradleEnvSourceSystemEnvironment implements GradleEnvSource {
    @Override
    public String queryKey(String key) {
        return System.getenv(key);
    }
}
