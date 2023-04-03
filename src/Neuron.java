import java.util.Random;

public class Neuron {
    // Random ağırlık değerleri üretilmesi
    Random random = new Random();
    double  w1=random.nextDouble(0,1);
    double  w2=random.nextDouble(0,1);
    double  w3=random.nextDouble(0,1);
    double  w4=random.nextDouble(0,1);


   public double sonuc(float x1,float x2,float x3,float x4){ // Verilen formüle uygun nöron değerlerinin hesaplanması
        double sonuc = w1*x1 + w2*x2 + w3*x3 + w4*x4;
        return sonuc;
     }
}
