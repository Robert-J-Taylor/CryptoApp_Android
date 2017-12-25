package roberttaylor.cproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import roberttaylor.cproject.View.ViewCoinList;

public class MainActivity extends AppCompatActivity {
    private Button signIn;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            //Call some material design APIs here
//        }else{
//            //Implement this feature without material design
//        }
        register= (TextView) findViewById(R.id.registerLink);
        signIn  = (Button) findViewById(R.id.signInButton);
        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                MainActivity.this.startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ViewCoinList.class));
            }
        });
    }
}
