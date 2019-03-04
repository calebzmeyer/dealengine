package input;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import card.Suit;
import deal.Seat;
import filter.Filter;
import filter.basic_count.HCPFilter;
import filter.basic_count.LengthFilter;
import filter.basic_count.LongerFilter;
import filter.basic_count.ShorterFilter;
import filter.distribution.BalancedFilter;
import filter.logic_filter.AndFilter;
import filter.logic_filter.NotFilter;
import filter.logic_filter.OrFilter;
import pbn.PBNConfig;

public class Parser {
	
	private File inputFile;
	private PBNConfig pbnConfig;
	
	private Stack<State> stateStack = new Stack<State>();
	private Stack<Seat> seatStack = new Stack<Seat>();
	private Stack<List<Filter>> filterStack = new Stack<List<Filter>>();
	
	public Parser(File inputFile) {
		this.inputFile = inputFile;
		this.pbnConfig = new PBNConfig();
		this.filterStack.push(new ArrayList<Filter>());
	}
		
	public AndFilter parse() {
		
		try {
			
			Scanner inputScanner = new Scanner(this.inputFile);
			
			int lineCounter = 0;
			while(inputScanner.hasNextLine()) {
				
				String line = inputScanner.nextLine().trim().toLowerCase();
				lineCounter++;
				
				if(line.startsWith("#")) continue;
				
				if(line.startsWith("!")) {
					this.stateStack.push(State.NOT);
					this.filterStack.push(new ArrayList<Filter>());
					line = line.substring(1).trim();
				}
				
				if(line.startsWith("north [")) {
					this.stateStack.push(State.HAND);
					this.seatStack.push(Seat.North);
					this.filterStack.push(new ArrayList<Filter>());
				}
				
				if(line.startsWith("east [")) {
					this.stateStack.push(State.HAND);
					this.seatStack.push(Seat.East);
					this.filterStack.push(new ArrayList<Filter>());
				}
				
				if(line.startsWith("south [")) {
					this.stateStack.push(State.HAND);
					this.seatStack.push(Seat.South);
					this.filterStack.push(new ArrayList<Filter>());
				}
				
				if(line.startsWith("west [")) {
					this.stateStack.push(State.HAND);
					this.seatStack.push(Seat.West);
					this.filterStack.push(new ArrayList<Filter>());
				}
			
				if(line.startsWith("]")) {
					
					State currentState = this.stateStack.pop();
					Seat currentSeat = this.seatStack.pop();
					List<Filter> currentFilters = this.filterStack.pop();
					
					if(currentState != State.HAND) {
						System.err.println(lineCounter + ": " + "Received ']' for " + currentSeat + " but top of stateStack is not HAND");
						inputScanner.close();
						return null;
					}
					
					this.filterStack.peek().add(new AndFilter(currentFilters));
				}
				
				if(line.startsWith("{")) {
					this.stateStack.push(State.AND);
					this.filterStack.push(new ArrayList<Filter>());
				}
				
				if(line.startsWith("}")) {
					
					State currentState = this.stateStack.pop();
					
					if(currentState != State.AND) {
						System.err.println(lineCounter + ": " + "Received '}' but top of stateStack is not AND");
						inputScanner.close();
						return null;
					}
					
					AndFilter andFilter = new AndFilter(this.filterStack.pop());
					this.filterStack.peek().add(andFilter);
				}
				
				if(line.startsWith("(")) {
					this.stateStack.push(State.OR);
					this.filterStack.push(new ArrayList<Filter>());
				}
				
				if(line.startsWith(")")) {
					
					State currentState = this.stateStack.pop();
					
					if(currentState != State.OR) {
						System.err.println(lineCounter + ": " + "Received ')' but top of stateStack is not OR");
						inputScanner.close();
						return null;
					}
					
					OrFilter orFilter = new OrFilter(this.filterStack.pop());
					this.filterStack.peek().add(orFilter);
				}
				
				if(line.startsWith("\"")) {
					
					Filter filter = parseFilter(line);
					if(filter == null) {	
						System.err.println(lineCounter + ": " + "did not satisfy any legal case:\t" + line);
						inputScanner.close();
						return null;
					}
					
					this.filterStack.peek().add(filter);
				}
				
				if(this.stateStack.peek() == State.NOT) {
					
					this.stateStack.pop();
					
					NotFilter notFilter = new NotFilter(this.filterStack.pop());
					this.filterStack.peek().add(notFilter);
				}
			}
			
			inputScanner.close();
			return new AndFilter(this.filterStack.pop());
			
		} catch (FileNotFoundException e) {}
		
		return null;
	}
	
	private Filter parseFilter(String line) {
		
		Seat currentSeat = this.seatStack.peek();
		
		String property = line.split(":")[0].replaceAll("\"", "");
		String value = line.split(":")[1].replaceAll("\"", "").trim();
		
		if(property.equals("hcp")) {
			int[] range = parseRange(value);
			return new HCPFilter(currentSeat, range[0], range[1]);
		}
		
		if(property.equals("clubs")) {
			int[] range = parseRange(value);
			return new LengthFilter(currentSeat, Suit.Clubs, range[0], range[1]);
		}
		
		if(property.equals("diamonds")) {
			int[] range = parseRange(value);
			return new LengthFilter(currentSeat, Suit.Diamonds, range[0], range[1]);
		}
		
		if(property.equals("hearts")) {
			int[] range = parseRange(value);
			return new LengthFilter(currentSeat, Suit.Hearts, range[0], range[1]);
		}
		
		if(property.equals("spades")) {
			int[] range = parseRange(value);
			return new LengthFilter(currentSeat, Suit.Spades, range[0], range[1]);
		}
		
		if(property.equals("balanced")) {
			if(value.equals("true"))		return new BalancedFilter(currentSeat, true);
			else if(value.equals("false"))	return new BalancedFilter(currentSeat, false);
			else System.err.println("Invalid value for Balanced Filter: " + value);
		}
		
		if(property.equals("shorter")) {
			
			Suit[] suits = parseSuits(value);
			if(suits == null) return null;
			
			if(suits[0] == null || suits[1] == null) {
				System.err.println("One or Both suits are specified incorrectly.");
				return null;
			}
			
			return new ShorterFilter(currentSeat, suits[0], suits[1]);
		}
		
		if(property.equals("longer")) {
			
			Suit[] suits = parseSuits(value);
			if(suits == null) return null;
			
			if(suits[0] == null || suits[1] == null) {
				System.err.println("One or Both suits are specified incorrectly.");
				return null;
			}
			
			return new LongerFilter(currentSeat, suits[0], suits[1]);
		}
		
		return null;
	}
	
	private int[] parseRange(String rangeStr) {
		
		int[] range = new int[2];
		
		if(rangeStr.contains("-")) {
		
			range[0] = Integer.parseInt(rangeStr.split("-")[0]);
			range[1] = Integer.parseInt(rangeStr.split("-")[1]);
		
		}
		
		else {
			range[0] = Integer.parseInt(rangeStr.split("-")[0]);
			range[1] = range[0];
		}
		
		return range;
	}
	
	private Suit[] parseSuits(String suitsStr) {
		
		Suit[] suits = new Suit[2];
		String[] suitsArr = new String[2]; 
		
		if(!(suitsStr.contains("<") || suitsStr.contains(">"))) {
			System.err.println("No < or > present in line.");
			return null;
		}
		
		if(suitsStr.contains("<")) suitsArr = suitsStr.split("<");
		if(suitsStr.contains(">")) suitsArr = suitsStr.split(">");
		
		suitsArr[0] = suitsArr[0].trim();
		suitsArr[1] = suitsArr[1].trim();
		
		for(int index = 0; index < suitsArr.length; index++) {
			if(suitsArr[index].equals("clubs")) suits[index] = Suit.Clubs;
			if(suitsArr[index].equals("diamonds")) suits[index] = Suit.Diamonds;
			if(suitsArr[index].equals("hearts")) suits[index] = Suit.Hearts;
			if(suitsArr[index].equals("spades")) suits[index] = Suit.Spades;
		}
		
		return suits;
	}
	
	public PBNConfig getPBNConfig() {
		return this.pbnConfig;
	}
}