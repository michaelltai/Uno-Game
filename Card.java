package oop;

public class Card implements Comparable<Card>{
	
	enum Colors {
		RED, GREEN, BLUE, YELLOW, ANY;
	}

	enum Symbols {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, 
		REVERSE, SKIP, DRAWTWO, WILD, WILDDRAWFOUR;
	}

	protected Colors color;
	protected Symbols symbol;
	
	public Card()
	{
		this.setColor(null);
		this.setSymbol(null);
	}
	
	public Card(Colors color, Symbols symbol)
	{
		this.setColor(color);
		this.setSymbol(symbol);
	}
	
	public Colors getColor()
	{
		return color;
	}
	
	public Symbols getSymbol()
	{
		return symbol;
	}
	
	public void setColor(Colors color)
	{
		this.color = color;
	}
	
	public void setSymbol(Symbols symbol)
	{
		this.symbol = symbol;
	}
	
	@Override
	public int compareTo(Card x)
	{
		//sort by color then by symbol
		if (color.ordinal() < x.color.ordinal())
			return -1;
		else if (color.ordinal() > x.color.ordinal())
			return 1;
		else
		{
			if (symbol.ordinal() < x.symbol.ordinal())
				return -1;
			else
				return 1;
		}
	}
	
	
	@Override
	public boolean equals(Object x)
	{
		//return true when either color or symbol matches
		if (x instanceof Card)
		return (color == ((Card)x).color || symbol == ((Card)x).symbol);
		else
			return false;
	}
	
	//check if hand card can play against card x
	public boolean playable(Card x, Card.Colors validColor)
	{
		//when last card on pile is not WILD or WILDDRAWFOUR
		//any card is playable when it matches the last card by color or symbol
		if(x.getSymbol() != Card.Symbols.WILD && x.getSymbol() != Card.Symbols.WILDDRAWFOUR)
		{
			if(getSymbol().ordinal() < 13)
				return (equals(x));
			else
				return true;
		}
		//when last card on pile is WILD or WILDDRAWFOUR
		//only cards that match the color set by the previous player is playable
		else
			return getColor() == validColor;
		
	}
	
	@Override
	public String toString()
	{
		return (color.toString() + " " + symbol.toString());
	}
	
	public void cardAction(Game x)
	{
		
	}

}
