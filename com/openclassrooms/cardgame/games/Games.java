package com.openclassrooms.cardgame.games;

import com.openclassrooms.cardgame.controller.GameController;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.DeckFactory;
import com.openclassrooms.cardgame.view.CommandLineView;
import com.openclassrooms.cardgame.view.GameSwingView;

public class Games {
	public static void main(String[] args) {
		GameSwingView gsv = new GameSwingView();
		gsv.createAndShowGUI();

		//GameController gc = new GameController(new Deck(), new View(), new HighCardGameEvaluator());
		GameController gc = new GameController(DeckFactory.makeDeck(DeckFactory.DeckType.NORMAL), gsv, new LowCardGameEvaluator());
		gc.run();
	}
}
