package com.dev.wcp4.controleos.Adapter;


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
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.Entidades.Cliente;
import com.dev.wcp4.controleos.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private Context mContext;
    private List<OrdemServico> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textNomeCl, textDataAb, textDesc, textEnd, textStatOs, textNumOs;
        //RatingBar ratingBar;
        //public ImageView thumbnail, overflow;


        public MyViewHolder(View view) {
            super(view);
            textNomeCl = (TextView) view.findViewById(R.id.textNomeCliente);
            textNumOs = (TextView) view.findViewById(R.id.textNumeroOs);
            textEnd = (TextView) view.findViewById(R.id.textEndereco);
            //textDataAb = (TextView) view.findViewById(R.id.textDataAbertura);
            textDesc = (TextView) view.findViewById(R.id.textDescricao);
            textStatOs = (TextView) view.findViewById(R.id.textStatusOs);
            view.setOnClickListener(this);

            //thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            //overflow = (ImageView) view.findViewById(R.id.image2);
        }

        @Override
        public void onClick(View view) {
            criaAlertDialog(view, getAdapterPosition());

        }

        public void criaAlertDialog(View view, int position) {
            OrdemServico ordemservico = list.get(position);
            ;

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
            alertDialog.setTitle("INFORMAÇÕES");

            final TextView textNomeCl = new TextView(mContext);
            textNomeCl.setText("Nome Cliente:" +ordemservico.getClienteNome());

            final TextView textNumOs = new TextView(mContext);
            textNumOs.setText("N. O.S.: " + ordemservico.getIdOS());

            //final TextView textEnd = new TextView(mContext);
            //textEnd.setText("Endereço: " + ordemservico.getClienteNome());

            final TextView textDataAb = new TextView(mContext);
            textDataAb.setText("Data Abertura: " + ordemservico.getDataAberturaOS());

            final TextView textDesc = new TextView(mContext);
            textDesc.setText("Descrição Problema: " + ordemservico.getDescricaoOS());

            final TextView textStatOs = new TextView(mContext);
            textStatOs.setText("Status: " + ordemservico.getStatusOS());

            final TextView textEquip = new TextView(mContext);
            textEquip.setText("Equipamentos: " + ordemservico.getEquipamentosOS());

            final TextView textUsuario = new TextView(mContext);
            textUsuario.setText("Usuario: " + ordemservico.getUsuarioNome());


            LinearLayout linearLayout = new LinearLayout(mContext);
            linearLayout.addView(textDataAb);
            linearLayout.addView(textNomeCl);
            linearLayout.addView(textDesc);
            linearLayout.addView(textNumOs);
            linearLayout.addView(textStatOs);
            linearLayout.addView(textUsuario);
            linearLayout.addView(textEquip);

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
        //holder.textNomeCl.setText(ordemservico.getClienteNome());
        holder.textNumOs.setText(ordemservico.getIdOS());
        //holder.textEnd.setText(ordemservico.getClienteNome());
        //holder.textDataAb.setText(ordemservico.getDataAberturaOS());
        holder.textDesc.setText(ordemservico.getDescricaoOS());
        holder.textStatOs.setText(ordemservico.getStatusOS());

        // loading album cover using Glide library
        // Glide.with(mContext).load(user.getImg()).into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });
        */
    }

    /**
     * Showing popup menu when tapping on 3 dots
     */
    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.activity_base_drawer, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    /**
     * Click listener for popup menu items
     */
    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            /*
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(mContext, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(mContext, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            */
            return false;
        }
    }

    @Override
    public int getItemCount() {

        return list.size();
    }

}