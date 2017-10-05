package com.dev.wcp4.controleos.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<OrdemServico> list = new ArrayList<>();
    String status;
    String id;
    ImageButton btn1, btn2, btn3;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textNomeCl, textDataAb, textDesc, textEnd, textStatOs, textNumOs, textContatoOs;

        public MyViewHolder(View view) {
            super(view);
            textNomeCl = (TextView) view.findViewById(R.id.textNomeCliente);
            textNumOs = (TextView) view.findViewById(R.id.textNumeroOs);
            //textEnd = (TextView) view.findViewById(R.id.textEndereco);
            textDataAb = (TextView) view.findViewById(R.id.textDataAbertura);
            textDesc = (TextView) view.findViewById(R.id.textDescricao);
            textStatOs = (TextView) view.findViewById(R.id.textStatusOs);
            textContatoOs = (TextView) view.findViewById(R.id.textContato);

            btn1 = (ImageButton) view.findViewById(R.id.botaoNovoAcomp);
            btn2 = (ImageButton) view.findViewById(R.id.botaoAlterStatus);
            btn3 = (ImageButton) view.findViewById(R.id.botaoAbrir);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            criaAlertDialog(view, getAdapterPosition());
        }

        @SuppressLint("SetTextI18n")
        public void criaAlertDialog(View view, int position) {
            OrdemServico ordemservico = list.get(position);

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("Dados Adicionais: ");

            final TextView textNomeCl = new TextView(mContext);
            textNomeCl.setText("Nome Cliente: " +ordemservico.getClienteNome());

            final TextView textNumOs = new TextView(mContext);
            textNumOs.setText("Numero da O.S.: " + ordemservico.getIdOS());

            //final TextView textEnd = new TextView(mContext);
            //textEnd.setText("Endereço: " + ordemservico.getClienteNome());


            final TextView textDataAb = new TextView(mContext);
            String data = ordemservico.getDataAberturaOS().replaceAll("-", "/");
            String[] s = data.split("/");
            String novaData = s[2]+"/"+s[1]+"/"+s[0];
            textDataAb.setText("Aberta em: " +novaData);

            final TextView textDesc = new TextView(mContext);
            textDesc.setText("Descrição: " + ordemservico.getDescricaoOS());

            final TextView textStatOs = new TextView(mContext);
            if (ordemservico.getStatusOS() == 0){
                status = "Aberto";
            }
            if (ordemservico.getStatusOS() == 1) {
                status = "Em Analise";
            }
            if (ordemservico.getStatusOS() == 2) {
                status = "Aguardando autorização do Cliente";
            }
            if (ordemservico.getStatusOS() == 3) {
                status = "Orçamento Aprovado";
            }
            if (ordemservico.getStatusOS() == 4) {
                status = "Em reparo";
            }
            if (ordemservico.getStatusOS() == 5) {
                status = "Pronto para Entrega/Retirada";
            }
            if (ordemservico.getStatusOS() == 6) {
                status = "Finalizado";
            }
            textStatOs.setText("Status: " +status);

            final TextView textEquip = new TextView(mContext);
            textEquip.setText("Equipamento(s): " + ordemservico.getEquipamentosOS());

            final TextView textUsuario = new TextView(mContext);
            textUsuario.setText("O.S. Aberta por: " + ordemservico.getUsuarioNome());

            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.addView(textNumOs);
            linearLayout.addView(textDataAb);
            linearLayout.addView(textNomeCl);
            linearLayout.addView(textEquip);
            linearLayout.addView(textDesc);
            linearLayout.addView(textStatOs);
            linearLayout.addView(textUsuario);

            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(LinearLayout.TEXT_ALIGNMENT_CENTER);
            linearLayout.setPadding(16, 16, 16, 16);

            alertDialog.setView(linearLayout);
            alertDialog.create();
            alertDialog.show();

        }
    }

    public Adapter(Context mContext, List<OrdemServico> ordemservico) {
        this.mContext = mContext;
        this.list = ordemservico;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card_os, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        OrdemServico ordemservico = list.get(position);

        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "bt add acompanhamento", Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "bt alter status", Toast.LENGTH_SHORT).show();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "bt abrir em nova tela", Toast.LENGTH_SHORT).show();
            }
        });

        if (ordemservico.getStatusOS() == 0){
            status = "Aberto";
        }
        if (ordemservico.getStatusOS() == 1) {
            status = "Em Analise";
        }
        if (ordemservico.getStatusOS() == 2) {
            status = "Aguardando autorização";
        }
        if (ordemservico.getStatusOS() == 3) {
            status = "Orçamento Aprovado";
        }
        if (ordemservico.getStatusOS() == 4) {
            status = "Em reparo";
        }
        if (ordemservico.getStatusOS() == 5) {
            status = "Pronto para Entrega/Retirada";
        }
        if (ordemservico.getStatusOS() == 6) {
            status = "Finalizado";
        }
        //esses holder que vai pra recyclerview******************************************
        holder.textNomeCl.setText(ordemservico.getClienteNome());
        id = Integer.toString(ordemservico.getIdOS());
        holder.textNumOs.setText(id);
        //holder.textEnd.setText(ordemservico.getUsuarioNome());

        String data = ordemservico.getDataAberturaOS().replaceAll("-", "/");
        String[] s = data.split("/");
        String novaData = s[2]+"/"+s[1]+"/"+s[0];

        holder.textDataAb.setText(novaData);
        holder.textDesc.setText(ordemservico.getDescricaoOS());
        holder.textContatoOs.setText(ordemservico.getContato());
        holder.textStatOs.setText(status);

    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.activity_base_drawer, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        public MyMenuItemClickListener() {
        }
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            return false;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}