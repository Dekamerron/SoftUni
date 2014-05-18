import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.sun.org.apache.bcel.internal.generic.GOTO;


public class BlackJack {
	
	public static double playerMoney = 500;
	public static boolean cashOut = false;

	public static void main(String[] args) throws InterruptedException {

		ArrayList<String> cards = new ArrayList<String>();
		
		createDeck(cards);
		
		while(!cashOut){
			
        	playerMoney = deal(cards, playerMoney);
        	
        	if(cards.size() < 104){
            	createDeck(cards);
            }
        	
        	if(playerMoney == 0 ){
            	System.out.println("\n\n\n\n\n\n");
            	System.out.println("\t\t\t\t\t\t\t\t Game Over");
            	System.out.println("\n\n\n\n\n\n\n");
            	break;
            }
            else if(playerMoney == 0.5){
            	System.out.println("\n\n\n\n\n\n");
            	System.out.println("\t\t\t\t\t\t\t  Thank you for the tip!");
            	System.out.println("\t\t\t\t\t\t\t\t Game Over");
            	System.out.println("\n\n\n\n\n\n\n");
            	break;
            }
        }
		
		if(cashOut){
    		System.out.println("\n\n\n\n\n\n");
        	System.out.printf("\t\t\t\t\t Congratulations! You have $%.2f! Have a nice day!", playerMoney);
        	System.out.println("\n\n\n\n\n\n\n\n");
    	}
	}
	
	public static double deal(ArrayList<String> cards, double playerMoney) throws InterruptedException {
		
		System.out.println("Write 'take all' for cash out!");
		System.out.printf("You have: $%.2f \n", playerMoney);
		Scanner in = new Scanner(System.in);
		
		double bet = 0.0;
		
    	while(true) {
    		
    		System.out.print("What is your bet: ");
    		String checker = in.nextLine();
    		
    		if(checker.contains("take all")){
    			cashOut = true;
    			break;
    		}
    		//Check is the input a number.
    		else if(checker.matches("\\d+")){ 
    			
    			bet = Double.parseDouble(checker);
    			bet = (double) Math.round(bet);
    			
    			if(bet == 0) {
    				System.out.println("You must bet!");
    			} else if(bet < 0) {
    				System.out.println("You can't be serious!");
    			} else if(bet > playerMoney) {
    				System.out.println("You don't hava enough money!");
    			}else {
    				break;
    			}
    			
    		} else {
    			continue;
    		} 
		}
    	
    	while(!cashOut){
	    	System.out.printf("You bet: $%.2f \n", bet);
	    	System.out.println();
	    	
			int playerCard1 = randomCard(cards);
			cards.remove(cards.indexOf(playerCard1 + ""));
			int playerCard2 = randomCard(cards);
			cards.remove(cards.indexOf(playerCard2 + ""));
			int playerCount = getCount(cardConverter(playerCard1)) + getCount(cardConverter(playerCard2));
			System.out.printf("You have(%d):  %s  %s \n", playerCount, cardConverter(playerCard1), cardConverter(playerCard2));
			
			int dealerCard1 = randomCard(cards);
			cards.remove(cards.indexOf(dealerCard1 + ""));
			int dealerCard2 = randomCard(cards);
			cards.remove(cards.indexOf(dealerCard2 + ""));
			int dealerCount = getCount(cardConverter(dealerCard1)) + getCount(cardConverter(dealerCard2));
			System.out.printf("Dealer have(%d): %s X \n", getCount(cardConverter(dealerCard1)), cardConverter(dealerCard1));
			
			String playerCards = cardConverter(playerCard1) + " " + cardConverter(playerCard2);
			String playerCardsCopy = cardConverter(playerCard1) + cardConverter(playerCard2);
			
			String dealerCards = " " ;
			String dealerCardsCopy = cardConverter(dealerCard1) + cardConverter(dealerCard2);
	
			while(true){
	
				//Check for Black Jack
				if((playerCards.contains("A") && (playerCards.contains("10") ||
												  playerCards.contains("J")  ||
												  playerCards.contains("K")  ||
												  playerCards.contains("Q")) && playerCount == 21) &&
					(getCount(cardConverter(dealerCard1)) != 10 && getCount(cardConverter(dealerCard1)) != 11) ){
					
					System.out.println("Black Jack! You win! \n");
					playerMoney += bet * 1.5;
					Thread.sleep(3000);
					break;
				}
				else if((playerCards.contains("A") && (playerCards.contains("10") ||
													   playerCards.contains("J")  ||
													   playerCards.contains("K")  ||
													   playerCards.contains("Q")) && playerCount == 21) && 
						(getCount(cardConverter(dealerCard1)) == 10 || getCount(cardConverter(dealerCard1)) == 11) ){
					
					dealerCards = cardConverter(dealerCard1) + " " + cardConverter(dealerCard2);
					if(dealerCount != 21){
						System.out.printf("Dealer have(%d): %s \n", dealerCount, dealerCards);
						System.out.println("Black Jack! You win! \n");
						playerMoney += bet * 1.5;
						Thread.sleep(3000);
						break;
					}
					else if(dealerCount == 21){
						System.out.printf("Dealer have(%d): %s \n", dealerCount, dealerCards);
						System.out.println("Draw!");
						Thread.sleep(3000);
						break;
					}
				}
				
				//Two Aces case
				if(playerCardsCopy.contains("A") && playerCount > 21){
					playerCount -= 10;
					StringBuilder strBuilder = new StringBuilder(playerCardsCopy);
					strBuilder.deleteCharAt(playerCardsCopy.indexOf("A"));
					playerCardsCopy = strBuilder.toString();
				}
				
				System.out.println("Deal or stay? [d/s]");
				String command = in.next();
				
				if(command.equals("d")){
					Thread.sleep(1000);
					dealerCards = cardConverter(dealerCard1) + " X" ;
					
					int playerNextCard = randomCard(cards);
					cards.remove(cards.indexOf(playerNextCard + ""));
					playerCount += getCount(cardConverter(playerNextCard));
					
					playerCards += " " + cardConverter(playerNextCard);
					playerCardsCopy += cardConverter(playerNextCard);
	
					if(playerCardsCopy.contains("A") && playerCount > 21){
							playerCount -= 10;
							StringBuilder strBuilder = new StringBuilder(playerCardsCopy);
							strBuilder.deleteCharAt(playerCardsCopy.indexOf("A"));
							playerCardsCopy = strBuilder.toString();
					}
					
					if(playerCount > 21){
						System.out.printf("You have(%d): %s \n", playerCount, playerCards);
						System.out.printf("Too many!");
						playerMoney-=bet;
						Thread.sleep(2000);
						break;
					}
					else if(playerCount <= 21){
						System.out.printf("You have(%d): %s \n", playerCount, playerCards);
						continue;
					}	
				}
				
				else if(command.equals("s")){
					
					Thread.sleep(1000);
					dealerCards = cardConverter(dealerCard1) + " " + cardConverter(dealerCard2);
					
					if(dealerCardsCopy.contains("A") && dealerCount > 21){
						dealerCount -= 10;
						StringBuilder strBuilder = new StringBuilder(dealerCardsCopy);
						strBuilder.deleteCharAt(dealerCardsCopy.indexOf("A"));
						dealerCardsCopy = strBuilder.toString();
					}
					
					for (int i = 0; i < 1; i++) {
						System.out.printf("You have(%d): %s \n", playerCount, playerCards);
						System.out.printf("Dealer have(%d): %s \n", dealerCount, dealerCards);
						
					}
					
					while(true){
						
						if(dealerCount < 17){
							Thread.sleep(3000);
							int dealerNextCard = randomCard(cards);
							cards.remove(cards.indexOf(dealerNextCard + ""));
							dealerCount += getCount(cardConverter(dealerNextCard));
							
							dealerCards += " " + cardConverter(dealerNextCard);
							dealerCardsCopy += cardConverter(dealerNextCard);
							
							if(dealerCardsCopy.contains("A") && dealerCount > 21){
								dealerCount -= 10;
								StringBuilder strBuilder = new StringBuilder(dealerCardsCopy);
								strBuilder.deleteCharAt(dealerCardsCopy.indexOf("A"));
								dealerCardsCopy = strBuilder.toString();	
							}
							
							System.out.printf("Dealer have(%d): %s \n", dealerCount, dealerCards);
							
							if(dealerCount < 17){
								continue;
							}
							else if(dealerCount > 21){
								System.out.println("You win!");
								playerMoney+=bet;
								break;
							}
							else if(dealerCount >= 17){
								continue;
							}
							Thread.sleep(1000);
						}
						
						if(dealerCount >= 17){
							if(dealerCount > playerCount){
								System.out.println("You lost!");
								playerMoney-=bet;
								break;
							}
							else if(dealerCount == playerCount){
								System.out.println("Draw");
								break;
							}
							else if(dealerCount < playerCount){
								System.out.println("You win!");
								playerMoney+=bet;
								break;
							}
						}
					}
					Thread.sleep(4000);
					break;
				}
			}
			break;
	    }
		System.out.println();
		return playerMoney;
	} //End of deal method
	
	public static void createDeck(ArrayList<String> cards){
		
		cards.removeAll(cards);
		
		for (int i = 1; i < 7; i++) {				//6 decks of cards
			for (int j = 1; j <= 13; j++) {			//13 cards in each deck
				for (int k = 1; k <= 4; k++) {		//4 colors for each type of card
					cards.add(i + "" + j + "" + k);
				}
			}
		}
	}
	
	public static int randomCard(ArrayList<String> cards)
	{
		Random randomGenerator = new Random();
		int value = randomGenerator.nextInt(cards.size());
		int randomCard = Integer.parseInt(cards.get(value));
		return randomCard;
	}
	
	public static String cardConverter(int card)
	{
		String finalCard  = "";
		String typeOfCard = "";
		char typeOfSymbol = ' ';
		
		if(card < 1000)
		{
			int cardType = (card / 10) % 10;
			switch(cardType)
			{
				case 1: typeOfCard = "A"; break;
				case 2: typeOfCard = "2"; break;
				case 3: typeOfCard = "3"; break;
				case 4: typeOfCard = "4"; break;
				case 5: typeOfCard = "5"; break;
				case 6: typeOfCard = "6"; break;
				case 7: typeOfCard = "7"; break;
				case 8: typeOfCard = "8"; break;
				case 9: typeOfCard = "9"; break;
				
			}
		}
		else if(card > 1000)
		{
			int cardType = (card / 10) % 100;
			switch(cardType)
			{
				case 10: typeOfCard = "10"; break;
				case 11: typeOfCard = "J"; break;
				case 12: typeOfCard = "Q"; break;
				case 13: typeOfCard = "K"; break;
				
			}
		}
		
		int symbolType = card % 10;
		switch(symbolType)
		{
			case 1: typeOfSymbol = '\u2660'; break;
	        case 2: typeOfSymbol = '\u2665'; break;
	        case 3: typeOfSymbol = '\u2663'; break;
	        case 4: typeOfSymbol = '\u2666'; break;
		}

		finalCard = typeOfCard + typeOfSymbol;
		return finalCard;
	}
	
	public static int getCount(String card){
		
		int count = 0;
        if (contains(card, "A")){
            count = 11;
        }
        else if (contains(card, "J") || contains(card, "Q") || contains(card, "K")){
            count = 10;
        }
        
        for (int i = 2; i <= 10; i++) {
			if(contains(card, i + "")){
				count = i;
			}
		}
      
        return count;
	}
	public static boolean contains(String card, String input){

    	if(card.contains(input)){
    		return true;
    	} else {
    		return false;
    	}
    }

}