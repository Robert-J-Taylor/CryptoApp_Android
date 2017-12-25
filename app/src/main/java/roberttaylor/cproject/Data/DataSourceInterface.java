package roberttaylor.cproject.Data;

import java.util.List;

/**
 * Created by Robert Taylor on 12/23/2017.
 */

public interface DataSourceInterface {
    List<CoinList> getListOfData();

    CoinList createNewListItem();

    void deleteListItem(CoinList coinList);

    void insertListItem(CoinList temporaryListItem);
}
