import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class Neuron {
	
	private List<Connection> inConnections;
	private List<Connection> outConnections;
		
	public Neuron() {
		inConnections = new LinkedList<>();
		outConnections = new LinkedList<>();
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
	
	
}
