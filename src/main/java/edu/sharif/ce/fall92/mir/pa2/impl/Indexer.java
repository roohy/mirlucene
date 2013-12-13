package edu.sharif.ce.fall92.mir.pa2.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldSelectorResult;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class Indexer {

private IndexWriter indexWriter;
	boolean stopWord;
	boolean stem;
	
	public Indexer(String indexDir , boolean mytype)throws Exception{
		
		Directory dir = FSDirectory.open(new File(indexDir));
		StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_30);
		indexWriter = new IndexWriter(dir, standardAnalyzer,
				true,
				IndexWriter.MaxFieldLength.UNLIMITED);
	}
	
	public Indexer(String indexDir,boolean stopWord,boolean stem) throws IOException{
		
		//getting the categor to open
		Directory dir = FSDirectory.open(new File(indexDir));
//		Directory index = new RAMDirectory();
		
		//making an analyzer
		StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_30);
		SimpleAnalyzer simpleAnalyzer = new SimpleAnalyzer();
		PositionalPorterStopAnalyzer stopStemAnalyzer = new PositionalPorterStopAnalyzer();
		PorterAnalyzer porterAnalyzer = new PorterAnalyzer();
		Analyzer analyzer;
		if( stopWord){
			if(stem){
				analyzer = stopStemAnalyzer;
			}else{
				analyzer = standardAnalyzer;
			}
			
		}
		else{
			if(stem){
				analyzer = porterAnalyzer;
			}
			else
				analyzer = simpleAnalyzer;
		}
		//making a config variable for index writer
//		IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_36, analyzer);

		//making the index writer
//		this.indexWriter = new IndexWriter(dir, config);
		
		indexWriter = new IndexWriter(dir, analyzer,
				true,
				IndexWriter.MaxFieldLength.UNLIMITED);
	}
	
	public void close() throws IOException{
		indexWriter.close();
	}
	
	public int index(String dataDir,FileFilter filter) throws Exception{
		System.out.println("directory is "+dataDir);
		File[] files = new File(dataDir).listFiles();
		System.out.println("System detected "+ files.length+ " files in directory");
		for ( File f: files){
			if(!f.isDirectory() && !f.isHidden() && f.exists() &&
					f.canRead()&&
					(filter == null || filter.accept(f))){
				indexFile(f);
				
			}
				
		}
		
		return indexWriter.numDocs();
	}
	
	public static class TextFileFilter implements FileFilter{
		public boolean accept(File path){
			return path.getName().toLowerCase().endsWith("");
		}
	}
	
	protected 	Document getDocument( File f)throws Exception{
		Document result = new Document();
		result.add(new Field("contents", new FileReader(f)));
		result.add(new Field("filename" , f.getName(),
				Field.Store.YES , Field.Index.NOT_ANALYZED));
		result.add(new Field("fullpath",f.getCanonicalPath(),
				Field.Store.YES, Field.Index.NOT_ANALYZED));
		return result;
	}
	protected Document getDocumentFromItem(Item item){
		Document result = new Document();
		result.add(new Field("body" ,item.body , Field.Store.YES , Field.Index.ANALYZED));
		result.add(new Field("title",item.title ,
				Field.Store.YES , Field.Index.ANALYZED));
		result.add(new Field("link",item.link,
				Field.Store.YES,Field.Index.NOT_ANALYZED));
		result.add(new Field("creation_date",item.creation_date,
				Field.Store.YES,Field.Index.NOT_ANALYZED));
		return result;
	}
	private void indexFile(File f)throws Exception{
		System.out.println("Index "+f.getCanonicalPath());
		Document doc = getDocument(f);
		indexWriter.addDocument(doc);
	}
	
	public void indexItems(StackExchangeClass items)throws Exception{
		for(Item item : items.items){
//			System.out.println("indexing "+item.title);
			Document doc = getDocumentFromItem(item);
//			System.out.println("faaaaaap"+doc.get("body"));
			indexWriter.addDocument(doc);
		}
	}

}
