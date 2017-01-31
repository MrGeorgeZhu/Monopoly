
public class Street extends Property
	{
		private String color;
		private int houseCount;
		
	public Street(String n, Player o, int p, int r, String c, int h)
		{
			super(n, o, p, r);
			color = c;
			houseCount = h;
		}

	public String getColor()
		{
			return color;
		}

	public void setColor(String color)
		{
			this.color = color;
		}

	public int getHouseCount()
		{
			return houseCount;
		}

	public void setHouseCount(int houseCount)
		{
			this.houseCount = houseCount;
		}

	}
