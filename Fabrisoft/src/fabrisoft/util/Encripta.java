package fabrisoft.util;
 
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.tools.ShellFunctions.input;
import org.bouncycastle.jcajce.provider.digest.SHA3;
 
public final class Encripta {
 
    public static String getHash(String str){
        return byteArrayToString(new SHA3.Digest256().digest(str.getBytes()));
    }	
    
    public static String getHash(){
        return byteArrayToString(new SHA3.Digest256().digest(
                Long.toHexString(
                        Double.doubleToLongBits(Math.random())
                ).getBytes()));
    }
        
    private static String byteArrayToString(byte[] bytes) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int parteAlta = ((bytes[i] >> 4) & 0xf) << 4;
            int parteBaixa = bytes[i] & 0xf;
            if (parteAlta == 0) s.append('0');
            s.append(Integer.toHexString(parteAlta | parteBaixa));
        }
        return s.toString();
    }
}