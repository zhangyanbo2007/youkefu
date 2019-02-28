package com.ukefu.util.ai;

import java.io.IOException;

import org.apache.commons.lang.ArrayUtils;

public class AiDic {
	
	private final AiDicTrie trie ;
	
	private Node[] dic = new Node[0];
	
	class Node {
		int code;
		int depth;
		int[] id ;
		AiDic aiDic ;
		public String toString(){
			return aiDic.trie.getDicMap().inverse().get(code) ;
		}
	};
	
	public AiDic(AiDicTrie trie){
		this.trie = trie ;
	}
	public Node search(String content) throws IOException{
		return search(DicSegment.segment(content)) ;
	}
	
	private Node search(String[] content){
		if (ArrayUtils.isEmpty(content)){
			return null ;
		}
		Node[] temp = dic ;
		Node result =null  ;
		for(int i=0 ; i< content.length ; i++){
			String word = content[i] ;
			int code = trie.getWordInx(word) ;
			int inx = fetch(temp,code , false) ;
			if(inx >= 0){
				if(inx < temp.length && temp[inx] != null){
					result = temp[inx] ;
				}
				if(inx >= temp.length){
					result = null ; break ;
				}
				if(temp[inx]!=null && temp[inx].aiDic!=null){
					temp = temp[inx].aiDic.dic ;
				}
			}
		}
		return result ;
	}
	
	private int fetch(Node[] dicDept , int code , boolean fixed){
		int dicInx = -1 ;
		for(int i=0 ; i<dicDept.length ; i++){
			Node node = dicDept[i] ;
			if(node!=null){
				if(code == node.code){
					dicInx = i ;
				}else{
					continue ;
				}
			}
			break ;
		}
		return dicInx ;
	}
	
	public void insert(String content , int id) throws IOException{
		insert(DicSegment.segment(content) , id);
	}
	
	private void insert(String[] content , int id) {
		if (ArrayUtils.isEmpty(content)){
			return ;
		}
		AiDic temp = this ;
		for(int i=0 ; i< content.length ; i++){
			String word = content[i] ;
			int code = trie.getWordInx(word) ;
			int inx = fetch(temp.dic,code , true) ;
			if (inx < 0){
				inx = temp.dic.length ;
				Node newNode = new Node();
				newNode.code = code ;
				newNode.depth = i ;
				newNode.aiDic = new AiDic(trie) ;
				temp.dic = (Node[]) ArrayUtils.add(temp.dic, newNode) ;
			}
			if(i == (content.length -1)){
				if(temp.dic[inx].id == null){
					temp.dic[inx].id = new int[]{id};
				}else{
					temp.dic[inx].id = ArrayUtils.add(temp.dic[inx].id, id) ;
				}
			}
			temp = temp.dic[inx].aiDic ;
		}
	}
}
