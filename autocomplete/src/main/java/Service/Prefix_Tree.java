package Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import Model.data_struct;


@Repository
public class Prefix_Tree implements data_struct {

	private Prefix_Tree [] children;
	private List<Integer> locations=new ArrayList<Integer>();
	private boolean endOfWord=false;
	private char val;


	public Prefix_Tree(char value) {
		this.val=value;
		children=new Prefix_Tree[26];
	}


	public void add(String word) {//adding word to the tree

		if(word.length()==0) {
			return;
		}

		int n=word.charAt(0)-97;

		if(children[n]==null) {
			children[n]=new Prefix_Tree(word.charAt(0));
			locations.add(n);
		}

		if(word.length()==1) {
			children[n].set_end();
		}

		else {
			children[n].add(word.substring(1)); 
		}
	}


	public void set_end() {
		endOfWord=true;
	}


	public String[] get_words(String pre) {

		if(!checker(pre)) {
			String [] errorMsg= {"Illigal input, please try again"};
			return errorMsg;
		}

		List<String> l;

		if(pre.length()==0) {//if the string is empty return all the words
			l= getWords();
		}	
		else {
			l=getWords(pre);
		}

		String[] ans= new String [l.size()];


		for(int i=0;i<l.size();i++) {
			ans[i]=l.get(i);

		}

		return ans;

	}

	public List<String> getWords(String pre){//going down in the tree with the given prefix

		int n=pre.charAt(0)-97;


		if(children[n]==null) {//if there is no words with this prefix return empty list
			return new ArrayList<String>();
		}

		if(pre.length()>1) {
			List<String> list=new ArrayList<String>();
			List<String> temp= children[n].getWords(pre.substring(1));

			for(String s:temp) {
					list.add(pre.charAt(0)+s);
			}
			return list;
		}

		return children[n].getWords();
	}



	public List<String> getWords(){//return all the strings from the given node

		List<String> list=new ArrayList<String>();
		for(Integer i:locations) {
			List<String> temp=children[i].getWords();
			for(String s:temp) {
				if(val!='\0') {
					list.add(val+s);
				}
				else {
					list.add(s);

				}
			}
		}
		if(endOfWord) {
			list.add(""+val);
		}


		return list;

	}


	private boolean checker(String pre) {//checking if the string is legal

		int n=pre.length();

		for(int i=0;i<n;i++) {
			if(pre.charAt(i)<97||pre.charAt(i)>122) {
				return false;
			}
		}
		return true;
	}

	public Prefix_Tree [] getChildren() {return this.children;}
	public List<Integer> getLocations() {return this.locations;}
	public char getValue() {return this.val;}

}
