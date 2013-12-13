package edu.sharif.ce.fall92.mir.pa2.base;

import java.util.List;

public interface StackExchangeAdvancedSearchInterface {
	static enum SortingMeasure {
		ACTIVITY,
		CREATION,
		VOTES,
		RELEVANCE
	}

	/**
	 * Initializes the indexer parameters.
	 * 
	 * @param initParams
	 *            parameters to initialize the indexer with
	 */
	public void init(InitializationParameters initParams);

	/**
	 * Adds a document collection to the index. Existing documents will get updated.
	 * 
	 * @param documents
	 *            JSON-formatted collection of documents getting indexed.
	 */
	public void addOrUpdateDocuments(String documents);

	/**
	 * Uses index to find documents matching the query criteria.
	 * 
	 * @param query
	 * @return list of matching documents
	 */
	public List<Integer> searchFor(SearchQuery query);
}
