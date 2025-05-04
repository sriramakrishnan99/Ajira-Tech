package com.ageof.war.common;

import com.ageof.war.platoon.PlatoonImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


@Component
public class BattleInfo {
    @Autowired
    private Scanner scanner;

    @Autowired
    private PlatoonClassesDetails platoonClassesDetails;

    public List<Map.Entry<String, Integer>> ownArmyDetails;

    public List<Map.Entry<PlatoonImp, Integer>> ownArmy;
    public List<Map.Entry<String, Integer>> opponentArmyDetails;

    public List<Map.Entry<PlatoonImp, Integer>> opponentArmy;

    public BattleInfo(Scanner scanner) {
        this.scanner = scanner;
    }

    public PlatoonClassesDetails getPlatoonClassesDetails() {
        return platoonClassesDetails;
    }

    public void setPlatoonClassesDetails(PlatoonClassesDetails platoonClassesDetails) {
        this.platoonClassesDetails = platoonClassesDetails;
    }

    public void gatherBattleDetails(){
        ApplicationContext appContext = ApplicationContextProvider.getApplicationContext();
        FetchInput fetchInput = appContext.getBean(FetchInput.class);
        String ownArmyDetails = scanner.nextLine();
        String opponentArmyDetails = scanner.nextLine();
        this.ownArmyDetails      = Utility.convertToMap(ownArmyDetails, fetchInput.getPlatoonDelimitter(), fetchInput.getPlatoonAndItsCountDelimitter());
        this.opponentArmyDetails = Utility.convertToMap(opponentArmyDetails, fetchInput.getPlatoonDelimitter(), fetchInput.getPlatoonAndItsCountDelimitter());
    }

    public List<Map.Entry<PlatoonImp, Integer>> buildArmy(@Autowired List<Map.Entry<String, Integer>> armyDetails){
        List<Map.Entry<PlatoonImp, Integer>>  army = armyDetails
        .stream()
        .map(entry ->
            Map.entry(
                platoonClassesDetails.getDetails()
                    .stream()
                    .filter(_entry -> _entry.getKey().getName().equals(entry.getKey()))
                    .map(_entry -> _entry.getValue())
                    .findFirst()
                    .get(),
                entry.getValue()
            )
        )
        .collect(Collectors.toList());
        return army;
    }

    public void gatherArmy(){
        gatherBattleDetails();
        ownArmy      = buildArmy(ownArmyDetails);
        opponentArmy = buildArmy(opponentArmyDetails);
    }
}
