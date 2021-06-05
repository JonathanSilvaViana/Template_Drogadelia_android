package br.com.drogadelia;

    //classe destinada aos produtos que serão comercializados dentro do app

public class Produto {

    //variáveis de dados principais
    private final String nomeProduto;
    private final String nomeMarca;
    private final String descricao;
    private final Double preco;



    public Produto(String nomeProduto, String nomeMarca, String descricao, Double preco)

    {

    this.nomeProduto = nomeProduto;
    this.nomeMarca = nomeMarca;
    this.descricao = descricao;
    this.preco = preco;

    }

    //obtenção de strings


    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getNomeMarca() {
        return nomeMarca;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getPreco() {
        return preco;
    }

}
