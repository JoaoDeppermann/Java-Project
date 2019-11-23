
package fitters;

public class DichotomicScaler {

    Double[] referenceData;
    Double[] scaledData;
    double bestScale;
    int numberOfIterations;
    double precision;

    public DichotomicScaler(Double[] referenceData, Double[] scaledData, double minScale, double maxScale, int maxIterations) {
        this.referenceData = referenceData;
        this.scaledData = scaledData;
        double a = 0;
        int i = 0;

        for (i = 0; i < maxIterations; i++) {

            a = (minScale + maxScale) / 2;

            if (getError(minScale) < getError(maxScale) && getError(a) < getError(maxScale)) {
                maxScale = a;
            } else if (getError(a) < getError(minScale) && getError(maxScale) < getError(minScale)) {
                minScale = a;
            } else {
                break;
            }
        }
        this.numberOfIterations = i;
        this.bestScale = a;
        this.precision = (maxScale - minScale) / 2;

    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public double getBestScale() {
        return bestScale;
    }

    public double getPrecision() {
        return precision;
    }
    
    public String toString() {
        return "Number of iterations:" + this.numberOfIterations + "\n" 
                + "BestScale: " + this.bestScale + "\n"  + "Precision: "
                + this.precision + "\n";
    }

    public double getError(double scale) {
        double err = 0;
        
        for (int i = 0; i < referenceData.length; i++) {
            err = (referenceData[i] - scaledData[i] * scale) * (referenceData[i] - scaledData[i] * scale) + err;
        }
        return err;
    }
}
