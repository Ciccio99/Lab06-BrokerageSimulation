/**
 * File:
 *   $Id: Brokerage.java,v 1.2 2014/04/08 17:02:31 afs2842 Exp $
 *   
 * Revisions:
 *   $Log: Brokerage.java,v $
 *   Revision 1.2  2014/04/08 17:02:31  afs2842
 *   Lab06 has been completed. Beep Boop Bop...
 *
 *   Revision 1.1  2014/04/08 16:32:55  afs2842
 *   Project needs to be commented
 *
 */

import java.util.*;

/**
 * Implementation of the Brokerage class.  In this simplified simulation
 * the brokerage will manage a single client's investments.  It will
 * also track the movement of the market as a whole.
 * 
 * @author atd: Aaron T Deever
 * @author Alberto Scicali
 *
 */
public class Brokerage {
    
    /* Map containing stocks available and their current price per share.
     */
    private Map<String, Integer> market = 
            new HashMap<String, Integer>();
    // TreeMap holdings the investor's stocks & Investor's current total cash
    private TreeMap<String, Stock> holdings;
    private int totalCash;
    
    /**
     * Constructor.  Initializes the investor and the market as a whole.
     * In this simplified simulation there is just a single investor and the
     * whole market is tracked by the brokerage.
     * @param initialInvestment initial investment
     */
    public Brokerage(int initialInvestment) { 
    	holdings = new TreeMap<String, Stock>();
    	totalCash = initialInvestment;
        /* initialize the market */
        market.put("GOOG", 1183);
        market.put("AMZN", 360);
        market.put("AAPL", 532);
        market.put("YHOO", 38);
        market.put("MSFT", 40);
        market.put("EBAY", 57);
    }
    
    /**
     * Add to Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, the number of shares requested
     * is a positive value, and that the client has sufficient funds.
     * @param tickerSymbol the particular stock to buy
     * @param shares the number of shares requested
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean increaseHolding(String tickerSymbol, int shares) { 
        if(shares < 1 || !market.containsKey(tickerSymbol) || totalCash < market.get(tickerSymbol)*shares)
        	return false;
        if(!holdings.containsKey(tickerSymbol)){
        	holdings.put(tickerSymbol, new Stock(tickerSymbol, market.get(tickerSymbol)));
        }
        totalCash -= holdings.get(tickerSymbol).buyShares(shares);
        return true;
    }
    
    /**
     * Reduce Investor's holding.  This function should error-check to 
     * ensure the ticker symbol exists, and the number of shares to reduce
     * is a positive value no greater than the number currently held.
     * @param tickerSymbol the particular stock to sell
     * @param shares the number of shares to sell
     * @return true if transaction is completed.  False otherwise.
     */
    public boolean reduceHolding(String tickerSymbol, int shares) { 
        if(!market.containsKey(tickerSymbol) || !holdings.containsKey(tickerSymbol) || holdings.get(tickerSymbol).getShares() < shares || shares < 1)
        	return false;
        totalCash += holdings.get(tickerSymbol).sellShares(shares);
        if(holdings.get(tickerSymbol).getShares() == 0)
        	holdings.remove(tickerSymbol);
        return true;
    }
    
    /**
     * Generates a string to represent the investor's portfolio.  Can be
     * requested in alphabetical order, or in decreasing order of the
     * value of the holdings (shares * price per share). 
     * @param choice "N" for by name, "V" for by value
     * @return String representing the portfolio.  This string must
     * include the name, number of shares, price per share, and total 
     * value for each stock in the portfolio.  The entries must be
     * sorted according to the input request.
     */
    public String accessPortfolio(String choice) { 
        ArrayList<Stock> myStocks = new ArrayList<Stock>();
        for(String key : holdings.keySet()){
        	myStocks.add(holdings.get(key));
        }
        if(choice.equalsIgnoreCase("v"))
        	// Sort by values
        	Collections.sort(myStocks, new StockValueComparator());
        else
        	// Natural Sorting (Tickers)
        	Collections.sort(myStocks);
        	String currPort = String.format("CURRENT PORTFOLIO:\n" +
        			"Cash Available: %d\n" +
        			"%-7s%-7s%-7s%-11s\n==================================\n", totalCash, "SYMBOL"," SHARES"," PRICE"," TOTAL VALUE");
        	for(Stock zeStock : myStocks){
        		currPort += String.format("%6s%8d%6d%13d\n", 
        				zeStock.getTicker(), zeStock.getShares(), zeStock.getCurrStockValue(), zeStock.getSharesValue());
        	}
        return currPort;
    }
    
    /**
     * Update the price per share of each stock using a random value to
     * determine the change.  A multiplier is applied to the stock price and
     * the result is rounded to the nearest integer.  A minimum price of $1 is
     * required. (For the given inputs, this constraint will always hold
     * without checking). This method can also be used to update the price of
     * a stock inside any stock object that contains that information.
     * (Also updates the stock values within the investor's portfolio)
     * @return A string "ticker" that indicates
     *         the ticker symbols and their prices.
     */
    public String tickerUpdate() { 
        
        String output = "";
        
        for(String str : market.keySet()) { 
            int currVal = market.get(str);
            int num = (int)(Math.random() * 5);
            int newVal;
            switch(num) { 
            case 0:
                newVal = (int)(currVal * .9 + 0.5);
                break;
            case 1:
                newVal = (int)(currVal * .95 + 0.5);
                break;
            case 2:
                newVal = currVal;
                break;
            case 3:
                newVal = (int)(currVal * 1.1 + 0.5);
                break;
            case 4:
            default:
            	newVal = (int)(currVal * 1.2 + 0.5);
                break;
            }
            
            market.put(str,  newVal);
            if(holdings.containsKey(str))
            	holdings.get(str).updateStockValue(newVal);
            output += str + " " + newVal + "      ";
        }
    
        return output;
    }
    
    /**
     * Sell all remaining stocks in the portfolio.
     * @return the cash value of the portfolio.
     */
    public int closeAccount() {
    	Set<String> keys = market.keySet();
    	for(String key : keys){
    		if(holdings.containsKey(key))
    			reduceHolding(key, holdings.get(key).getShares());
    	}
        return totalCash;
    }
}
