
import java.util.LinkedList;
import java.util.List;

public class Layer {
	
	private List<Neuron> neurons;
	
	public Layer(Integer neuronCount) {
		neurons = new LinkedList<>();
		for (int i = 0; i < neuronCount; i++) {
			neurons.add(new Neuron());
		}
	}
	
	public Neuron getNeuron(Integer index) {
		return neurons.get(index);
	}
	
	public Integer getSize() {
		return neurons.size();
	}

	public List<Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<Neuron> neurons) {
		this.neurons = neurons;
	}
	
}
