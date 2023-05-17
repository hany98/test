package hanzy.stake.limbo.hanzimbo.hash;

public class Main {
    public static void main(String[] args) {
        String serverSeed = "827435a31f1d739df9d0a42d62290f499efb6975dcc76d97469b193cb81733e0";
        String clientSeed = "Iyal5gneLc";
        int nonce = 511;

        try {
            String hash = Sha256.hash(serverSeed, clientSeed.concat(":").concat(String.valueOf(nonce)).concat(":0"));
            System.out.printf("hash -> %s%n", hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}