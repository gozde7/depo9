import java.util.Arrays;
import java.util.Random;

public class LoadBalancer {
    private final int k;
    private final double tau; // Temperature
    private final double[] qValues; // Tahmini ödüller (1/latency)
    private final double alpha = 0.1; // Öğrenme oranı (Step size)
    private final Random random = new Random();

    public LoadBalancer(int k, double tau) {
        this.k = k;
        this.tau = tau;
        this.qValues = new double[k];
        Arrays.fill(qValues, 0.01); // Başlangıç tahmini
    }

    public int selectServer() {
        double[] probabilities = calculateSoftmax();
        double r = random.nextDouble();
        double cumulativeProbability = 0.0;

        for (int i = 0; i < k; i++) {
            cumulativeProbability += probabilities[i];
            if (r <= cumulativeProbability) return i;
        }
        return k - 1;
    }

    private double[] calculateSoftmax() {
        double[] probs = new double[k];
        double maxQ = Arrays.stream(qValues).max().orElse(0.0);
        double sum = 0.0;

        // Nümerik Stabilite: (q - maxQ) kullanarak overflow önlenir
        for (int i = 0; i < k; i++) {
            probs[i] = Math.exp((qValues[i] - maxQ) / tau);
            sum += probs[i];
        }

        for (int i = 0; i < k; i++) {
            probs[i] /= sum;
        }
        return probs;
    }

    public void updateState(int serverId, double latency) {
        // Ödül: Düşük latency yüksek ödül demektir
        double reward = 1000.0 / latency;
        // Hareketli Ortalama (Non-stationary durumlar için)
        qValues[serverId] += alpha * (reward - qValues[serverId]);
    }
}
