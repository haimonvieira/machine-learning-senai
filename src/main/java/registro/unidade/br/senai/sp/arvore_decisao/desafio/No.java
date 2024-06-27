package registro.unidade.br.senai.sp.arvore_decisao.desafio;

/*
Aqui é feito a estrutura do nó da árvore da decisão.
Onde o nó tem a pergunta objetiva e a resposta binária (sim ou não)
e a resposta na folha.
 */

public class No {

    String pergunta;
    String decisao;
    No verdadeiro;
    No falso;

    public No(String pergunta){
        this.pergunta = pergunta;
    }

    public No(String pergunta, String decisao){
        this.pergunta = pergunta;
        this.decisao = decisao;
    }

    public void definirFilhos (No verdadeiro, No falso){
        this.verdadeiro = verdadeiro;
        this.falso = falso;
    }

}
