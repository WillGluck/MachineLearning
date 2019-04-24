
public class MockDataLoader implements IDataLoader {
    
    private Double[][] trainingData = {{0d, 0d}, {1d, 0d}, {0d, 1d}, {1d, 1d}};
    private Double[] labels = {0d, 0d, 0d, 1d};
         
    @Override
    public Double[] getExample() {
        return null;
    }

    @Override
    public Double[][] getBatch(Integer batchSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[][] getAllData() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Integer getDataSize() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[] getTrainingExample() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[][] getTrainingBatch(Integer batchSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[][] getAllTrainingData() {
        return trainingData;
    }

    @Override
    public Double getSingleLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[] getLabelBatch(Integer batchSize) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Double[] getAllLabels() {
        return labels;
    }

    @Override
    public Integer getTrainingDataSize() {
        return trainingData.length;
    }

}
