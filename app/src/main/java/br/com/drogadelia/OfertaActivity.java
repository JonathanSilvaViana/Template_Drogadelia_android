package br.com.drogadelia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class OfertaActivity extends AppCompatActivity {

    Context context;

    ImageButton carrinho_bt, updade_bt;

    final static int transicao = 2600;
    final static int transicaoII = 5200;
    final static int transicaoIII = 10400;
    ImageView imgV;

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

        //executa a transição das imagens

        imgV = (ImageView)findViewById(R.id.imageSlider);

        //inicia o slide
        autoslide();





    }

    public void autoslide()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgV.setImageResource(R.drawable.banner2);

            }
        }, transicao);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgV.setImageResource(R.drawable.banner3);

            }
        }, transicaoII);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgV.setImageResource(R.drawable.banner1);

            }
        }, transicaoIII);
    }


}