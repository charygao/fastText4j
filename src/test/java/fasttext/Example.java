package fasttext;

import java.io.IOException;

public class Example {

    static public void main(String[] args) {

//    String path = args[0];
        String path = "/opt/app/fasttext/geek-model-v1.bin";
        FastText model = null;

        try {
            long start = System.currentTimeMillis();

            /* First you will have to load your model */
            model = FastText.loadModel(path);


            long load_model = System.currentTimeMillis() - start ;
            start = System.currentTimeMillis();

            for (int i = 0; i < 100000; i++) {
                FastTextPrediction prob = model.predict("wo shi ju zi");

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

}