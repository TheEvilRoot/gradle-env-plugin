package xyz.theevilroot.gep.core.core.internal;

public class GradleEnvSourceSystemEnvironment implements GradleEnvSource {
    @Override
    public String queryKey(String key) {
        return System.getenv(key);
    }
}
