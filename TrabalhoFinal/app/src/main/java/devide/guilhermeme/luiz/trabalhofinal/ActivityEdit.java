package devide.guilhermeme.luiz.trabalhofinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityEdit extends AppCompatActivity {

    EditText editTime, editJogador, editNumero, editTamanho, editAno, editCodigo;
    Button buttonAtualizar, buttonExcluir, buttonVoltar;

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

        buttonAtualizar = (Button)findViewById(R.id.buttonAtualizar);
        buttonExcluir = (Button)findViewById(R.id.buttonExcluir);
        buttonVoltar= (Button)findViewById(R.id.buttonVoltar);

        buttonExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String codigo = editCodigo.getText().toString();
                
                if(codigo.isEmpty()){
                    Toast.makeText(ActivityEdit.this, "Codigo nao encontrado", Toast.LENGTH_SHORT).show();
                } else {
                    Camisas camisas = new Camisas();
                    camisas.setCodigo(Integer.parseInt(codigo));
                    db.apagarCamisas(camisas);

                    limpaCampos();
                    Toast.makeText(ActivityEdit.this, "Campo excluido com sucesso", Toast.LENGTH_SHORT).show();
                }

            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ActivityEdit.this, MainActivity.class);
                startActivity(it);
            }
        });

        buttonAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                atualizaCampos();
            }
        });
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

        db.atualizaCamisas(new Camisas(codigo1,time,jogador, numero1, tamanho, ano1));
        Toast.makeText(this, "Alteracao Realizada Com Sucesso!", Toast.LENGTH_SHORT).show();
    }
}
