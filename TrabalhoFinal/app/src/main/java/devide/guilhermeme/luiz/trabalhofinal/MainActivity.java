package devide.guilhermeme.luiz.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Toast.makeText(this, "Campos Limpos!", Toast.LENGTH_SHORT).show();
    }

    public void cadastraCampos(){
        String time = editTime.getText().toString();
        String jogador = editJogador.getText().toString();
        String numero = editNumeroJogador.getText().toString();
        int numero1 = Integer.parseInt(numero);
        String tamanho = editTamanho.getText().toString();
        String ano = editAno.getText().toString();
        int ano1 = Integer.parseInt(ano);

        if( time == "" || jogador == ""  || tamanho == ""){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
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

        return super.onOptionsItemSelected(item);
    }
}