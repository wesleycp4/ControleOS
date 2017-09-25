package com.dev.wcp4.controleos.Conexao;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Conexao {
    public static String postDados(String rootUrl,String parametrosUsuario) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(rootUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(parametrosUsuario.getBytes().length));
            connection.setRequestProperty("Content-Language", "pt-BR");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter= new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
            outputStreamWriter.write(parametrosUsuario);
            outputStreamWriter.flush();

            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String linha;
            StringBuffer resposta = new StringBuffer();

            while ((linha = bufferedReader.readLine()) != null) {
                resposta.append(linha);
                resposta.append('\r');
            }
            bufferedReader.close();
            return resposta.toString();

        } catch (Exception erro) {
            System.out.print(erro.toString());
            return  erro.toString();

        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
