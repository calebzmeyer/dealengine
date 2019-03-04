package pbn;

public class PBNConfig {
	
	private boolean rotatingDealer;
	
	public PBNConfig() {}
	
	public PBNConfig(boolean rotatingDealer) {
		this.rotatingDealer = rotatingDealer;
	}
	
	public void setRotatingDealer(boolean rotatingDealer) {
		this.rotatingDealer = rotatingDealer;
	}
	
	public boolean getRotatingDealer() {
		return this.rotatingDealer;
	}
}