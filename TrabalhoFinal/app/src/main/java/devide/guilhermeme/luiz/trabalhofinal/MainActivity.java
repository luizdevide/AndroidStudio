package devide.guilhermeme.luiz.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTime, editJogador, editNumeroJogador, editTamanho, editAno;
    Button buttonCadastrar, buttonLimpar, buttonEditar;
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

        buttonCadastrar = (Button)findViewById(R.id.buttonCadastrar);
        buttonLimpar = (Button)findViewById(R.id.buttonLimpar);
        buttonEditar = (Button)findViewById(R.id.buttonEditar);

        listViewCamisas = (ListView) findViewById(R.id.listViewCamisas);

        listarCamisas();

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                limpaCampos();
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cadastraCampos();
                limpaCampos();
                listarCamisas();
            }
        });

        buttonEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, ActivityEdit.class);
                startActivity(it);
            }
        });
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

        db.addCamisas(new Camisas(time,jogador, numero1, tamanho, ano1));
        Toast.makeText(this, "Cadastro Realizado Com Sucesso!", Toast.LENGTH_SHORT).show();
    }

    public void listarCamisas(){

        List<Camisas> camisas = db.listaTodasCamisas();

        arrayList =  new ArrayList<String>();

        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);

        listViewCamisas.setAdapter(adapter);

        for(Camisas c : camisas){
            arrayList.add(c.getCodigo() + " - " + c.getJogador() + " - " + c.getTime() + " - " + c.getNumeroJogador() + " - " + c.getTamanho() + " - " + c.getAno());
            adapter.notifyDataSetChanged();
        }
    }
}
