package ir.sharif.messenger.common.stats;

public class Statistics {

    private Statistics() {}

    public static double getMin(byte[] bytes) {
        byte min = Byte.MAX_VALUE;
        for (byte b : bytes) {
            if (b < min)
                min = b;
        }
        return min;
    }

    public static double getMax(byte[] bytes) {
        byte max = Byte.MIN_VALUE;
        for (byte b : bytes) {
            if (b > max)
                max = b;
        }
        return max;
    }

    public static double getMean(byte[] bytes) {
        double mean = 0.0;
        for (byte b : bytes)
            mean += b;
        mean /= bytes.length;
        return mean;
    }

    public static double getStd(byte[] bytes) {
        double mean = getMean(bytes);
        double variance = 0.0;
        for (byte b : bytes) {
            variance += (b - mean) * (b - mean);
        }
        variance /= bytes.length;
        return Math.sqrt(variance);
    }

}
