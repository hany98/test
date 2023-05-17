package hanzy.stake.limbo.hanzimbo.algo.limbo;

import hanzy.stake.limbo.hanzimbo.model.algo.param.AlgoParam;
import hanzy.stake.limbo.hanzimbo.model.algo.param.LimboThresholdAlgoParam;
import hanzy.stake.limbo.hanzimbo.model.dto.AlgoResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LimboThresholdAlgo{

    private ArrayList<Double> dataSet;
    // My Statistics Variables
    private Map<Integer, Integer> winStreakMap;
    private Map<Integer, Integer> lossStreakMap;

    private int totalWins;

    private int totalLoss;

    private int maxWinStreak;
    private int maxLossStreak;

    private int totalRoundsWon;
    private int totalRoundsLost;

    private double totalAmountWon;
    private double totalAmountLost;

    private int currentWinStreak;
    private int currentLossStreak;

    private double maxAmountLost;
    private double maxAmountWon;

    private double maxConcurrentAmountLost;
    private double currentConcurrentAmountLost;

    private boolean repeatsAfterBetLossMet;

    private double currentAmount;
    private double betAmount;
    private double betAmountInput;

    private double targetMultiplier;
    private int lossStreakThreshold;
    private int repeatsAfterBetLoss;

    public LimboThresholdAlgo(AlgoParam params, ArrayList<Double> dataSet){
        resetVariables();
        LimboThresholdAlgoParam thresholdParams = (LimboThresholdAlgoParam) params;
        this.targetMultiplier = thresholdParams.getTargetMultiplier();
        this.lossStreakThreshold = thresholdParams.getLossStreakThreshold();
        this.repeatsAfterBetLoss = thresholdParams.getRepeatsAfterBetLoss();
        this.dataSet = dataSet;
    }

    private void resetVariables() {
        betAmountInput = 0;

        // Param Variables - example of random initialization
        currentAmount = 0;
        betAmount = 1;
        winStreakMap = new HashMap<>();
        lossStreakMap = new HashMap<>();

        totalWins = 0;
        totalLoss = 0;

        maxWinStreak = 0;
        maxLossStreak = 0;

        totalRoundsWon = 0;
        totalRoundsLost = 0;

        totalAmountWon = 0;
        totalAmountLost = 0;

        currentWinStreak = 0;
        currentLossStreak = 0;

        maxAmountLost = 0;
        maxAmountWon = 0;

        maxConcurrentAmountLost = 0;
        currentConcurrentAmountLost= 0 ;

        repeatsAfterBetLossMet = false;
    }

    public AlgoResult testAlgorithm() {
        for(double result : dataSet){
            updateStatistics(result);
            updateConditions();
            updateAmount();
            updateMaxAndMinAmount();
        }

        double outcomePercentage = getPercentageOutcome(totalAmountWon, totalAmountLost);
        outcomePercentage = Math.floor(outcomePercentage * 100) / 100;
        double outcomeValue = totalAmountWon + totalAmountLost;

        return AlgoResult.builder()
                .outcomeValue(outcomeValue)
                .outcomePercentage(outcomePercentage)
                .lossStreakMap(lossStreakMap)
                .winStreakMap(winStreakMap)
                .totalAmountLost(totalAmountLost)
                .totalAmountWon(totalAmountWon)
                .maxAmountWon(maxAmountWon)
                .maxAmountLost(maxAmountLost)
                .maxConcurrentAmountLost(maxConcurrentAmountLost)
                .build();
    }

    private void updateStatistics(double result) {
        if (result >= targetMultiplier) {
            updateStreakMap(lossStreakMap, currentLossStreak);
            totalWins++;
            currentWinStreak++;
            currentLossStreak = 0;
            if (currentWinStreak > maxWinStreak)
                maxWinStreak = currentWinStreak;
        } else {
            updateStreakMap(winStreakMap, currentWinStreak);
            totalLoss++;
            currentLossStreak++;
            currentWinStreak = 0;
            if (currentLossStreak > maxLossStreak)
                maxLossStreak = currentLossStreak;
        }
    }

    private void updateConditions() {
        if (currentLossStreak > lossStreakThreshold + repeatsAfterBetLoss)
            repeatsAfterBetLossMet = true;
        else
            repeatsAfterBetLossMet = false;
    }

    private void updateStreakMap(Map<Integer, Integer> map, int streak) {
        if (streak > 0) {
            if (map.containsKey(streak)) {
                int value = map.get(streak);
                map.put(streak, value + 1);
            } else {
                map.put(streak, 1);
            }
        }
    }

    private void updateAmount() {
        if (betAmountInput > 0) {
            currentAmount -= betAmountInput;
            currentConcurrentAmountLost -= betAmountInput;

            if (currentWinStreak > 0) {
                double amountWon = betAmountInput * targetMultiplier;
                    currentConcurrentAmountLost += amountWon;
                    if(currentConcurrentAmountLost > 0)
                        currentConcurrentAmountLost = 0;

                totalRoundsWon += 1;
                currentAmount += amountWon;
                totalAmountWon += amountWon - betAmountInput;
            } else {
                totalRoundsLost += 1;
                totalAmountLost -= betAmountInput;
            }

            if(currentConcurrentAmountLost < maxConcurrentAmountLost)
                maxConcurrentAmountLost = currentConcurrentAmountLost;
        }
        if (currentLossStreak >= lossStreakThreshold && !repeatsAfterBetLossMet)
            betAmountInput = betAmount;
        else
            betAmountInput = 0;
    }

    private void updateMaxAndMinAmount() {
        if(currentAmount > maxAmountWon)
            maxAmountWon = currentAmount;
        else if(currentAmount < maxAmountLost)
            maxAmountLost = currentAmount;
    }

    private double getPercentageOutcome(double totalAmountWon, double totalAmountLost){
        double ret;
        if(totalAmountLost == 0 && totalAmountWon == 0)
            ret = 0;
        else if(totalAmountLost == 0)
            ret = -1;
        else
            ret = (-totalAmountWon*100 / (totalAmountLost));

        return ret;
    }
}
