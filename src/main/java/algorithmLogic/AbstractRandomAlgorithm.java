package algorithmLogic;


import java.util.Arrays;

public abstract class AbstractRandomAlgorithm {

    protected double[] randomValues;

    public AbstractRandomAlgorithm(int numOfIterations){
        randomValues = new double[numOfIterations];
        initializeArrayOfRandomValues(numOfIterations);
    }

    public int[] generateArrayOfIndices(){
        int[] array = new int[20];
        Arrays.fill(array, 0);

        for (int i = 0; i < randomValues.length; i++) {
            int index = RandomUtils.getIndexByValue(randomValues[i]);
            array[index] = array[index] + 1;
        }

        return array;
    }

    public double computeDispersion(){
        double dispersion = 0;
        double oneDivideN = 1/randomValues.length;

        for (int i = 0; i < randomValues.length; i++) {
            dispersion += oneDivideN*(randomValues[i]-0.5)*(randomValues[i]-0.5);
        }

        return dispersion;
    }

    abstract protected void initializeArrayOfRandomValues(int numOfIterations);
}
