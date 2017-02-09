import java.util.*;
import javax.swing.*;
import java.io.IOException;
public class Main
	{
		static int turn = 0;
		static Scanner userInput = new Scanner (System.in);
		static ArrayList <Tile> board = new ArrayList <Tile>();
		static Object[] options = {"Roll", "Check Properties", "Sell House", "Mortgage Property", "Buy Mortgage", "I'm bored."};
		public static void main(String[]args) throws IOException
		{
			Object[] versions = {"Normal", "Co14er"};
			int ver = JOptionPane.showOptionDialog(Tax.frame, "Choose the version", "Versions", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, versions, versions[0]);
			if (ver==0)
			Rules.generateBoard();
			else
				Rules.generateCo14();
			System.out.print("Enter character name: ");
			String name = userInput.nextLine();
			System.out.println();
			Player player = new Player(name, 1500, 0);
			while(player.getCash()>=0)
			{
				if (player.isInJail()&&player.getCardCount()>0&&turn>0)
					{
						player.setCardCount(player.getCardCount()-1);
						System.out.println("Out.");
						player.release(true);
					}
				else if (player.isInJail()&&turn>0)
					{
						System.out.println("Wait.");
						turn--;
					}
				else if (player.isInJail()&&turn==0)
					{
						int r1 = 0, r2 = 0;
						Object [] out = {"Pay $50", "Roll"};
						int choice = JOptionPane.showOptionDialog(Tax.frame, "Choose your action", "In Jail", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, out, out[0]);
						if (choice == 0)
							player.setCash(player.getCash()-50);
						else
							{
							r1 = (int)(Math.random()*6)+1; System.out.println("You rolled a "+r1);
							r2 = (int)(Math.random()*6)+1; System.out.println("You rolled a "+r2);
							System.out.println("Rolling");
							}
						player.release(r1==r2||choice==0);
					}
				else
					{
				int choice = JOptionPane.showOptionDialog(Tax.frame, "Choose action", "Monopoly", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (choice)
				{
				case 0: {player.rollDice2(); break;}
				case 1: 
				{
					printProperty(player);
					break;
				}
				case 2:
				{
					ArrayList <Property> properties = printProperty(player);
					ArrayList <Street> streets = new ArrayList <Street>();
					for (Property p: properties)
					{
						if (p instanceof Street)
						{
							System.out.println(p.getName()+"  "+p.getRent()+"  "+((Street)p).getHouseCount()+"   "+((Street)p).getHouseCost());
							streets.add((Street)p);
						}
					}
					System.out.println("Choose the one you want to sell.");
					int a = userInput.nextInt();
					Rules.sellHouse(streets.get(a-1), player);
					break;
				}
				case 3:
						{
					ArrayList <Property> properties = printProperty(player);
					System.out.println("Choose the one you want to mortgage.");
					int a = userInput.nextInt();
					Rules.Mortgage(properties.get(a-1), player);
					break;
						}
				case 4:
						{
					ArrayList <Property> properties = printProperty(player);
					System.out.println("Choose the one you want to buy back.");
					int a = userInput.nextInt();
					Rules.payMortgage(properties.get(a-1), player);
					break;
						}
				default :
				{
					System.out.println("Well, let's put you in somewhere fun.");
					player.arrest();
					break;
				}
				}
				Tile t = board.get(player.getPosition());
				Rules.chance(t, player);
				System.out.println(player.getName()+" landed on "+t.getName()+" with $"+player.getCash());
				if (t instanceof Street)
					Rules.buyHouse((Street) t, player);
				if (t instanceof Property)
					Rules.buyProperty((Property) t, player);
				else if (t instanceof Tax)
					((Tax)t).payTax(player);
				if (player.getDoubleCount()>=3||player.getPosition()==29)
					{
						JOptionPane.showMessageDialog(Tax.frame, "You are in big trouble, son.");
						player.arrest(); turn = 3;
					}
					}
			}
			JOptionPane.showMessageDialog(Tax.frame, "GG.");
		}
		public static ArrayList <Property> printProperty(Player player)
		{
			int i = 0;
			ArrayList <Property> list = new ArrayList <Property>();
			System.out.println();
			System.out.printf("%-5s","Num");
			System.out.printf("%-20s","Name");
			System.out.printf("%-10s","Price");
			System.out.printf("%-10s","Rent");
			System.out.printf("%-10s","MortStat");
			System.out.println();
			for (Tile t: board)
			{
				if (t instanceof Property)
				{
					if (((Property) t).getOwner()==player)
					{
						i++;
						System.out.printf("%-5s",i+".");
						System.out.printf("%-20s",((Property)t).getName());
						System.out.printf("%-10d",((Property)t).getPrice());
						System.out.printf("%-10d",((Property)t).getRent());
						System.out.printf("%-10b",((Property)t).isInMortgage());
						System.out.println();
						//System.out.println(((Property) t).getName()+((Property) t).getPrice()+((Property) t).getRent()+((Property) t).isInMortgage());
						list.add((Property) t);
					}
				}
			}
			System.out.println();
			return list;
		}
	}
