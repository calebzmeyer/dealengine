package pbn;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.List;

import deal.Deal;
import deal.metainfo.Dealer;
import deal.metainfo.Vulnerability;

public class PBNWriter {
	
	private PBNConfig pbnConfig;
	
	public PBNWriter(PBNConfig pbnConfig) {
		this.pbnConfig = pbnConfig;
	}
	
	public void writePBN(List<Deal> deals, String fileName) throws FileNotFoundException {
		
		PrintStream pbnStream = new PrintStream(fileName);
		
		pbnStream.println("% PBN 2.1");
		pbnStream.println("% EXPORT");
		pbnStream.println("%");
		
		for(int boardNo = 1; boardNo <= deals.size(); boardNo++) {
			
			Dealer dealer = (this.pbnConfig.getRotatingDealer()) ? new Dealer(boardNo) : new Dealer(1);
			PBN pbn = new PBN(boardNo, dealer, new Vulnerability(boardNo), deals.get(boardNo - 1));
			
			pbnStream.println();
			pbnStream.println(pbn);
		}
		
		pbnStream.close();
	}
	
	public void writePBN(List<Deal> hands) throws FileNotFoundException {
		
		this.writePBN(hands, "Output.pbn");
	}
}