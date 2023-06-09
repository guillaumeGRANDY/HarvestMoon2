package org.polytech.serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtils {
    public static void save(Object data) throws Exception {
        FileOutputStream fileOut = new FileOutputStream("backup.dat");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        objectOut.writeObject(data);
        objectOut.close();
        fileOut.close();
        System.out.println("La sauvegarde a été effectuée avec succès !");
    }

    public static Object load() throws Exception {
        FileInputStream fileIn = new FileInputStream("backup.dat");
        ObjectInputStream objectIn = new ObjectInputStream(fileIn);
        Object obj = objectIn.readObject();
        objectIn.close();
        fileIn.close();
        return obj;
    }
}
