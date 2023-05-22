package org.polytech.utils;

import javax.swing.*;
import java.util.Objects;

public class Utils {
    /**
     * Renvoie une image
     * @param name le nom de l'image
     * @param extension son extension
     * @return une image
     */
    public static ImageIcon getImageFromResources(String name, ExtensionImage extension) {
        return new ImageIcon(Objects.requireNonNull(Utils.class.getClassLoader().getResource(name + extension.getExtension())));
    }
}
