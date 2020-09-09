package oop;

public class Wild extends Card{
	
	public Wild()
	{
		super();
	}
	
	public Wild(Card.Colors c)
	{
		super(c, Card.Symbols.WILD);
	}
	
	@Override
	public void cardAction(Game x)
	{
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
