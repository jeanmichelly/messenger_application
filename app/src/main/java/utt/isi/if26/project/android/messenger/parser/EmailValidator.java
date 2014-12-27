package utt.isi.if26.project.android.messenger.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jean-michelly on 26/12/2014.
 */
public class EmailValidator {

    static private Matcher matcher;

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    static private Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    /**
     * Validate hex with regular expression
     *
     * @param hex
     *            hex for validation
     * @return true valid hex, false invalid hex
     */
    static public boolean validate (final String hex) {

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }

}
