package com.example.fawadsayedrahim_comp304lab6_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    //replace with your package name
    public static final String INFO_INTENT = "INFO_UPDATE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);


    }
    //
    public void startService(View view) {
        //textView.setText("Here");
        startService(new Intent(getBaseContext(), SimpleService.class));
    }

    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), SimpleService.class));
    }

    //This will handle the broadcast
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        //
        @Override
        public void onReceive(Context context, Intent intent) {
            //
            textView.setText("Here");
            String action = intent.getAction();
            if (action.equals(SimpleService.INFO_INTENT)) {
              Double info = intent.getDoubleExtra(INFO_INTENT,0.0);
                 textView.setText(String.valueOf(info));


                //String info = intent.getDoubleExtra(INFO_INTENT);
                //textView.setText(info);
            }
        }
    };

    public void onResume() {
        super.onResume();
        //This needs to be in the activity that will end up receiving the broadcast
        registerReceiver(receiver, new IntentFilter(INFO_INTENT));
    }
}
