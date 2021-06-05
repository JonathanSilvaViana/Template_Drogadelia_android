package br.com.drogadelia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class OfertaActivity extends AppCompatActivity {

    Context context;

    ImageButton carrinho_bt, voltar_slide, avancar_slide, voltar_slide_dois, voltar_slide_um, avancar_slide_dois, avancar_slide_tres, bt_pause, bt_play;
    Button bt_update_app;
    String imgA, imgB, imgC;
    int pegarID_img;
    Intent carrinhodecompras, bannerLegal;
    RecyclerView lista_ofertas;

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
                carrinhodecompras = new Intent(OfertaActivity.this, CarrinhoActivity.class);
                startActivity(carrinhodecompras);
            }
        });

        //executa a transição das imagens

        //nome das imagens dos slides
        imgA = "banner1";
        imgB = "banner2";
        imgC = "banner3";

        imgV = (ImageView)findViewById(R.id.imageSlider);

        /*imgV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vai_bannerLegal();
            }
        });*/

        //inicia o slide
        autoslide();

        //encontra o botão para retroceder o slide
        voltar_slide = (ImageButton)findViewById(R.id.voltar_slide);
        voltar_slide_dois = (ImageButton)findViewById(R.id.voltar_slide_dois);
        voltar_slide_um = (ImageButton)findViewById(R.id.voltar_slide_um);

        //faz voltar o slide anterior
        voltar_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retroce_slide_img_tres();
            }
        });

        voltar_slide_dois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrocede_slide_img_dois();
            }
        });

        voltar_slide_um.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrocede_slide_img_zero();
            }
        });


        //encontra o botão para avançar o slide
        avancar_slide = (ImageButton)findViewById(R.id.avancar_slide);
        avancar_slide_dois = (ImageButton)findViewById(R.id.avancar_slide_dois);
        avancar_slide_tres = (ImageButton)findViewById(R.id.avancar_slide_tres);

        avancar_slide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanca_slide_dois();
            }
        });

        avancar_slide_dois.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Avancar_slide_tres();
            }
        });

        avancar_slide_tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avanca_slide_img_zero();
            }
        });


        //encontra os botões de play e pause
        bt_pause = (ImageButton) findViewById(R.id.bt_pause);
        bt_play = (ImageButton) findViewById(R.id.bt_play);

        bt_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pausar();
            }
        });

        bt_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        bt_update_app = (Button)findViewById(R.id.bt_update_app);

        bt_update_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();
            }
        });

        //encontra o layout da lista de ofertas
        lista_ofertas = (RecyclerView)findViewById(R.id.recycler_ofertas);

        //criar aqui o web service

    }

    public void autoslide()
    {

        //avisa quando o slide inicia
        Toast.makeText(getApplicationContext(), R.string.initslide, Toast.LENGTH_SHORT).show();

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
                bt_pause.setVisibility(View.GONE);
                bt_play.setVisibility(View.VISIBLE);
                //avisa quando o slide termina
                Toast.makeText(getApplicationContext(), R.string.endslide, Toast.LENGTH_SHORT).show();
            }
        }, transicaoIII );



    }

    //nesses metodos pode ser necessário deixar os demais botões ocultos
    public void retroce_slide_img_tres()
    {

        voltar_slide.setVisibility(View.GONE);
        avancar_slide.setVisibility(View.GONE);
        voltar_slide_dois.setVisibility(View.VISIBLE);
        avancar_slide_tres.setVisibility(View.VISIBLE);
        voltar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        avancar_slide_tres.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner3);

    }

    public void retrocede_slide_img_dois()
    {
        voltar_slide_dois.setVisibility(View.GONE);
        avancar_slide_tres.setVisibility(View.GONE);
        voltar_slide_um.setVisibility(View.VISIBLE);
        avancar_slide_dois.setVisibility(View.VISIBLE);
        voltar_slide_um.setBackgroundColor(getResources().getColor(R.color.black));
        avancar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner2);
    }

    public void retrocede_slide_img_zero()
    {
        voltar_slide_um.setVisibility(View.GONE);
        avancar_slide_dois.setVisibility(View.GONE);
        voltar_slide.setVisibility(View.VISIBLE);
        avancar_slide.setVisibility(View.VISIBLE);
        voltar_slide.setBackgroundColor(getResources().getColor(R.color.black));
        avancar_slide.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner1);
    }

    //métodos de avançar o slide

    public void avanca_slide_dois()
    {
        avancar_slide.setVisibility(View.GONE);
        voltar_slide.setVisibility(View.GONE);
        avancar_slide_dois.setVisibility(View.VISIBLE);
        voltar_slide_um.setVisibility(View.VISIBLE);
        avancar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        voltar_slide_um.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner2);
    }

    public void Avancar_slide_tres()
    {
        avancar_slide_dois.setVisibility(View.GONE);
        voltar_slide_um.setVisibility(View.GONE);
        avancar_slide_tres.setVisibility(View.VISIBLE);
        voltar_slide_dois.setVisibility(View.VISIBLE);
        avancar_slide_tres.setBackgroundColor(getResources().getColor(R.color.black));
        voltar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner3);
    }

    public void avanca_slide_img_zero()
    {
        avancar_slide_tres.setVisibility(View.GONE);
        voltar_slide_dois.setVisibility(View.GONE);
        avancar_slide.setVisibility(View.VISIBLE);
        voltar_slide.setVisibility(View.VISIBLE);
        avancar_slide_tres.setBackgroundColor(getResources().getColor(R.color.black));
        voltar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        imgV.setImageResource(R.drawable.banner1);
    }

    public void pausar()
    {
        bt_pause.setVisibility(View.GONE);
        bt_play.setVisibility(View.VISIBLE);
        onStop();
    }

    public void play()
    {
        bt_play.setVisibility(View.GONE);
        bt_pause.setVisibility(View.VISIBLE);
        autoslide();
    }

    public void vai_bannerLegal()
    {
        bannerLegal = new Intent(this, BannerLegalActivity.class);
        startActivity(bannerLegal);
    }

    public void atualizar()
    {
        recreate();
        //finish();
        //startActivity(getIntent());
    }

}