package com.ukefu.util.ai;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ansj.app.keyword.KeyWordComputer;
import org.ansj.app.keyword.Keyword;
import org.ansj.app.phrase.Occurrence;
import org.ansj.app.phrase.PhraseExtractor;
import org.ansj.app.summary.SummaryComputer;
import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.library.AmbiguityLibrary;
import org.ansj.library.DicLibrary;
import org.ansj.library.StopLibrary;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.util.MyStaticValue;
import org.apache.commons.lang.StringUtils;

import com.ukefu.webim.web.model.Words;

public class DicSegment {
	
	private static List<String> librarykeyList = null ;
	
	private static String loadpath = null ;
	
	public synchronized static String[] segment(String content) throws IOException{
		List<Term> terms = null ;
		if (librarykeyList != null && librarykeyList.size()>0) {
			terms = NlpAnalysis.parse(content,DicLibrary.gets(librarykeyList)).getTerms();
		}else {
			terms = NlpAnalysis.parse(content).getTerms();
		}
		List<String> words = new ArrayList<String>() ;
		for(Term term : terms) {
			words.add(term.getName()) ;
		}
		
		return words.toArray(new String[words.size()]);
	}
	
	public static String[] keyword(String content) throws IOException{
		return keyword(content);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] keyword(String content , int num) throws IOException{
		KeyWordComputer kwc = new KeyWordComputer(num);
		NlpAnalysis nlpAnalysis = new NlpAnalysis();
		nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
		kwc.setAnalysisType(nlpAnalysis);
		Collection<Keyword> result = kwc.computeArticleTfidf("", content) ;
		List<String> words = new ArrayList<String>() ;
		for(Keyword keyword : result) {
			words.add(keyword.getName()) ;
		}
		return words.toArray(new String[words.size()]);
	}
	
	public static boolean isChineseByBlock(char c) {
		Character.UnicodeScript sc = Character.UnicodeScript.of(c);	
		if (sc == Character.UnicodeScript.COMMON) {
            return true;
        } else {
            return false;
        }
    }
	public static void loadDic(String path , List<Words> wordsList) throws IOException{
		loadpath = path ;
		
		File dicPath = new File(path) ;
		if(!dicPath.exists()){
			dicPath.mkdirs() ;
		}
		File ukefudicFile = new File(path ,"ukefu.dic") ;//ukefu词典
		if(!ukefudicFile.getParentFile().exists()){
			ukefudicFile.getParentFile().mkdirs();
		}
		if(!ukefudicFile.exists()){
			ukefudicFile.createNewFile();
		}
		File ambiguitydicFile = new File(path ,"ambiguity.dic") ;//歧义词典
		if(!ambiguitydicFile.getParentFile().exists()){
			ambiguitydicFile.getParentFile().mkdirs();
		}
		if(!ambiguitydicFile.exists()){
			ambiguitydicFile.createNewFile();
		}
		File stopdicFile = new File(path ,"stop.dic") ;//停词词典
		if(!stopdicFile.getParentFile().exists()){
			stopdicFile.getParentFile().mkdirs();
		}
		if(!stopdicFile.exists()){
			stopdicFile.createNewFile();
		}
		try {
			librarykeyList = new ArrayList<String>() ;
			File[] tempList = dicPath.listFiles();
			for(File file : tempList){
				String name = file.getName();
				if (file.isFile() && name.contains("ukefu")) {
					String dicname = DicLibrary.DEFAULT+name.replaceAll(".dic","").replaceAll("ukefu-", "");//格式：[dic]+[类型id]
					if (MyStaticValue.ENV.get(dicname)!=null && !StringUtils.isBlank(MyStaticValue.ENV.get(dicname))) {
						MyStaticValue.reloadLibrary(dicname);
					}else {
						MyStaticValue.ENV.put(dicname, new File(path,name).getAbsolutePath());
					}
					librarykeyList.add(dicname);
				}
			}
			MyStaticValue.ENV.put(DicLibrary.DEFAULT,ukefudicFile.getAbsolutePath());
			MyStaticValue.ENV.put(AmbiguityLibrary.DEFAULT,ambiguitydicFile.getAbsolutePath());//歧义词典
			MyStaticValue.ENV.put(StopLibrary.DEFAULT,stopdicFile.getAbsolutePath());//停词词典
		} catch (Exception e) {
			e.printStackTrace();
		}//加载字典文件
		if (wordsList != null && wordsList.size()>0) {//加载数据库词典表
			for(Words words : wordsList){
				if(!StringUtils.isBlank(words.getContent())){
		    		for(String word : words.getContent().split("[, ，:；;\\n\t ]")){
		    			if(!StringUtils.isBlank(word)){
		    				DicLibrary.insert(DicLibrary.DEFAULT+"ukefu", word);
		    			}
		    		}
		    	}
			}
		}
	}
	
	public static void removeWord(String word , String librarykey){
		if (!StringUtils.isBlank(word)) {
			DicLibrary.delete(DicLibrary.DEFAULT +librarykey, word);
		}
	}
	
	public static void removeLibrary(String librarykey){
		if (!StringUtils.isBlank(librarykey)) {
			MyStaticValue.removeLibrary(DicLibrary.DEFAULT +librarykey);
		}
	}
	
	public static void addWord(List<String> words , String librarykey){
		if (DicLibrary.get(DicLibrary.DEFAULT+librarykey) == null) {
			MyStaticValue.ENV.put(DicLibrary.DEFAULT+librarykey,new File(loadpath,"ukefu-"+librarykey+".dic").getAbsolutePath());
			librarykeyList.add(DicLibrary.DEFAULT+librarykey);
		}
		if(words!=null && words.size() > 0  ) {
			for(String word : words) {
				DicLibrary.insert(DicLibrary.DEFAULT +librarykey, word);
			}
		}
	}
	
	public static String[] byNature(String content , Set<String> expectedNature){
		List<String> wordList = new ArrayList<String>();
		if (!StringUtils.isBlank(content) && expectedNature != null && expectedNature.size() > 0) {
			Result result = NlpAnalysis.parse(content,DicLibrary.gets(librarykeyList));//分词结果的一个封装，主要是一个List<Term>的terms
            List<Term> terms = result.getTerms(); //拿到terms
            for(int i=0; i<terms.size(); i++) {
                String word = terms.get(i).getName(); //拿到词
                String natureStr = terms.get(i).getNatureStr(); //拿到词性
                if(expectedNature.contains(natureStr)) {
                    wordList.add(word+"/"+natureStr);
                }
            }
		}
		return wordList.toArray(new String[wordList.size()]);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String summary(String content , int num) throws IOException{
		String summary = null;
		if (!StringUtils.isBlank(content) && num >= 0) {
			SummaryComputer sc = new SummaryComputer("", content);
			KeyWordComputer kwc = new KeyWordComputer(num);
			NlpAnalysis nlpAnalysis = new NlpAnalysis();
			nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
			kwc.setAnalysisType(nlpAnalysis);
			List<Keyword> result = kwc.computeArticleTfidf("", content) ;
			summary = sc.toSummary(result).getSummary();
		}
		return summary;
	}
	
	public static String[] keyphrase(String content , int num) throws IOException{
		List<String> keyphrase = new ArrayList<String>();
		if (!StringUtils.isBlank(content) && num >= 0) {
			PhraseExtractor pe = new PhraseExtractor();
    		pe.fromText(content);
    		pe.setAnalysis(new NlpAnalysis());
    		pe.setLength(10);
    		NlpAnalysis nlpAnalysis = new NlpAnalysis();
			nlpAnalysis.setForests(DicLibrary.gets(librarykeyList));
			pe.setAnalysis(nlpAnalysis);
    		List<Map.Entry<String,Occurrence>> keyphraseList = pe.nbest(num);
    		for(Map.Entry<String,Occurrence> map: keyphraseList){
    			keyphrase.add(map.getKey());
    		}
		}
		return keyphrase.toArray(new String[keyphrase.size()]);
	}
	
	
}
