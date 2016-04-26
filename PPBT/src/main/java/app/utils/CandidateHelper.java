package app.utils;

/**
 * Created by Doublefinger on 4/25/16.
 */
public class CandidateHelper {
    public static int[] convert(String candidates){
        if(candidates.length() < 1)
            return null;

        int[] result = new int[candidates.length()];
        for(int i = 0; i < candidates.length(); i++) {
            result[i] = Character.getNumericValue(candidates.charAt(i));
        }
        return result;
    }
}
