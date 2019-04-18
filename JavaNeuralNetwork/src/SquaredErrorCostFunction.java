
public class SquaredErrorCostFunction extends AbstractCostFunction {

    public SquaredErrorCostFunction(NeuralNetwork nn, IDataLoader dataLoader, Integer batchSize) throws CostFunctionException {
        super(nn, dataLoader, batchSize);
    }

    private Integer batchSize;
	
	public Double calculate() throws NeuralNetworkException {
		
		Integer dataSize = dataLoader.getDataSize();
				
		Double[][] data = null;
		Integer[] labels = null;
		if (null == batchSize) {
		    data = dataLoader.getAllTrainingData();
		    labels = dataLoader.getAllLabels();
		} else {
		    data = dataLoader.getTrainingBatch(batchSize);
		    labels = dataLoader.getLabelBatch(batchSize);
		}
		
		for (int i = 0; i < dataSize; i++) {
			Double[] prediction = nn.predict(data[i]);
		}
		
		
		return 0d;
	}

    public Integer getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(Integer batchSize) {
        this.batchSize = batchSize;
    }
	
}
