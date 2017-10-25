package com.dev.wcp4.controleos.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Activity.OSActivity;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidade.Acompanhamento;
import com.dev.wcp4.controleos.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AcompanhamentoAdapter extends RecyclerView.Adapter<AcompanhamentoAdapter.MyViewHolder> {

    private Context mContext;
    //private String status;
    //private String parametros = "";
   // private int novoStatus;
    private List<Acompanhamento> list = new ArrayList<>();
    //String TAG = "TESTE";

    //private TextView txtAcompDesc;
    //private TextView txtAcompData;
   // private TextView txtAcompNome;

    //private Spinner spinnerStatus;
    //private ProgressBar progressBarList;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtAcompDesc, txtAcompData, txtAcompNome;

        public MyViewHolder(View view) {
            super(view);
            txtAcompDesc = (TextView) view.findViewById(R.id.txtDescricaoAcomp);
            txtAcompData = (TextView) view.findViewById(R.id.txtDataAcomp);
            //txtAcompNome = (TextView) view.findViewById(R.id.txtAcompAddPor);

            //ImageButton btn1 = (ImageButton) view.findViewById(R.id.botaoNovoAcomp);
           //ImageButton btn2 = (ImageButton) view.findViewById(R.id.botaoAbrir);

           view.setOnClickListener(this);

            /*btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adicionaAcompanhamento();
                }
            });

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    criaAlertDialog();
                }
            });*/

        }

        @Override
        public void onClick(View view) {
            Acompanhamento acompanhamento = list.get(getAdapterPosition());

            exibir("Adicionado por:\n"+acompanhamento.getNomeFunc());

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Informações: ");

            String data = acompanhamento.getDataAcompanhamento().replaceAll("-", "/");
            String[] s = data.split("/");
            String novaData = s[2] + "/" + s[1] + "/" + s[0];

            final TextView textDat = new TextView(mContext);
            textDat.setText("Data Adição:  " + novaData);

            final TextView textDesc = new TextView(mContext);
            textDesc.setText("\nDescrição:  " + acompanhamento.getDescricaoAcompanhamento());

            final TextView textAber = new TextView(mContext);
            textAber.setText("\nAdicionada Por:  " + acompanhamento.getNomeFunc());

            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.addView(textDat);
            linearLayout.addView(textDesc);
            linearLayout.addView(textAber);

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setPadding(16, 16, 16, 16);

            alertDialog.setView(linearLayout);
            alertDialog.create();
            alertDialog.show();


        }

        public void criaAlertDialog() {

        }


    }

    public AcompanhamentoAdapter(Context mContext, Context context, List<Acompanhamento> acompanhamento) {
        this.mContext = mContext;
        Context context1 = context;
        this.list = acompanhamento;
    }

    public AcompanhamentoAdapter(Context mContext, List<Acompanhamento> acompanhamento) {
        this.mContext = mContext;
        this.list = acompanhamento;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_linha, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Acompanhamento acompanhamento = list.get(position);

        //dados do card da recyvlerview
        holder.txtAcompDesc.setText(acompanhamento.getDescricaoAcompanhamento());

        //converte data xxxx-xx-xx para xx/xx/xxxx
        String data = acompanhamento.getDataAcompanhamento().replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2] + "/" + s[1] + "/" + s[0];

        holder.txtAcompData.setText(novaData);
        //holder.txtAcompNome.setText(acompanhamento.getNomeFunc());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void exibir(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

}