import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			Function<Double, Double> sigmoid = i -> {			
				return 0d;//TODO
			};
			NeuralNetwork nn = new NeuralNetwork(sigmoid, 2, 2, 1);
			
			
		} catch (NeuralNetworkException nnException) {
			nnException.printStackTrace();
		}
		
		
	}

}
