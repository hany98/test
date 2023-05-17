package hanzy.stake.limbo.hanzimbo.hash;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Sha256 {
    private static Mac algo;
    private static String previousSecretKey;
    public static final String ALGO_NAME = "HmacSHA256";
    private static final Map<String, Key> algoKeyMap = new HashMap<>();

    public static String hash(@NotNull String secretKey, @NotNull String message) throws InvalidKeyException, NoSuchAlgorithmException {
        if(!secretKey.equalsIgnoreCase(previousSecretKey))
            initAlgo(secretKey);
        return Hex.encodeHexString(algo.doFinal(message.getBytes(StandardCharsets.UTF_8)));
    }

    private static void initAlgo(String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        algo = Mac.getInstance(ALGO_NAME);
        algo.init(getAlgoKey(secretKey));
        previousSecretKey = secretKey;
    }

    private static Key getAlgoKey(String secretKey) {
        Key key;
        if(algoKeyMap.containsKey(secretKey)) {
            key = algoKeyMap.get(secretKey);
            if(!Objects.isNull(key))
                return key;
        }
        key = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), ALGO_NAME);
        algoKeyMap.put(secretKey, key);
        return key;
    }
}
