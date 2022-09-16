import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class SentenceGenerator003_036 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        boolean keepRunning = true;
        SentenceGenerator sentenceGenerator = new SentenceGenerator();
        while( keepRunning ) {
            System.out.println("Available sentence generators:");
            System.out.println("\t1 - Random Sentence Generator");
            System.out.println("\t2 - Sorted Sentence Generator");
            System.out.println("\t3 - Ordered Sentence Generator");
            System.out.println("\t0 - Exit program: clearly not a sentence generation option");
            System.out.println("Pick a sentence generator : ");

            Integer optionPicked = takeIntInput();
            String whichGenerator = "";

            switch(optionPicked) {
                case 1: //RSG
                    whichGenerator += "Random Sentence Generator";
                    sentenceGenerator = new SentenceGenerator(new LowercaseAdd(), new RandomGeneration());
                    break;
                case 2: //SSG
                    whichGenerator += "Sorted Sentence Generator";
                    sentenceGenerator = new SentenceGenerator(new LowercaseAdd(), new SortedGeneration());
                    break;
                case 3: //OSG
                    whichGenerator += "Ordered Sentence Generator";
                    sentenceGenerator = new SentenceGenerator(new UppercaseReversedAdd(), new OrderedGeneration());
                    break;
                case 0:
                    keepRunning = false;
                    continue;
                default:
                    System.out.println("Invalid option. Pick again.");
                    continue;
            }

            handleSentenceGeneration(sentenceGenerator, whichGenerator);
        }
    }

    private static void handleSentenceGeneration(SentenceGenerator sentenceGenerator, String whichGenerator) {
        boolean keepRunning = true;

        while(keepRunning) {
            System.out.println(whichGenerator.toUpperCase());
            System.out.println("Sentence generator related options: ");
            System.out.println("\t1 - Add a word to vocabulary");
            System.out.println("\t2 - View words in vocabulary");
            System.out.println("\t3 - Generate a sentence and print");
            System.out.println("\t0 - Go back");
            System.out.println("Pick an option:");

            Integer optionPicked = takeIntInput();

            switch(optionPicked) {
                case 1: //add word
                    System.out.println("Enter word to add:");
                    String word = takeStringInput();
                    sentenceGenerator.addWord(word);
                    break;
                case 2: //view words
                    System.out.println("Words in vocabulary:");
                    System.out.println(sentenceGenerator.vocabulary);
                    break;
                case 3: //generate sentence
                    String generatedSentence = sentenceGenerator.generateSentence();
                    System.out.println(generatedSentence);
                    break;
                case 0:
                    keepRunning = false;
                    break;
                default:
                    System.out.println(optionPicked);
                    System.out.println("Invalid option. Pick again.");
                    break;
            }
        }

        return;
    }

    private static String takeStringInput() {

        String toReturn = "";

        try{
            toReturn = input.next();
            if(input.hasNextLine()) input.nextLine();
        } catch(Exception e) {
            System.out.println("Invalid input.");
            if(input.hasNext()) input.nextLine();
        }

        return toReturn;
    }

    private static Integer takeIntInput() {
        Integer toReturn = -1;

        try{
            while(!input.hasNextInt()) {
                input.next();
            }
            toReturn = input.nextInt();
            if(input.hasNextLine()) input.nextLine();
        } catch(Exception e) {
            System.out.println("Invalid input. Choose an integer.");
            System.out.println(e.toString());
            if(input.hasNextLine()) input.nextLine();
        }
        return toReturn;
    }


}

interface AddToVocabularyBehaviour {

    void add(String word, List<String> vocabulary);
}

interface GenerateSentenceBehaviour {

    String generate(List<String> vocabulary);
}

class LowercaseAdd implements AddToVocabularyBehaviour {

    @Override
    public void add(String word, List<String> dict)
    {
        word = word.toLowerCase();
        dict.add(word);
    }

}

class UppercaseReversedAdd implements AddToVocabularyBehaviour{

    @Override
    public void add(String word, List<String> vocabulary) {
        StringBuilder builder = new StringBuilder(word);
        builder.reverse();
        word = builder.toString();
        word = word.toUpperCase();
        vocabulary.add(word);
    }
}

class RandomGeneration implements GenerateSentenceBehaviour{

    @Override
    public String generate(List<String> vocabulary) {
        List<Integer> posPicked = Helper.generateRandomListIndex(vocabulary);

        int pos = 0;
        String sentence = "";
        int sentenceSize = posPicked.size();
        for(Integer i: posPicked){
            if(pos < sentenceSize){
                sentence = sentence + vocabulary.get(i) + " ";
            }
            else{
                sentence = sentence + vocabulary.get(i);
            }
            pos++;
        }

        return sentence;
    }
}

class OrderedGeneration implements GenerateSentenceBehaviour{

    @Override
    public String generate(List<String> vocabulary) {
        String toReturn = "";
        for(int i=0; i < vocabulary.size(); i++){
            if(i < vocabulary.size() - 1)
                toReturn = toReturn + vocabulary.get(i) + " ";
            else
                toReturn = toReturn + vocabulary.get(i);
        }
        return toReturn;
    }
}

class SortedGeneration implements GenerateSentenceBehaviour {
    @Override
    public String generate(List<String> vocabulary) {
        String toReturn = "";
        List<Integer> posPicked = Helper.generateRandomListIndex(vocabulary);

        List<String> words = new ArrayList<String>();
        for(Integer i: posPicked) {
            words.add(vocabulary.get(i));
        }
        Collections.sort(words);

        int count = 0;
        for(String word: words) {
            if(count < posPicked.size() - 1) {
                toReturn += word + " ";
            } else {
                toReturn += word;
            }
            count++;
        }

        return toReturn;
    }
}


class SentenceGenerator {
    List<String> vocabulary = new ArrayList<String>()   ;
    AddToVocabularyBehaviour addToVocabularyBehaviour;
    GenerateSentenceBehaviour generateSentenceBehaviour;

    public SentenceGenerator(){

    }

    public SentenceGenerator(AddToVocabularyBehaviour _AddToDictionaryBehaviour, GenerateSentenceBehaviour _GenerateSentenceBehaviour) {
        this.addToVocabularyBehaviour = _AddToDictionaryBehaviour;
        this.generateSentenceBehaviour = _GenerateSentenceBehaviour;
    }


    String generateSentence()
    {
        if(generateSentenceBehaviour.generate(vocabulary).isEmpty())
            return "ERROR: Vocabulary Empty";

        return generateSentenceBehaviour.generate(vocabulary);
    }

    void addWord(String word)
    {
        addToVocabularyBehaviour.add(word, vocabulary);
    }
}

class Helper {

    public static List<Integer> generateRandomListIndex(List<String> vocabulary) {
        int dictionarySize = vocabulary.size();
        int sentenceSize = new Random().nextInt(dictionarySize) + 1;
        Set<Integer> set = new HashSet<Integer> ();

        while(set.size() < sentenceSize) {
            int random = new Random().nextInt(dictionarySize);
            set.add(random);
        }

        ArrayList<Integer> posPicked = new ArrayList<Integer>();
        for (Integer i : set) {
            posPicked.add(i);
        }
        Collections.shuffle(posPicked);
        return posPicked;
    }
}

