package Service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class Prefix_TreeTest {

	Prefix_Tree t;


	@Test
	public void testAdd() {
		
		//testing empty
		t=new Prefix_Tree('\0'); 

		Prefix_Tree [] tree_to_check=new Prefix_Tree[26];
		List<Integer> list_to_check=new ArrayList<Integer>();
		t.add("");
		for(int i=0;i<26;i++) {
			assertEquals(t.getChildren()[i],tree_to_check[i]);
		}
		assertEquals(t.getLocations(),list_to_check);


		//testing only with a
		tree_to_check[0]=new Prefix_Tree('a');
		list_to_check.add(0);
		t.add("a");
		for(int i=0;i<26;i++) {
			if(t.getChildren()[i]!=null) {
				assertEquals(t.getChildren()[i].getValue(),tree_to_check[i].getValue());
			}
		}
		assertEquals(t.getLocations(),list_to_check);


		//testing with a and b
		tree_to_check[1]=new Prefix_Tree('b');
		list_to_check.add(1);
		t.add("b");
		for(int i=0;i<26;i++) {
			if(t.getChildren()[i]!=null) {
				assertEquals(t.getChildren()[i].getValue(),tree_to_check[i].getValue());
			}
		}
		assertEquals(t.getLocations(),list_to_check);


		//testing that the value is correct
		tree_to_check[0]=new Prefix_Tree('b');
		assertNotEquals(t.getChildren()[0].getValue(),tree_to_check[0].getValue());

	}

	@Test
	public void testGetWords() {
		
		t=new Prefix_Tree('\0'); 
		Build_Data_Struct b=new Build_Data_Struct(t);
		String [] s= {"Illigal input, please try again"};

		//testing error cases
		assertEquals(t.get_words("hfbg7g3rb")[0],s[0]);
		assertEquals(t.get_words(" ")[0],s[0]);
		
		//testing list of word with prefix a
		try {
			String[] treeList=t.get_words("a");
			List<String> list_to_check=b.listForTest("a");
			
			assertEquals(list_to_check.size(),treeList.length);
			for(int i=0;i<s.length;i++) {
				assertTrue(list_to_check.contains(treeList[i]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//testing list of word with prefix al
		try {
			String[] treeList=t.get_words("ale");
			List<String> list_to_check=b.listForTest("ale");
			
			assertEquals(list_to_check.size(),treeList.length);
			for(int i=0;i<s.length;i++) {
				assertTrue(list_to_check.contains(treeList[i]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//testing output of empty string, expect all the words
		try {
			String[] treeList=t.get_words("");
			List<String> list_to_check=b.listForTest("");
			
			assertEquals(list_to_check.size(),treeList.length);
			for(int i=0;i<s.length;i++) {
				assertTrue(list_to_check.contains(treeList[i]));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}




}
