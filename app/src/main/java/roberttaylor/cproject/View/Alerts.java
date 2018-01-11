package roberttaylor.cproject.View;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import roberttaylor.cproject.R;

public class Alerts extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerts);
        // initiate view's
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Saved Exchanges", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(Alerts.this, UserAgreement.class));
            }
        });
    }
}
