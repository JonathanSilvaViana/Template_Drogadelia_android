package br.com.drogadelia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.ActionMode;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class OfertaActivity extends AppCompatActivity {

    Context context;

    ImageButton carrinho_bt, voltar_slide, avancar_slide, voltar_slide_dois, voltar_slide_um, avancar_slide_dois, avancar_slide_tres, bt_pause, bt_play;
    Button bt_update_app;
    String imgA, imgB, imgC, telefone_chat;
    int pegarID_img;
    Intent carrinhodecompras, bannerLegal;
    RecyclerView lista_ofertas;
    String[] fakedata;

    final static int transicao = 2600;
    final static int transicaoII = 5200;
    final static int transicaoIII = 10400;
    ImageView imgV, imageBanner, listafalsa;

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

        //executa a transi????o das imagens

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

        //encontra o bot??o para retroceder o slide
        voltar_slide = (ImageButton)findViewById(R.id.voltar_slide);
        voltar_slide_dois = (ImageButton)findViewById(R.id.voltar_slide_dois);
        voltar_slide_um = (ImageButton)findViewById(R.id.voltar_slide_um);

        //define a cor do controle de voltar prim??rio
        voltar_slide.setBackground(getResources().getDrawable(R.drawable.background_bts));

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


        //encontra o bot??o para avan??ar o slide
        avancar_slide = (ImageButton)findViewById(R.id.avancar_slide);
        avancar_slide_dois = (ImageButton)findViewById(R.id.avancar_slide_dois);
        avancar_slide_tres = (ImageButton)findViewById(R.id.avancar_slide_tres);

        //define a cor do controle de slide prim??rio
        avancar_slide.setBackground(getResources().getDrawable(R.drawable.background_bts));

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


        //encontra os bot??es de play e pause
        bt_pause = (ImageButton) findViewById(R.id.bt_pause);
        bt_play = (ImageButton) findViewById(R.id.bt_play);

        //define os estilos dos bot??es de play e pause
        bt_play.setBackground(getResources().getDrawable(R.drawable.background_bts));
        bt_pause.setBackground(getResources().getDrawable(R.drawable.background_bts));

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

        //define os dados falsos
        fakedata = getResources().getStringArray(R.array.produtos);

        List<Produto> produtos = new ArrayList<Produto>();

        //lista_ofertas.setAdapter(produtos);

        //encontra a imagem de banner

        imageBanner = (ImageView) findViewById(R.id.imageBanner);

        imageBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vai_bannerLegal();
            }
        });


        telefone_chat = getResources().getString(R.string.numero_chat);

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


        //Toolbar barra = (Toolbar)findViewById(R.id.toolbar);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //barra.inflateMenu(R.menu.menu);




    }

    //nesses metodos pode ser necess??rio deixar os demais bot??es ocultos
    public void retroce_slide_img_tres()
    {

        voltar_slide.setVisibility(View.GONE);
        avancar_slide.setVisibility(View.GONE);
        voltar_slide_dois.setVisibility(View.VISIBLE);
        avancar_slide_tres.setVisibility(View.VISIBLE);
        //voltar_slide_dois.setBackgroundColor(getResources().getColor(R.color.black));
        voltar_slide_dois.setBackground(getResources().getDrawable(R.drawable.background_bts));
        avancar_slide_tres.setBackground(getResources().getDrawable(R.drawable.background_bts));
        imgV.setImageResource(R.drawable.banner3);

    }

    public void retrocede_slide_img_dois()
    {
        voltar_slide_dois.setVisibility(View.GONE);
        avancar_slide_tres.setVisibility(View.GONE);
        voltar_slide_um.setVisibility(View.VISIBLE);
        avancar_slide_dois.setVisibility(View.VISIBLE);
        voltar_slide_um.setBackground(getResources().getDrawable(R.drawable.background_bts));
        avancar_slide_dois.setBackground(getResources().getDrawable(R.drawable.background_bts));
        imgV.setImageResource(R.drawable.banner2);
    }

    public void retrocede_slide_img_zero()
    {
        voltar_slide_um.setVisibility(View.GONE);
        avancar_slide_dois.setVisibility(View.GONE);
        voltar_slide.setVisibility(View.VISIBLE);
        avancar_slide.setVisibility(View.VISIBLE);
        voltar_slide.setBackground(getResources().getDrawable(R.drawable.background_bts));
        avancar_slide.setBackground(getResources().getDrawable(R.drawable.background_bts));
        imgV.setImageResource(R.drawable.banner1);
    }

    //m??todos de avan??ar o slide

    public void avanca_slide_dois()
    {
        avancar_slide.setVisibility(View.GONE);
        voltar_slide.setVisibility(View.GONE);
        avancar_slide_dois.setVisibility(View.VISIBLE);
        voltar_slide_um.setVisibility(View.VISIBLE);
        avancar_slide_dois.setBackground(getResources().getDrawable(R.drawable.background_bts));
        voltar_slide_um.setBackground(getResources().getDrawable(R.drawable.background_bts));
        imgV.setImageResource(R.drawable.banner2);
    }

    public void Avancar_slide_tres()
    {
        avancar_slide_dois.setVisibility(View.GONE);
        voltar_slide_um.setVisibility(View.GONE);
        avancar_slide_tres.setVisibility(View.VISIBLE);
        voltar_slide_dois.setVisibility(View.VISIBLE);
        avancar_slide_tres.setBackground(getResources().getDrawable(R.drawable.background_bts));
        voltar_slide_dois.setBackground(getResources().getDrawable(R.drawable.background_bts));
        imgV.setImageResource(R.drawable.banner3);
    }

    public void avanca_slide_img_zero()
    {
        avancar_slide_tres.setVisibility(View.GONE);
        voltar_slide_dois.setVisibility(View.GONE);
        avancar_slide.setVisibility(View.VISIBLE);
        voltar_slide.setVisibility(View.VISIBLE);
        avancar_slide_tres.setBackground(getResources().getDrawable(R.drawable.background_bts));
        voltar_slide_dois.setBackground(getResources().getDrawable(R.drawable.background_bts));
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

    public void chat()
    {
        Uri uri = Uri.parse("smsto:" + telefone_chat);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;

    }

    //evento de defini????o sobre menus de navega????o
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId())
        {
            case R.id.suporte:
                chat();
                return true;
            case R.id.categoria:
                Toast.makeText(this, "categoria", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.suporte:
                chat();
                return true;
            case R.id.categoria:
                vai_bannerLegal();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }



    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.suporte:
                chat();
                mode.finish(); // Action picked, so close the CAB
                return true;
            default:
                return false;
        }
    }


    }

