package oop;

public class Reverse extends Card{
	
	public Reverse()
	{
		super();
	}
	
	public Reverse(Card.Colors c)
	{
		super(c, Card.Symbols.REVERSE);
	}
	
	@Override
	public void cardAction(Game x)
	{
		x.reverseDirection();
	}
}
