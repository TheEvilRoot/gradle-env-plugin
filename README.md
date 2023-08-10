# GradleEnv

*GradleEnv* is a simple plugin to manage environment variables inside gradle project. 
It supports retrieving values by key from system environment, properties files (like local.properties). Other options are pending!

## Configuration

Add `gradleEnv` block into your `build.gradle` file and define sources to be used for key retrieval. 
Source order is preserved when querying key.

```groovy
gradleEnv {
    enableSystemEnvironment() // system environment variables
    propertiesFile("project.properties") // "public" properties file that is checked out by VCS
    propertiesFile("local.properties") // "private" properties file that is not checked out by VSC
}
```

Then you can retrieve value by key, for example for maven repository credentials and URLs:

```groovy
maven {
    url = project.gradleEnv.queryKey("ORG_REPOSITORY") // retrieve from project.properties
    credentials {
        username = project.gradleEnv.queryKey("ORG_USER") // retrieve from system env or local.properties
        password = project.gradleEnv.queryKey("ORG_PASSWORD") // retrieve from local.properties
    }
}
```

Sample project is available at [gradle-env-plugin](gradle-env-plugin-sample) directory (plugin is applied using Gradle Composite Build).

To apply plugin to the gradle project, use `apply plugin: 'xyz.theevilroot.gradle-env-plugin'`