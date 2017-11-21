package devide.guilhermeme.luiz.trabalhofinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ActivityEdit extends AppCompatActivity {

    EditText editTime, editJogador, editNumero, editTamanho, editAno, editCodigo;

    BancoDados db = new BancoDados(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editTime = (EditText)findViewById(R.id.editTime);
        editJogador = (EditText)findViewById(R.id.editJogador);
        editNumero = (EditText)findViewById(R.id.editNumero);
        editTamanho = (EditText)findViewById(R.id.editTamanho);
        editAno = (EditText)findViewById(R.id.editAno);


    }

    public void limpaCampos(){
        editCodigo.setText(null);
        editTime.setText(null);
        editAno.setText(null);
        editJogador.setText(null);
        editNumero.setText(null);
        editTamanho.setText(null);

        editTime.requestFocus();
    }

    public void atualizaCampos(){
        String codigo = editCodigo.getText().toString();
        int codigo1 = Integer.parseInt(codigo);
        String time = editTime.getText().toString();
        String jogador = editJogador.getText().toString();
        String numero = editNumero.getText().toString();
        int numero1 = Integer.parseInt(numero);
        String tamanho = editTamanho.getText().toString();
        String ano = editAno.getText().toString();
        int ano1 = Integer.parseInt(ano);

        if( time.isEmpty() || jogador.isEmpty() || numero.isEmpty() || tamanho.isEmpty() || ano.isEmpty()){
            Toast.makeText(this, "Preencha os campos corretamente", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            db.atualizaCamisas(new Camisas(codigo1,time,jogador, numero1, tamanho, ano1));
            Toast.makeText(this, "Alteracao Realizada Com Sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.excluir:

                String codigo = editCodigo.getText().toString();

                if (codigo.isEmpty()) {
                    Toast.makeText(ActivityEdit.this, "Codigo nao encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    Camisas camisas = new Camisas();
                    camisas.setCodigo(Integer.parseInt(codigo));
                    db.apagarCamisas(camisas);

                    limpaCampos();
                    Toast.makeText(ActivityEdit.this, "Campo excluido com sucesso", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.alterar:
                atualizaCampos();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
