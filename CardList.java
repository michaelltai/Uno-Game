package oop;

import java.util.ArrayList;
import java.util.Collections;

@SuppressWarnings("serial")
public class CardList extends ArrayList<Card>
{
	public void createDeck()
	{
		int wildCnt = 0, drawFourCnt = 0;
		for (Card.Colors c : Card.Colors.values())
		{
			if (c == Card.Colors.ANY)
				break;
			for (Card.Symbols s : Card.Symbols.values())
			{
				if(s.ordinal() < 10)
					add(new Card(c, s));
				else if (s == Card.Symbols.REVERSE)
					add(new Reverse(c));
				else if (s == Card.Symbols.SKIP)
					add(new Skip(c));
				else if (s == Card.Symbols.DRAWTWO)
					add(new DrawTwo(c));
				else if (s == Card.Symbols.WILD)
				{
					if (wildCnt++ < 2)
						add(new Wild(Card.Colors.ANY));
				}
				else if (s == Card.Symbols.WILDDRAWFOUR)
				{
					if (drawFourCnt++ < 2)
						add(new WildDrawFour(Card.Colors.ANY));	
				}
				else;		
			}				
		}
		Collections.shuffle(this);
	}
	

	//if deck is exhausted, cards from pile are passed to deck 
	//top card in pile remains in pile
	//deck is then shuffled
	public void exhaustedDeck(CardList pile)
	{
		if (isEmpty())
		{
			addAll(pile);
			pile.clear();
			pile.add(remove(size()-1));
			Collections.shuffle(this);	
		}
	}
	
	
	public void displayCard()
	{
		Collections.sort(this);
		int i = 1;
		for (Card x: this)
			System.out.print(i++ + "~ " + x + "\n");
	}
	
}