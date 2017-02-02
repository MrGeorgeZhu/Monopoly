import java.util.*;
import javax.swing.*;
public class Main
	{
		static Scanner userInput = new Scanner (System.in);
		static ArrayList <Tile> board = new ArrayList <Tile>();
		static Object[] options = {"Roll", "Check Properties", "Sell House"};
		public static void main(String[]args)
		{
			Rules.generateBoard();
			System.out.print("Enter character name: ");
			String name = userInput.nextLine();
			System.out.println();
			Player player = new Player(name, 1500, 0);
			while(player.getCash()>=0)
			{
				int choice = JOptionPane.showOptionDialog(Tax.frame, "Choose action", "Monopoly", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				switch (choice)
				{
				case 0: {player.rollDice(); break;}
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
				}
				Tile t = board.get(player.getPosition());
				System.out.println(player.getName()+" landed on "+t.getName()+" with $"+player.getCash());
				if (t instanceof Property)
					Rules.buyProperty((Property) t, player);
				else if (t instanceof Tax)
					((Tax)t).payTax(player);
			}
		}
		public static ArrayList <Property> printProperty(Player player)
		{
			ArrayList <Property> list = new ArrayList <Property>();
			for (Tile t: board)
			{
				if (t instanceof Property)
				{
					if (((Property) t).getOwner()==player)
					{
						System.out.println(((Property) t).getName()+((Property) t).getPrice()+((Property) t).getRent()+((Property) t).isInMortage());
						list.add((Property) t);
					}
				}
			}
			return list;
		}
	}
