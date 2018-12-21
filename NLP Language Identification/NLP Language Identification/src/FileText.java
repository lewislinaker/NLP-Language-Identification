public class FileText {

    private final int SPACE = 32;
    private final int lenghtOfTheArray = 27;
    private final int ASCICODE = 96;
    private final int numOfUnigrams = 27;
    private final int numOfBigrams = 729;

    private int[] unigrams;
    private int count = 0;

    private BigramNode[] bigramNodes;

    private double probability;

    public FileText(){

        setUnigrams();
        setBigrams();
    }

    private void setUnigrams() { unigrams = new int[numOfUnigrams]; }

    private void setBigrams() {

        bigramNodes = new BigramNode[numOfBigrams];

        for (int i=0; i<numOfBigrams; i++) {
            bigramNodes[i] = new BigramNode(0, 0);
        }
    }

    public double getProbability() { return probability; }

    public void train(String text) {

        countFreq(text);
        calcProb();
    }

    public void test(String text) {

        probability = 0;

        for(int i=0; i<text.length()-1; i++){

            int j = i+2;
            String bigram = text.substring(i,j);
            int key = hash(bigram);

            if (key != 0)
                probability += Math.log(bigramNodes[key].getValueProb());

        }
    }

    private void countFreq(String langText) {

        int unigram,  unigramKey, unigramValue;
        String bigram;

        for (int i=0; i<langText.length()-1; i++) {

            unigram = langText.charAt(i);

            if (unigram == SPACE){

                unigramKey = 0;
                addUnigram(unigramKey);

            } else {

                unigramKey = unigram - ASCICODE ;
                addUnigram(unigramKey);
            }

            int j = i+2;
            bigram = langText.substring(i, j);
            addBigram(bigram);

            count++;

        }


        unigramKey = langText.charAt(langText.length()-1);
        if (unigramKey != SPACE){
            unigramValue = unigrams[unigramKey - ASCICODE];
            unigrams[unigramKey - ASCICODE] = ++unigramValue;

            count++;
        }

    }

    private  void calcProb() {

        int unigramValue;
        double bigramValue, bigramProb;

        int j = 0;

        for (int i = 1; i< bigramNodes.length; i++){

            bigramValue = bigramNodes[i].getValueFreq();

            if (i % lenghtOfTheArray != 0) {
                unigramValue = unigrams[j];
            } else {
                j += 1;
                unigramValue = unigrams[j];
            }

            bigramProb = ((bigramValue + 1) / (unigramValue + numOfUnigrams));

            bigramNodes[i].setValueProb(bigramProb + Math.abs(-1));
        }

    }

    private int hash(String str) {

        int key;
        int c1,c2;

        c1 = str.charAt(0);
        if (c1 == SPACE)
            c1 = 0;
        else
            c1 -= ASCICODE;


        c2 = str.charAt(1);
        if (c2 == SPACE)
            c2 = 0;
        else
            c2 -= ASCICODE;

        key = c1*lenghtOfTheArray  + c2;

        return key;
    }

    private void addBigram(String bigram) {

        int bigramValue;
        int bigramKey;

        bigramKey = hash(bigram);
        bigramValue = bigramNodes[bigramKey].getValueFreq();
        bigramNodes[bigramKey].setValueFreq(++bigramValue);
    }

   private void addUnigram(int index) {

      int unigramValue;

      unigramValue = unigrams[index];
      unigrams[index] = ++unigramValue;
    }

}