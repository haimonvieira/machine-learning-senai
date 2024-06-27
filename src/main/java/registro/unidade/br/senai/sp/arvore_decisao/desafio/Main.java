package registro.unidade.br.senai.sp.arvore_decisao.desafio;

public class Main {

    public static void main(String[] args) {

        //Definir os dados da árvore
        String[][] dados = {
                     //o segundo nao desta linha apenas para manter a simetria,
                //pois no nosso modelo nao tem
                {"Não", "Não", "Não", "Não levar guarda-chuva"},//folha 1
                {"Sim", "Não","Não", "Levar guarda-chuva"},//folha 2
                {"Sim", "Sim", "Sim", "Vá de carro"},//folha 3
                {"Sim", "Sim", "Não", "Levar capa"}//folha4

        };


        //Criar a árvore de decisão
        No raiz = ArvoreDecisao.construirArvore(dados);

        //Desenhar árvore e exibir no frame
        ArvoreDecisao.desenharArvore(raiz);

    }

}
