package com.openclassrooms.cardgame.controller;

import com.openclassrooms.cardgame.games.GameEvaluator;
import com.openclassrooms.cardgame.model.Deck;
import com.openclassrooms.cardgame.model.Player;
import com.openclassrooms.cardgame.model.PlayingCard;
import com.openclassrooms.cardgame.view.CommandLineView;
import com.openclassrooms.cardgame.view.GameViewable;

import java.util.ArrayList;
import java.util.List;

public class GameController {

	enum GameState {
		AddingPLayers, CardsDealt, WinnerRevealed;
	}

	Deck deck;
	List<Player> players;
	Player winner;
	GameViewable view;

	GameState gameState;
	GameEvaluator evaluator;

	public GameController(Deck deck, GameViewable view, GameEvaluator evaluator) {
		super();
		this.deck = deck;
		this.view = view;
		this.players = new ArrayList<Player>();
		this.gameState = GameState.AddingPLayers;
		view.setController(this);
		this.evaluator = evaluator;
	}

	public void run() {
		while (gameState == GameState.AddingPLayers) {
			view.promptForPlayerName();
		}

		switch (gameState) {
			case CardsDealt -> {
				view.promptForFlip();
				break;
			}
			case WinnerRevealed -> {
				view.promptForNewGame();
				break;
			}
		}
	}

	public void addPlayer(String playerName) {
		if (gameState == GameState.AddingPLayers) {
			players.add(new Player(playerName));
			view.showPlayerName(players.size(), playerName);
		}
	}

	public void startGame() {
		if(gameState != GameState.CardsDealt) {
			deck.shuffle();
			int playerIndex = 1;
			for(Player player : players) {
				player.addCardToHand(deck.removeTopCard());
				view.showFaceDownCardForPlayer(playerIndex++, player.getName());
			}
			gameState = GameState.CardsDealt;
		}
		this.run();
	}

	public void flipCards() {
		int playerIndex = 1;
		for(Player player : players) {
			PlayingCard pc = player.getCard(0);
			pc.flip();
			view.showCardForPlayer(playerIndex++, player.getName(),
					pc.getRank().toString(), pc.getSuit().toString());
		}
		evaluateWinner();
		displayWinner();
		rebuildDeck();
		gameState = GameState.WinnerRevealed;
		this.run();
	}

	void evaluateWinner() {
		winner = evaluator.evaluateWinner(players);
	}

	void displayWinner() {
		view.showWinner(winner.getName());
	}

	void rebuildDeck() {
		for (Player player : players) {
			deck.returnCardsToDeck(player.removeCard());
		}
	}

	void exitGame() {
		System.exit(0);
	}

	public void nextAction(String nextChoice) {
		if("+q".equals(nextChoice)) {
			exitGame();
		} else {
			startGame();
		}
	}
}
