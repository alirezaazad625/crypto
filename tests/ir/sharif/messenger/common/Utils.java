package ir.sharif.messenger.common;

import static ir.sharif.messenger.common.stats.Statistics.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Utils {

    public static void assertUniform(final byte[] bytes, final double errorMarginPct) {
        double gotMin = getMin(bytes);
        double gotMax = getMax(bytes);
        double gotMean = getMean(bytes);
        double gotStd = getStd(bytes);
        double span = gotMax - gotMin;
        double errorDelta = errorMarginPct / 100.0 * span;

        assertEquals((gotMax + gotMin) / 2.0, gotMean, errorDelta, "collection mean was not in expected range");
        assertEquals(1.0 / 12.0 * Math.pow(span, 2), Math.pow(gotStd, 2), Math.pow(errorDelta, 2), "collection variance was not in expected range");
    }

}
