package com.meisshi.meisshi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by agustin on 13/11/2017.
 */

public class Text {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_FACEBOOK_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_INSTAGRAM_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_TWITTER_REGEX =
            Pattern.compile("^@[A-Z0-9._%+-]+$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_WEBSITE_REGEX =
            Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_TELEPHONE_REGEX =
            Pattern.compile("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]", Pattern.CASE_INSENSITIVE);

    public static boolean isEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    public static boolean isFacebook(String facebook) {
        Matcher matcher = VALID_FACEBOOK_REGEX.matcher(facebook);
        return matcher.find();
    }
    public static boolean isTwitter(String twitter) {
        Matcher matcher = VALID_TWITTER_REGEX .matcher(twitter);
        return matcher.find();
    }
    public static boolean isInstagram(String instagram) {
        Matcher matcher = VALID_INSTAGRAM_REGEX .matcher(instagram);
        return matcher.find();
    }
    public static boolean isWebsite(String website) {
        Matcher matcher = VALID_WEBSITE_REGEX .matcher(website);
        return matcher.find();
    }

    public static boolean isTelephone(String telephone) {
        Matcher matcher = VALID_TELEPHONE_REGEX .matcher(telephone);
        return matcher.find();
    }
}
