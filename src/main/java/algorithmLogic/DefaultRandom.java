package algorithmLogic;

import java.util.Arrays;

public class DefaultRandom implements RandomAlgorithm {

    public int[] run(int numOfIterations) {
        int[] array = new int[20];
        Arrays.fill(array, 1);
        for (int i = 0; i < numOfIterations; i++) {
            double randomVal = Math.random();
            int index = RandomUtils.getIndexByValue(randomVal);
            array[index] = array[index] + 1;
        }
        System.out.println(Arrays.toString(array));
        return array;
    }
}
