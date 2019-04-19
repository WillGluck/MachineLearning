import java.util.function.Function;

public class SquaredErrorCostFunction extends AbstractCostFunction {

    public SquaredErrorCostFunction(NeuralNetwork nn, IDataLoader dataLoader, Integer batchSize) throws CostFunctionException {
        super(nn, dataLoader, batchSize);
    }

    private Integer batchSize;
	
	@Override
	public Function<Double, Function<Double, Double>> getFunction() {
		return i -> j -> Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));		
	}

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
	
}
