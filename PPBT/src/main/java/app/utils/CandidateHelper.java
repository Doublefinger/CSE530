package app.utils;

/**
 * Created by Doublefinger on 4/25/16.
 */
public class CandidateHelper {
    private static String[] names = {"trump", "cruz", "bernie", "clinton"};
    public static String[] convert(String candidates){
        if(candidates.length() < 1)
            return null;

        String[] result = new String[candidates.length()];
        for(int i = 0; i < candidates.length(); i++) {
            int index = Character.getNumericValue(candidates.charAt(i)) - 1;
            result[i] = names[index];
        }
        return result;
    }
}
