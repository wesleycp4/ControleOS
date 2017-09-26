package com.dev.wcp4.controleos.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev.wcp4.controleos.R;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //inicia os botoes flutuantes
        botoesFlutuantes();



        //recycleview













        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
            //super.onBackPressed();
        }
        BaseActivity.this.finish();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.acao_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_novo_os) {
            //Toast.makeText(this,"novo os",Toast.LENGTH_SHORT).show();
            Intent novaos = new Intent(getContexto(),NovaOSActivity.class);
            startActivity(novaos);

        } else if (id == R.id.nav_consultar_os) {
            //Toast.makeText(this,"Consultar O.S.",Toast.LENGTH_SHORT).show();
            Intent consult = new Intent(getContexto(),ConsultarOrdemActivity.class);
            startActivity(consult);

        } else if (id == R.id.nav_cadastro_cliente) {
            //Toast.makeText(this,"Cadastrar Novo Cliente",Toast.LENGTH_SHORT).show();
            Intent cadast = new Intent(getContexto(),CadastrarClienteActivity.class);
            startActivity(cadast);

        } else if (id == R.id.nav_consultar_cliente) {
            //Toast.makeText(this,"Consultar cliente",Toast.LENGTH_SHORT).show();
            Intent consc = new Intent(getContexto(),ConsultarClienteActivity.class);
            startActivity(consc);

        } else if (id == R.id.nav_logout) {
            exibir("Logout feito com sucesso.");
            Intent intent = new Intent(this, MainActivity.class);
            BaseActivity.this.finish();
            startActivity(intent);

        } else if (id == R.id.nav_avançado) {
            exibir("opcs avançadas");
            Intent intent = new Intent(this, NovoUserActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_sair) {
            exibir("sair app");
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Confirmar fechamento do aplicativo");
            alertDialogBuilder
                    .setMessage("Para sair do sistema clique em SIM!")
                    .setCancelable(false)
                    .setPositiveButton("Sim",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            BaseActivity.this.finish();
                        }
                    })
                    .setNegativeButton("Não",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void botoesFlutuantes(){
        FloatingActionButton botao_atualizar = (FloatingActionButton) findViewById(R.id.botao_atualizar);
        botao_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Atualizando...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton botao_cons = (FloatingActionButton) findViewById(R.id.botao_consultar);
        botao_cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent consult = new Intent(getContexto(),ConsultarOrdemActivity.class);
                startActivity(consult);
            }
        });

        FloatingActionButton botao_add = (FloatingActionButton) findViewById(R.id.botao_addos);
        botao_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Cliente ja cadastrado no sistema?");
                alertDialogBuilder
                        //.setMessage("Cliente ja cadastrado no sistema?")
                        .setCancelable(true)
                        .setNeutralButton("Sim",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                Intent novaos = new Intent(getContexto(),NovaOSActivity.class);
                                startActivity(novaos);
                            }
                        })

                        .setNegativeButton("Não",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int id) {
                                Intent cadast = new Intent(getContexto(),CadastrarClienteActivity.class);
                                startActivity(cadast);
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });
    }

    public Context getContexto(){
        return this;
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }
}
