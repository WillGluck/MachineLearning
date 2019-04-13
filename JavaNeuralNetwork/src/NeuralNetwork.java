
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

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
