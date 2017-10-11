package com.dev.wcp4.controleos.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.wcp4.controleos.Adapter.Adapter;
import com.dev.wcp4.controleos.Conexao.Conexao;
import com.dev.wcp4.controleos.Conexao.Rotas;
import com.dev.wcp4.controleos.Entidades.OrdemServico;
import com.dev.wcp4.controleos.R;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.dev.wcp4.controleos.R.id.filtro0;
import static com.dev.wcp4.controleos.R.id.menu_flutuante;
import static com.dev.wcp4.controleos.R.id.progressBar;
import static com.dev.wcp4.controleos.R.id.snackbar_action;
import static com.dev.wcp4.controleos.R.id.snackbar_text;

public class BaseActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    final Context context = this;
    private String parametros;
    private ArrayList<OrdemServico> list = new ArrayList<>();
    private ProgressBar progressBar;
    String param = "";

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        progressBar = (ProgressBar) findViewById(R.id.progressBarBase) ;
        progressBar.setVisibility(View.VISIBLE);

        //7 é tratado no servidor web para exibir tudo menos as o.s. fechadas
        param = "status=7";
        new carregaDados().execute(Rotas.URL_DADOS_OS);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView textUsuario = (TextView) findViewById(R.id.textViewOla);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(this,list);
        recyclerView.setAdapter(adapter);

        botaoflutuante();

        //recupera o nome do usuario do sharedpreferences
        SharedPreferences prefs = getSharedPreferences("arq", MODE_PRIVATE);
        String usuario = prefs.getString("nomedoUser", null);
        assert usuario != null;
        usuario = usuario.substring(0,1).toUpperCase().concat(usuario.substring(1));

        exibir("Bem vindo, "+usuario);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        textUsuario = (TextView)header.findViewById(R.id.textViewOla);
        textUsuario.setText("Olá, " +usuario);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
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

        switch (item.getItemId()) {
            case R.id.acao_atualizar:
                progressBar.setVisibility(View.VISIBLE);
                new carregaDados().execute(Rotas.URL_DADOS_OS);
                progressBar.setVisibility(View.INVISIBLE);
                exibir("Atualizado!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        } else if (id == R.id.nav_atualizar_cliente) {
            //Toast.makeText(this,"Consultar cliente",Toast.LENGTH_SHORT).show();
            Intent att = new Intent(getContexto(),AtualizarClienteActivity.class);
            startActivity(att);

        }else if (id == R.id.nav_logout) {
            exibir("Logout feito com sucesso.");
            Intent intent = new Intent(this, MainActivity.class);
            BaseActivity.this.finish();
            startActivity(intent);

        } else if (id == R.id.nav_avançado) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.opcoes_activity, null);
            final EditText usuarioDialog = (EditText) alertLayout.findViewById(R.id.usuario_dialog);
            final EditText senhaDailog = (EditText) alertLayout.findViewById(R.id.senha_dialog);
            final CheckBox cbShowPassword = (CheckBox) alertLayout.findViewById(R.id.checkBox);

            cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        // to encode password in dots
                        senhaDailog.setTransformationMethod(null);
                    } else {
                        // to display the password in normal text
                        senhaDailog.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                }
            });

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Autorização necessaria!\nInforme usuario e senha:");
            alert.setView(alertLayout);
            alert.setCancelable(true);
            alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    exibir("Operação Cancelada");
                }
            });

            alert.setPositiveButton("Entrar!", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String usuario = usuarioDialog.getText().toString();
                    String senha = senhaDailog.getText().toString();
                    if(!usuario.isEmpty() && !senha.isEmpty()){
                        parametros = "usuario=" + usuario + "&senha=" + senha + "&adm=" + 1;
                        new Logar().execute(Rotas.URL_LOGINADM);
                    } else{
                        exibir("Informe o Usuario e a Senha para acessar!");
                    }
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();

        } else if (id == R.id.nav_sair) {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Deseja encerrar a aplicação?");
            alertDialogBuilder
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

    private class Logar extends AsyncTask<String, Object, String> {

        @Override
        protected void onProgressUpdate(Object... values) {
        }
        @Override
        protected void onPostExecute(String s) {
            //exibir(s);
            try {
                JSONObject jsonObj = new JSONObject(s);
                if(!jsonObj.getBoolean("error")){
                    startActivity(new Intent(BaseActivity.this,NovoUserActivity.class));
                }else{
                    exibir("Este Usuario não é Administrador!");
                }
            } catch (JSONException e) {
                exibir(e.getMessage());
            }
        }
        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0],parametros);
        }
    }

    private class carregaDados extends AsyncTask<String, Object, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Object... values) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            try {

                JSONObject jsonObj = new JSONObject(s);

                JSONArray jsonArray = jsonObj.getJSONArray("osDados");
                list.clear();
                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject obj  = jsonArray.getJSONObject(i);
                        OrdemServico os = new OrdemServico(
                                obj.getInt("idos"),
                                obj.getString("descricao"),
                                obj.getString("equipamento"),
                                obj.getString("dataabertura"),
                                obj.getString("contato_cliente"),
                                obj.getString("contato2_cliente"),
                                obj.getInt("status"),
                                obj.getString("nome"),
                                obj.getString("nome_cliente"),
                                obj.getString("email_cliente"),
                                obj.getString("rua_cliente"),
                                obj.getInt("numero_cliente"),
                                obj.getString("complemento_cliente"),
                                obj.getString("bairro_cliente"),
                                obj.getString("cidade_cliente"),
                                obj.getString("estado_cliente"),
                                obj.getString("cep_cliente")
                        );
                        list.add(os);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected String doInBackground(String... url) {
            return Conexao.postDados(url[0],param);
        }
    }

    public void botaoflutuante(){
        com.github.clans.fab.FloatingActionMenu menuFlutuante = (com.github.clans.fab.FloatingActionMenu) findViewById(R.id.menu_flutuante);
        com.github.clans.fab.FloatingActionButton itemMenuAtualizar = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.botaoAtualizar);
        com.github.clans.fab.FloatingActionButton itemMenuFiltrar = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.botaoFiltroLista);
        com.github.clans.fab.FloatingActionButton itemMenuAdicionarOs = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.botaoAddOs);
        menuFlutuante.setClosedOnTouchOutside(true);

        itemMenuAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                new carregaDados().execute(Rotas.URL_DADOS_OS);
                //exibir("Atualizado!");
                Snackbar.make(v, "Atualizado!", Snackbar.LENGTH_SHORT).setAction("Action", null).show();
                progressBar.setVisibility(View.INVISIBLE);
                menuFlutuante.close(true);
            }
        });

        itemMenuFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(v);
                menuFlutuante.close(true);
                exibir("Escolha uma opção de filtro!");
            }
        });

        itemMenuAdicionarOs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                menuFlutuante.close(true);
            }
        });

    }

    public Context getContexto(){
        return this;
    }

    public void exibir(String msg){
        Toast.makeText(this,msg, Toast.LENGTH_SHORT).show();
    }

    private void showPopupMenu(View view) {
        PopupMenu popup = new PopupMenu(getContexto(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.base_filtro, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();

    }

    private class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.filtro0:
                    exibir("Somente O.S.(s) Abertas");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=0";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro1:
                    exibir("Somente O.S.(s) Em Analise");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=1";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro2:
                    exibir("Somente O.S.(s) Aguardando Autorição");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=2";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro3:
                    exibir("Somente O.S.(s) com Orçamento Aprovado");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=3";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro4:
                    exibir("Somente O.S.(s) Em Reparo");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=4";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro5:
                    exibir("Somente O.S.(s) Prontas para entrega ou retirada");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=5";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro6:
                    exibir("Somente O.S.(s) Finalizadas");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=6";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.filtro7:
                    exibir("Vizualização Padrão");
                    progressBar.setVisibility(View.VISIBLE);
                    list.clear();
                    param = "status=7";
                    new carregaDados().execute(Rotas.URL_DADOS_OS);
                    progressBar.setVisibility(View.INVISIBLE);
                    return true;
                default:
            }

            return false;
        }

    }

}
