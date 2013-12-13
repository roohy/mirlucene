package edu.sharif.ce.fall92.mir.pa2.impl;

import java.io.File;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import edu.sharif.ce.fall92.mir.pa2.base.SearchQuery;

public class Searcher {
//	private static IndexSearcher searcher;	
	
	public static void search(String indexDir , SearchQuery searchQuery)throws Exception{
		Directory dir = FSDirectory.open(new File(indexDir));
		IndexReader reader = IndexReader.open(dir);
		IndexSearcher searcher = new IndexSearcher(reader);
		
		// making the query string 
		String queryString = "";
		if((searchQuery.getFromDate() != null && searchQuery.getFromDate() != 0)&&
				(searchQuery.getToDate() != null && searchQuery.getToDate() != 0)){
			queryString += "creation_date:["+searchQuery.getToDate()+" TO "+
				searchQuery.getToDate()+"] ";
		}
		if( searchQuery.getUrl() != null && searchQuery.getUrl() != ""){
			queryString +="url:\""+searchQuery.getUrl()+"\" ";
		}
		if( searchQuery.getQ() != null && searchQuery.getQ() != ""){
			queryString +="body:"+searchQuery.getQ()+" title:\""+
					searchQuery.getQ()+"\" ";
		}
		
		
		QueryParser parser = new QueryParser(Version.LUCENE_30, "title"
				, new StandardAnalyzer(Version.LUCENE_30));
		Query oquery = parser.parse(queryString);
		long start = System.currentTimeMillis();
		TopDocs hits = searcher.search(oquery, 10);
		long end = System.currentTimeMillis();
		
		/*Term term = new Term("body","is");
		Query q = new TermQuery(term);
		TopDocs hits = searcher.search(q, 10);*/
		System.err.println("Found "+hits.totalHits +" document(s) (in "/*+(end-start)*/
				+"milliseconds) that matched query '"+queryString+"':");
		for(ScoreDoc scoreDoc : hits.scoreDocs){
			Document doc = searcher.doc(scoreDoc.doc);
			System.out.println(doc.get("title"));
		}
		searcher.close();
	}
}
