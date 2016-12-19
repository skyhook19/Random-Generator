package algorithmLogic;

//http://stratum.ac.ru/education/textbooks/modelir/lection22.html method description

public class MiddleCompositionMethod extends AbstractRandomAlgorithm{

    public MiddleCompositionMethod(int numOfIterations) {
        super(numOfIterations);
    }

    @Override
    protected void initializeArrayOfRandomValues(int numOfIterations) {
        double r0 = Math.random()*10000;
        double r1 = Math.random()*10000;

        for(int i = 0; i<numOfIterations; i++){
            int newR1 = getNewR1(r0*r1);
            randomValues[i] = Double.parseDouble("0." + newR1);
            r0 = r1;
            r1 = newR1;
            System.out.println("randomValues[" + i + "] = " + randomValues[i]);
            System.out.println("r0 = " + r0);
            System.out.println("r1 = " + r1);
        }
    }

//остался баг, число вырождается при количестве итераций ~ >900
    private Integer getNewR1(double number){
        int newR1 = (int)number;
        String str = "" + newR1;
        String res = "";
        System.out.println("str = " + str);
        if(str.length() > 4){
            int difference = str.length() - 4;

            for(int i = 0; i < 4; i++){
                res += str.toCharArray()[difference/2+i];
            }
        }
        System.out.println("res = " + res);

        return Integer.parseInt(res);
    }
}
