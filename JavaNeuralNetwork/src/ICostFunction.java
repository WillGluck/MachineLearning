import java.util.function.Function;

public interface ICostFunction {

	Double calculate() throws NeuralNetworkException;
	
	Function<Double, Function<Double, Double>> getFunction();

}
