package hanzy.stake.limbo.hanzimbo.generator;

import hanzy.stake.limbo.hanzimbo.hash.Sha256;
import hanzy.stake.limbo.hanzimbo.model.dto.LimboGeneratorResult;
import hanzy.stake.limbo.hanzimbo.requests.GenerateRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LimboGenerator {
    private static final double KEY = 16777216d;

    private static final Map<Integer, Double> FIRST_FORMULA_INDEX_MAP  = Map.of(
            1, Math.pow(256, 1),
            2, Math.pow(256, 2),
            3, Math.pow(256, 3),
            4, Math.pow(256, 4));

    public static LimboGeneratorResult getResultModel(int generationSeedId, GenerateRequest request, int nonce) {
        return new LimboGeneratorResult()
                .setGenerationSeedId(generationSeedId)
                .setNonce(nonce)
                .setResult(getResult(request.getServerSeed(), request.getClientSeed(), nonce));
    }

    public static double getResult(String serverSeed, String clientSeed, int nonce) {
        try {
            return Sha256
                    .hash(serverSeed, clientSeed.concat(":").concat(String.valueOf(nonce)).concat(":0"))
                    .transform(LimboGenerator::hashToEdged);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static double hashToEdged(String hash) {
        AtomicInteger index = new AtomicInteger(1);

        int rawNumber = (int) (hash
                .transform(LimboGenerator::hashToNumbers)
                .stream()
                .map(number -> applyFirstFormula(number, index.getAndIncrement()))
                .reduce(Double::sum)
                .orElseThrow(() -> new RuntimeException("Error while applying algorithm."))
                * KEY);

        return Math.max(Math.floor((KEY / (rawNumber + 1d) / 0.99) * 100) / 100, 1d);
    }

    private static double applyFirstFormula(int number, int index) {
        return number / FIRST_FORMULA_INDEX_MAP.get(index);
    }

    private static List<Integer> hashToNumbers(String hash) {
        return Arrays.stream(hash.split("(?<=\\G.{2})", 5))
                .toList()
                .subList(0, 4)
                .stream()
                .map(hex -> Integer.parseInt(hex, 16))
                .collect(Collectors.toList());
    }
}
