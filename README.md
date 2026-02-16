Softmax Action Selection Based Client-Side Load Balancer
Bu proje, dağıtık sistemlerde değişken (non-stationary) ve gürültülü sunucu performansları altında toplam gecikme süresini (latency) minimize etmek amacıyla tasarlanmış bir Akıllı Yük Dengeleyici (Load Balancer) simülasyonudur.

🚀 Projenin Amacı
Geleneksel Round-Robin veya Random algoritmaları, sunucuların o anki performans değişimlerini dikkate almaz. Bu projede uygulanan Softmax Action Selection algoritması, geçmiş performans verilerini kullanarak sunuculara olasılıksal ağırlıklar atar. Böylece sistem, hızlı sunucuları sömürürken (exploitation), yavaşlayan sunucuları iyileşme ihtimaline karşı gözlemlemeye (exploration) devam eder.

🛠️ Kullanılan Teknolojiler ve Yöntemler
Dil: Java (JDK 8+)

Yöntem: Agentic Kodlama (AI destekli modüler mimari tasarımı)

Algoritma: Softmax Action Selection

Simülasyon Modeli: Non-stationary Distribution (Zamanla değişen sunucu yanıt süreleri)

🏗️ Proje Mimarisi
Proje, her biri belirli bir sorumluluğa sahip 4 ana bileşenden oluşur:

ServerCluster: Sunucu kümesini ve gürültülü ağ ortamını simüle eder.

LoadBalancer: Softmax algoritmasını ve ödül güncelleme mekanizmasını barındırır.

Analysis: Performans verilerini toplar ve istatistiksel rapor üretir.

Main: Tüm bileşenleri bir araya getirerek simülasyonu koşturur.

📈 Teknik Detaylar & Nümerik Stabilite
Softmax fonksiyonunda kullanılan e 
Q 
i
​	
 /τ
  ifadesi, büyük Q değerlerinde floating point overflow (taşma) hatasına yol açabilir.
Çözüm: Bu projede, her bir ödül değerinden o anki maksimum ödül değerinin çıkarıldığı "Max-Normalization" tekniği kullanılarak nümerik stabilite sağlanmıştır:

P 
i
​	
 = 
∑e 
(Q 
j
​	
 −Q 
max
​	
 )/τ
 
e 
(Q 
i
​	
 −Q 
max
​	
 )/τ
 
​	
 
📊 Analiz Sonuçları
Simülasyon sonucunda elde edilen veriler, Softmax algoritmasının statik algoritmalara (Random, Round-Robin) kıyasla ortalama gecikme süresini önemli ölçüde düşürdüğünü kanıtlamaktadır.

Softmax: En düşük gecikme ve yüksek adaptasyon.

Round-Robin: Sabit dağıtım, performans değişimlerine duyarsız.

Random: Tamamen kör seçim.

💻 Kurulum ve Çalıştırma
Projeyi yerel makinenizde çalıştırmak için:

Depoyu klonlayın: git clone <repo-url>

Kaynak klasörüne gidin: cd src

Derleyin: javac *.java

Çalıştırın: java Main
