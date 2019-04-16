
public interface IDataLoader {
	
	Double[] getSingleExample();
	
	Double[][] getBatch(Integer batchSize);
	
	Double[][] getAllData();
	
	Integer getDataSize();
	

}
