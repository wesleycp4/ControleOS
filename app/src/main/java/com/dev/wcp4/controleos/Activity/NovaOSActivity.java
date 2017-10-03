package com.dev.wcp4.controleos.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.wcp4.controleos.R;

public class NovaOSActivity extends AppCompatActivity {

    private ImageView imagemEquip;
    private ImageView imagemEquip2;
    //private Button botaoFoto;
    //private Button botaoFoto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_os);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        botoesFlutuantes();

        this.imagemEquip = (ImageView) findViewById(R.id.fotoPreview);




    }

    public void botoesFlutuantes() {
        FloatingActionButton botao_add_img = (FloatingActionButton) findViewById(R.id.botaoAddImg);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*FloatingActionButton botao_add_img2 = (FloatingActionButton) findViewById(R.id.botaoAddImg2);
        botao_add_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, " add img2", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish(); // Finaliza a Activity atual
        return true;
    }

    public void onBackPressed(){
        finish();
    }

   /* public void carregaImagem(String localArquivoFoto) {
        if(localArquivoFoto != null) {
            Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
            imagemContato.setImageBitmap(imagemFoto);
            imagemContato.setTag(localArquivoFoto);
        }
    }*/

}