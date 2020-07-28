package main;
/**
 * This class holds all informations about luggage (dimension, weight, fees).
 *
 * @author Pierre (pcb3), Sagar (ssa23), Andéol (ag188)
 */

public class Luggage {
	
	public double weight, length, width, height;// in cm
	public double fee;
	public static final double max_free_dimension = 120000; // cm3.  or 12L
	public static final double max_free_weight = 26.0; // in kg
	
	public Luggage(double length, double width, double height, double weight) {
		this.length=length;
		this.width=width;
		this.height=height;
		this.weight=weight;
	}
	
	
	
	
	
	
	// ******************************
	// Methods
	// ******************************
	
	
	
	// Setters

	public void setlength(double newLength) {
		this.length = newLength;
	}
	public void setWidth(double newWidth) {
		this.width = newWidth;
	}
	public void setHeight(double newHeight) {
		this.height = newHeight;
	}
	public void setWeight(double newWeight) {
		this.weight = newWeight;
	}
	
	// Getters

	public double getLength() {
		return this.length;
	}
	public double getWidth() {
		return this.width;
	}
	public double getHeight() {
		return this.height;
	}
	public double getWeight() {
		return this.weight;
	}
	public double getVolume() {
		return (length*height*width);
	}
	
	/**
	* Calculate the additional fees.
	*
	* @return double additionalFees.
	*/
	public double getAdditionalFees() {
		double additionalFees = 0.0, x = this.length * this.width * this.height;
		if( x > max_free_dimension)
			additionalFees +=0.2*(x - max_free_dimension);
		if(this.weight > max_free_weight)
			additionalFees += 2*(weight-max_free_weight); // 2 pounds each additional kg
		return additionalFees;
	}
	
	@Override
	public String toString() {
		return "luggage: " + length + " x " + width + " x " + height + " cm, " + weight + " kg.";
	}

}
