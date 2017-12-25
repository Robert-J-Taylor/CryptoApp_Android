package roberttaylor.cproject.Data;

/**
 * Created by Robert Taylor on 12/18/2017.
 */

public class CoinList {
    private int volume;
    private int coinValue;
    private int walletValue;
    private int valueFluctuation;
    private String coinName;

    public CoinList(int volume, int coinValue, int walletValue, int valueFluctuation, String coinName) {
        this.volume = volume;
        this.coinValue = coinValue;
        this.walletValue = walletValue;
        this.valueFluctuation = valueFluctuation;
        this.coinName = coinName;
    }


    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(int coinValue) {
        this.coinValue = coinValue;
    }

    public int getWalletValue() {
        return walletValue;
    }

    public void setWalletValue(int walletValue) {
        this.walletValue = walletValue;
    }

    public int getValueFluctuation() {
        return valueFluctuation;
    }

    public void setValueFluctuation(int valueFluctuation) {
        this.valueFluctuation = valueFluctuation;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }
}
