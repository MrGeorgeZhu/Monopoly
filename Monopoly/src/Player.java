
public class Player
	{
		private String name;
		private int cash;
		private boolean isInJail;
		private int doubleCount;
		private int cardCount;
		private int position;
		private boolean wentToJail = false;
		public Player(String n, int c, int d)
		{
			name = n;
			cash = c;
			doubleCount = d;
			this.setDoubleCount(0);
			this.setPosition(0);
			this.setCardCount(0);
		}
		public int getCardCount() {
			return cardCount;
		}
		public void setCardCount(int cardCount) {
			this.cardCount = cardCount;
		}
		public void setPosition(int position) {
			this.position = position;
		}
		public String getName()
			{
				return name;
			}
		public void setName(String name)
			{
				this.name = name;
			}
		public int getCash()
			{
				return cash;
			}
		public void setCash(int cash)
			{
				this.cash = cash;
			}
		public int getDoubleCount()
			{
				return doubleCount;
			}
		public void setDoubleCount(int doubleCount)
			{
				this.doubleCount = doubleCount;
			}
		public void rollDice()
		{
			int r1 = (int)(Math.random()*6)+1;
			System.out.println("You rolled a "+r1);
			int r2 = (int)(Math.random()*6)+1;
			System.out.println("You rolled a "+r2);
			if (this.position+r1+r2<Main.board.size())
				this.position += (r1+r2);
			else
				{
				this.position = (this.position+r1+r2)%Main.board.size();
				this.cash = this.cash+200;
				System.out.println("Salary");
				}
			this.doubleCount = (r1==r2)? this.doubleCount++ : 0;
		}
		public void rollDice2()
		{
			int last = this.position;
			int r1 = (int)(Math.random()*6)+1;
			System.out.println("You rolled a "+r1);
			int r2 = (int)(Math.random()*6)+1;
			System.out.println("You rolled a "+r2);
			if (!wentToJail)
					this.position = (this.position+r1+r2)%Main.board.size();
			else
					this.position = (this.position-r1-r2<0)? Main.board.size()+(this.position-r1-r2) : this.position-r1-r2;
			//if ((wentToJail&&this.position > last)||(!wentToJail&&this.position < last))
			if (wentToJail ^ (this.position < last))
				{
					this.cash = this.cash+200;
					System.out.println("Salary");
				}
		}
		public int getPosition() {
			return position;
		}
		public void arrest()
		{
			this.isInJail = true;
			wentToJail = !wentToJail;
			this.setPosition(10);
		}
		public boolean isInJail()
		{
			return isInJail;
		}
		public void release(boolean b)
		{
			if (b)
				this.isInJail=false;
		}
	}
