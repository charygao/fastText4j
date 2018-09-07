package fasttext;

import fasttext.util.FileUtil;
import org.junit.Test;

import java.io.IOException;

public class Example {
    String path = "/opt/app/fasttext/supervised.model.bin";
    FastText model = null;

    static public void main(String[] args) {


//    String path = args[0];
        String path = "/opt/app/fasttext/supervised.model.bin";
        FastText model = null;

        try {
            long start = System.currentTimeMillis();

            /* First you will have to load your model */
            model = FastText.loadModel(path);


            long load_model = System.currentTimeMillis() - start ;
            start = System.currentTimeMillis();

            for (int i = 0; i < 10; i++) {
                FastTextPrediction prob = model.predict("str");

                System.out.println(prob.label() + prob.probability());
            }

            System.out.println("load_model cost:" + load_model);
            System.out.println("cost:" + (System.currentTimeMillis() - start));

        } catch (Exception e) {

            System.out.println("Oops something went wrong. Exception: " + e.getMessage());

        } finally {
            // Closing is only mandatory for memory-mapped models
            if (model != null) {
                try {
                    model.close();
                } catch (IOException e) {
                    System.out.println("Error while closing fastText model");
                }
            }
        }

    }



    @Test
    public void test() throws IOException {

        long start = System.currentTimeMillis();

        /* First you will have to load your model */
        model = FastText.loadModel(path);

        long load_model = System.currentTimeMillis() - start ;
        start = System.currentTimeMillis();


        String testFilePath = "/home/test.txt" ;
        FileUtil.readFile(testFilePath, oneLine -> {
            FastTextPrediction prob = model.predict(oneLine);

            System.out.println(prob.label() + prob.logProbability() + "  " + prob.probability());
            System.out.println(oneLine);
        });

        System.out.println("load_model cost:" + load_model);
        System.out.println("cost:" + (System.currentTimeMillis() - start));


    }




}