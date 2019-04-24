
public interface IDataLoader {
	
	Double[] getExample();
	
	Double[][] getBatch(Integer batchSize);
	
	Double[][] getAllData();
	
	Integer getDataSize();
	
	Integer getTrainingDataSize();
	
	Double[] getTrainingExample();
	    
    Double[][] getTrainingBatch(Integer batchSize);
	    
    Double[][] getAllTrainingData();
	
	Double getSingleLabel();
	
	Double[] getLabelBatch(Integer batchSize);
	
	Double[] getAllLabels();
	
}
