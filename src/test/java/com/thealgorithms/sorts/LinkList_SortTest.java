package com.thealgorithms.sorts;
import org.junit.jupiter.api.Test;

import com.thealgorithms.sorts.LinkList_Sort;

import static org.junit.jupiter.api.Assertions.*;
public class LinkList_SortTest {
    @Test
	void testForOneElement() 
	{
        int a[]={56};
		Node n = LinkList_Sort.sortWith(a,2);
		assertTrue(LinkList_Sort.isSorted(n));
	}

	@Test
	void testForTwoElements() 
	{
        int a[]={6,4};
		Node n = LinkList_Sort.sortWith(a,1);
		assertTrue(LinkList_Sort.isSorted(n));
	}

	@Test
	void testForThreeElements() 
	{
		int a[]={875,253,12};
		Node n = LinkList_Sort.sortWith(a,3);
		assertTrue(LinkList_Sort.isSorted(n));
	}

	@Test
	void testForFourElements() 
	{
		int a[]={86,32,87,13};
		Node n = LinkList_Sort.sortWith(a,2);
		assertTrue(LinkList_Sort.isSorted(n));
	}

	@Test
	void testForFiveElements() 
	{
		int a[]={6,5,3,0,9};
		Node n = LinkList_Sort.sortWith(a,1);
		assertTrue(LinkList_Sort.isSorted(n));
	}


	@Test
	void testForSixElements() 
	{
		int a[]={9,65,432,32,47,327};
		Node n = LinkList_Sort.sortWith(a,3);
		assertTrue(LinkList_Sort.isSorted(n));
	}

	@Test
	void testForSevenElements() 
	{
		int a[]={6,4,2,1,3,6,7};
		Node n = LinkList_Sort.sortWith(a,1);
		assertTrue(LinkList_Sort.isSorted(n));
    }

	@Test
	void testForEightElements() 
	{
		int a[]={123,234,145,764,322,367,768,34};
		Node n = LinkList_Sort.sortWith(a,2);
		assertTrue(LinkList_Sort.isSorted(n));
	}
}
