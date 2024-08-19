package com.example.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView output = findViewById(R.id.output_number);
        Button sharebutton = findViewById(R.id.share_button);

        // Main Body Start

        // Receiving the data passed by putExtra() method
        Intent i = getIntent();
        String username = i.getStringExtra("Name");

        // Generating Random Number
        int finalLuckyNumber = generateRandomNumber();
        output.setText(""+finalLuckyNumber);

        //Share the data to another apps
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareData(username,finalLuckyNumber);
            }
        });
    }

    public int generateRandomNumber(){
        Random rand = new Random();
        int upper_limit = 1000;

        int randomNumberGenerated = rand.nextInt(upper_limit); //  give random number from 0 to 1000
        return randomNumberGenerated;
    }

    public void shareData(String userName ,  int finalLuckyNumber){
        Intent i =   new Intent (Intent.ACTION_SEND);
        i.setType("Text/plane");

        //Additional information
        i.putExtra(Intent.EXTRA_SUBJECT,userName + "got lucky today");
        i.putExtra(Intent.EXTRA_TEXT,"His lucky number is "+finalLuckyNumber);

        startActivity(Intent.createChooser(i,"chose a Platform"));

    }
}