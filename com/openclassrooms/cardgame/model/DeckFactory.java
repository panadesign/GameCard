package com.openclassrooms.cardgame.model;

public class DeckFactory {
	public enum DeckType {
		NORMAL, //52 CARDS
		SMALL, //32 CARDS
		TEST
	}

	public static Deck makeDeck(DeckType type) {
		switch(type) {
			case NORMAL : return new NormalDeck();
			case SMALL :return new SmallDeck();
			case TEST : return new TestDeck();
		}
		return new NormalDeck();
	}

}
