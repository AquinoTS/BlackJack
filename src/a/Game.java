package a;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Classe com os metodos de análise dos jogos
public class Game {
    private DeckOfCards deck; // Inicializa o deck criado na classe DeckOfCards
    private List<Player> players; 
    private Player dealer; 
    private Scanner scanner; 

    public Game() {
        deck = new DeckOfCards(); 
        players = new ArrayList<>(); 
        dealer = new Player("Dealer"); 
        scanner = new Scanner(System.in); // Incializa o scan que será a ação a ser tomada
    }

    // Método para adicionar player ao jogo
    public void addPlayer(Player player) {
        players.add(player);
    }

    // Método para entregar a mão inicial
    public void dealInitialHands() {
        for (Player player : players) {
            player.clearHand(); 
                               
            player.addCardToHand(deck.dealCard());
                                                   
            player.addCardToHand(deck.dealCard()); 
        }
        dealer.clearHand(); 
        dealer.addCardToHand(deck.dealCard()); 
        dealer.addCardToHand(deck.dealCard()); 
    }


    public void playRound() {
        for (Player player : players) { 
            while (true) { 
                System.out.println(player.getName() + ", do you want to (1) Hit, (2) Stand, or (3) Show deck? ");
                int choice = scanner.nextInt(); 
                if (choice == 1) {
                    playerHit(player); // Chama método de sacar (Hit)
                    if (player.calculateHandValue() > 21) {
                        System.out.println(player.getName() + " Busted! (value:" + player.calculateHandValue() + ")");
                        break; // Se o jogador atingir 21 ou mais, ele não pode mais sacar.
                    }
                } else if (choice == 2) {
                    break; // O jogador optou por "stand".
                } else if (choice == 3) {
                    player.showDeck(); // Não é necessário passar o jogador como argumento aqui
                }
            }
        }

        // Verifica se todos os jogadores estouraram
        boolean allPlayersBusted = true;
        for (Player player : players) {
            if (player.calculateHandValue() <= 21) {
                allPlayersBusted = false;
                break;
            }
        }

        // Se todos os jogadores estouraram, encerre a rodada sem a lógica do dealer
        if (allPlayersBusted) {
            return;
        }

        // Lógica do dealer (ele saca até chegar a 17 ou mais, como não há apostas a
        // rodada simplesmente encerra)
        while (dealer.calculateHandValue() < 17) {
            playerHit(dealer);
            dealer.showDeck();
        }

        // Quando o dealer para de sacar, a rodada está completa.
    }

    // Método para jogador que decide jogar mais (Hit)
    private void playerHit(Player player) {
        player.addCardToHand(deck.dealCard()); // Adiciona uma carta à mão do jogador
        System.out.println(player.getName() + " received a card.");
    }

    // gets
    public Player getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() { // Esta é a lógica de um get para ArrayList
        return players;
    }
}