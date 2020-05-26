package com.example.mathjaxintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Intent intent = getIntent();
        String question = "When $a \\ne 0$, there are two solutions to \\(ax^2 + bx + c = 0\\) and they are\n" +
                "    \\(x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.\\) "
                +"When $a \\ne 0$, there are two solutions to \\(ax^2 + bx + c = 0\\) and they are\n" +
                "    $$x = {-b \\pm \\sqrt{b^2-4ac} \\over 2a}.$$ ";


        LinearLayout layout = (LinearLayout) findViewById(R.id.post_preview);
        layout.removeAllViews();
        PostViewHelper.getInstance().parsePostAndRender(question, layout, this,
                inflater, null);



    }
}
