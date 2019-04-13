
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Layer {
	
	private List<Neuron> neurons;
	
	public Layer(Integer neuronCount) {
		neurons = new LinkedList<>();
		for (int i = 0; i < neuronCount; i++) {
			neurons.add(new Neuron());
		}
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}
	
}
