package roberttaylor.cproject.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import roberttaylor.cproject.R;

public class Exchanges extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exchanges);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Saved Exchanges", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(Exchanges.this, APIKeySettings.class));
            }
        });
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkboxBitFinex:
                if (checked){

                }
                // Do Something
            else
                // Remove
                break;
            case R.id.checkboxBitStamp:
                if (checked){

                }

            else
                break;
            case R.id.checkboxBTCe:
                if (checked)
                {

                }
            else

                break;
            case R.id.checkboxCoinBase:
                if (checked)
                {

                }
                else

                    break;
            case R.id.checkboxKraken:
                if (checked)
                {

                }
                else

                    break;
            case R.id.checkboxBTCChina:
                if (checked)
                {

                }
                else

                    break;
            case R.id.checkboxBitCoinSource:
                if (checked)
                {

                }
                else

                    break;

        }
    }
}
