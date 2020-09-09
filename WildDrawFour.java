package oop;

public class WildDrawFour extends Card{
	
	public WildDrawFour()
	{
		super();
	}
	
	public WildDrawFour(Card.Colors c)
	{
		super(c, Card.Symbols.WILDDRAWFOUR);
	}
	
	@Override
	public void cardAction(Game x)
	{
		
		Player challenged = x.currPlayer;
		Player challenger = x.getNextPlayer();
		
		int challenge = Game.promptAndValidateInput(challenger + ". Do you want to challenge? <1> YES, <2> NO ", 1, 2);
		
		//if next player does not want to challenge the current player who plays WILDDRAWFOUR
		//next player will draws 4 cards then his turn is skipped
		if (challenge == 2)
		{
			for (int i = 0; i < 4; i++)
				challenger.drawCard(x.deck);
			x.currPlayer = x.getNextPlayer();
		}
		//if next players wants to challenge
		else
		{
			//current player's hand cards are checked for playable cards
			//if found, current player draws the four cards instead
			//next player takes no card and plays normally
			//last card changes to the previous card
			Card prev = x.pile.get(x.pile.size()-2);
			if (challenged.playableCard(prev, x.validColor))
			{
				System.out.println("Challenge succeeds!");
				for (int i = 0; i < 4; i++)
					challenged.drawCard(x.deck);
				x.lastCard = prev;
				return;	
			}
			
			//if not found, the challenger takes 6 cards instead and loses a turn
			else
			{
				System.out.println("Challenge fails!");
				for (int i = 0; i < 6; i++)
					challenger.drawCard(x.deck);
				x.currPlayer = x.getNextPlayer();
				
			}
		}
		
		//current player sets the color for wild card if challenge fails or does not take place
		int color = Game.promptAndValidateInput("<1>RED, <2>GREEN, <3>BLUE, <4>YELLOW? ", 1, 4);
		
		switch(color)
		{
		case 1:
			x.validColor = Card.Colors.RED;
			break;
		case 2:
			x.validColor = Card.Colors.GREEN;
			break;
		case 3:
			x.validColor = Card.Colors.BLUE;
			break;
		case 4:
			x.validColor = Card.Colors.YELLOW;
			break;
		}
	}
}
