package oop;

public class DrawTwo extends Card{

	public DrawTwo()
	{
		super();
	}
	
	public DrawTwo(Card.Colors c)
	{
		super(c, Card.Symbols.DRAWTWO);
	}
	@Override
	public void cardAction(Game x)
	{
		//next player takes his turn and draws two cards
		//his turn is then skipped later
		x.currPlayer = x.getNextPlayer();
		for (int i = 0; i < 2; i++)
			x.currPlayer.drawCard(x.deck);
	}
	
}
