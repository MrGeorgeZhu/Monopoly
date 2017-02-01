import javax.swing.*;
public class Tax extends Tile
	{
		private int amount;
		public Tax(String n, int a) 
		{
			super(n);
			amount = a;
		}
		public int getAmount() {
			return amount;
		}
		public void payTax(Player taxpayer)
		{
			Object[] choice = {"10%", "75 dollar"};
			JFrame frame = new JFrame();
			if (super.getName().equals("Income"))
			{
				int option = JOptionPane.showOptionDialog(frame, "Pay ur tax", "Choose wisely", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choice, choice[0]);
				if (option==0) taxpayer.setCash(taxpayer.getCash()*9/10);
				else taxpayer.setCash(taxpayer.getCash()-this.amount);
			}
			else taxpayer.setCash(taxpayer.getCash()-this.amount);
		}
	}
