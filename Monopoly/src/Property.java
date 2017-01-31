
public class Property extends Tile
	{
		private Player owner;
		private int price;
		private int rent;
	public Property(String n, Player o, int p, int r)
		{
			super(n);
			owner = o;
			price = p;
			rent = r;
		}
	public Player getOwner()
		{
			return owner;
		}
	public void setOwner(Player owner)
		{
			this.owner = owner;
		}
	public int getPrice()
		{
			return price;
		}
	public void setPrice(int price)
		{
			this.price = price;
		}
	public int getRent()
		{
			return rent;
		}
	public void setRent(int rent)
		{
			this.rent = rent;
		}

	}
