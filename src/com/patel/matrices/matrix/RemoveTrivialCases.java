package com.patel.matrices.matrix;

import java.util.*;
public class RemoveTrivialCases {

	/**
	 * @param args
	 */
	@SuppressWarnings("all")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList[][] mylist = new ArrayList[2][1];
		mylist[0][0] = new ArrayList();
		mylist[1][0] = new ArrayList();
		
		// 1st array [1,2,1,0,4]
		mylist[0][0].add(1);
		mylist[0][0].add(2);
		mylist[0][0].add(1);
		mylist[0][0].add(0);
		mylist[0][0].add(4);
		
		//2nd array [1,1,5,1,0]
		mylist[1][0].add(1);
		mylist[1][0].add(1);
		mylist[1][0].add(5);
		mylist[1][0].add(1);
		mylist[1][0].add(0);
		
		//Remove the 0's from both the arrays
		mylist[0][0].removeAll(Collections.singleton(0));
		mylist[1][0].removeAll(Collections.singleton(0));
		
		System.out.println("List1: "+ mylist[0][0]);
		System.out.println("List2: "+ mylist[1][0]);
		
		int origRow1Size = mylist[0][0].size();
		int origRow2Size = mylist[1][0].size();	
		
		boolean valueEqualsDimension = true;
		
		while (valueEqualsDimension)
		{
			Iterator iterator1 = mylist[0][0].iterator();
			Iterator iterator2 = mylist[1][0].iterator();
			
			System.out.println("origRow1Size: "+ origRow1Size + "\torigRow2Size: "+ origRow2Size);
			
			while (iterator1.hasNext()){				
				int value1 = (int)iterator1.next();

				//Check if the value of the element is greater-than or equal to
				// the corresponding array
				if(value1 >= mylist[0][0].size()){	
					// Remove the element that is > = array size
					iterator1.remove();
					// Reduce the value of all the elements by 1 in the neighboring array 
					int index2 = 0;
					while(iterator2.hasNext()){
						int value2 = (int)iterator2.next();
						mylist[1][0].set(index2, (value2 - 1));
						index2++ ;
					}
				}
			} // inner-while 1
			
						
			while (iterator2.hasNext()){				
				int value2 = (int)iterator2.next();
				//Check if the value of the element is greater-than or equal to
				// the corresponding array
				if(value2 >= mylist[1][0].size()){	
					// Remove the element that is > = array size
					iterator2.remove();
					// Reduce the value of all the elements by 1 in the neighboring array
					int index1 = 0;
					while(iterator1.hasNext()){
						int value1 = (int)iterator1.next();
						mylist[0][0].set(index1, (value1 - 1));
						index1++ ;
					}
				}
			} // inner-while 2
			
			//Remove the 0's from both the arrays
			mylist[0][0].removeAll(Collections.singleton(0));
			mylist[1][0].removeAll(Collections.singleton(0));
			
			// Check if the length of the arrays changed after the iteration
			// if the size changed, repeat the while loop
			// else set valueEqualsDimension to false to exit the while loop
			if (origRow1Size != mylist[0][0].size() || origRow2Size != mylist[1][0].size())
			{
				origRow1Size = mylist[0][0].size();
				origRow2Size = mylist[1][0].size();
			}
			else
			{
				valueEqualsDimension = false;
			}
		}		
		System.out.println(mylist[0][0]);
		System.out.println(mylist[1][0]);
		
	}

}
