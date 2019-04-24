public abstract class AbstractCostFunction implements ICostFunction {
	
	protected NeuralNetwork nn;
	protected IDataLoader dataLoader;
	protected Integer batchSize;
	
	public AbstractCostFunction(NeuralNetwork nn, IDataLoader dataLoader, Integer batchSize) throws CostFunctionException {
		
		this.nn = nn;
		this.dataLoader = dataLoader;
		
		if (null != batchSize && (0 >= batchSize || batchSize < dataLoader.getTrainingDataSize()))
            throw new CostFunctionException("Batch size needs to be within the dataset size.");

		this.batchSize = batchSize;
	}
	
	public Double calculate() throws NeuralNetworkException {
		
		Integer dataSize = dataLoader.getTrainingDataSize();
				
		Double[][] data = null;
		Double[] labels = null;
		
		if (null == batchSize) {
		    data = dataLoader.getAllTrainingData();
		    labels = dataLoader.getAllLabels();
		} else {
		    data = dataLoader.getTrainingBatch(batchSize);
		    labels = dataLoader.getLabelBatch(batchSize);
		}
		
		Double cost = 0d;
		for (int i = 0; i < dataSize; i++) {
			Double[] prediction = nn.predict(data[i]);			
			Double label = labels[i];
			for (Integer j = 0; i < prediction.length; j++) {
				cost += getFunction().apply(prediction[i]).apply(label.equals(j.doubleValue()) ? 1d : 0d);
			}
		}		
		return cost;
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
