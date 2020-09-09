package oop;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	enum Direction { FORWARDS, BACKWARDS };
	
	ArrayList<Player> players;
	CardList deck;
	CardList pile;
	Card lastCard;
	Direction direction;
	Player currPlayer;
	Card.Colors validColor;
	
	public Game()
	{
		players = new ArrayList<Player>();
		deck = new CardList();
		pile = new CardList();
		lastCard = null;
		currPlayer = null;
		direction = Direction.FORWARDS;
		validColor = Card.Colors.ANY;
	}
	
	
	public static void main(String[] args)
	{
		System.out.println(((2%4) + 4));
		Game game = new Game();
		game.createPlayers();
		game.deck.createDeck();
		game.initializeHandCards();
		//if first card of pile is an action card, it is pushed back to the pile
		//another card is drawn from deck
		while(true)
		{
			game.pile.add(game.deck.remove(0));
			if(game.pile.get(0).symbol.ordinal() > 10)
				game.deck.add(game.pile.remove(0));
			else 
				break;	
		}
		game.currPlayer = game.players.get(0);
		game.lastCard = game.pile.get(game.pile.size()-1);
		game.validColor = game.lastCard.getColor();
		
		System.out.println("Game ready!");
		
		do {
				game.deck.exhaustedDeck(game.pile);
				
				System.out.println(game.currPlayer + ", It's your turn!");
				game.currPlayer.play(game);
				
				game.validColor = game.lastCard.getColor();
				game.lastCard = game.pile.get(game.pile.size()-1);
				game.lastCard.cardAction(game);
				game.currPlayer = game.getNextPlayer();	
			}while(!game.updateGameStatus());
		
		Scanner input = new Scanner(System.in);
		input.close();
	}
	
	public void createPlayers()
	{
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		int num = promptAndValidateInput("How many players? ", 2, 4);
		
		for (int i = 0; i < num; i++)
		{
			System.out.println("Enter name for Player" + (i+1) + ": ");
			String p = input.nextLine();
			players.add(new Player(p));
		}
	}

	public void initializeHandCards()
	{
		for(int i = 0; i < 7; i++)
			for (Player x: players)
				x.drawCard(deck);
	}
	
	//check any player wins or loses the game
	public boolean updateGameStatus()
	{
		int z = currPlayer.getStatus();
		if (z == -1)
		{
			System.out.println(currPlayer + " LOST");	
			players.remove(currPlayer);
			return false;
		}
		else if (z == 1)
		{
			System.out.println(currPlayer + " WIN");
			return true;		
		}
		else
			return false;
	}
	
	  public Player getNextPlayer() 
	  {
	      if (direction == Direction.FORWARDS) 
	    	  return players.get((players.indexOf(currPlayer) + 1) % players.size());
	      else
	    	  return players.get(((players.indexOf(currPlayer) - 1) % players.size() + players.size()) % players.size());
	  }

	  public void reverseDirection() 
	    {
	        if (direction == Direction.FORWARDS) 
	            direction = Direction.BACKWARDS;
	        else
	        	direction = Direction.FORWARDS;
	    }
	    

	//this method will prompt user for integer input
	//check for non-integer input error and out-of-range error
	//the loop stops when valid input is entered
	  public static int promptAndValidateInput(String prompt, int from, int to)
		{
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			int temp = -1;
			
			while(true)
			{
				System.out.println(prompt);
				try 
				{
					temp = Integer.parseInt(input.nextLine());
				}
				catch (NumberFormatException ex) 
				{
					System.out.println("Invalid input!");
					continue;
				}
				
				if (temp < from || temp > to)
				{
					System.out.println("Invalid option!");
					continue;
				}
				break;
					
			}
			return temp;
		}
		

}