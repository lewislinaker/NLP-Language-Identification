public class BigramNode {

    private int valueFreq;
    private double valueProb;

    public BigramNode(int valueFreq, double valueProb) {

        this.valueFreq = valueFreq;
        this.valueProb = valueProb;
    }

    public void setValueFreq(int valueFreq) { this.valueFreq = valueFreq; }
    public void setValueProb(double valueProb) { this.valueProb = valueProb; }

    public int getValueFreq() { return valueFreq; }
    public double getValueProb() { return valueProb; }
}
