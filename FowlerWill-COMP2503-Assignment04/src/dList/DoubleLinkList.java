package dList;

import comparisonObjects.CompareObjects;


public class DoubleLinkList<type> 
{
	private Node<type> start; // will always keep a reference to the first node in the list
	private Node<type> end;   // will always keep a reference to the last node in the list
	public int size;
	
	/**
	 * default constructor
	 */
	public DoubleLinkList()
	{
		start = end = null;
		size = 0;
	}

	/**
	 * gives back the size of the list
	 * @return the number of nodes in the list
	 */
	public int getSize() 
	{ 
		Node<type> cur = start;
		int s = 0;
		if(cur == null)
			return s;
		else 
		{
			s++; //add one just in case it's size of  just one
			while(cur.getNext() != null) 
			{
				s++;
				cur = cur.getNext();
			}
		}
		return s;
		
	}

	/**
	 * creates an Iterator object that begins at the start of the list
	 * @return the iterator object
	 */
	public DoubleLinkListIterator<type> getStartIterator()
	{
		//DO NOT CHANGE THIS METHOD
		return new DoubleLinkListIterator<type>(start);
	}
	
	/**
	 * creates an Iterator object that begins at the end of the list
	 * @return the iterator object
	 */
	public DoubleLinkListIterator<type> getEndIterator()
	{
		//DO NOT CHANGE THIS METHOD
		return new DoubleLinkListIterator<type>(end);
	}
	
	/**
	 * This method will create a new node to contain the data and add it to the
	 * front of the list
	 * @param data the data element to add to the start of the list
	 */
	public void addToFront(type data)
	{
		if(start == null)
		{
			start = end = new Node<type>(data);
			size = 1;
		}
		else 
		{
			Node<type> newStart = new Node<type>(data);
			newStart.setNext(start);
			start.setPrev(newStart);
			start = newStart;
			size++;
		}
	}
	/**
	 * This method will create a new node to contain the data and add it to the end of the list
	 * You should try to do this add in O(1)
	 * @param data the data element to add to the end of the list
	 */
	public void addToEnd(type data)
	{
		if(end == null)
		{
			start = end = new Node<type>(data);
			size = 1;
		}
		else 
		{
			Node<type> newEnd = new Node<type>(data);
			newEnd.setPrev(end);
			end.setNext(newEnd);
			end = newEnd;
			size++;
		}
	}

	/**
	 * This method will create a new node to contain the data and add it sorted into the list.
	 * It should apply an insertion sort step to place the node properly in the list. The first node
	 * should be the smallest and the end node should be the largest. Note: This method only
	 * works if all insertions to the list are done using this method rather then the other
	 * add methods. 
	 * @param data the data element to add to the list
	 * @param comparator the compare object used to determine the proper insertion point
	 * @see CompareObjects 
	 */
	public void addSorted(type data, CompareObjects<type> comparator)
	{
		Node<type> newNode = new Node<type>(data);
		Node<type> cur = start;
		
		switch (size) {
		case 0:
			this.addToFront(data);
			break;
		case 1:
			if(comparator.compare(cur.getData(), newNode.getData()) < 1)
				this.addToEnd(data);
			else
				this.addToFront(data);
			break;
		default:
			int i = 0;
			while(comparator.compare(cur.getData(), newNode.getData()) < 1 && cur.getNext() != null)
			{
				cur = cur.getNext();
				i++;
			}
			
			if(cur.getNext() == null)
			{
				if(comparator.compare(cur.getData(), newNode.getData()) < 1)
					this.addToEnd(data);
				else
				{
					Node<type> prev = cur.getPrev();
					cur.setPrev(newNode);
					newNode.setNext(cur);
					newNode.setPrev(prev);
					prev.setNext(newNode);
					size ++;
				}
				break;
			}
			else if( i == 0 )
			{
				this.addToFront(data);
			}
			else 
			{
				Node<type> prev = cur.getPrev();
				cur.setPrev(newNode);
				newNode.setNext(cur);
				newNode.setPrev(prev);
				prev.setNext(newNode);
				size ++;
			}
			break;
		}
	}

	/**
	 * Retrieves the data stored at the given index/node in the list. The first
	 * node is considered to be stored at index 0
	 * @param index the index to get data from
	 * @return the data element stored at the given location if the index is within the bounds
	 * @throws Exception if the index is out of bounds or the list is empty
	 */
	public type get(int index) throws Exception
	{
		Node<type> cur = start;
		int i = 0;

		while(cur != null && i != index)
		{
			cur = cur.getNext();
			i++;
		}
		if(i == index && cur != null)
			return cur.getData();
		else 
			throw new Exception();
	}
	
	/**
	 * Removes the data stored at the given index/node in the list. The first
	 * node is considered to be stored at index 0. The dat from this index will be removed.
	 * @param index the index to get data/node to remove
	 * @return the data element stored at the given location if the index is within the bounds
	 * @throws Exception if the index is out of bounds or the list is empty
	 */
	public type removeAt(int index) throws Exception
	{
		Node<type> cur = start;
		int i = 0;

		while(cur != null && i != index)
		{
			cur = cur.getNext();
			i++;
		}
		if(i == index && cur != null)
		{
			type retData = cur.getData();
			if(cur.getNext() == null && cur.getPrev() == null)
			{
				start = end = cur = null;
			}
			else if(cur.getPrev() == null)
			{
				cur.getNext().setPrev(null);
				start = cur.getNext();
			}
			else if(cur.getNext() == null)
			{
				cur.getPrev().setNext(null);
				end = cur.getPrev();
			}
			else
			{
				cur.getNext().setPrev(cur.getPrev());
				cur.getPrev().setNext(cur.getNext());
			}
			size--;
			return retData;
		}
		else 
			throw new Exception();
	}

	/**
	 * prints the list to console/standard output from the first node to the last node
	 */
	public void print()
	{
		Node<type> curr = start;

		System.out.print("Start->");

		while(curr != null)
		{
			System.out.print("["+curr.getData()+"]->");
			curr = curr.getNext();
		}

		System.out.println("null");
	}

}
