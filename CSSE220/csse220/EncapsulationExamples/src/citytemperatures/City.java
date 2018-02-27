package citytemperatures;

import java.util.ArrayList;

public class City {
	private ArrayList<Double> temperatures;
	private String name;

	public City(String name) {

		this.temperatures = new ArrayList<Double>();
		this.name = name;
	}
	public void addTemperature(Double temperature) {
		this.temperatures.add(temperature);
	}
	
	public Double average() {
		double sum = 0;
		for (int i = 0 ; i < this.temperatures.size(); i++) {
			sum += this.temperatures.get(i);
		}
		return (sum/this.temperatures.size());
	}
	 
	public String getName() {
		return this.name;
	}
}
