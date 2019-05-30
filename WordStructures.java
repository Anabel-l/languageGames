import java.util.*;

public class WordStructures {

	public static Scanner kbr = new Scanner(System.in);
	public static Scanner kbr2 = new Scanner(System.in);
	private int langDiversity = 3;
	private String C = "1";
	private String V = "0";
	private String[][] vowels = { { "a", "á", "à", "aa", "a'a", "ai", "ae", "ao", "au" /* A (if you need labeling for this part, you're an idiot) */ },
			{ "e", "é","ee", "è", "ei", "ea", "eu", "eo" /* E */ },
			{ "o", "oa","oo", "oi", "ou", "oe", "ò", "ó"/* O */ },
			{ "u", "ue", "uu","ua", "ui", "uo", "ú", "ù" /* U */ },
			{ "i", "ia", "ii","ie", "io", "iu", "i'i", "ì", "í"/* I */ }, { "'", " ", "" /* Silence */ } };

	private String[] vowelset;
	private String[] conset;
	private String[][] consonants = { { "b", "d", "v", "g"/* hard-soft */ },
			{ "w", "l", "m", "n", "r", "s", "y"/* soft */ }, { "c", "z", "s" /* s-ish */ },
			{ "h", "f", "th", "v", "z", "j"/* th-ish */ }, { "q", "k", "c", "t", "p", "x" /* hard */ },
			{ "sh", "j", "wh", "f", "s"/* super soft */ },
			{ "b", "c", "d", "g", "l", "m", "n", "p", "r", "s", "t"/* normality */ },
			{ "d", "g", "l", "n", "r", "s", "t"/* super-normal */ },
			{ "st", "dj", "tr", "dr", "sd", "kj", "kr", "cr", "ck", "rx"/* doubles */ } };
	private String[] wordStructure;

	public WordStructures() {
		vowelset = new String[vowels.length * langDiversity];
		conset = new String[consonants.length * langDiversity];
		wordStructure = new String[langDiversity * 2];
		fillVowels();
		fillConsonants();
		autoFillWordStructure();
		System.out.println(".......generating.......");
	}

	public void fillVowels() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (int i = 0; i < vowels.length; i++) {
				int random = (int) (Math.random() * 101);
				if (i != vowels.length - 1) {
					if (random < 90) {
						vowelset[a] = vowels[i][0];
					} else {
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					}
				} else {
					if (random < 5) {
						vowelset[a] = vowels[i][(int) (Math.random() * vowels[i].length)];
					} else {
						vowelset[a] = vowels[i - 3][(int) (Math.random() * vowels[i].length)];
					}
				}
				a++;
			}
		}
	}

	public void changeLanguage() {
		fillConsonants();
		fillVowels();
		autoFillWordStructure();
	}

	public void fillConsonants() {
		int a = 0;
		for (int b = 0; b < langDiversity; b++) {
			for (int i = 0; i < consonants.length; i++) {
				conset[a] = consonants[i][(int) (Math.random() * consonants[i].length)];
				a++;
			}
		}
	}

	public String makeSentence() {
		String sentence = "";
		String firstWord = makeWord();
		sentence += firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
		for (int i = 0; i <= (int) (Math.random() * 7) + 3; i++) {
			sentence += " " + makeWord();
		}
		int random = (int) (Math.random() * 10);
		if (random == 6) {
			sentence += "?";
		} else if (random == 7) {
			sentence += "!";
		} else {
			sentence += ".";
		}
		return sentence;
	}

	public String makePoemLine() {
		String sentence = "";
		String firstWord = makeWord();
		sentence += firstWord.substring(0, 1).toUpperCase() + firstWord.substring(1);
		for (int i = 0; i <= (int) (Math.random() * 3); i++) {
			sentence += " " + makeWord();
		}
		int random = (int) (Math.random() * 10);
		if (random == 6) {
			sentence += "?";
		} else if (random == 7) {
			sentence += "!";
		} else {
			sentence += ".";
		}
		return sentence;
	}

	public String getConsonant() {
		return conset[(int) (Math.random() * conset.length)];
	}

	public String getVowel() {
		return vowelset[(int) (Math.random() * vowelset.length)];
	}

	private String getConsonant(int lang) {
		return consonants[lang][(int) (Math.random() * consonants[lang].length)];
	}

	private String getVowel(int lang) {
		return vowels[lang][(int) (Math.random() * vowels[lang].length)];
	}

	private String addLetter(int probability /* of 1 (c) Out of 100 */) {
		int random = (int) (Math.random() * 100) + 1;
		if (random < probability) {
			return C; // c
		} else {
			return V; // v
		}
	}

	public void autoFillWordStructure() {
		for (int i = 0; i < wordStructure.length; i++) {
			String word = "";
			int length;
			int randomize = (int) (Math.random() * 100) + 1;
			if (randomize <= 75)
				length = (int) (Math.random() * 4) + 1;
			else if (randomize <= 80)
				length = (int) (Math.random() * 3) + 4;
			else if (randomize <= 90)
				length = (int) (Math.random() * 3) + 7;
			else
				length = 2;
			
			for (int b = 0; b < length; b++) {
				if(b==0 && length==1){
					word+=addLetter(0);
				} else if (b != 0 && word.substring(b - 1, b).equalsIgnoreCase(C)) {
					word += addLetter(5);
				} else if (b != 0 && word.substring(b - 1, b).equalsIgnoreCase(V)) {
					word += addLetter(90);
				} else {
					word += addLetter(50);
				}
			}
			wordStructure[i] = word;
		}
	}

	public String makeWord() {
		String word = "";
		int num = (int) (Math.random() * wordStructure.length);
		for (int i = 0; i < wordStructure[num].length(); i++) {
			if (wordStructure[num].substring(i, i + 1).equals(C)) {
				word += getConsonant();
			} else {
				word += getVowel();
			}
		}
		return word;
	}
}