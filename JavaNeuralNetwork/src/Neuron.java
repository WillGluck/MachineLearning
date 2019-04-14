import java.util.LinkedList;
import java.util.List;

public class Neuron {
	
	private List<Connection> inConnections;
	private List<Connection> outConnections;
	private Double value;
	private Double bias;
		
	public Neuron() {
		inConnections = new LinkedList<>();
		outConnections = new LinkedList<>();
	}
	
	public void addValue(Double value) {
		if (null == value)
			value = 0.0;
		this.value += value;
	}
	
	public void addInConnection(Connection inConnection) {
		inConnections.add(inConnection);
	}
	
	public void addOutConnection(Connection outConnection) {
		outConnections.add(outConnection);
	}

	public List<Connection> getInConnections() {
		return inConnections;
	}

	public void setInConnections(List<Connection> inConnections) {
		this.inConnections = inConnections;
	}

	public List<Connection> getOutConnections() {
		return outConnections;
	}

	public void setOutConnections(List<Connection> outConnections) {
		this.outConnections = outConnections;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getBias() {
		return bias;
	}

	public void setBias(Double bias) {
		this.bias = bias;
	}
	
}
