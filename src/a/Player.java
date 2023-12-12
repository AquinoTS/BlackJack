package a;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name; // Nome do jogador
    private List<Card> hand; // Mao do jogador (ArrayList do objeto carta)

    // Construtor que aceita um nome
    public Player(String name) {
        this.name = name; // Constrói um nome do jogador recebido por parametro
        hand = new ArrayList<>(); // Cria uma nova ArrayList para receber a mao do jogador
    }

    // Método para adicionar carta à mão do jogador
    public void addCardToHand(Card card) {
        hand.add(card); /*
                         * Adiciona carta ao ArrayList (diferença para o array é que este é dinâmico,
                         * podendo receber itens sem limite) da mão
                         */
    }

    // Pega
    public List<Card> getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }

    // Metódo para limpar a ArrayList <Hand>
    public void clearHand() {
        hand.clear();
    }

    // Método para calcular o valor da mão
    public int calculateHandValue() {
        int handValue = 0;
        int numberOfAces = 0; // Contador para Ás

        for (Card card : hand) {
            handValue += card.getValue(); // Somador para obter o valor total da mão com as respectivas cartas

            // Verifica se há Ás (cartas com valor 1)
            if (card.getValue() == 1) {
                numberOfAces++;
            }
        }

        // Se o valor da mão for menor ou igual a 11 e houver Ás, conte um Ás como 11
        while (numberOfAces > 0 && handValue <= 11) {
            handValue += 11; // Conta um Ace como 11
            numberOfAces--; // Diminui o contador (auxiliar)
        }

        return handValue;
    }

    // Método simples para mostrar a mão do jogador
    public void showDeck() {
        System.out.println(this.getName() + "'s Hand:"); // Impressão melhor
        List<Card> hand = this.getHand(); // Set auxiliary ArrayList hand to getHand (hand = new ArrayList<>())
        for (Card card : hand) { // For each iteration of the loop, card will hold the current card object from
                                 // the hand collection, allowing you to perform operations or access properties
                                 // of that card within the loop.
            System.out.println(card);
        }
        System.out.println(this.getName() + "'s Hand's value:" + " [" + calculateHandValue() + "]\n");
    }

}
