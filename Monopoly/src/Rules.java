import javax.swing.*;
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
		public static void generateBoard()
		{
			Main.board.add(new Tile("Start"));
			Main.board.add(new Street("Mediterranian Avenue", null, 60, 2, "Purple", 50));
			Main.board.add(new Tile("Community Chest"));
			Main.board.add(new Street("Baltic Avenue", null, 60, 4, "Purple", 50));
			Main.board.add(new Tax("Income", 200));
			Main.board.add(new Property("Reading Railroad", null, 200, 25));
			/*
			 * George, please finish generating this board.
			 * Google an image of monopoly board if you don't have one.
			 * Every land with color tile on it need to be instantiated as a Street object.
			 * Utilities like water, electricity and railroads need to be a Property object.
			 * Luxury tax need to be a Tax object.
			 * Community chest, chance, Go to jail and free parking need to be a Tile object.
			 * Main.board should have a length of 40.
			 * Check the constructor if you don't know how to instantiate.
			 * Wechat me if there's any problem.
			 * Thank you.
			 */
		}
	}
