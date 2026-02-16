# Softmax Action Selection Based Client-Side Load Balancer

Bu proje, bir dağıtık sistem mühendisi perspektifiyle; değişken (non-stationary) ve gürültülü sunucu performansları altında toplam gecikme süresini (latency) minimize etmek amacıyla geliştirilmiş bir **Akıllı Yük Dengeleyici (Load Balancer)** simülasyonudur.

## 🚀 Proje Özeti
Geleneksel Round-Robin veya Random algoritmaları, sunucuların anlık yük değişimlerini ve performans dalgalanmalarını dikkate almaz. Bu projede, Takviyeli Öğrenme (Reinforcement Learning) prensiplerine dayalı **Softmax Action Selection** algoritması kullanılarak; geçmiş performans verilerine göre olasılıksal seçim yapan adaptif bir istemci tarafı yük dengeleyici tasarlanmıştır.



## 🛠️ Teknik Mimari ve Agentic Kodlama
Proje geliştirme sürecinde **Agentic Kodlama** yöntemi benimsenmiştir. Bu kapsamda; sistem mimarisi özerk modüller (Server, Balancer, Analysis) halinde kurgulanmış ve özellikle nümerik stabilite gibi kritik mühendislik problemleri "Self-Reflection" (öz-yansıtma) döngüleriyle optimize edilmiştir.

### Ana Bileşenler:
* **`ServerCluster.java`**: Sunucuların yanıt sürelerini simüle eder. Sunucular "Non-stationary" özelliktedir; yani performansları zamanla rastgele değişir ve gürültülüdür.
* **`LoadBalancer.java`**: Softmax algoritmasının kalbidir. Tahmini ödülleri (1/Latency) tutar ve olasılıksal seçimi gerçekleştirir.
* **`Analysis.java`**: Simülasyon verilerini toplayarak Round-Robin ve Random algoritmalarıyla karşılaştırmalı performans raporu hazırlar.
* **`Main.java`**: Tüm sistemi orkestre eden ana giriş noktasıdır.

## 🧠 Algoritma ve Nümerik Stabilite
Softmax algoritmasında seçim olasılığı şu formülle hesaplanır:
$$P_i = \frac{e^{Q_i / \tau}}{\sum_{j=1}^{K} e^{Q_j / \tau}}$$

**Nümerik Stabilite Çözümü:**
Üstel fonksiyonlar ($e^x$), $Q$ değerleri büyüdüğünde "Floating Point Overflow" (bellek taşması) hatasına neden olur. Bu projede, her bir ödül değerinden o anki maksimum ödülün çıkarıldığı **Max-Normalization** tekniği kullanılmıştır. Bu sayede `Math.exp()` fonksiyonu her zaman güvenli aralıkta çalışır:
$$P_i = \frac{e^{(Q_i - Q_{max}) / \tau}}{\sum e^{(Q_j - Q_{max}) / \tau}}$$



## 📊 Performans Analizi
Simülasyon sonucunda Softmax algoritmasının, statik algoritmalara kıyasla aşağıdaki avantajları sağladığı gözlemlenmiştir:
1.  **Exploration-Exploitation Dengesi:** $\tau$ (Temperature) parametresi ile sistemin ne kadar risk alacağı kontrol edilebilir.
2.  **Adaptasyon:** Sunucu performansı düştüğünde, Softmax bu sunucunun olasılığını hızla azaltarak trafiği daha hızlı sunuculara yönlendirir.
3.  **Verimlilik:** Toplam sistem gecikmesinde (Total Latency) Random seçime göre %30-%40 bandında iyileşme sağlanmaktadır.

## 💻 Kurulum ve Kullanım
1. Projeyi bilgisayarınıza indirin veya klonlayın.
2. Terminal üzerinden `src` klasörüne gidin.
3. Derlemek için: `javac *.java`
4. Çalıştırmak için: `java Main`

---
*Bu proje [Gözde - Öğrenci No] tarafından [Ders Adı] ödevi kapsamında hazırlanmıştır.*
