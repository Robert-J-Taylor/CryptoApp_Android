package roberttaylor.cproject;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import roberttaylor.cproject.Data.CoinList;
import roberttaylor.cproject.Data.DataSourceInterface;
import roberttaylor.cproject.Logic.Controller;
import roberttaylor.cproject.View.ViewInterface;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {
    //We technically could've just used the FakeDataSource here, but you don't always want to use
    //Mocks over Fakes and vice versa. Depends on your use case.
    @Mock
    DataSourceInterface dataSource;

    @Mock
    ViewInterface view;

    @Mock
    View testViewRoot;

    /**
     * Often times we'll want data to run our tests against. I like to define such Data as a series
     * of Static variables in the Test file itself for convenience.
     */
    private static final CoinList TEST_ITEM = new CoinList(
            199,200,300,200,"BTC");


    private static final int POSITION = 3;

    /**
     * Since we're testing our Controller Class, this one is the Real implementation!
     * How this works is we make a Real instance of the Controller talk to Fake Instances of
     * what it would normally talk to (i.e. our Mocks above). Since the Controller does most of the
     * thinking (Logic), it follows that it would benefit the most from a good suite of Tests.
     */
    Controller controller;

    /**
     * the @Before annotation makes this Code run before the Tests themselves. You can use this
     * to initialize the different components of your Test, should you need to. You can also
     * initialize things in the Test methods themselves. Whatever works best for your Use Case.
     */
    @Before
    public void setUpTest() {
        controller = new Controller(view, dataSource);
    }

    @Test
    public void onGetListDataSuccessful() {
        //Since we require a List<ListItem> to be returned by the dataSource, we can define it here:
        List<CoinList> listOfData = new ArrayList<>();
        listOfData.add(TEST_ITEM);


        //This is where we tell our "Mocks" what to do when our Controller talks to them. Since they
        //aren't real objects, we must tell them exactly what to do if we want responses from them.
        Mockito.when(dataSource.getListOfData())
                .thenReturn(listOfData);

        //This is the method we are testing
        controller.getListFromDataSource();

        //Check how the Tested Class responds to the data it receives
        //or test it's behaviour
        Mockito.verify(view).setUpAdapterAndView(listOfData);
    }


    @Test
    public void onListItemClicked() {
        controller.onListItemClick(TEST_ITEM);

        Mockito.verify(view).startPortfolioActivity(
                TEST_ITEM.getCoinName(),
                TEST_ITEM.getVolume());
    }


    // @Test
    // public void onGetListDataUnsuccessful() {

    /**************************
     *
     * Unit Test Homework:
     *
     * Implement the "View", so that when we don't recieve a List, it shows some kind of
     * error message to the user. This is common practice that you should learn!
     *
     * I've written some hints you'll have to decipher into Java code:
     *************************/
    //1 Set up your Mock dataSource

    //2 Call the method you wish to test on the Controller

    //3 Verify that the View has been told to show a message (I'd suggest showing a Toast for now)

    //Profit???

    // }
//    @Test
//    public void onCreateNewListItemClick() {
//        //1 Set up your Mock dataSource
//        Mockito.when(dataSource.createNewListItem())
//                .thenReturn(TEST_ITEM);
//
//        //2 Call the method you wish to test on the Controller
//        controller.createNewListItem();
//
//        //3 Verify the behaviour of the View, based on the event
//        Mockito.verify(view).addNewListItemToView(
//                TEST_ITEM
//        );
//    }

    /**
     * User has swiped to delete an Item from the List. Since this operation may be accidental, it
     * is necessary to provide a way for the User to Undo this operation. For now, we will
     * 1. Temporarily store ListItem and position in adapter
     * 2. Tell the Datasource to delete the Item (this ensures view/data layer consistency as much as
     * possible)
     * <p>
     * After that, the user will either undo, or the snackbar dialog will timeout (see tests below)
     */
//    @Test
//    public void onListItemSwiped() {
//
//        controller.onListItemSwiped(POSITION, TEST_ITEM);
//
//        //ensure consistency between View and Data Layers
//        Mockito.verify(dataSource).deleteListItem(TEST_ITEM);
//        Mockito.verify(view).deleteListItemAt(POSITION);
//
//        //give user the option to undo action
//        Mockito.verify(view).showUndoSnackbar();
//
//    }


    /**
     * When the User Undoes delete operation, we must do the following:
     * - Add the temp item back into the Datasource
     * - Animate the item back into the View
     */
//    @Test
//    public void onUndoDeleteOperation() {
//        Mockito.when(dataSource.createNewListItem())
//                .thenReturn(TEST_ITEM);
//
//        //this test requires temporary position and item to be set. We can achieve this by calling
//        //controller.onListItemSwiped() first.
//        controller.onListItemSwiped(POSITION, TEST_ITEM);
//
//        controller.onUndoConfirmed();
//
//        Mockito.verify(dataSource).insertListItem(TEST_ITEM);
//
//        Mockito.verify(view).insertListItemAt(
//                POSITION,
//                TEST_ITEM
//        );
//    }

    /**
     * Since the only interaction with Controller is from methods called on it within the Test,
     * there isn't really anything to verify against. However, we could use the Debugger to step
     * through the Test if necessary.
     * <p>
     * ¯\_(ツ)_/¯
     */
//    @Test
//    public void onSnackbarTimeout() {
//        controller.onListItemSwiped(POSITION, TEST_ITEM);
//
//        controller.onSnackbarTimeout();
//    }


}