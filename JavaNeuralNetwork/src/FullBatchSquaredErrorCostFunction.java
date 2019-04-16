
public class FullBatchSquaredErrorCostFunction extends AbstractCostFunction {

	public FullBatchSquaredErrorCostFunction(NeuralNetwork nn, IDataLoader dataLoader) {
		super(nn, dataLoader);
		// TODO Auto-generated constructor stub
	}
	
	public Double calculate() throws NeuralNetworkException {
		
		Integer dataSize = dataLoader.getDataSize();
		
		Double[][] data = dataLoader.getAllData();
		
		for (int i = 0; i < dataSize; i++) {
			nn.predict(data[i]);
		}
		
		
		return 0d;
	}

}
