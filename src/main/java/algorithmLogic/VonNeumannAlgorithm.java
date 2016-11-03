package algorithmLogic;


public class VonNeumannAlgorithm implements RandomAlgorithm {

    @Override
    public int[] run(int numOfIterations) {
        int res = 0;
        for (int i = 0; i < 20; i++) {
            int tmp = i * i + i % 2;
            res += (tmp * 2 * Math.random() / Math.random()) / 1000;
        }
        return new DefaultRandom().run(numOfIterations);
    }
}
