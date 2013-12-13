package edu.sharif.ce.fall92.mir.pa2.base;

import java.io.Serializable;

public class InitializationParameters implements Serializable {
	private static final long serialVersionUID = -5913558645232386396L;

	Boolean useStemmer;
	Boolean removeStopwords;

	public InitializationParameters(Boolean useStemmer, Boolean removeStopwords) {
		this.useStemmer = useStemmer;
		this.removeStopwords = removeStopwords;
	}

	public Boolean getUseStemmer() {
		return useStemmer;
	}

	public Boolean getRemoveStopwords() {
		return removeStopwords;
	}
}
