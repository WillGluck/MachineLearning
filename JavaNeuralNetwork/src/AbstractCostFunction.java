
public abstract class AbstractCostFunction implements ICostFunction {
	
	protected NeuralNetwork nn;
	protected IDataLoader dataLoader;
	
	public AbstractCostFunction(NeuralNetwork nn, IDataLoader dataLoader) {
		this.nn = nn;
		this.dataLoader = dataLoader;
	}

	public NeuralNetwork getNn() {
		return nn;
	}
	
}
