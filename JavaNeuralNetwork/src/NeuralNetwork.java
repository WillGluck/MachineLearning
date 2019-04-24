
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

public class NeuralNetwork {

	private List<Layer> layers;
	private Function<Double, Double> activationFuction;
	
	
	public NeuralNetwork(Function<Double, Double> activationFunction, Integer...layersNeuronCount) throws NeuralNetworkException {
		
		this.activationFuction = activationFunction;
		
		if (null == layersNeuronCount || 2 > layersNeuronCount.length)
			throw new NeuralNetworkException("Not enougth layers informed. You should inform at least 2 (input and otuput) layers.");
				
		layers = new LinkedList<>();
		for (Integer layerNeuronCount: layersNeuronCount) {			
			layers.add(new Layer(layerNeuronCount));	
		}
		
		for (Layer layer: layers) {			
			for (Neuron neuron: layer.getNeurons()) {
				
				int layerIndex = layers.indexOf(layer);
				
				if (0 != layerIndex) {
					Layer lastLayer = layers.get(layerIndex - 1);
					lastLayer.getNeurons().stream().forEach(i -> neuron.addInConnection(new Connection(Math.random(),  i)));
				}
								
				if (layers.size() != (1 + layerIndex)) {
					Layer nextLayer = layers.get(1 + layerIndex);
					nextLayer.getNeurons().stream().forEach(i -> neuron.addOutConnection(new Connection(Math.random(), i)));
				}
			}
		}
	}
	
	public Double[] predict(Double...values) throws NeuralNetworkException {
		
		int inputLayerSize = layers.get(0).getSize();
		if (null == values || values.length != inputLayerSize)
			throw new NeuralNetworkException("The input has to have the same size as the input layer - " + inputLayerSize);
		
		Layer firstLayer = layers.get(0);
		for (int i = 0; i < values.length; i++) {			
			firstLayer.getNeuron(i).setValue(values[i]);
		}
		
		for (Layer layer: layers) {
			
			if (0 == layers.indexOf(layer))
				continue;
			
			for (Neuron neuron: layer.getNeurons()) {
			    neuron.setValue(0d);
				for (Connection connection: neuron.getInConnections()) {
					Double weightValueProduct = connection.getTargetNeuron().getValue() * connection.getWeight();
					neuron.addValue(weightValueProduct);
				}
				neuron.setValue(activationFuction.apply(neuron.getValue() + neuron.getBias()));
			}			
		}
		
		return layers.get(layers.size() - 1).getNeurons().stream().map(i -> i.getValue()).toArray(Double[]::new);
		
	}
	
	public Integer getPredictionIndex(Double[] result) {
	     Integer answerIndex = IntStream.range(0, result.length)
	             .reduce((i,j) -> result[i] < result[j] ? j : i)
	             .getAsInt();
	     return answerIndex; 
	}
	
	
			
	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}
	
	public List<Layer> getLayers() {
		return layers;
	}

	public Function<Double, Double> getActivationFuction() {
		return activationFuction;
	}

	public void setActivationFuction(Function<Double, Double> activationFuction) {
		this.activationFuction = activationFuction;
	}
	

	public Integer getInputSize() {
		return layers.get(0).getSize();
	}
	
	public Integer getOutputSize() {
		return layers.get(0).getSize();
	}
	
}

