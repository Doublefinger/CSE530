package app.utils;

/**
 * Created by Doublefinger on 4/26/16.
 */
public class AmericanStates {
    public static String[] abbreviations =
            {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FL",
                    "GA", "GU", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA",
                    "ME", "MD", "MH", "MA", "MI", "FM", "MN", "MS", "MO", "MT",
                    "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "MP", "OH",
                    "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX",
                    "UT", "VT", "VA", "VI", "WA", "WV", "WI", "WY"};
    public static String[] states =
            {"Alabama", "Alaska", "American Samoa", "Arizona", "Arkansas",
                    "California", "Colorado", "Connecticut", "Delaware",
                    "Dist. of Columbia", "Florida", "Georgia", "Guam",
                    "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa",
                    "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
                    "Marshall Islands", "Massachusetts", "Michigan", "Micronesia",
                    "Minnesota", "Mississippi", "Missouri", "Montana",
                    "Nebraska", "Nevada", "New Hampshire", "New Jersey",
                    "New Mexico", "New York", "North Carolina", "North Dakota",
                    "Northern Marianas", "Ohio", "Oklahoma", "Oregon", "Palau",
                    "Pennsylvania", "Puerto Rico", "Rhode Island", "South Carolina",
                    "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
                    "Virginia", "Virgin Islands", "Washington", "West Virginia",

                    "Wisconsin", "Wyoming"};
    public static String searchForState(String name) {
        for(int i = 0; i < abbreviations.length; i++){
            if(name.contains(abbreviations[i])){
                return abbreviations[i];
            }
        }
        for(int i = 0; i < states.length; i++){
            if(name.contains(states[i])){
                return abbreviations[i];
            }
        }
        return "Outside";
    }
}
