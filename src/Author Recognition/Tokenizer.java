import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Erdem on 3/16/2016.
 *
 * A basic tokenizer class gets rid of all nonsense characters.
 *
 * ASD-asd becomes Asdasd
 * asd.asd becomes asdasd
 * 1920.3 doesnt change since it is double
 */
public class Tokenizer {

   static String [] stopwords = {
            "acaba",
            "altmýþ",
            "altý",
            "ama",
            "bana",
            "bazý",
            "belki",
            "ben",
            "benden",
            "beni",
            "benim",
            "beþ",
            "bin",
            "bir",
            "biri",
            "birkaç",
            "birkez",
            "birþey",
            "birþeyi",
            "biz",
            "bizden",
            "bizi",
            "bizim",
            "bu",
            "buna",
            "bunda",
            "bundan",
            "bunu",
            "bunun",
            "da",
            "daha",
            "dahi",
            "de",
            "defa",
            "diye",
            "doksan",
            "dokuz",
            "dört",
            "elli",
            "hem",
            "hep",
            "hepsi",
            "her",
            "hiç",
            "iki",
            "ile",
            "ise",
            "için",
            "katrilyon",
            "kez",
            "ki",
            "kim",
            "kimden",
            "kime",
            "kimi",
            "kýrk",
            "milyar",
            "milyon",
            "mu",
            "mü",
            "mý",
            "nasýl",
            "ne",
            "neden",
            "nerde",
            "nerede",
            "nereye",
            "niye",
            "niçin",
            "on",
            "ona",
            "ondan",
            "onlar",
            "onlardan",
            "onlari",
            "onlarýn",
            "onu",
            "sanki",
            "sekiz",
            "seksen",
            "sen",
            "senden",
            "seni",
            "senin",
            "siz",
            "sizden",
            "sizi",
            "sizin",
            "trilyon",
            "tüm",
            "ve",
            "veya",
            "ya",
            "yani",
            "yedi",
            "yetmiþ",
            "yirmi",
            "yüz",
            "çok",
            "çünkü",
            "üç",
            "þey",
            "þeyden",
            "þeyi",
            "þeyler",
            "þu",
            "þuna",
            "þunda",
            "þundan",
            "þunu"};




    /*
    * tokenize a whole group of words including the new lines and tab blanks and such
    */
    public static String tokenizer(String words){
        StringBuilder builder = new StringBuilder();
        Scanner scanWords = new Scanner(words);
        while (scanWords.hasNextLine()){
            String line = scanWords.nextLine();
            Scanner scanLine = new Scanner(line);
            while (scanLine.hasNext()){
                String potentialToken = scanLine.next();
                String token = tokenizeString(potentialToken);
             //   if(!Arrays.asList(stopwords).contains(token)){
                builder.append(token);
                builder.append(" ");
            //}
            }
            scanLine.close();
        }
        scanWords.close();

        return builder.toString();
    }

    /*
     *  check a potential token is really a pretty token, if not avoid unnecessary chars and create a good token  out of it
     */
    private static String tokenizeString(String potentialToken) {
        StringBuilder stringBuilder = new StringBuilder();
        if (potentialToken.length() > 0) { // parseDouble thinks "1980." a double. avoid that.
            if (!Character.isDigit(potentialToken.charAt(potentialToken.length() - 1)) && !Character.isLetter(potentialToken.charAt(potentialToken.length() - 1))) {
                potentialToken = potentialToken.substring(0, potentialToken.length() - 1);
            }
        }
        if(isDouble(potentialToken) && isDouble(potentialToken.replace(",","."))){

            return potentialToken;
        }
        else{
            for (int j = 0; j < potentialToken.length(); j++) {
                if (Character.isDigit(potentialToken.charAt(j)) || Character.isLetter(potentialToken.charAt(j))) {

                    stringBuilder.append(potentialToken.charAt(j));
                }
            }


        }
        return stringBuilder.toString();
    }





    /*
     * checks a string is a double returns accordingly
     */
    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException nfe) {}
        return false;
    }
}
