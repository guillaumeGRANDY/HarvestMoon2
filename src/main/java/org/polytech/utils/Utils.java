package org.polytech.utils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class Utils {
    /**
     * Renvoie une image
     * @param name le nom de l'image
     * @param extension son extension
     * @return une image
     */
    public static ImageIcon getImageIconFromResources(String name, ExtensionImage extension) {
        return new ImageIcon(Objects.requireNonNull(Utils.class.getClassLoader().getResource(name + extension.getExtension())));
    }

    public static Image getImageFromResources(String name, ExtensionImage extension) {
        return new ImageIcon(Objects.requireNonNull(Utils.class.getClassLoader().getResource(name + extension.getExtension()))).getImage();
    }

    public static Font getFontFromResources(String name) {
        try {
            URL resource = Utils.class.getClassLoader().getResource("font/" + name + ".otf");
            if (resource == null) {
                throw new RuntimeException("Resource not found: " + "font/" + name + ".otf");
            }
            InputStream fontAsStream = resource.openStream();
            return Font.createFont(Font.TRUETYPE_FONT, fontAsStream);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
