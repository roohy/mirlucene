package edu.sharif.ce.fall92.mir.pa2.impl;

import java.util.List;

import edu.sharif.ce.fall92.mir.pa2.base.InitializationParameters;
import edu.sharif.ce.fall92.mir.pa2.base.SearchQuery;
import edu.sharif.ce.fall92.mir.pa2.base.StackExchangeAdvancedSearchInterface;

public class MyStackExchangeAdvancedSearchImpl implements StackExchangeAdvancedSearchInterface {
	
	Indexer indexer;
	String indexDir;
	InitializationParameters params;
//	@Override
	public void init(InitializationParameters initParams) {
		// TODO Auto-generated method stub
		indexDir = "indexDIR";
		params = initParams;
		try{
			if(params == null){
				indexer = new Indexer(indexDir,true);
			}
			else{
				indexer = new Indexer(indexDir,params.getRemoveStopwords(),
						params.getUseStemmer());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//	@Override
	public void addOrUpdateDocuments(String documents) {
		// TODO Auto-generated method stub
		StackExchangeClass items = StackDownloader.Json2Doc(documents);
		try{
			indexer.indexItems(items);
			indexer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

//	@Override
	public List<Integer> searchFor(SearchQuery query) {
		// TODO Auto-generated method stub
		try{
//			System.out.println("searching for "+query.getQ());
			Searcher.search(indexDir, query);
//			System.out.println("haha directory for  search "+indexDir);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
