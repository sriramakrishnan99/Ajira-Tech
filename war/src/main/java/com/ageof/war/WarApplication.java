package com.ageof.war;

import com.ageof.war.common.*;
import com.ageof.war.exception.NoChanceOfWinning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class WarApplication {

	private static Logger logger = LoggerFactory.getLogger(WarApplication.class);
	public static void main(String[] args)	 {
		ApplicationContext context = SpringApplication.run(WarApplication.class, args);

		BattleInfo battleInfo = context.getBean(BattleInfo.class);
		battleInfo.gatherArmy();

		Battle battle = context.getBean(Battle.class);
		List<Map.Entry<String, Integer>> winningPlacement = null;
		try {
			winningPlacement = battle.getWinningPlacement(battleInfo.ownArmy, battleInfo.opponentArmy);
		} catch(NoChanceOfWinning ex) {
			Utility.printErrorLog(logger, ex.getMessage());
			System.exit(0);
		}

		PrintOutput printOutput = context.getBean(PrintOutput.class);
		printOutput.setOutput(winningPlacement);

		logger.info(printOutput.toString());
	}

}
