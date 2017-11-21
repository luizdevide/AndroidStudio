package devide.guilhermeme.luiz.trabalhofinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTime, editJogador, editNumeroJogador, editTamanho, editAno;
    ListView listViewCamisas;

    BancoDados db = new BancoDados(this);

    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;

    private ConstraintLayout constraintLayout;
    private int opcao = Color.WHITE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = (ConstraintLayout) findViewById(R.id.layoutPrincipal);
        lerPreferenciaCor();

        editTime = (EditText)findViewById(R.id.editTime);
        editJogador = (EditText)findViewById(R.id.editJogador);
        editNumeroJogador = (EditText)findViewById(R.id.editNumeroJogador);
        editTamanho = (EditText)findViewById(R.id.editTamanho);
        editAno = (EditText)findViewById(R.id.editAno);

        listViewCamisas = (ListView) findViewById(R.id.listViewCamisas);

        listarCamisas();
    }

    public void limpaCampos(){
        editTime.setText(null);
        editAno.setText(null);
        editJogador.setText(null);
        editNumeroJogador.setText(null);
        editTamanho.setText(null);

        editTime.requestFocus();
    }

    public void cadastraCampos(){
        String time = editTime.getText().toString();
        String jogador = editJogador.getText().toString();
        String numero = editNumeroJogador.getText().toString();
        int numero1 = Integer.parseInt(numero);
        String tamanho = editTamanho.getText().toString();
        String ano = editAno.getText().toString();
        int ano1 = Integer.parseInt(ano);

        if( time.isEmpty() || jogador.isEmpty() || numero.isEmpty() || tamanho.isEmpty() || ano.isEmpty()){
            Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            db.addCamisas(new Camisas(time, jogador, numero1, tamanho, ano1));
            Toast.makeText(this, "Cadastro Realizado Com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public void listarCamisas(){

        List<Camisas> camisas = db.listaTodasCamisas();

        arrayList =  new ArrayList<String>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewCamisas.setAdapter(adapter);

        for(Camisas c : camisas){
            arrayList.add(c.getCodigo() + " - " + c.getJogador() + " - " + c.getTime() + " \n Numero: " + c.getNumeroJogador() + " - Tamanho: " + c.getTamanho() + " - Ano: " + c.getAno());
            adapter.notifyDataSetChanged();
        }
    }

    private void mudarCorFundo(){
        constraintLayout.setBackgroundColor(opcao);
    }

    private void lerPreferenciaCor(){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.preferencias_cores),
                        Context.MODE_PRIVATE);

        opcao = sharedPref.getInt(getString(R.string.cor_fundo), opcao);

        mudarCorFundo();
    }

    private void salvarPreferenciaCor(int novoValor){
        SharedPreferences sharedPref =
                getSharedPreferences(getString(R.string.preferencias_cores),
                        Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();

        editor.putInt(getString(R.string.cor_fundo), novoValor);

        editor.commit();

        opcao = novoValor;

        mudarCorFundo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        switch(opcao){
            case Color.GRAY:
                menu.getItem(0).setChecked(true);
                return true;
            case Color.RED:
                menu.getItem(1).setChecked(true);
                return true;
            case Color.WHITE:
                menu.getItem(2).setChecked(true);
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salvar:
                cadastraCampos();
                limpaCampos();
                listarCamisas();
                break;

            case R.id.editar:
                Intent it = new Intent(MainActivity.this, ActivityEdit.class);
                startActivity(it);
                break;

            case R.id.Sobre:
                Intent aAbout = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aAbout);
                break;
        }

        item.setChecked(true);

        switch(item.getItemId()){
            case R.id.menuItemCinza:
                salvarPreferenciaCor(Color.GRAY);
                return true;
            case R.id.menuItemVermelho:
                salvarPreferenciaCor(Color.RED);
                return true;
            case R.id.menuItemBranco:
                salvarPreferenciaCor(Color.WHITE);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}