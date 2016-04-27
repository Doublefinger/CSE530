package app.controllers;

import app.controllers.authorization.Protected;
import app.models.Tweet;
import app.utils.AmericanStates;
import app.utils.CandidateHelper;
import app.utils.JsonHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Doublefinger on 4/25/16.
 */

@Protected
public class TweetController extends BaseController {
    public void getCount() {
        Map<String, Integer> result = new HashMap<>();
        String candidateIndice = param("candidates");
        String[] candidates = CandidateHelper.convert(candidateIndice);
        for (int i = 0; i < candidates.length; i++) {
            result.put(candidates[i], Tweet.count("candidate = ?",  Character.getNumericValue(candidateIndice.charAt(i))).intValue());
        }
        respond(JsonHelper.fromMap(result));
    }

    public void getCountByRetweetCount() {
        String count = param("count");
        Map<String, Integer> result = new HashMap<>();
        String candidateIndice = param("candidates");
        String[] candidates = CandidateHelper.convert(candidateIndice);
        for (int i = 0; i < candidates.length; i++) {
            result.put(candidates[i], Tweet.count("candidate = ? and retweet_count >= ?",
                    Character.getNumericValue(candidateIndice.charAt(i)), Integer.parseInt(count)).intValue());
        }
        respond(JsonHelper.fromMap(result));
    }

    public void getCountByVerified() {
        Map<String, Integer> result = new HashMap<>();
        String candidateIndice = param("candidates");
        String[] candidates = CandidateHelper.convert(candidateIndice);
        for (int i = 0; i < candidates.length; i++) {
            result.put(candidates[i], Tweet.count("candidate = ? and (retweeted != 0 or favorited != 0)",  Character.getNumericValue(candidateIndice.charAt(i))).intValue());
        }
        respond(JsonHelper.fromMap(result));
    }

    public void getPlace() {
        Map<String, Map<String, Integer>> result = new HashMap<>();
        String candidateIndice = param("candidates");
        String[] candidates = CandidateHelper.convert(candidateIndice);
        Pattern p = Pattern.compile("\'([^\']+)\'");
        for (int i = 0; i < candidates.length; i++) {
            List<Tweet> list = Tweet.find("candidate = ? and place != \"\"", Character.getNumericValue(candidateIndice.charAt(i)));
            Map<String, Integer> tempMap = new HashMap<>();
            for (Tweet t : list) {
                String place = t.getString("place");
                int index = place.indexOf("full_name");
                String temp = place.substring(index);
                Matcher m = p.matcher(temp);
                if (m.find()) {
                    place = m.group(1);
                } else {
                    place = "";
                }
                place = AmericanStates.searchForState(place);
                if (tempMap.containsKey(place)) {
                    tempMap.put(place, tempMap.get(place) + 1);
                } else {
                    tempMap.put(place, 1);
                }
            }
            result.put(candidates[i], tempMap);
        }
        respond(JsonHelper.fromMap(result));
    }
}
