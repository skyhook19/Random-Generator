package algorithmLogic;

import java.util.Arrays;

public class DefaultRandom extends AbstractRandomAlgorithm {

    public DefaultRandom(int numOfIterations) {
        super(numOfIterations);
    }

    @Override
    protected void initializeArrayOfRandomValues(int numOfIterations) {
        for (int i = 0; i < numOfIterations; i++) {
            randomValues[i] = Math.random();
        }
    }
}
