
public interface IDataLoader {
	
	Double[] getExample();
	
	Double[][] getBatch(Integer batchSize);
	
	Double[][] getAllData();
	
	Integer getDataSize();
	
	Double[] getTrainingExample();
	    
    Double[][] getTrainingBatch(Integer batchSize);
	    
    Double[][] getAllTrainingData();
	
	Integer getSingleLabel();
	
	Integer[] getLabelBatch(Integer batchSize);
	
	Integer[] getAllLabels();

}
