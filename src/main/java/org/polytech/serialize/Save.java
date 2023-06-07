package org.polytech.serialize;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save {
    public static void Save(Object data, String filePath) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(data);
            objectOut.close();
            fileOut.close();
            System.out.println("La sauvegarde a été effectuée avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
