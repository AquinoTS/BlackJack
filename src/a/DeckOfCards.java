package a;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Inicia o baralho que será usado durante o jogo
public class DeckOfCards {
   private Card[] deck; // array de objetos Card
   private int currentCard; // índice da próxima Carta a ser distribuída (0-51)
   private static final int NUMBER_OF_CARDS = 52; // constante do número de Cartas
   // gerador de números aleatórios seguro
   private static final SecureRandom randomNumbers = new SecureRandom();
   // ArrayList do naipe das cartas
   private static final List<String> suits = Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades");

// construtor preenche o baralho de Cartas
   public DeckOfCards() {
      deck = new Card[NUMBER_OF_CARDS]; // cria array de objetos Card
      currentCard = 0; // a primeira Carta distribuída será deck[0]

   // preenche o baralho com objetos Card e atribui valores e naipes às cartas
      for (int count = 0; count < deck.length; count++) {
         String face = getRandomCardFace(); // Atribui a face da carta com base no índice (13 índices)
         String suit = suits.get(count / 13); // Escolhe o naipe aleatoriamente
         int value = getValueForCard(face); // Chama o método para adquirir valor numérico da carta
         deck[count] = new Card(face, suit, value);
      }

      shuffle(); // Embaralha o baralho após criá-lo
   }

   private String getRandomCardFace() {
      // Gera um valor aleatório de 1 a 13 para representar a face da carta
      int randomValue = randomNumbers.nextInt(13) + 1; // +1 porque array começa em 0
      if (randomValue == 1) {
         return "Ace";
      } else if (randomValue == 11) {
         return "Jack";
      } else if (randomValue == 12) {
         return "Queen";
      } else if (randomValue == 13) {
         return "King";
      } else {
         return Integer.toString(randomValue);
      }
   }

// embaralha o baralho de Cartas com algoritmo de uma passagem
   public void shuffle() {
	   // a próxima chamada ao método dealCard deve começar em deck[0] novamente
      currentCard = 0;

      // Embaralha o baralho usando Collections.shuffle
      List<Card> cardList = Arrays.asList(deck);
      Collections.shuffle(cardList);
      cardList.toArray(deck);
   }

// Método para obter o valor de uma carta com base na face
   private int getValueForCard(String face) {
      if (face.equals("Ace")) {
         return 1; // "Ás" vale 1 ponto
      } else if (face.equals("Jack") || face.equals("Queen") || face.equals("King")) {
         return 10; // "J", "Q" e "K" valem 10 pontos
      } else {
         return Integer.parseInt(face); // Retorna o valor das cartas em INT
      }
   }

   // distribui uma Carta
   public Card dealCard() {
	   // determina se ainda há Cartas para serem distribuídas
      if (currentCard < deck.length)
         return deck[currentCard++]; // retorna a Carta atual no array
      else
         return null;  // retorna null para indicar que todas as Cartas foram distribuídas
   }

   public void dealInitialCards(Player player) {
      player.addCardToHand(dealCard());
      player.addCardToHand(dealCard());
   }

}