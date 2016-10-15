package algorithmLogic;


public class RandomUtils {

    public static int getIndexByValue(double value) {
        if (value >= 0 && value <= 0.05) return 0;
        else if (value > 0.05 && value <= 0.1) return 1;
        else if (value > 0.1 && value <= 0.15) return 2;
        else if (value > 0.15 && value <= 0.2) return 3;
        else if (value > 0.2 && value <= 0.25) return 4;
        else if (value > 0.25 && value <= 0.3) return 5;
        else if (value > 0.3 && value <= 0.35) return 6;
        else if (value > 0.35 && value <= 0.4) return 7;
        else if (value > 0.4 && value <= 0.45) return 8;
        else if (value > 0.45 && value <= 0.5) return 9;
        else if (value > 0.5 && value <= 0.55) return 10;
        else if (value > 0.55 && value <= 0.6) return 11;
        else if (value > 0.6 && value <= 0.65) return 12;
        else if (value > 0.65 && value <= 0.7) return 13;
        else if (value > 0.7 && value <= 0.75) return 14;
        else if (value > 0.75 && value <= 0.8) return 15;
        else if (value > 0.8 && value <= 0.85) return 16;
        else if (value > 0.85 && value <= 0.9) return 17;
        else if (value > 0.9 && value <= 0.95) return 18;
        else return 19;
    }
}
