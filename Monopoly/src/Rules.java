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
		public static void generateCo14()
		{
			generateBoard();
			Main.board.get(0).setName("Trailhead");
			Main.board.get(1).setName("Mount Sherman");
			Main.board.get(2).setName("Trail Fortune");
			Main.board.get(3).setName("Quandary Peak");
			Main.board.get(4).setName("Initiative");
			Main.board.get(5).setName("Little Bear Blanca");
			Main.board.get(6).setName("Mount Berstadt");
			Main.board.get(7).setName("Adventure");
			Main.board.get(8).setName("Torrey Peak");
			Main.board.get(9).setName("Gray's Peak");
			Main.board.get(10).setName("Base camp");
			Main.board.get(11).setName("Mt. Yale");
			Main.board.get(12).setName("Pikes Peak");
			Main.board.get(13).setName("Mt. Princeton");
			Main.board.get(14).setName("Mt. Harvard");
			Main.board.get(15).setName("Crestone");
			Main.board.get(16).setName("Humboldt Peak");
			Main.board.get(17).setName("Trail Fortune");
			Main.board.get(18).setName("Mt. Lindsey");
			Main.board.get(19).setName("Kit Karsen Peak");
			Main.board.get(20).setName("Scenetic Viewpoint");
			Main.board.get(21).setName("Mt. Sneffels");
			Main.board.get(22).setName("Adventure");
			Main.board.get(23).setName("Uncompahgre Peak");
			Main.board.get(24).setName("Mt. Eolus");
			Main.board.get(25).setName("Wilson El-Diente");
			Main.board.get(26).setName("Mount of the holy Cross");
			Main.board.get(27).setName("Mt. Massive");
			Main.board.get(28).setName("Mt. Evans");
			Main.board.get(29).setName("Mt. Elbert");
			Main.board.get(30).setName("Go to Basecamp");
			Main.board.get(31).setName("Castle Peak");
			Main.board.get(32).setName("Snowmass Mountain");
			Main.board.get(33).setName("Trail Fortune");
			Main.board.get(34).setName("Pyramid Peak");
			Main.board.get(35).setName("Maroon Bells");
			Main.board.get(36).setName("Adventure");
			Main.board.get(37).setName("Longs Peak");
			Main.board.get(38).setName("Lost Boot");
			Main.board.get(39).setName("Capitol Peak");
		}
	}
