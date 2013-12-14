package edu.sharif.ce.fall92.mir.pa2.base;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.sharif.ce.fall92.mir.pa2.impl.MyStackExchangeAdvancedSearchImpl;

public class EntryPoint {
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return encoding.decode(ByteBuffer.wrap(encoded)).toString();
			}
	public static void main(String[] args)throws Exception {
		// Our tests will be placed here. This is just a demonstration of how things work.
		// Feel free to define your own `main` function. However, note that it will be replaced with our tests. So, use
		// it for your own experimentation only.

		 StackExchangeAdvancedSearchInterface searchEngine = new MyStackExchangeAdvancedSearchImpl();
//		 InitializationParameters initParams = new InitializationParameters(false, false);
		 searchEngine.init(null);
		//
		 String json = readFile("/home/roohy/Documents/MIR-2/PA2_code_v1/json_data/sample2.json", Charset.defaultCharset());
		 
		 searchEngine.addOrUpdateDocuments(json);
		 SearchQuery testquery = new SearchQuery(null,
				 null, null, "garbage",null, null, null);
		 testquery.fromDate = 1386837481;
		 testquery.toDate = 1386837481;
		 searchEngine.searchFor(testquery);
		// SearchResult s1 = searchEngine.searchFor(someQuery);
		// searchEngine.addOrUpdateDocuments("Another JSON-formatted document collection");
		// SearchResult s2 = searchEngine.searchFor(anotherQuery);
		// run lots of tests, and evaluate them
		
		
	}
}
