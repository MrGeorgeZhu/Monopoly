
public class Street extends Property
	{
		private String color;
		private int houseCount;
		private int houseCost;
		private boolean isInMortage;
	public Street(String n, Player o, int p, int r, String c, int hC)
		{
			super(n, o, p, r);
			color = c;
			houseCount = 0;
			houseCost = hC;
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

	public int getHouseCost()
		{
			return houseCost;
		}

	public void setHouseCost(int houseCost)
		{
			this.houseCost = houseCost;
		}
	public boolean isInMortage() {
		return isInMortage;
	}
	public void setInMortage(boolean isInMortage) {
		this.isInMortage = isInMortage;
	}
	}
