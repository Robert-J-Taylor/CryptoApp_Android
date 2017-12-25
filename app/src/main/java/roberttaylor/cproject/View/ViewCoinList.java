package roberttaylor.cproject.View;



import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import roberttaylor.cproject.ChartActivity;
import roberttaylor.cproject.ContentFragment;
import roberttaylor.cproject.Data.CoinList;
import roberttaylor.cproject.Data.FakeDataSource;
import roberttaylor.cproject.FollowedUsers;
import roberttaylor.cproject.Logic.Controller;
import roberttaylor.cproject.Payment;
import roberttaylor.cproject.R;
import roberttaylor.cproject.SearchUsers;
import roberttaylor.cproject.Settings;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;


public class ViewCoinList extends AppCompatActivity implements ViewAnimator.ViewAnimatorListener,ViewInterface,View.OnClickListener{
    private static final String EXTRA_COIN_NAME ="EXTRA_COIN_NAME";
    private static final String EXTRA_VOLUME = "EXTRA_VOLUME";
    private static final String EXTRA_COIN_VALUE ="EXTRA_COIN_VALUE";
    private static final String EXTRA_VALUE_FLUCTUATION ="EXTRA_VALUE_FLUCTUATION";
    private static final String EXTRA_WALLET_VALUE ="EXTRA_WALLET_VALUE";

    private List<CoinList> listOfData;
    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private Controller controller;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private Button addCoin;
    private TextView priceLabel;
    private Button viewSampleGraph;
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCAUD";
    //private final String BASE_URL = "http://wtfdnsftw.freeddns.org:5000/candlestick?exchange=binance&market=NEOBTC&start_date=2017-11-19%2020:00:00.000";
    private LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coin_list);

        recyclerView = (RecyclerView) findViewById(R.id.coinListRecyclerID);
        layoutInflater = getLayoutInflater();
        //this is Dependency Injection here
        controller = new Controller(this,new FakeDataSource());
        FloatingActionButton fabulous = (FloatingActionButton) findViewById(R.id.fab_create_new_item);
        fabulous.setOnClickListener(this);
        priceLabel= findViewById(R.id.priceLabel);
        contentFragment = ContentFragment.newInstance(R.layout.activity_view_coin_list);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        linearLayout = findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });
        addCoin= findViewById(R.id.addCoinButton);
        viewSampleGraph = findViewById(R.id.viewSampleGraphButton);
        viewSampleGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewCoinList.this, ChartActivity.class));
            }
        });
        addCoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        Log.d("Bitcoin", "Url is: " + BASE_URL);
                        letsDoSomeNetworking(BASE_URL);
            }
        });


        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

    }
    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.small_cancel);
        list.add(menuItem0);
        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.PAYMENT, R.drawable.payment_image);
        list.add(menuItem);
        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.FOLLOW, R.drawable.follow_image);
        list.add(menuItem2);
        SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.FOLLOWING, R.drawable.followers_image);
        list.add(menuItem3);
        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.SETTINGS, R.drawable.settings);
        list.add(menuItem4);


    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_notifications, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;
            case ContentFragment.PAYMENT:
                Intent intent = new Intent(ViewCoinList.this, Payment.class);
                startActivity(intent);
                return screenShotable;
            case ContentFragment.FOLLOW:
                Intent intent2 = new Intent(ViewCoinList.this, SearchUsers.class);
                startActivity(intent2);
                return screenShotable;
            case ContentFragment.FOLLOWING:
                Intent intent3 = new Intent(ViewCoinList.this, FollowedUsers.class);
                startActivity(intent3);
                return screenShotable;
            case ContentFragment.SETTINGS:
                Intent intent4 = new Intent(ViewCoinList.this, Settings.class);
                startActivity(intent4);
                return screenShotable;
            default:
                return screenShotable;
        }
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);

    }

    @Override
    public void enableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();

    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                try{
                    String price = response.getString("last");
                   // JSONObject jsonObject = response.getJSONObject(0);
                   // String price = jsonObject.getString("time");
                    Intent intent = new Intent(ViewCoinList.this,Portfolio.class);
                    intent.putExtra("Price", price);
                    startActivity(intent);

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

    @Override
    public void startPortfolioActivity(String coinName, int volume) {
                 Intent i = new Intent(this, Portfolio.class);
                 i.putExtra(EXTRA_COIN_NAME,coinName);
                 i.putExtra(EXTRA_VOLUME,volume);
                 startActivity(i);
    }

    @Override
    public void setUpAdapterAndView(List<CoinList> listOfData) {
           this.listOfData = listOfData;
           recyclerView.setLayoutManager(new LinearLayoutManager(this));
           adapter = new CustomAdapter();
           recyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void addNewListItemToView(CoinList newItem) {
        listOfData.add(newItem);

        int endOfList = listOfData.size() -1;
        adapter.notifyItemInserted(endOfList);

        recyclerView.smoothScrollToPosition(endOfList);
    }

    @Override
    public void deleteListItemAt(int position) {
        listOfData.remove(position);

        adapter.notifyItemRemoved(position);
    }

    @Override
    public void showUndoSnackbar() {
    Snackbar.make(
            findViewById(R.id.coinListRecyclerID),
            getString(R.string.action_delete_item),
            Snackbar.LENGTH_LONG
    )
            .setAction(R.string.action_undo, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    controller.onUndoConfirmed();
                }

            })
            .addCallback(new BaseTransientBottomBar.BaseCallback<Snackbar>() {
                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    controller.onSnackbarTimeout();
                }
            })
    .show();

    }

    @Override
    public void insertListItemAt(int position, CoinList listItem) {
        listOfData.add(position,listItem);

        adapter.notifyItemInserted(position);
    }


    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId ==R.id.fab_create_new_item){
            //User wishes to create a new RecyclerView item
            controller.createNewListItem();
        }

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
        
        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.listcoin,parent,false);

            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            CoinList currentItem = listOfData.get(position);

            holder.coinName.setText(
                    currentItem.getCoinName()
            );

            holder.volume.setText(String.valueOf(currentItem.getVolume()));

            holder.walletValue.setText(String.valueOf(currentItem.getWalletValue()));
            holder.valueFluctuation.setText(String.valueOf(currentItem.getWalletValue()));
            holder.coinValue.setText(String.valueOf(currentItem.getWalletValue()));


        }

        @Override
        public int getItemCount() {
            //Helps the Adapter decide how many Items it will need to manage
            return listOfData.size();
        }

        public void onItemMove(int fromPos, int toPos) {
            Collections.swap(listOfData,fromPos,toPos);
            notifyItemMoved(fromPos,toPos);
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView coinName;
            private TextView volume;
            private TextView walletValue;
            private TextView valueFluctuation;
            private TextView coinValue;
            private ViewGroup container;
            public CustomViewHolder(View itemView){
                super(itemView);
                this.coinName = itemView.findViewById(R.id.coinTitle);
                this.volume = itemView.findViewById(R.id.volume);
                this.walletValue = itemView.findViewById(R.id.walletValue);
                this.coinValue = itemView.findViewById(R.id.coinValue);
                this.valueFluctuation = itemView.findViewById(R.id.coinPercentChange);
                this.container = itemView.findViewById(R.id.cardviewCoin);

                this.container.setOnClickListener(this);

            }
            @Override
            public void onClick(View v) {
                    CoinList listItem = listOfData.get(this.getAdapterPosition()
                    );
                    controller.onListItemClick(listItem);
            }
        }
    }
    private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean isItemViewSwipeEnabled() {
                return true;
            }

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                final int fromPos = viewHolder.getAdapterPosition();
                final int toPos = target.getAdapterPosition();
                adapter.onItemMove(fromPos,toPos);
                return true;
            }


            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                controller.onListItemSwiped(
                        position,
                        listOfData.get(position)
                );
            }
        };

        return simpleItemTouchCallback;
    }

}
