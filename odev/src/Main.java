public class Main {
    public static void main(String[] args) {
        int K = 5;
        int totalRequests = 5000; // Analiz için daha fazla veri
        double tau = 0.2; // Temperature (0.1 - 0.5 arası idealdir)

        ServerCluster cluster = new ServerCluster(K);
        LoadBalancer softMaxLB = new LoadBalancer(K, tau);
        Analysis analyzer = new Analysis();

        System.out.println("Simülasyon ve Analiz Başlatılıyor...");

        for (int i = 0; i < totalRequests; i++) {
            // 1. Softmax Testi
            int sSelected = softMaxLB.selectServer();
            double sLat = cluster.getLatency(sSelected);
            softMaxLB.updateState(sSelected, sLat);
            analyzer.recordSoftmax(sLat);

            // 2. Random Testi
            int rSelected = (int) (Math.random() * K);
            analyzer.recordRandom(cluster.getLatency(rSelected));

            // 3. Round-Robin Testi
            int rrSelected = i % K;
            analyzer.recordRoundRobin(cluster.getLatency(rrSelected));
        }

        // Raporu Yazdır
        analyzer.printReport();
    }
}