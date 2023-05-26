package org.polytech.utils;

public enum ExtensionImage {
    PNG(".png");

    private final String extension;

    ExtensionImage(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
