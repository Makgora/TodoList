package Controller;

import java.io.*;
import java.util.Base64;


public class SerializeHelper {

    /** Read the object from Base64 string. Prevent encoding issue.
     * @param s the serialized string encoded in base64
     * @return the deserialized object
     * @throws IOException, related to file manipulation
     * @throws ClassNotFoundException
     */
    public static Object deserialize( String s ) throws IOException ,
            ClassNotFoundException {
        byte [] data = Base64.getDecoder().decode( s );
        ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(  data ) );
        Object o  = ois.readObject();
        ois.close();
        return o;
    }

    /** Write the object to a Base64 string.
     * @param o an object to serialize
     * @return a base64 encoded string representing the serialized object
     * @throws IOException, related to file manipulation
     * */
    public static String serialize( Serializable o ) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream( baos );
        oos.writeObject( o );
        oos.close();
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }
}