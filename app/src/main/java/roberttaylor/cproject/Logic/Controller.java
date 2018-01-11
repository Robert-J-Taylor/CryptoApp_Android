package roberttaylor.cproject.Logic;

import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import roberttaylor.cproject.Data.CoinList;
import roberttaylor.cproject.Data.DataSourceInterface;
import roberttaylor.cproject.View.ViewInterface;

/**
 * Created by Robert Taylor on 12/23/2017.
 */

public class Controller {
    private final List<CoinList> mItems = new ArrayList<>();
    private CoinList temporaryListItem;
    private int temporaryListItemPosition;

    private ViewInterface view;
    private DataSourceInterface dataSource;

    public Controller(ViewInterface view, DataSourceInterface dataSource) {
        this.view = view;
        this.dataSource = dataSource;
        getListFromDataSource();
    }

    public void getListFromDataSource() {
        view.setUpAdapterAndView(
                dataSource.getListOfData()
        );
    }

    public void onListItemClick(CoinList selectedItem) {
        view.startPortfolioActivity(
                selectedItem.getCoinName(),
                selectedItem.getVolume(),
                selectedItem.getCoinValue(),
                selectedItem.getWalletValue(),
                selectedItem.getValueFluctuation()
        );
    }

    public void createNewListItem() {
        /*
        To simulate telling the DataSource to create a new record and waiting for its response, we'll simply
        have it return a new ListItem.
        Once we work with live data we'll use RxJava 2( or some other API/FRAMEWORK for
        Asynchronous communicaiton) to have the Datasource do this on the IO thread,
        and respond via an Asynchronous callback to the main thread.
         */
        CoinList newItem = dataSource.createNewListItem();
        view.addNewListItemToView(newItem);
    }

    public void onListItemSwiped(int position, CoinList coinList) {
        //ensure that the view and data layers have consistent state
        dataSource.deleteListItem(coinList);
        view.deleteListItemAt(position);

        temporaryListItemPosition = position;
        temporaryListItem = coinList;

        view.showUndoSnackbar();


    }

    public void onUndoConfirmed() {
        if(temporaryListItem !=null){
            //ensure View/Data consistency
            dataSource.insertListItem(temporaryListItem);
            view.insertListItemAt(temporaryListItemPosition,temporaryListItem);

            temporaryListItem = null;
            temporaryListItemPosition = 0;
        }else{

        }
    }

    public void onSnackbarTimeout() {
        temporaryListItem = null;
        temporaryListItemPosition = 0;
    }




}
