package algorithmLogic;


public class VonNeumannAlgorithm extends AbstractRandomAlgorithm {


    public VonNeumannAlgorithm(int numOfIterations) {
        super(numOfIterations);
    }

    @Override
    protected void initializeArrayOfRandomValues(int numOfIterations) {
        int res = 0;
        for (int i = 0; i < 20; i++) {
            int tmp = i * i + i % 2;
            res += (tmp * 2 * Math.random() / Math.random()) / 1000;
        }
    }
}
