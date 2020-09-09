package oop;

public class Skip extends Card{
	
	public Skip()
	{
		super();
	}
	
	public Skip(Card.Colors c)
	{
		super(c, Card.Symbols.SKIP);
	}
	
	@Override
	public void cardAction(Game x)
	{
		x.currPlayer = x.getNextPlayer();
	}
}
