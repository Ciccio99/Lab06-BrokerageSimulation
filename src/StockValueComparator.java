import java.util.Comparator;

/**
 * File:
 * $Id: StockValueComparator.java,v 1.1 2014/04/08 16:32:53 afs2842 Exp $
 * 
 * Log:
 * $Log: StockValueComparator.java,v $
 * Revision 1.1  2014/04/08 16:32:53  afs2842
 * Project needs to be commented
 *
 * 
 */

/**
 * @author Alberto Scicali
 *
 */
public class StockValueComparator implements Comparator<Stock> {

	@Override
	/**
	 * Compares Stock objects based on their value
	 * @param stock1, stock2
	 * @return stock1<stock2 positive, stock1>stock2 negative, stock1==stock2 0
	 */
	public int compare(Stock stock1, Stock stock2) {
		// TODO Auto-generated method stub
		if(stock1.getSharesValue() < stock2.getSharesValue())
			return 1;
		else if (stock1.getSharesValue() > stock2.getSharesValue())
			return -1;
		else
			return 0;
	}

}
