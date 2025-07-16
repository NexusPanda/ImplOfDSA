package SerializationExamples;

import java.io.*;

public class SerializationExample implements Serializable {
    String name;
    String role;
    String power;

    public SerializationExample(String name, String role, String power) {
        this.name = name;
        this.role = role;
        this.power = power;
    }

    public static void main(String[] args) {
        SerializationExample serializationExample =
                new SerializationExample("Panda","Hero","Rolling Thunder Strike!!!");

        // Serialization
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializationExample);
            System.out.println("Object Serialized");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // DeSerialization
        try {
            FileInputStream fileInputStream = new FileInputStream("file.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SerializationExample deserialize = (SerializationExample) objectInputStream.readObject();
            System.out.println("Object DeSerialized");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
