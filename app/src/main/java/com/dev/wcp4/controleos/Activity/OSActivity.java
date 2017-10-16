package com.dev.wcp4.controleos.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.system.Os;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class OSActivity extends AppCompatActivity {

    private TextView txtDesc;
    private TextView txtEquip;
    private ArrayList<OrdemServico> ordemServicos = new ArrayList<>();
    private String param = "idos=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        txtDesc = (TextView) findViewById(R.id.txtDesc);
        txtEquip = (TextView) findViewById(R.id.txtEquip);

        Intent intent = getIntent();
        intent.getExtras();
        String teste =  intent.getStringExtra("id");
        param +=teste;
        new carregaDados().execute(Rotas.URL_DADOS_OS_TUDO);


    }

    public void setaDadosOs(OrdemServico dadosOs){
        txtEquip.setText(dadosOs.getClienteNome());
        txtDesc.setText(dadosOs.getClienteBairro());

        Toast.makeText(this,dadosOs.getClienteNome(), Toast.LENGTH_SHORT).show();
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


    private class carregaDados extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
           // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jsonObj = new JSONObject(s);
                    JSONArray jsonArray = jsonObj.getJSONArray("os");
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            JSONObject obj  = jsonArray.getJSONObject(i);

                            OrdemServico os = new OrdemServico(
                                    obj.getInt("idos"),
                                    obj.getString("descricao"),
                                    obj.getString("equipamento"),
                                    obj.getString("imagem"),
                                    obj.getString("dataabertura"),
                                    obj.getString("datafechamento"),
                                    obj.getLong("valororcamento"),
                                    obj.getLong("valorfinal"),
                                    obj.getInt("status")
                            );
//parei aqui

                            Toast.makeText(getApplicationContext(),os.getClienteNome(), Toast.LENGTH_SHORT).show();
                           ordemServicos.add(os);


                        }catch (JSONException e){
                           e.getMessage();
                        }
                    }
                setaDadosOs(ordemServicos.get(0));
                //preencheArray(list);
            } catch (JSONException e) {
                e.getMessage();
            }
            //adapter.notifyDataSetChanged();
          //  progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0],param);
        }
    }

}