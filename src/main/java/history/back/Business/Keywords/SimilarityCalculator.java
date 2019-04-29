package history.back.Business.Keywords;

/**
 * @author shamik.majumdar
 */
public interface SimilarityCalculator {
    double calculateSimilarity(String oneContent, String otherContet);
}
