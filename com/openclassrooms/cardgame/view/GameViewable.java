package com.openclassrooms.cardgame.view;

import com.openclassrooms.cardgame.controller.GameController;

public interface GameViewable {
	public void setController(GameController gc);
	public void promptForPlayerName();
	public void promptForFlip();
	public void promptForNewGame();
	public void showWinner(String playerName);
	public void showPlayerName(int player, String playerName);
	public void showFaceDownCardForPlayer(int i, String playerName);
	public void showCardForPlayer(int i, String PlayerName, String rank, String suit);
}
