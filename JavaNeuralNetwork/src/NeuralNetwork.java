
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
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
	
	public Integer predict(Double...values) throws NeuralNetworkException {
		
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
				for (Connection connection: neuron.getInConnections()) {
					Double weightValueProduct = neuron.getValue() * connection.getWeight();
					connection.getTargetNeuron().addValue(weightValueProduct);
				}
				neuron.setValue(activationFuction.apply(neuron.getValue() + neuron.getBias()));
			}			
		}
		
		List<Double> lastNeuronsValues = layers.get(layers.size() - 1).getNeurons().stream().map(i -> i.getValue()).collect(Collectors.toList());
		Integer answerIndex = IntStream.range(0, lastNeuronsValues.size())
								   .reduce((i,j) -> lastNeuronsValues.get(i) < lastNeuronsValues.get(j) ? j : i)
							   	   .getAsInt();	
		
		return answerIndex;
	}
	
	public Integer predict(Integer...values) throws NeuralNetworkException {
		return predict(Arrays.asList(values).stream().map(i -> i.doubleValue()).toArray(Double[]::new));
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
	
}

