package tree;
/**
 * JUnit test Class for the Binary Search Tree Class 
 *  
 * @author JKidney
 * @version 1 
 * 
 *      Created: Oct 17, 2013
 * Last Updated: Oct 17, 2013 - creation (jkidney)
 *               Oct 24, 2013 - tests created (jkidney)
 */

import static org.junit.Assert.*;


import org.junit.Test;
import org.junit.Before;

import tree.treeIterators.*;
import comparisonObjects.*;

public class TestBinarySearchTree 
{
	private BinarySearchTree<Integer,Integer> tree;

	/**
	 * Helper method used to populate the tree with data 
	 */
	public void addAllData(int[] thingsToAdd) 
	{
		for (int i : thingsToAdd) {
			tree.add(i,i);
		}
	}

	/**
	 * Helper method used to confirm that all data in the tree
	 * is in the proper binary search tree format based upon a level-order
	 * traversal of the tree.
	 */
	public void confirmMatch(int[] expectedElements)
	{
		BinaryTreeIterator<Integer,Integer> iter = tree.getTraversalIterator(BinarySearchTree.LEVEL_TRAV);
		int index=0;

		//confirm that we have matching tree size an expected number of elements
		String errMsg = "Tree size does not match expected number of nodes:";
		errMsg += " Expected " + expectedElements.length + " got " + tree.getSize(); 
		assertEquals(errMsg, expectedElements.length,tree.getSize());

		//confirm that each node in the traversal matches the expected node from a Level
		//order traversal at the given iteration. Checks to make sure that the expected key matches
		while(iter.canMoveToNext())
		{
			int key = iter.getCurrentKey();
			int expectedKey = expectedElements[index];
			assertEquals("Node at wrong location in tree: ",expectedKey,key);

			iter.moveToNext();
			index++;
		}

	}

	/**
	 * Called before the start of each test case below is run ( this is done by JUnit)
	 */
	@Before
	public void setUp()
	{
		tree = new BinarySearchTree<Integer,Integer>(new IntegerCompare());
	}

	/**
	 * Checks to make sure the tree is empty when it is first created
	 */
	@Test
	public void tree_Empty_at_start() 
	{
		try
		{
			BinaryTreeIterator<Integer,Integer> iter = tree.getTraversalIterator(BinarySearchTree.LEVEL_TRAV);

			assertEquals("Tree not empty at time of creation: root is not null",null, iter.getCurrentNode() );
			assertEquals("Tree not empty at time of creation:",0, tree.getSize());
		}
		catch(Exception ex)
		{
			fail("Exception caught: " + ex.getMessage());
		}
	}


	/*====================================================================
    Add to the tree tests
    ==================================================================*/
	/**
	 * Tests adding a single element added to the tree
	 */
	@Test
	public void add_Single_Element() 
	{
		try
		{
			int[] elements = { 5 };
			addAllData(elements);
			confirmMatch(elements);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 * Tests adding three elements to the tree
	 * second element should go to the right of the root, third should go
	 * to the left of the root. Creates 2 full levels in the tree.
	 */
	@Test
	public void add_Three_Nodes()
	{
		try
		{
			int[] elements = { 5, 100, 2 };
			int[] expectedLevelOrderTrav = { 5, 2, 100 };

			addAllData(elements);	
			confirmMatch(expectedLevelOrderTrav);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 * Tests adding 7 nodes to the tree, down an extra level
	 * two extra node down the left, two extra down the right
	 * creates a full third level to the tree
	 */
	@Test
	public void add_Seven_Nodes()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 7, 102 };

			addAllData(elements);	
			confirmMatch(expectedLevelOrderTrav);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 * Adds multiple nodes to the tree and confirms that they have
	 * been added properly. (this tree has 6 levels)
	 */
	@Test
	public void add_Multiple_Nodes()
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 7, 102, -50, 4, 6, 10, 150, -25, 8, 140, -40, -15, 9, 145 };

			addAllData(elements);	
			confirmMatch(expectedLevelOrderTrav);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/*====================================================================
    Find in tree tests
    ==================================================================*/
	/**
	 * Tests trying to do a find on an empty tree
	 */
	@Test
	public void find_On_An_Empty_tree() 
	{
		try
		{
			assertEquals("List not empty at time of creation:",0, tree.getSize());

			Integer value = tree.find(2);
			assertNull("find method returned an object on an empty list: ",value);
		}
		catch(Exception ex)
		{
			fail("Exception caught in find test");
		}
	}

	/**
	 * Tests trying to do a find on a single element in a non empty tree where the node should be in the tree
	 */
	@Test
	public void find_in_Tree_Node_Exists() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			addAllData(elements);	

			Integer value = tree.find(10);

			assertNotNull("find method returned null for a node that should be in the tree: ",value);
			assertEquals("Returned node value does not match expected value: ", 10, value.intValue());

		}
		catch(Exception ex)
		{
			fail("Exception caught in find test");
		}
	}

	/**
	 * Tests trying to do a find on a single element in a non empty tree where the node is not in the tree
	 */
	@Test
	public void find_in_Tree_Node_Does_Not_Exists() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			addAllData(elements);	

			Integer value = tree.find(-10);
			assertNull("find method returned non null for a node that should not be in the tree: ",value);		
		}
		catch(Exception ex)
		{
			fail("Exception caught in find test");
		}
	}

	/**
	 * Tests trying to do a find on all nodes in a non empty tree (all valid finds)
	 */
	@Test
	public void find_All_Tree_Nodes_Exist() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			addAllData(elements);	

			for(int expectedValue: elements)
			{
				Integer value = tree.find(expectedValue);
				assertNotNull("find method returned null for a node that should be in the tree: ",value);
				assertEquals("Returned node value does not match expected value: ", expectedValue, value.intValue());
			}

		}
		catch(Exception ex)
		{
			fail("Exception caught in find test");
		}
	}

	/**
	 * Tests trying to do a find on all nodes in a non empty tree (all invalid finds)
	 */
	@Test
	public void find_All_Tree_Nodes_None_Exist() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			addAllData(elements);	

			for(int expectedValue: elements)
			{
				Integer value = tree.find(expectedValue * 1000);
				assertNull("find method returned obkect for a node that should not be in the tree: ",value);
			}

		}
		catch(Exception ex)
		{
			fail("Exception caught in find test");
		}
	}

	/*====================================================================
	    remove TESTS
	    ==================================================================*/
	/**
	 *  Tests trying to do a remove on an empty list. 
	 */
	@Test
	public void remove_On_An_Empty_Tree() 
	{
		try
		{
			Integer data = tree.remove(2);
			assertNull("remove method returned an object on an empty list: ",data);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}


	/**
	 *  Tests trying to do a remove of a single element that is not in the tree
	 */
	@Test
	public void remove_On_An_NonEmpty_Tree_Node_Does_Not_Exist() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 7, 102 };
			addAllData(elements);	

			Integer data = tree.remove(1000);
			assertNull("remove method returned an object for a node not in the tree: ",data);

			confirmMatch(expectedLevelOrderTrav);// confirm that the tree was not changed after call
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 *  Tests trying to do a remove of a single element that is a leaf node
	 */
	@Test
	public void remove_Leaf_Node() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102 };
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 102 };
			addAllData(elements);	

			Integer data = tree.remove(7);
			assertNotNull("remove method returned null for a node that should be in the tree: ",data);
			assertEquals("Returned node value does not match expected value: ",7, data.intValue());

			confirmMatch(expectedLevelOrderTrav);// confirm that the tree was not changed after call
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 *  Tests trying to do a remove of a single element that has one child
	 */
	@Test
	public void remove_Parent_With_One_Child() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145,9 };
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 7, 102, -25, 4, 6, 10, 150, -40, -15, 8, 140, 9, 145 };

			addAllData(elements);	

			Integer data = tree.remove(-50);
			assertNotNull("remove method returned null for a node that should be in the tree: ",data);
			assertEquals("Returned node value does not match expected value: ",-50, data.intValue());

			confirmMatch(expectedLevelOrderTrav);// confirm that the tree was not changed after call
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 *  Tests trying to do a remove of a single element that has two children
	 *  The in order successor is a leaf node
	 */
	@Test
	public void remove_Parent_With_Two_Child_Successor_is_a_leaf() 
	{
		try
		{
			int[] elements = { 5, 100, 2, 3, 7, 1, 102, 4, -50, -25, -40, -15, 6, 10, 150, 8, 140,145};
			int[] expectedLevelOrderTrav = { 5, 2, 100, 1, 3, 8, 102, -50, 4, 6, 10, 150, -25, 140, -40, -15,145 };

			addAllData(elements);	

			Integer data = tree.remove(7);
			assertNotNull("remove method returned null for a node that should be in the tree: ",data);
			assertEquals("Returned node value does not match expected value: ",7, data.intValue());

			confirmMatch(expectedLevelOrderTrav);// confirm that the tree was not changed after call
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}


	/**
	 *  Tests trying to do a remove of a single element that has two children
	 *  The in order successor has a single child
	 */
	@Test
	public void remove_Parent_With_Two_Child_Successor_has_single_Child() 
	{
		try
		{
			int[] elements = {5,2,10,1,15,3,8,4};
			int[] expectedLevelOrderTrav = {5,3,10,1,4,8,15 };

			addAllData(elements);	

			Integer data = tree.remove(2);
			assertNotNull("remove method returned null for a node that should be in the tree: ",data);
			assertEquals("Returned node value does not match expected value: ",2, data.intValue());

			confirmMatch(expectedLevelOrderTrav);// confirm that the tree was not changed after call
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}

	/**
	 *  Tests trying remove all nodes from the tree
	 */
	@Test
	public void remove_All() 
	{
		try
		{
			int index = 0;
			int[] elements = {5,2,10,7,4,3};
			int[] rem = { 5, 3, 10, 7, 2, 4 };
			int[][] expectedLevelOrderTravs = 
				{ 
					{7,2,10,4,3},
					{7,2,10,4},
					{7,2,4},
					{2,4},
					{4},
					{}
				};

			addAllData(elements);	

			for(index=0; index < rem.length; index++ )
			{
				Integer data = tree.remove(rem[index]);
				assertNotNull("remove method returned null for a node that should be in the tree: ",data);
				assertEquals("Returned node value does not match expected value: ",rem[index], data.intValue());

				confirmMatch(expectedLevelOrderTravs[index]);// confirm that the tree was not changed after call
			}
			
			tree.printAllNodes(BinarySearchTree.IN_TRAV);
		}
		catch(Exception ex)
		{
			fail("Exception caught");
		}
	}
	//
	//	/**
	//	 * Tests trying to do a removeAt(index) that is a fail on a lower/upper bound
	//	 */
	//	@Test
	//	public void removeAt_On_Out_Of_Bounds() 
	//	{
	//		try
	//		{
	//			int[] elements  = { 5, 10, 20, 1 };
	//			addToFront(elements);
	//
	//			try
	//			{
	//				tree.removeAt(-1); // lower bound
	//				fail("Exception not thrown for lower bound");
	//			}
	//			catch(Exception er1){ }
	//
	//			try
	//			{
	//				tree.removeAt(100); // upper bound
	//				fail("Exception not thrown for upper bound");
	//			}
	//			catch(Exception er2){ }
	//
	//			try
	//			{
	//				tree.removeAt(4); // right on the edge of the upper bound
	//				fail("Exception not thrown for hight bound on edge");
	//			}
	//			catch(Exception er3){ }
	//
	//			assertTrue(true); // if it gets here it means that the past 
	//			//two tests for bounds past
	//		}
	//		catch(Exception ex)
	//		{
	//			fail("Unknown Exception caught");
	//		}
	//
	//	}
	//
	//	/**
	//	 * Helper methods for testing the remove function
	//	 */
	//	public int[] toArray(ArrayList<Integer> numbers)
	//	{
	//		int[] list = new int[numbers.size()];
	//		for(int index=0; index < list.length; index++)
	//			list[index] = numbers.get(index);
	//		return list;
	//	}
	//
	//	public int[] toRevArray(ArrayList<Integer> numbers)
	//	{
	//		int[] list = new int[numbers.size()];
	//		int count = 0;
	//		for(int index=list.length-1; index >= 0; index--)
	//		{
	//			list[count] = numbers.get(index);
	//			count++;
	//		}
	//
	//		return list;
	//	}
	//
	//	/**
	//	 * Tests RemoveAt on an non empty list 
	//	 * First, middle and end points
	//	 */
	//	@Test
	//	public void removeAT_NotEmpty_check() 
	//	{
	//		try
	//		{
	//			int[] elements = { 5, 4, 7, 11 , 20 };
	//			int[] indexToRem = {1,2,2,0,0};
	//			ArrayList<Integer> numbers = new ArrayList<Integer>();
	//
	//			for(int value : elements)
	//				numbers.add(value);
	//
	//			addToEnd(elements);
	//
	//			for(int index : indexToRem)
	//			{
	//				int remValue = numbers.remove(index);
	//				assertEquals( new Integer(remValue), tree.removeAt(index));
	//				confirmElementOrderForwards( toArray(numbers) );
	//				confirmElementOrderBackwards( toRevArray(numbers) );
	//				assertEquals( numbers.size() , tree.getSize() );
	//			}
	//
	//			// list should be empty so confirm that all refs are set properly
	//			DoubleLinkListIterator<Integer> iter = tree.getStartIterator();
	//			DoubleLinkListIterator<Integer> iter2 = tree.getEndIterator();
	//
	//			assertEquals(null, iter.getCurrentData() );
	//			assertEquals(null, iter2.getCurrentData() );
	//			assertEquals(0, tree.getSize());
	//		}
	//		catch(Exception ex)
	//		{
	//			fail("Exception caught");
	//		}
	//	}


}
