import java.util.Random;

public class ServerCluster {
    private final int k;
    private final double[] baseLatencies; // Sunucuların temel gecikme süreleri
    private final Random random = new Random();

    public ServerCluster(int k) {
        this.k = k;
        this.baseLatencies = new double[k];
        for (int i = 0; i < k; i++) {
            baseLatencies[i] = 50 + random.nextDouble() * 150; // 50ms - 200ms arası
        }
    }

    public double getLatency(int serverId) {
        // Gürültü ekle (Gaussian Noise)
        double noise = random.nextGaussian() * 10;

        // Zamanla değişen performans (Non-stationary)
        // Her istekte temel latency %1 ihtimalle küçük bir değişim yaşar
        if (random.nextDouble() < 0.01) {
            baseLatencies[serverId] += (random.nextDouble() - 0.5) * 20;
        }

        return Math.max(1.0, baseLatencies[serverId] + noise);
    }

    public int getK() { return k; }
}
