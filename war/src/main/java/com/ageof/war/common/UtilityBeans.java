package com.ageof.war.common;

import com.ageof.war.platoon.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class UtilityBeans {

    @Bean("consoleScanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

    @Bean
    public PlatoonClassesDetails getDetails(){
        List<Map.Entry<PlatoonClass, PlatoonImp>> details = new ArrayList<>();
        details.add(
            Map.entry(PlatoonClass.MILITIA,
                     new Militia(
                        PlatoonClass.MILITIA.name(),
                        Set.of(PlatoonClass.SPEARMEN, PlatoonClass.LIGHT_CAVALRY)
                     )
            )
        );
        details.add(
            Map.entry( PlatoonClass.SPEARMEN,
                     new Spearmen(
                        PlatoonClass.SPEARMEN.name(),
                        Set.of(PlatoonClass.LIGHT_CAVALRY, PlatoonClass.HEAVY_CAVALRY)
                     )
            )
        );
        details.add(
            Map.entry( PlatoonClass.LIGHT_CAVALRY,
                     new LightCavalry(
                        PlatoonClass.LIGHT_CAVALRY.name(),
                        Set.of(PlatoonClass.FOOT_ARCHER, PlatoonClass.CAVALRY_ARCHER)
                     )
            )
        );
        details.add(
            Map.entry( PlatoonClass.HEAVY_CAVALRY,
                     new HeavyCavalry(
                        PlatoonClass.HEAVY_CAVALRY.name(),
                        Set.of(PlatoonClass.MILITIA, PlatoonClass.FOOT_ARCHER, PlatoonClass.LIGHT_CAVALRY)
                     )
            )
        );
        details.add(
            Map.entry( PlatoonClass.CAVALRY_ARCHER,
                     new CavalryArcher(
                        PlatoonClass.CAVALRY_ARCHER.name(),
                        Set.of(PlatoonClass.SPEARMEN, PlatoonClass.HEAVY_CAVALRY)
                     )
            )
        );
        details.add(
            Map.entry( PlatoonClass.FOOT_ARCHER,
                     new FootArcher(
                        PlatoonClass.FOOT_ARCHER.name(),
                        Set.of(PlatoonClass.MILITIA, PlatoonClass.CAVALRY_ARCHER)
                     )
            )
        );

        return new PlatoonClassesDetails(details);
    }

    @Bean
    public Comparator<PlatoonImp> getComparator(){
         Comparator<PlatoonImp> comparator = (platoon1, platoon2) -> {
            Optional<PlatoonClass> _platoon = platoon1.getAdvantageOver()
                    .stream()
                    .filter( platoon -> platoon.name().equals(platoon2.getName()) )
                    .findFirst();
            return _platoon.isPresent() ? 1 : 0;
        };
         return comparator;
    }
}
