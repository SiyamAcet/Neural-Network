import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class NeuralNetwork {
    // Değişkenler ve Nöron nesneleri
    static double ogrenmeKatsayisi1=0.01;
    static double ogrenmeKatsayisi2=0.005;
    static double ogrenmeKatsayisi3=0.025;
    static Neuron noron1 = new Neuron();
    static Neuron noron2 = new Neuron();
    static Neuron noron3 = new Neuron();

    public static int[] ogrenmeGenel(){
        int[] sonuclar = new int[3];
        try {
            Scanner reader = new Scanner(new FileReader("iris.data"));

            while (reader.hasNextLine()) {// Dosya okuma ile veri alınması ve verilerin parçalanıp işleme tabi tutulması
                String metin = reader.nextLine();
                String[] parcalanmisDizi = metin.split(",");
                float canakYaprakUzunlugu = Float.parseFloat(parcalanmisDizi[0]) / 10;
                float canakYaprakGenisligi = Float.parseFloat(parcalanmisDizi[1]) / 10;
                float tacYaprakUzunlugu = Float.parseFloat(parcalanmisDizi[2]) / 10;
                float tacYaprakGenisligi = Float.parseFloat(parcalanmisDizi[3]) / 10;
                String bitkiTuru = parcalanmisDizi[4];

                // Nöron sınıfında oluşturulan sonuç fonksiyonun çağrılması ve n1, n2 ve n3 değerlerine eşitlenmesi
                double n1 = noron1.sonuc(canakYaprakUzunlugu, canakYaprakGenisligi, tacYaprakUzunlugu, tacYaprakGenisligi);
                double n2 = noron2.sonuc(canakYaprakUzunlugu, canakYaprakGenisligi, tacYaprakUzunlugu, tacYaprakGenisligi);
                double n3 = noron3.sonuc(canakYaprakUzunlugu, canakYaprakGenisligi, tacYaprakUzunlugu, tacYaprakGenisligi);

                // egit fonksiyonun çağrılması ve farklı öğrenme katsayıları için doğru bilinen kısımların bir listeye atılması
                sonuclar[0] +=egit(n1,n2,n3,ogrenmeKatsayisi1,canakYaprakUzunlugu,canakYaprakGenisligi,tacYaprakUzunlugu,tacYaprakGenisligi,bitkiTuru);
                sonuclar[1] +=egit(n1,n2,n3,ogrenmeKatsayisi2,canakYaprakUzunlugu,canakYaprakGenisligi,tacYaprakUzunlugu,tacYaprakGenisligi,bitkiTuru);
                sonuclar[2] +=egit(n1,n2,n3,ogrenmeKatsayisi3,canakYaprakUzunlugu,canakYaprakGenisligi,tacYaprakUzunlugu,tacYaprakGenisligi,bitkiTuru);
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Dosya bulunamadı...");
        }
        return sonuclar;
    }

    public static void main(String[] args){
        int[] dogruSayisi2= new int[1];
        for (int i =0; i<20;++i){ // öğrenme katsayısı 0.01 ve 20 epok için doğru bitki bulma yüzdesi
            dogruSayisi2[0]=ogrenmeGenel()[0];
        }
        System.out.println("Ogrenme katsayısı 0.01  ve 20 epok için bilinen doğru bitki yüzdesi: " + dogruSayisi2[0]*100/150);

        int[] dogruSayisi1=new int[1];
        for (int i =0; i<50;++i){//öğrenme katsayısı 0.01 ve 50 epok için doğru bitki bulma yüzdesi
        dogruSayisi1[0]=ogrenmeGenel()[0];
        }
        System.out.println("Ogrenme katsayısı 0.01  ve 50 epok için bilinen doğru bitki yüzdesi: " + dogruSayisi1[0]*100/150);

        int[] dogruSayisi3= new int[1];
        for (int i =0; i<100;++i){//öğrenme katsayısı 0.01 ve 100 epok için doğru bitki bulma yüzdesi
            dogruSayisi3[0]=ogrenmeGenel()[0];
        }
        System.out.println("Ogrenme katsayısı 0.01  ve 100 epok için bilinen doğru bitki yüzdesi: " + dogruSayisi3[0]*100/150);
        System.out.println();

        int[] newDogruSayisi2= new int[1];
        for (int i =0; i<20;++i){//öğrenme katsayısı 0.005 ve 20 epok için doğru bitki bulma yüzdesi
            newDogruSayisi2[0]=ogrenmeGenel()[1];
        }
        System.out.println("Ogrenme katsayısı 0.005 ve 20 epok için bilinen doğru bitki yüzdesi: " + newDogruSayisi2[0]*100/150);

        int[] newDogruSayisi1=new int[1];
        for (int i =0; i<50;++i){//öğrenme katsayısı 0.005 ve 50 epok için doğru bitki bulma yüzdesi
            newDogruSayisi1[0]=ogrenmeGenel()[1];
        }
        System.out.println("Ogrenme katsayısı 0.005 ve 50 epok için bilinen doğru bitki yüzdesi: " + newDogruSayisi1[0]*100/150);

        int[] newDogruSayisi3= new int[1];
        for (int i =0; i<100;++i){//öğrenme katsayısı 0.005 ve 100 epok için doğru bitki bulma yüzdesi
            newDogruSayisi3[0]=ogrenmeGenel()[1];
        }
        System.out.println("Ogrenme katsayısı 0.005 ve 100 epok için bilinen doğru bitki yüzdesi: " + newDogruSayisi3[0]*100/150);
        System.out.println();

        int[] dogruSayisiTest2 = new int[1];
        for (int i=0; i<20;++i){//öğrenme katsayısı 0.025 ve 20 epok için doğru bitki bulma yüzdesi
            dogruSayisiTest2[0]=ogrenmeGenel()[2];
        }
        System.out.println("Ogrenme katsayısı 0.025 ve 20 epok için bilinen doğru bitki yüzdesi: " + dogruSayisiTest2[0]*100/150);

        int[] dogruSayisiTest1=new int[1];
        for (int i=0;i<50;++i){//öğrenme katsayısı 0.25 ve 50 epok için doğru bitki bulma yüzdesi
            dogruSayisiTest1[0]=ogrenmeGenel()[2];
        }
        System.out.println("Ogrenme katsayısı 0.025 ve 50 epok için bilinen doğru bitki yüzdesi: " + dogruSayisiTest1[0]*100/150);

        int[] dogruSayisiTest3=new int[1];
        for (int i=0; i<100;++i){//öğrenme katsayısı 0.25 ve 100 epok için doğru bitki bulma yüzdesi
            dogruSayisiTest3[0]=ogrenmeGenel()[2];
        }
        System.out.println("Ogrenme katsayısı 0.025 ve 100 epok için bilinen doğru bitki yüzdesi: " + dogruSayisiTest3[0]*100/150);

    }

    public static int egit(double n1, double n2, double n3, double ogrenmeKatsayisi,float x1,float x2,float x3,float x4,String bitkiTuru) {
        int dogruSayisi=0;
        if (bitkiTuru.equals("Iris-setosa")){

            if (n1>n3 && n1>n2){// n1 değeri en büyük geldiyse doğru sayısı 1 artar değilse ogrenme katsayısına göre arttırma ve azaltama işlemleri yapılır
                dogruSayisi++;

            }
            else if (n2>n1 && n2>n3){
                    noron1.w1 += (ogrenmeKatsayisi * x1);
                    noron1.w2 += (ogrenmeKatsayisi * x2);
                    noron1.w3 += (ogrenmeKatsayisi * x3);
                    noron1.w4 += (ogrenmeKatsayisi * x4);

                    noron2.w1 -= (ogrenmeKatsayisi * x1);
                    noron2.w2 -= (ogrenmeKatsayisi * x2);
                    noron2.w3 -= (ogrenmeKatsayisi * x3);
                    noron2.w4 -= (ogrenmeKatsayisi * x4);
            }
            else {
                    noron1.w1 += (ogrenmeKatsayisi * x1);
                    noron1.w2 += (ogrenmeKatsayisi * x2);
                    noron1.w3 += (ogrenmeKatsayisi * x3);
                    noron1.w4 += (ogrenmeKatsayisi * x4);

                    noron3.w1 -= (ogrenmeKatsayisi * x1);
                    noron3.w2 -= (ogrenmeKatsayisi * x2);
                    noron3.w3 -= (ogrenmeKatsayisi * x3);
                    noron3.w4 -= (ogrenmeKatsayisi * x4);
            }
        }
        else if (bitkiTuru.equals("Iris-versicolor")){
            if (n2>n1 && n2>n3){// n2 değeri en büyük geldiyse doğru sayısı 1 artar değilse ogrenme katsayısına göre arttırma ve azaltama işlemleri yapılır
                dogruSayisi++;

            }
            else if (n1>n2 && n1>n3){
                    noron2.w1 += (ogrenmeKatsayisi * x1);
                    noron2.w2 += (ogrenmeKatsayisi * x2);
                    noron2.w3 += (ogrenmeKatsayisi * x3);
                    noron2.w4 += (ogrenmeKatsayisi * x4);

                    noron1.w1 -= (ogrenmeKatsayisi * x1);
                    noron1.w2 -= (ogrenmeKatsayisi * x2);
                    noron1.w3 -= (ogrenmeKatsayisi * x3);
                    noron1.w4 -= (ogrenmeKatsayisi * x4);
            }
            else{
                    noron2.w1 += (ogrenmeKatsayisi * x1);
                    noron2.w2 += (ogrenmeKatsayisi * x2);
                    noron2.w3 += (ogrenmeKatsayisi * x3);
                    noron2.w4 += (ogrenmeKatsayisi * x4);

                    noron3.w1 -= (ogrenmeKatsayisi * x1);
                    noron3.w2 -= (ogrenmeKatsayisi * x2);
                    noron3.w3 -= (ogrenmeKatsayisi * x3);
                    noron3.w4 -= (ogrenmeKatsayisi * x4);
            }
        }
        else if (bitkiTuru.equals("Iris-virginica")){
            if (n3>n1 && n3>n2){// n3 değeri en büyük geldiyse doğru sayısı 1 artar değilse ogrenme katsayısına göre arttırma ve azaltama işlemleri yapılır
                dogruSayisi++;

            }
            else if (n2>n1 && n2>n3){
                noron3.w1 += (ogrenmeKatsayisi * x1);
                noron3.w2 += (ogrenmeKatsayisi * x2);
                noron3.w3 += (ogrenmeKatsayisi * x3);
                noron3.w4 += (ogrenmeKatsayisi * x4);

                noron2.w1 -= (ogrenmeKatsayisi * x1);
                noron2.w2 -= (ogrenmeKatsayisi * x2);
                noron2.w3 -= (ogrenmeKatsayisi * x3);
                noron2.w4 -= (ogrenmeKatsayisi * x4);
            }
            else {
                noron3.w1 += (ogrenmeKatsayisi * x1);
                noron3.w2 += (ogrenmeKatsayisi * x2);
                noron3.w3 += (ogrenmeKatsayisi * x3);
                noron3.w4 += (ogrenmeKatsayisi * x4);

                noron1.w1 -= (ogrenmeKatsayisi * x1);
                noron1.w2 -= (ogrenmeKatsayisi * x2);
                noron1.w3 -= (ogrenmeKatsayisi * x3);
                noron1.w4 -= (ogrenmeKatsayisi * x4);
            }
        }
        return dogruSayisi;
    }
}
