
public abstract class AbstractCostFunction implements ICostFunction {
	
	protected NeuralNetwork nn;
	protected IDataLoader dataLoader;
	protected Integer batchSize;
	
	public AbstractCostFunction(NeuralNetwork nn, IDataLoader dataLoader, Integer batchSize) throws CostFunctionException {
		this.nn = nn;
		this.dataLoader = dataLoader;
		if (null != batchSize && (0 >= batchSize || batchSize < dataLoader.getDataSize()))
            throw new CostFunctionException("Batch size needs to be within the dataset size.");
        this.batchSize = batchSize;
	}

	public NeuralNetwork getNn() {
		return nn;
	}

    public void setNn(NeuralNetwork nn) {
        this.nn = nn;
    }

    public IDataLoader getDataLoader() {
        return dataLoader;
    }

    public void setDataLoader(IDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
	
}
