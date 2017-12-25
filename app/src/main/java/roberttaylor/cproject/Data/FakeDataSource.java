package roberttaylor.cproject.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import roberttaylor.cproject.R;

/**
 * Created by Robert Taylor on 12/23/2017.
 */

public class FakeDataSource implements DataSourceInterface {

    private static final int sizeofCollection = 12;

    private final String[] coinName = {
            "BTC",
            "XRP",
            "ETH",
            "MIOTA",
            "BCH",
            "LTC",
            "DASH"

    };
    private final int[] volume = {
            100,1412,4512,5289,22299,9294,24141
    };
    private final int[] walletValue ={
            4125,2021,440,1000,4220,1002,50294
    };
    private final int[] coinValue={
            16002,400,10293,10,13290,258,408
    };

    private final int[] valueFluctuation={
        1230,100,4214,4892,2948,984,49
    };
    public FakeDataSource() {
    }

    @Override
    public List<CoinList> getListOfData() {
        ArrayList<CoinList> listOfData = new ArrayList<>();


        //randomize new coin
//        for (int i =0; i<12; i++){
//            listOfData.add(createNewListItem()
//            );
//        }
        for(int i=0; i<7;i++){
            CoinList newCoin = new CoinList(volume[i],walletValue[i],coinValue[i],valueFluctuation[i],coinName[i]);
        listOfData.add(newCoin);}

        return listOfData;
    }

    @Override
    public CoinList createNewListItem() {
        //randomize new coin
        Random random = new Random();
        int randOne = random.nextInt(4);
        int randTwo = random.nextInt(4);
        int randThree = random.nextInt(4);
        int randFour = random.nextInt(4);
        int randFive = random.nextInt(4);
        CoinList singleListItem = new CoinList(

                volume[randTwo],
                walletValue[randThree],
                coinValue[randFour],
                valueFluctuation[randFive],
                coinName[randOne]
        );
        return singleListItem;

    }

    @Override
    public void deleteListItem(CoinList coinList) {

    }

    @Override
    public void insertListItem(CoinList temporaryListItem) {

    }
//    private final int[] color ={
//            R.color.RED,
//            R.color.BLUE,
//            R.color.GREEN
//    };
}
