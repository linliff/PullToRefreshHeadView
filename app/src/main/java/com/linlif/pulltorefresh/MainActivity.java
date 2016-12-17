package com.linlif.pulltorefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void normal(View view){

        Intent intent = new Intent(this , GirdViewAvtivity.class);
        startActivity(intent);

    }

    public void listView(View view){

        Intent intent = new Intent(this , HeadListViewActivity.class);
        startActivity(intent);

    }

    public void girdView(View view){

        Intent intent = new Intent(this , HeadGirdViewActivity.class);
        startActivity(intent);

    }

}
