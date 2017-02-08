import javax.swing.*;
import java.util.Scanner;
import java.io.*;
public class Rules
	{
		public static void buyProperty (Property house, Player player)
		{
			int buy = 1;
			Object choice[] = {"Buy it", "No"};
			if (house.getOwner() == null && player.getCash()>=house.getPrice())
				{
					buy = JOptionPane.showOptionDialog(Tax.frame, "Do you want to buy the property for $"+house.getPrice()+"?", "Choose wisely", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
				}
			if (buy == 0){ house.setOwner(player);
			System.out.println(player.getName()+" paid $"+house.getPrice());
			player.setCash(player.getCash()-house.getPrice());}
		}
		public static void buyHouse (Street land, Player player)
		{
			boolean ownsAll = false;
			for (int i = 0; i < Main.board.size(); i++)
				{
					if (Main.board.get(i) instanceof Street)
						{
							Street s = (Street) Main.board.get(i);
							if (s.getColor().equals(land.getColor()))
								ownsAll = s.getOwner() == player && Math.abs(s.getHouseCount()-land.getHouseCount())<1 && land.getHouseCount()<5;
						}
				}
			if (ownsAll&&player.getCash()>=land.getHouseCost()&&land.getOwner()==player)
				{
					int buy = 1;
					Object choice[] = {"Buy it", "No"};
					buy = JOptionPane.showOptionDialog(Tax.frame, "Do you want to add a house for $"+land.getHouseCost()+"?", "Choose wisely", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
					if (buy == 0){
						player.setCash(player.getCash()-land.getHouseCost());
						System.out.println(player.getName()+" paid $"+land.getHouseCost());
						land.setHouseCount(land.getHouseCount()+1);}
				}
		}
		public static void sellHouse(Street land, Player player)
		{
			if (land.getOwner()!=player||land.getHouseCount()<1)
			{
				System.out.println("Can't do");
			}
			else 
			{
				System.out.println(player.getName()+" earned $"+land.getHouseCost());
				land.setHouseCount(land.getHouseCount()-1);
				player.setCash(player.getCash()+land.getHouseCost());
			}
		}
		public static void Mortgage (Property property, Player player)
		{
			if (property.isInMortgage())
				System.out.println("Can't do");
			else
			{
				property.setInMortgage(true);
				System.out.println(player.getName()+" earned $"+property.getPrice()/2);
				player.setCash(player.getCash()+property.getPrice()/2);
			}
		}
		public static void payMortgage (Property property, Player player)
		{
			if (!property.isInMortgage())
				{
					System.out.println("Can't do");
				}
			else
				{
			System.out.println(player.getName()+" paid $"+property.getPrice()/2);
			property.setInMortgage(false);
			player.setCash(player.getCash()-property.getPrice()/2);
				}
		}
		public static void chance(Tile t, Player player)
		{
			if ((t.getName().equals("Community Chest")||t.getName().equals("Chance")&&(int)(Math.random()*5)==0))
					{
				player.setCardCount(player.getCardCount()+1);
				System.out.println("You got an card.");
					}
		}
		public static void generateBoard()
		{
			Main.board.add(new Tile("Start"));
			Main.board.add(new Street("Mediterranian Avenue", null, 60, 2, "Brown", 50));
			Main.board.add(new Tile("Community Chest"));
			Main.board.add(new Street("Baltic Avenue", null, 60, 4, "Brown", 50));
			Main.board.add(new Tax("Income tax", 200));
			Main.board.add(new Property("Reading Railroad", null, 200, 25));
			Main.board.add(new Street("Oriental Avenue", null, 100, 6,"Light Blue", 50));
			Main.board.add(new Tile("Chance"));
			Main.board.add(new Street("Vermont avenue", null, 100, 6, "Light Blue", 50));
			Main.board.add(new Street("Connecticut Avenue", null, 120, 6, "Light Blue", 50));
			Main.board.add(new Tile("Jail"));
			Main.board.add(new Street("St. Charles Place", null, 140, 6, "Purple", 50));
			Main.board.add(new Property("Electric Company", null, 140, 25));
			Main.board.add(new Street("State Avenue", null, 140, 6, "Purple", 50));
			Main.board.add(new Street("Virginia Avenue", null, 160, 6, "Purple", 50));
			Main.board.add(new Property("Pennsylvania Railroad", null, 200, 25));
			Main.board.add(new Street("St. James Place", null, 180, 6, "Orange", 50));
			Main.board.add(new Tile("Community Chest"));
			Main.board.add(new Street("Tennessee Avenue", null, 180, 6, "Orange", 50));
			Main.board.add(new Street("New York Avenue", null, 200, 6, "Orange", 50));
			Main.board.add(new Tile("Free Parking"));
			Main.board.add(new Street("Kentuky Avenue", null, 220, 6, "Red", 50));
			Main.board.add(new Tile("Chance"));
			Main.board.add(new Street("Indiana Avenue", null, 220, 6, "Red", 50));
			Main.board.add(new Street("Illinois Avenue", null, 240, 6, "Red", 50));
			Main.board.add(new Property("B.&O. Railroad", null, 200, 25));
			Main.board.add(new Street("Atlantic Avenue", null, 260, 6, "Yellow", 50));
			Main.board.add(new Street("Ventnor Avenue", null, 260, 6, "Yellow", 50));
			Main.board.add(new Property("Water Works", null, 150, 25));
			Main.board.add(new Tile("Go to jail!"));
			Main.board.add(new Street("Pacific Avenue", null, 300, 6, "Green", 50));
			Main.board.add(new Street("North Carolina Avenue", null, 300, 6, "Green", 50));
			Main.board.add(new Tile("Community Chest"));
			Main.board.add(new Street("Pensylvania Avenue", null, 300, 6, "Green", 50));
			Main.board.add(new Property("Short Line", null, 200, 25));
			Main.board.add(new Street("Park Place", null, 350, 6, "Dark Blue", 50));
			Main.board.add(new Tax("Luxury tax", 100));
			Main.board.add(new Street("Boardwalk", null, 400, 6, "Dark Blue", 50));
		}
		public static void generateCo14() throws IOException
		{
			generateBoard();
			Scanner file = new Scanner(new File ("co14er.txt"));
			for (int i = 0; i < Main.board.size(); i++)
				{
					Main.board.get(i).setName(file.nextLine());
				}
		}
	}
