package roberttaylor.cproject;

import android.webkit.JavascriptInterface;

/**
 * Created by Robert Taylor on 12/4/2017.
 */

public class StockSymbolJavaScriptInterface {
    String stock;

    StockSymbolJavaScriptInterface(String stock) {
        this.stock = stock;
    }

    @JavascriptInterface
    public String getStockSymbol() {
        return stock;
    }
}

