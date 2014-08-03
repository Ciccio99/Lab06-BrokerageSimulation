/**
 * File:
 * $Id: Stock.java,v 1.2 2014/04/08 17:02:31 afs2842 Exp $
 * 
 * Log: $Log: Stock.java,v $
 * Log: Revision 1.2  2014/04/08 17:02:31  afs2842
 * Log: Lab06 has been completed. Beep Boop Bop...
 * Log:
 * Log: Revision 1.1  2014/04/08 16:32:59  afs2842
 * Log: Project needs to be commented
 * Log:
 */

/**
 * @author Alberto Scicali
 *
 */
public class Stock implements Comparable<Stock>{
	private String ticker;
	private int shares;
	private int stockValue;
	private int sharesValue;
	
	/**
	 * Constructor for Stock class object
	 * @param ticker
	 * @param currValue
	 */
	public Stock(String ticker, int currValue){
		this.ticker = ticker;
		this.shares = 0;
		this.stockValue = currValue;
		this.sharesValue = 0;
	}
	
	/**
	 * returns quantity of shares held
	 * @return shares quantity
	 */
	public int getShares(){
		return this.shares;
	}
	/**
	 * 
	 * @return stock's ticker
	 */
	public String getTicker(){
		return this.ticker;
	}
	/**
	 * Updates the stock's current value and the total shares value
	 * @param newValue
	 */
	public void updateStockValue(int newValue){
		stockValue = newValue;
		sharesValue = stockValue * shares;
	}
	/**
	 * Increases holdings of share
	 * @param quantity
	 * @return the value of shares bought
	 */
	public int buyShares(int quantity){
		shares += quantity;
		sharesValue = shares*stockValue;
		return quantity*stockValue;
	}
	/**
	 * 
	 * @return values of all shares held of this stock
	 */
	public int getSharesValue(){
		return sharesValue;
	}
	/**
	 * Sells a given amount of shares
	 * @param quantity
	 * @return the values of the shares sold
	 */
	public int sellShares(int quantity){
		if(quantity <= shares)
			shares -= quantity;
		sharesValue = shares*stockValue;
		
		return quantity*stockValue;
	}
	/**
	 * 
	 * @return the current value of the stock.
	 */
	public int getCurrStockValue(){
		return stockValue;
	}

	@Override
	/**
	 * Changes the natural sort compare based on a stock's ticker String.
	 */
	public int compareTo(Stock stock2) {
		// TODO Auto-generated method stub
		return this.ticker.compareTo(stock2.getTicker());
	}
	
	

}
