package history.back.Business.Keywords;

import java.util.Set;

/**
 * @author shamik.majumdar
 */
public interface KeywordGenerator {
    Set<String> generateKeyWords(String content);
}
