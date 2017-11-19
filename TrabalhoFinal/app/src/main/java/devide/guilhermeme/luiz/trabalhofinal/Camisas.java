package devide.guilhermeme.luiz.trabalhofinal;

public class Camisas {

    String jogador, tamanho, time;
    int codigo,numeroJogador, ano;

    //Construtor
    public Camisas() {

    }

    //Update e insert
    public Camisas(int _codigo, String _time, String _jogador, int _numeroJogador, String _tamanho, int _ano) {

        this.codigo = _codigo;
        this.time = _time;
        this.jogador = _jogador;
        this.numeroJogador = _numeroJogador;
        this.tamanho = _tamanho;
        this.ano = _ano;
    }

    public Camisas(String _time, String _jogador, int _numeroJogador, String _tamanho, int _ano) {

        this.time = _time;
        this.jogador = _jogador;
        this.numeroJogador = _numeroJogador;
        this.tamanho = _tamanho;
        this.ano = _ano;
    }


    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public int getNumeroJogador() {
        return numeroJogador;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNumeroJogador(int numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
