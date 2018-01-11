package roberttaylor.cproject.View;

import java.util.List;

import roberttaylor.cproject.Data.CoinList;

/**
 * Created by Robert Taylor on 12/23/2017.
 */

public interface ViewInterface {

    void startPortfolioActivity(String coinName,int volume, int coinValue, int walletValue, int valueFluctuation);

    void setUpAdapterAndView(List<CoinList> listOfData);

    void addNewListItemToView(CoinList newItem);

    void deleteListItemAt(int position);

    void showUndoSnackbar();

    void insertListItemAt(int temporaryListItemPosition, CoinList temporaryListItem);
}
