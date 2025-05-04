package com.ageof.war.common;

import com.ageof.war.exception.NoChanceOfWinning;
import com.ageof.war.platoon.PlatoonImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Battle {
    @Autowired
    private Comparator<PlatoonImp> comparator;
    @Value("${required.win.count}")
    private int requiredWinCount;

    @Value("${strength.multiplier}")
    private int strengthMultiplier;

    public Comparator<PlatoonImp> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<PlatoonImp> comparator) {
        this.comparator = comparator;
    }

    public int getRequiredWinCount() {
        return requiredWinCount;
    }

    public void setRequiredWinCount(int requiredWinCount) {
        this.requiredWinCount = requiredWinCount;
    }

    public int getStrengthMultiplier() {
        return strengthMultiplier;
    }

    public void setStrengthMultiplier(int strengthMultiplier) {
        this.strengthMultiplier = strengthMultiplier;
    }

    public int capableFightCount(PlatoonImp platoon1, PlatoonImp platoon2, int count) {
        int multiplier = comparator.compare(platoon1, platoon2) == 1 ? strengthMultiplier : 1;
        return count * multiplier;
    }

    public boolean hasRequiredWinPossibility(List<Map.Entry<PlatoonImp, Integer>> ownArmy, List<Map.Entry<PlatoonImp, Integer>> opponentArmy){
        PlatoonImp ownPlatoon, opponentPlatoon;
        int ownCount, opponentCount, winCount=0, multiplier;

        for(int i=0; i<opponentArmy.size(); i++){
            opponentPlatoon = opponentArmy.get(i).getKey();
            opponentCount   = opponentArmy.get(i).getValue();

            ownPlatoon = ownArmy.get(i).getKey();
            ownCount   = ownArmy.get(i).getValue();

            int capableFightCountOfOwnPlatoon = capableFightCount(ownPlatoon, opponentPlatoon, ownCount);;
            int capableFightCountOfOpponentPlatoon = capableFightCount(opponentPlatoon, ownPlatoon, opponentCount);

            if(capableFightCountOfOwnPlatoon - capableFightCountOfOpponentPlatoon > 0) {
                winCount++;
            }
        }

        return winCount >= requiredWinCount;
    }

    public Optional<List<Map.Entry<PlatoonImp, Integer>>> getFirstWinningPlacement(List<Map.Entry<PlatoonImp, Integer>> ownArmy, List<Map.Entry<PlatoonImp, Integer>> opponentArmy){
        for(int i=0; i<ownArmy.size(); i++){
            for(int j=0; j<ownArmy.size(); j++){
                if(i == j) continue;
                Collections.swap(ownArmy, i, j);
                if(hasRequiredWinPossibility(ownArmy, opponentArmy)){
                    return Optional.of(ownArmy);
                }
            }
        }
        return Optional.ofNullable(null);
    }

    public List<Map.Entry<String, Integer>> getWinningPlacement(List<Map.Entry<PlatoonImp, Integer>> ownArmy, List<Map.Entry<PlatoonImp, Integer>> opponentArmy) {
        Optional<List<Map.Entry<PlatoonImp, Integer>>> winningPlacement = getFirstWinningPlacement(ownArmy, opponentArmy);
        if(winningPlacement.isPresent()) {
            return winningPlacement
                .get()
                .stream()
                .map(ownArmyEntry -> Map.entry(ownArmyEntry.getKey().getName(), ownArmyEntry.getValue()))
                .collect(Collectors.toList());
        }
        else{
           throw new NoChanceOfWinning();
        }
    }
}
