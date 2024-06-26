package com.app.blog.utils;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtils {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern EDGESDHASHES = Pattern.compile("(^-|-$)");
    public static String generateSlug(String input) {

            String whitespace = WHITESPACE.matcher(input).replaceAll("-");
            String normalized = Normalizer.normalize(whitespace, Normalizer.Form.NFD);
            String slug = NONLATIN.matcher(normalized).replaceAll("");
            slug = EDGESDHASHES.matcher(slug).replaceAll("");
            return slug.toLowerCase(Locale.ENGLISH);
    }
}
