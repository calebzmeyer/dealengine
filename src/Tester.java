import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import deal.Deal;
import filter.logic_filter.AndFilter;
import generator.Generator;
import input.Parser;
import pbn.PBNWriter;

public class Tester {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 2) {
			
			System.err.println("Missing required input parameters. The program requires a path to a directory containing specification files, or to a specific file, as well as the quantity of hands to generate.");
			System.err.println("Sample: java Tester.java \"Specifications\\FILENAME.bs\" 50");
			System.exit(-1);
		}
		
		String path = args[0];
		int quantity = Integer.parseInt(args[1]);
		
		File file = new File(path);
		if(!file.exists()) {
			System.err.println("The specified file: " + path + " does not exist!");
			System.exit(-1);
		}
		
		if(file.isDirectory()) {
			
			for(File f : file.listFiles()) {
				if(!new File("PBN\\" + f.getName().replaceAll(".bs", ".pbn")).exists()) {
					generatePBN(f, quantity);
				}
			}
		}
		
		else generatePBN(file, quantity);
	}
	
	private static void generatePBN(File file, int quantity) throws FileNotFoundException {
		
		Parser parser = new Parser(file);
		
		AndFilter conditions = parser.parse();
		Generator generator = new Generator(conditions);
		
		List<Deal> deals = generator.generate(quantity);
		
		PBNWriter pbnWriter = new PBNWriter(parser.getPBNConfig());
		pbnWriter.writePBN(deals, "PBN\\" + file.getName().replaceAll(".bs", ".pbn"));
	}
}