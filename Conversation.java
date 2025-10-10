import java.util.ArrayList;
import java.util.Scanner;



class Conversation implements ConversationRequirements {

  // Attributes 
  private int roundsLeft; // number of rounds left in the conversation
  private ArrayList<String> transcript; // transcript of the conversation
  private final ArrayList<String> cannedResponses;
  private ArrayList<String> userInput;
  private String lowerInput;
  boolean mirrored;
  private String lastWord;

  /**
   * Constructor 
   */
  Conversation() {
    roundsLeft = 0;
    transcript = new ArrayList<>();
    cannedResponses = new ArrayList<>();
      cannedResponses.add("Interesting, tell me more.");
      cannedResponses.add("Hmmm.");
      cannedResponses.add("Do you really think so?");
      cannedResponses.add("You don't say.");
      cannedResponses.add("That's quite fascinating.");
      cannedResponses.add("Oh, I see.");
      cannedResponses.add("Could you explain that?");
    userInput = new ArrayList<>();
    lowerInput = "";
    mirrored = false;
  }

  /**
   * Starts and runs the conversation with the user
   */
  @Override
  public void chat() {
      Scanner scanner = new Scanner(System.in);
      transcript = new ArrayList<>();
          
      System.out.println("Hello, how many rounds would you like to play?");
      transcript.add("Hello, how many rounds would you like to play?");
      this.roundsLeft = scanner.nextInt();
      transcript.add(Integer.toString(this.roundsLeft));
      scanner.nextLine();
      System.out.println("Great! Let's start our conversation. Say anything you want.");
      transcript.add("Great! Let's start our conversation. Say anything you want.");
      
      while(roundsLeft > 0) {
          String userLine = scanner.nextLine();
          transcript.add(userLine);
          String response = respond(userLine);
          System.out.println(response);
          transcript.add(response);
          roundsLeft--;
      } 
    System.out.println("It was nice talking to you! Goodbye!");
  }

  /**
   * Prints transcript of conversation
   */
  @Override
  public void printTranscript() {
    System.out.println("Here is the transcript of our conversation:");
    for (String line : transcript) {
      System.out.println(line);
    }
  }

  /**
   * Gives appropriate response (mirrored or canned) to user input
   * @param inputString the users last line of input
   * @return mirrored or canned response to user input  
   */
  @Override
  public String respond(String inputString) {
    String returnString = ""; 
    mirrored = false;

    lowerInput = inputString.toLowerCase();

    userInput = new ArrayList<>();
    String[] words = lowerInput.split(" ");
    lastWord = words[words.length-1];
    if (lastWord.endsWith(".") || lastWord.endsWith("!") || lastWord.endsWith("?") || lastWord.endsWith("~")){
      lastWord = lastWord.substring(0,lastWord.length() - 1);
      words[words.length - 1] = lastWord;
    }
    java.util.Collections.addAll(userInput, words);

    for (int i = 0; i < words.length; i++) {
      if (words[i].equals("i")) {
        words[i] = "you";
        mirrored = true;
      } else if (words[i].equals("me")) {
        words[i] = "you";
        mirrored = true;
      } else if (words[i].equals("my")) {
        words[i] = "your";
        mirrored = true;
      } else if (words[i].equals("am")) {
        words[i] = "are";
        mirrored = true;
      } else if (words[i].equals("you")) {
        words[i] = "I";
        mirrored = true;
      } else if (words[i].equals("your")) {
        words[i] = "my";
        mirrored = true;
      } else if (words[i].equals("i'm")) {
        words[i] = "you're";
        mirrored = true;
      }
      else if (words[i].equals("you're")) {
        words[i] = "I'm";
        mirrored = true;
      }
    }

    if (mirrored) {
      for (String word : words) {
        returnString += word + " ";
      }
      returnString = Character.toUpperCase(returnString.charAt(0)) + returnString.substring(1).trim() + "?";
    } else {
      int randomIndex = (int) (Math.random() * cannedResponses.size());
      returnString = cannedResponses.get(randomIndex);
    }
    
    return returnString; 
  }

  public static void main(String[] arguments) {

    Conversation myConversation = new Conversation();
    myConversation.chat();
    myConversation.printTranscript();

  }
}
