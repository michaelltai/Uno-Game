package oop;

public class Player {

	private String name;
	private CardList handCards;
	private int status; //0-normal, 1-won the game, -1-lost the game (hand cards exceeds limit) 
	
	public Player() 
	{
		setName("");
		handCards = new CardList();
		setStatus(0);
	}
	
	public Player(String name) 
	{
		setName(name);
		handCards = new CardList();
		setStatus(0);
	}	
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setStatus(int status)
	{
		this.status = status;
	}
	
	public int getStatus()
	{
		return status;
	}
	
	public void updateStatus()
	{
		if (handCards.size() > 12)
			setStatus(-1);	
		if (handCards.isEmpty())
			setStatus(1);
	}
	
	@Override
	public String toString()
	{
		return "Player " + getName();
	}
	
	//draw card from deck and add into hand cards
	public void drawCard(CardList deck)
	{
		handCards.add(deck.remove(0));
	}

	
	//discard card to pile
	public boolean discardCard(CardList pile, Card lastCard, Card.Colors validColor)
	{
		int which = Game.promptAndValidateInput("Which card to discard? Enter the number: ", 1, handCards.size());
		//check if the card to discard matches the last card on pile
			if (handCards.get(which-1).playable(lastCard, validColor))
			{
				pile.add(handCards.remove(which-1));
				return true;		
			}
			else
			{
				System.out.println("This card is not playable.");
				return false;			
			}
	}
	
	//player prompted to take turn and play
	public void play(Game x)
	{
		while(true)
		{
			//reset deck if the deck is exhausted
			x.deck.exhaustedDeck(x.pile);
			
			System.out.println("Last Card is " + x.lastCard);
			if (x.lastCard.color == Card.Colors.ANY)
				System.out.println("Valid Color is " + x.validColor);
			handCards.displayCard();
			
			//warning displayed for not having playable cards 
			if (!playableCard(x.lastCard, x.validColor))
				System.out.println("No playable cards!");
			System.out.println("");
			
			int which = Game.promptAndValidateInput("<1>Draw, <2>Discard? ", 1, 2);
			
			if (which == 1)
				drawCard(x.deck);
			else if (which == 2)
				//exit loop only if discarding is successful 
				if(discardCard(x.pile, x.lastCard, x.validColor))
					break;		
		}
		updateStatus();
	}

	//check if the player's hand cards for any playable card
	public boolean playableCard(Card x, Card.Colors validColor)
	{
		for (Card z: handCards)
			if (z.playable(x, validColor))
				return true;
		return false;
	}
	
}
