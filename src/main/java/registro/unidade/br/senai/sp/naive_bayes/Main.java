package registro.unidade.br.senai.sp.naive_bayes;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        //Método Simple Naive Bayes Classifier
        NaiveBayes naiveBayes = new NaiveBayes();

        //Dados para treino
        String[][] trainingData = {
                
                {"spam", "dinheiro facil, clique aqui"},
                {"spam", "divida ativa, use o link abaixo"},
                {"spam", "100% garantido. baixe agora."},
                {"spam", "python e melhor que java"},
                {"spam", "videos calientes"},
                {"spam", "veja estas dicas de como ganhar muito dinheiro em poucos minutos"},
                {"spam", "clique aqui para ganhar um iphone gratis"},
                {"spam", "parabens! voce foi selecionado para um premio especial"},
                {"spam", "compre agora e ganhe 50% de desconto"},
                {"spam", "voce precisa ver isso! clique agora"},
                {"spam", "encontre o amor da sua vida, inscreva-se aqui"},
                {"spam", "transmissao ao vivo do jogo, clique aqui"},
                {"spam", "descubra os segredos do universo, inscreva-se aqui"},
                {"spam", "milagre garantido, clique aqui para saber mais"},
                {"spam", "contribua para nossa campanha politica, clique aqui"},
                {"spam", "baixe este jogo agora e ganhe bonus"},
                {"spam", "baixe musicas ilimitadas gratuitamente"},
                {"spam", "assista filmes online gratis, clique aqui"},
                {"spam", "ganhe dinheiro rapido com este truque"},
                {"spam", "participe da nossa promocao e ganhe um carro"},
                {"spam", "oferta exclusiva, compre ja"},
                {"spam", "seu emprestimo foi aprovado, clique para mais detalhes"},
                {"spam", "voce ganhou uma viagem gratis, veja como"},
                {"spam", "atualize suas informacoes bancarias urgentemente"},
                {"spam", "ganhe seguidores no instagram instantaneamente"},
                {"spam", "receba atualizacoes de mercado, inscreva-se"},
                {"spam", "descubra como perder peso rapidamente"},
                {"spam", "ganhe criptomoedas de graca, saiba mais"},
                {"spam", "curso gratuito, inscreva-se agora"},
                {"spam", "veja como ganhar premios incriveis"},
                {"spam", "confira as ofertas de black friday"},
                {"spam", "clique aqui para desbloquear conteudo premium"},
                {"spam", "seu computador esta infectado, clique para limpar"},
                {"spam", "ofertas de emprego incriveis, clique aqui"},
                {"spam", "ganhe dinheiro com marketing afiliado"},
                {"spam", "promocao imperdivel, clique agora"},
                {"spam", "aprenda um novo idioma rapidamente"},
                {"spam", "assista series online gratuitamente"},
                {"spam", "oportunidade de investimento, saiba mais"},
                {"spam", "aumente sua pontuacao de credito agora"},
                {"spam", "participe do nosso sorteio e ganhe premios"},
                {"ham", "ola, tudo bem?"},
                {"ham", "reuniao, dia 25 de junho"},
                {"ham", "atividade de java"},
                {"ham", "trabalho escolar"},
                {"ham", "java e melhor que python"},
                {"ham", "vamos almocar juntos hoje?"},
                {"ham", "preciso de ajuda com o trabalho de matematica"},
                {"ham", "bom dia, voce esta disponivel para uma reuniao amanha?"},
                {"ham", "aqui esta o relatorio solicitado"},
                {"ham", "nao esqueca do nosso encontro na sexta-feira"},
                {"ham", "voce viu o ultimo episodio daquela serie?"},
                {"ham", "enviei o arquivo que voce pediu"},
                {"ham", "vamos assistir ao jogo de futebol hoje a noite?"},
                {"ham", "quem voce acha que vai ganhar a copa do mundo?"},
                {"ham", "qual e o seu time de futebol favorito?"},
                {"ham", "voce leu sobre a nova descoberta cientifica?"},
                {"ham", "o que voce acha da teoria da relatividade?"},
                {"ham", "estou estudando biologia molecular, e fascinante"},
                {"ham", "vamos ao culto no domingo?"},
                {"ham", "voce acredita em vida apos a morte?"},
                {"ham", "a fe pode mover montanhas"},
                {"ham", "voce vai votar nas proximas eleicoes?"},
                {"ham", "o que voce acha das novas politicas governamentais?"},
                {"ham", "a politica atual esta muito polarizada"},
                {"ham", "quer jogar algo hoje a noite?"},
                {"ham", "qual e o seu jogo favorito?"},
                {"ham", "estou no nivel 10 do meu jogo favorito"},
                {"ham", "voce gosta de ouvir musica classica?"},
                {"ham", "qual e a sua banda favorita?"},
                {"ham", "o concerto foi incrivel"},
                {"ham", "vamos ao cinema no sabado?"},
                {"ham", "qual filme voce recomenda?"},
                {"ham", "adorei o ultimo filme que assisti"},
                {"ham", "vamos nos encontrar na biblioteca para estudar?"},
                {"ham", "voce viu a ultima noticia sobre tecnologia?"},
                {"ham", "participe da nossa reuniao de projeto amanha"},
                {"ham", "voce pode me ajudar com o codigo de programacao?"},
                {"ham", "estou lendo um livro incrivel, voce gostaria de saber mais?"},
                {"ham", "vamos fazer uma caminhada no parque no domingo?"},
                {"ham", "voce conhece algum bom restaurante na cidade?"},
                {"ham", "que tal fazermos uma viagem no proximo feriado?"},
                {"ham", "estou assistindo a uma serie nova, voce ja viu?"},
                {"ham", "vamos jogar futebol no sabado?"},
                {"ham", "voce assistiu ao ultimo documentario sobre espaco?"},
                {"ham", "estou organizando um evento de caridade, voce quer participar?"},
                {"ham", "vamos sair para tomar um cafe?"},
                {"ham", "voce pode me enviar o material da ultima aula?"},
                {"ham", "qual e o seu livro favorito?"},
                {"ham", "vamos estudar juntos para o exame?"},
                {"ham", "voce ja ouviu falar sobre a nova exposicao no museu?"},
                {"ham", "estou pensando em adotar um cachorro, voce tem alguma dica?"},

        };

        //Encaminhar dados para análise
        naiveBayes.training(trainingData);
        String testMail = JOptionPane.showInputDialog("Insira sua mensagem de entrada" +
                " para testar o algoritmo de Laplace");
        String resultado = naiveBayes.classify(testMail);
        JOptionPane.showMessageDialog(null, "Resultado da classificação para '" + testMail +
                "': " + resultado);
        System.out.println("Resultado da classificação para \"" + testMail + "\": " +
                resultado);

    }

}
