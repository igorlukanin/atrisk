package util;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import play.Play;

public class ScalarHelper {
    public static int bound(int value,int min,int max) {
        return value < min ? min : value > max ? max : value;
    }

    public static String translate(String name) {
        try {
            Translate.setKey(Play.configuration.getProperty("external.bing.api-key"));
            return Translate.execute(name,Language.AUTO_DETECT,Language.ENGLISH);
        }
        catch (Exception e) {
            return name;
        }
    }
}
