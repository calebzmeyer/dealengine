import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import deal.Deal;
import filter.logic_filter.AndFilter;
import generator.Generator;
import input.Parser;
import pbn.PBNWriter;

public class Tester {
	
	private static final String path = "Specifications\\Blue Team Club\\Canape\\";
	private static final int QUANTITY = 10;
	
	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File(path);
		if(file.isDirectory()) {
			
			for(File f : file.listFiles()) {
				if(!new File("PBN\\" + f.getName().replaceAll(".bs", ".pbn")).exists()) {
					generatePBN(f);
				}
			}
		}
		
		else generatePBN(file);		
	}
	
	private static void generatePBN(File file) throws FileNotFoundException {
		
		Parser parser = new Parser(file);
		
		AndFilter conditions = parser.parse();
		Generator generator = new Generator(conditions);
		
		List<Deal> deals = generator.generate(QUANTITY);
		
		PBNWriter pbnWriter = new PBNWriter(parser.getPBNConfig());
		pbnWriter.writePBN(deals, "PBN\\" + file.getName().replaceAll(".bs", ".pbn"));
	}
}