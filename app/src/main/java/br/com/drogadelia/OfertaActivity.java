package br.com.drogadelia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;


public class OfertaActivity extends AppCompatActivity {

    Context context;

    ImageButton carrinho_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oferta);
        setTitle(R.string.app_name);

        carrinho_bt = (ImageButton)findViewById(R.id.carrinho_bt);

        carrinho_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent carrinhodecompras = new Intent(OfertaActivity.this, CarrinhoActivity.class);
                startActivity(carrinhodecompras);
            }
        });


    }
}