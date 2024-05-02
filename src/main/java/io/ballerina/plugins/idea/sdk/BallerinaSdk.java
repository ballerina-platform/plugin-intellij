package io.ballerina.plugins.idea.sdk;

public class BallerinaSdk {
    private String version;
    private String path;

    public BallerinaSdk(String path, String version) {
        this.version = version;
        this.path = path;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
