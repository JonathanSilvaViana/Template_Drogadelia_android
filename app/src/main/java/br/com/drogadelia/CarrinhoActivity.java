package br.com.drogadelia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class CarrinhoActivity extends AppCompatActivity {

    ImageButton bt_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        //encontra o botão
        bt_voltar = (ImageButton) findViewById(R.id.voltar_bt);

        //ação do botão para voltar
        bt_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBt_voltar();
            }
        });

    }

    //método para voltar a activity anterior
    public void setBt_voltar()
    {
        super.onBackPressed();
        //evento de retorno
    }
}