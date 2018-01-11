package roberttaylor.cproject.View;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;
import roberttaylor.cproject.ChartActivity;
import roberttaylor.cproject.R;

public class Portfolio extends AppCompatActivity {


    private static final String EXTRA_COIN_NAME = "EXTRA_COIN_NAME";
    private static final String EXTRA_VOLUME = "EXTRA_VOLUME";
    private static final String EXTRA_COIN_VALUE ="EXTRA_COIN_VALUE";
    private static final String EXTRA_VALUE_FLUCTUATION ="EXTRA_VALUE_FLUCTUATION";
    private static final String EXTRA_WALLET_VALUE ="EXTRA_WALLET_VALUE";
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCAUD";
    //private final String BASE_URL = "http://wtfdnsftw.freeddns.org:5000/candlestick?exchange=binance&market=NEOBTC&start_date=2017-11-19%2020:00:00.000";
    private TextView mPriceTextView;
    // Member Variables:
    private Button buttonTest;
    private TextView coinTitle;
    private TextView volumeDisplay;
    private TextView coinValueDisplay;
    private TextView valueFluctuation;
    private ImageView viewGraph;
    private TextView walletValueDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
         /*I wouldn't normally pass all this Data via Intent, so understand that this is just a quick
        implementation to get things working for the Demo. I'd normally pass just a Unique id as an
        extra, and then retrieve the appropriate Data from a Service.*/
        Intent i = getIntent();
        String coinName = i.getStringExtra(EXTRA_COIN_NAME);
        int volume = i.getIntExtra(EXTRA_VOLUME,0);
        int coinValue = i.getIntExtra(EXTRA_COIN_VALUE,0);
        int coinValueFluctuation = i.getIntExtra(EXTRA_VALUE_FLUCTUATION,0);
        int walletValue = i.getIntExtra(EXTRA_WALLET_VALUE,0);


        viewGraph=findViewById(R.id.viewGraph);
        viewGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Portfolio.this, ChartActivity.class));
            }
        });
        coinTitle = findViewById(R.id.coinPortfolioTitle);
        volumeDisplay = findViewById(R.id.portfolioVolume);
        coinValueDisplay = findViewById(R.id.portfolioCoinValue);
        valueFluctuation = findViewById(R.id.coinPortfolioValueFluctuation);
        walletValueDisplay = findViewById(R.id.portfolioWalletValue);

        coinTitle.setText(coinName);
        volumeDisplay.setText(String.valueOf(volume));
        coinValueDisplay.setText(String.valueOf(coinValue));
        valueFluctuation.setText(String.valueOf(coinValueFluctuation));
        walletValueDisplay.setText(String.valueOf(walletValue));




        //mPriceTextView= findViewById(R.id.priceLabel);
        //buttonTest = (Button) findViewById(R.id.findPriceButton);
        Intent intent= getIntent();
        mPriceTextView =(TextView)findViewById(R.id.priceLabel);
        String txt_put =intent.getStringExtra("Price");
        mPriceTextView.setText(txt_put);

//        if(extras != null){
//            coinTitle.setText(extras.getString("coinName"));
//        }
//        buttonTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("Bitcoin", "" + v);
//                String finalUrl = BASE_URL;
//                Log.d("Bitcoin", "Final url is: " + finalUrl);
//                letsDoSomeNetworking(finalUrl);
//            }
//        });
    }
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                try{
                    String price = response.getString("high");
//                   JSONObject jsonObject = response.getJSONObject(0);
//                   String price = jsonObject.getString("time");
                    mPriceTextView.setText(price);
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());

            }
        });


    }

}
