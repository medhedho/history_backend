package history.back.Business.Keywords;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author shamik.majumdar
 */
@Component
public class JaccardIndexBasedSimilarity implements SimilarityCalculator {
    private final KeywordGenerator keywordGenerator = new KeywordGenetorUsingCustomAnalyzer();

    @Override
    public double calculateSimilarity(String oneContent, String otherContet) {
        Set<String> keyWords1 = keywordGenerator.generateKeyWords(oneContent);
        Set<String> keyWords2 = keywordGenerator.generateKeyWords(otherContet);
        Set<String> denominator = Sets.union(keyWords1,keyWords2);
        Set<String> numerator = Sets.intersection(keyWords1,keyWords2);

        return denominator.size() > 0 ? (double)numerator.size()/(double)denominator.size() : 0;
    }
}
