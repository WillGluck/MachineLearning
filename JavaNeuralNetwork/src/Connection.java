
public class Connection {

	private Double weight;
	private Neuron targetNeuron;
	
	public Connection(Double weight, Neuron targetNeuron) {
		this.weight = weight;
		this.targetNeuron = targetNeuron;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Neuron getTargetNeuron() {
		return targetNeuron;
	}

	public void setTargetNeuron(Neuron targetNeuron) {
		this.targetNeuron = targetNeuron;
	}
	
}
