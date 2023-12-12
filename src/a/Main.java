package a;

import java.util.ArrayList;
import java.util.List;

// Definição da estrutura do programa: Main > BlackJackGame > Player > DeckOfCards > Card
public class Main {

    public static void main(String[] args) {

        // Inicializa um novo jogo de Blackjack
        Game game = new Game(); 

        // Define o número de jogadores
        int qtt_player = 2;
        
        System.out.println("Welcome to the BLACKJACK Game!");
        
        System.out.println("--------------------------------");

        // Cria uma lista para armazenar os jogadores
        List<Player> players = new ArrayList<>(); 

        // Cria e adiciona jogadores ao jogo
        for (int i = 1; i <= qtt_player; i++) {
            Player player = new Player("Player " + i); // Cria um novo jogador
            players.add(player); // Adiciona o jogador à lista de jogadores
            game.addPlayer(player); // Adiciona o jogador ao jogo
        }

        // Obtém o dealer (jogador especial)
        Player dealer = game.getDealer(); 

        // Distribui as cartas iniciais aos jogadores
        game.dealInitialHands(); 

        // Executa uma rodada do jogo
        game.playRound(); 

        // Exibe a mão dos jogadores
        for (Player player : players) {
            System.out.println(player.getName() + "'s Hand:");
            for (Card card : player.getHand()) {
                System.out.println(card); // Imprime cada carta na mão do jogador dentro do jogo
            }
        }

        // Exibe a mão do Dealer
        System.out.println("\nDealer's hand: ");
        for (Card card : dealer.getHand()) {
            System.out.println(card); // Imprime cada carta na mão do jogador dentro do jogo
        }

        // Determina o vencedor do jogo e imprime o resultado
        Player winner = determineWinner(players, dealer); 
        if (winner == null) {
            System.out.println("It's a tie!");
        } else if (winner == dealer) {
            System.out.println("Dealer wins");
        } else {
            System.out.println(winner.getName() + " wins!");
        }
    }

    // Método para determinar o vencedor (ou empate) do jogo
    private static Player determineWinner(List<Player> players, Player dealer) {
        Player winner = null;
        int dealerValue = dealer.calculateHandValue();

        // Itera sobre os jogadores para verificar quem ganhou
        for (Player player : players) {
            int playerValue = player.calculateHandValue();
            if (playerValue <= 21) {
                if (dealerValue > 21 || playerValue > dealerValue) {
                    winner = player; // O jogador ganhou ou o dealer estourou
                } else if (playerValue == dealerValue) {
                    winner = null; // Empate
                } else if (dealerValue > playerValue) {
                    winner = dealer; 
                }
            } else if (dealerValue <= 21) {
                winner = dealer;
            }
        }

        return winner; // Retorna o ganhador ou empate
    }
}
