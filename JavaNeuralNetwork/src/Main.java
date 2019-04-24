import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		
		try {
			
			Function<Double, Double> sigmoid = i -> {			
				return (1/( 1 + Math.pow(Math.E,(-1 * i))));
			};
			
			NeuralNetwork nn = new NeuralNetwork(sigmoid, 2, 2, 1);
			IDataLoader dataLoader = new MockDataLoader();
			ICostFunction costFunction = new SquaredErrorCostFunction(nn, dataLoader, null);
			
			Double cost = costFunction.calculate();
			
			System.out.println("Cost for all training data is " + cost);
			
			
		} catch (NeuralNetworkException nnException) {
			nnException.printStackTrace();
		} catch (CostFunctionException cfException) {
		    cfException.printStackTrace();
		}
		
		
	}

}
