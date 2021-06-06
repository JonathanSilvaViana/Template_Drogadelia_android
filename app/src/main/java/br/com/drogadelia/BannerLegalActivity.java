package br.com.drogadelia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class BannerLegalActivity extends AppCompatActivity {

    TextView toolbar_title;
    ImageButton voltar_bt;
    Button bt_nice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_legal);

        //encontra a barra de menu
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);

        //nomeia a barra de menu
        toolbar_title.setText(R.string.banner);

        voltar_bt = findViewById(R.id.voltar_bt);

        voltar_bt.setOnClickListener(v -> {
            super.onBackPressed();
        });

        bt_nice = (Button)findViewById(R.id.bt_nice);

        //essa classe funciona melhor com lambda
        bt_nice.setOnClickListener(v -> Toast.makeText(BannerLegalActivity.this, R.string.clickNiceButtom, Toast.LENGTH_LONG).show());
    }
}