package trivia;

import java.util.*;

// REFACTOR ME
public class GameBetter implements IGame {
   List<Player> players = new ArrayList<>();
   Map<Category, LinkedList<String>> questions = new HashMap<>();

   int currentPlayer = 0;

   public GameBetter() {
      Arrays.stream(Category.values()).forEach(c -> questions.put(c, new LinkedList<>()));

      for (int i = 0; i < 50; i++) {
         for (Category category : Category.values()) {
            questions.get(category).addLast(createQuestion(category, i));
         }
      }
   }

   public String createQuestion(Category category, int index) {
      return category.getLabel() + " Question " + index;
   }

   public boolean isPlayable() {
      return (howManyPlayers() >= 2);
   }

   public boolean add(String playerName) {
      Player player = new Player(playerName);
      players.add(player);

      System.out.println(playerName + " was added");
      System.out.println("They are player number " + players.size());
      return true;
   }

   public int howManyPlayers() {
      return players.size();
   }

   public void roll(int roll) {
      Player player = players.get(currentPlayer);

      System.out.println(player.getName() + " is the current player");
      System.out.println("They have rolled a " + roll);

      if (player.isInPenaltyBox()) {
         if (roll % 2 != 0) {
            player.setInPenaltyBox(false);

            System.out.println(players.get(currentPlayer) + " is getting out of the penalty box");
         } else {
            System.out.println(players.get(currentPlayer) + " is not getting out of the penalty box");
         }

      }

      if (!player.isInPenaltyBox()){

         player.setPlace((player.getPlace() + roll) % 12);

         System.out.println(players.get(currentPlayer)
                 + "'s new location is "
                 + player.getPlace());
         System.out.println("The category is " + currentCategory().getLabel());
         askQuestion();
      }

   }

   private void askQuestion() {
      String question = questions.get(currentCategory()).removeFirst();
      System.out.println(question);
   }


   private Category currentCategory() {
      int place = players.get(currentPlayer).getPlace();

      return Category.values()[place % 4];
   }

   public boolean wasCorrectlyAnswered() {
      Player player = players.get(currentPlayer);
      if (!player.isInPenaltyBox()) {
         System.out.println("Answer was correct!!!!");
         player.incrementPurse();
         System.out.println(players.get(currentPlayer)
                            + " now has "
                            + player.getPurse()
                            + " Gold Coins.");

         boolean winner = didPlayerWin();
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;

         return winner;
      } else {
         currentPlayer++;
         if (currentPlayer == players.size()) currentPlayer = 0;
         return true;
      }
   }

   public boolean wrongAnswer() {
      System.out.println("Question was incorrectly answered");
      System.out.println(players.get(currentPlayer) + " was sent to the penalty box");
      players.get(currentPlayer).setInPenaltyBox(true);

      currentPlayer++;
      if (currentPlayer == players.size()) currentPlayer = 0;
      return true;
   }


   private boolean didPlayerWin() {
      return !(players.get(currentPlayer).getPurse() == 6);
   }
}
