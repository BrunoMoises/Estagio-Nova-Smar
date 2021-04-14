package estoque;
public class Estoque {
    private String codigo;
    private String produto;
    private float valor;
    private int qtdeProduto; //quantidade do produto em estoque

    public Estoque(String codigo, String produto, float valor, int qtdeProduto) {
        this.codigo = codigo;
        this.produto = produto;
        this.valor = valor;
        this.qtdeProduto = qtdeProduto;
    }
    
    public Estoque() {
        this.codigo = "sem codigo";
        this.produto = "sem produto";
        this.valor = 0;
        this.qtdeProduto = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQtdeProduto() {
        return qtdeProduto;
    }

    public void setQtdeProduto(int qtdeProduto) {
        this.qtdeProduto = qtdeProduto;
    }

    @Override
    public String toString() {
        return "Estoque{" + "codigo=" + codigo + ", produto=" + produto + ", valor=" + valor + ", qtdeProduto=" + qtdeProduto + '}';
    }
}
