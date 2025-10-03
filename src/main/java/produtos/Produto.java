package produtos;

public class Produto {

    private int produtoID;
    private String produtoNome;
    private double produtoPreco;
    private int produtoQTD;

    public int getProdutoID() {
        return produtoID;
    }

    public void setProdutoID(int produtoID) {
        this.produtoID = produtoID;
    }

    public String getProdutoNome() {
        return produtoNome;
    }

    public void setProdutoNome(String produtoNome) {
        this.produtoNome = produtoNome;
    }

    public double getProdutoPreco() {
        return produtoPreco;
    }

    public void setProdutoPreco(double produtoPreco) {
        this.produtoPreco = produtoPreco;
    }

    public int getProdutoQTD() {
        return produtoQTD;
    }

    public void setProdutoQTD(int produtoQTD) {
        this.produtoQTD = produtoQTD;
    }

    @Override
    public String toString(){

        return  "Produto{" +
                  "produtoID" +produtoID+  '\''+
                  ", produtoNome" +produtoNome+ '\''+
                  ", produtoPreço" +produtoPreco+ '\''+
                  ", produtoQTD" +produtoQTD+ '\''+

                "}";


    }

}
