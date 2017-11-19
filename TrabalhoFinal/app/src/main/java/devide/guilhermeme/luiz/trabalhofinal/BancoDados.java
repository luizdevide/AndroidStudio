package devide.guilhermeme.luiz.trabalhofinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;
import android.webkit.ClientCertRequest;

import java.util.ArrayList;
import java.util.List;

public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSAO_BANCO = 1;
    private static final String BANCO_CAMISAS = "bd_camisas";

    private static final String TABELA_CAMISAS = "td_camisas";

    private static final String COLUNA_TIME = "times";
    private static final String COLUNA_CODIGO = "codigo";
    private static final String COLUNA_JOGADOR = "jogador";
    private static final String COLUNA_NUMERO = "numero";
    private static final String COLUNA_TAMANHO = "tamanho";
    private static final String COLUNA_ANO = "ano";

    public BancoDados(Context context) {
        super(context, BANCO_CAMISAS, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String QUERY_COLUNA_CAMISAS = " CREATE TABLE " + TABELA_CAMISAS + "("
                + COLUNA_CODIGO + " INTEGER PRIMARY KEY, "
                + COLUNA_TIME + " TEXT, "
                + COLUNA_JOGADOR + " TEXT, "
                + COLUNA_NUMERO + " TEXT, "
                + COLUNA_TAMANHO + " TEXT, "
                + COLUNA_ANO + " INTEGER)";

        db.execSQL(QUERY_COLUNA_CAMISAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /* CRUD */
    void addCamisas(Camisas camisas) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_TIME, camisas.getTime());
        values.put(COLUNA_JOGADOR, camisas.getJogador());
        values.put(COLUNA_NUMERO, camisas.getNumeroJogador());
        values.put(COLUNA_TAMANHO, camisas.getTamanho());
        values.put(COLUNA_ANO, camisas.getAno());

        db.insert(TABELA_CAMISAS, null, values);
        db.close();
    }

    void apagarCamisas(Camisas camisas) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABELA_CAMISAS, COLUNA_CODIGO + " = ?", new String[]{String.valueOf(camisas.getCodigo())});

        db.close();
    }

    Camisas selecionarCamisas(int codigo) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.query(TABELA_CAMISAS, new String[]{COLUNA_CODIGO, COLUNA_TIME, COLUNA_JOGADOR, COLUNA_NUMERO, COLUNA_TAMANHO, COLUNA_ANO}, COLUNA_CODIGO + " = ?",
                new String[]{String.valueOf(codigo)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Camisas camisas = new Camisas(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3), cursor.getString(4), cursor.getInt(5));

        return camisas;
    }

    void atualizaCamisas(Camisas camisas){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUNA_TIME, camisas.getTime());
        values.put(COLUNA_JOGADOR, camisas.getJogador());
        values.put(COLUNA_NUMERO, camisas.getNumeroJogador());
        values.put(COLUNA_TAMANHO, camisas.getTamanho());
        values.put(COLUNA_ANO, camisas.getAno());

        db.update(TABELA_CAMISAS, values, COLUNA_CODIGO + " = ?",
                new String[] {String.valueOf(camisas.getCodigo())});
    }

    public List<Camisas> listaTodasCamisas(){
        List<Camisas> listaCamisas = new ArrayList<Camisas>();

        String query = "SELECT * FROM " + TABELA_CAMISAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);

        if(c.moveToFirst()){
            do{
                Camisas camisas = new Camisas();

                camisas.setCodigo(Integer.parseInt(c.getString(0)));
                camisas.setTime(c.getString(1));
                camisas.setJogador(c.getString(2));
                camisas.setNumeroJogador(Integer.parseInt(c.getString(3)));
                camisas.setTamanho(c.getString(4));
                camisas.setAno(Integer.parseInt(c.getString(5)));

                listaCamisas.add(camisas);
            } while(c.moveToNext());
        }

        return listaCamisas;
    }
}