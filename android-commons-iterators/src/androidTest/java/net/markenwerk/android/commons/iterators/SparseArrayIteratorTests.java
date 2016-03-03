/*
 * Copyright (c) 2015 Torsten Krause, Markenwerk GmbH
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.markenwerk.android.commons.iterators;


import android.util.SparseArray;

import net.markenwerk.commons.datastructures.Entry;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

/**
 * JUnit test for {@link SparseArrayIterator}.
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 */
public class SparseArrayIteratorTests {


	/**
	 * Iterate over a {@code null} array.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void iterateNullArray() {

		new SparseArrayIterator<>(null);

	}

	/**
	 * Iterate over a payload array.
	 */
	@Test
	public void iterate() {

		SparseArray<Object> array = new SparseArray<>();
		array.put(23, new Object());
		array.put(42, new Object());

		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array);
		Assert.assertTrue(iterator.hasNext());

		Entry<Integer, Object> first = iterator.next();
		Assert.assertEquals(Integer.valueOf(23), first.getKey());
		Assert.assertSame(array.valueAt(0), first.getValue());
		Assert.assertTrue(iterator.hasNext());

		Entry<Integer, Object> second = iterator.next();
		Assert.assertEquals(Integer.valueOf(42), second.getKey());
		Assert.assertSame(array.valueAt(1), second.getValue());
		Assert.assertFalse(iterator.hasNext());

	}


	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test
	public void removeWithFallback() {

		Object replacement = new Object();
		SparseArray<Object> array = new SparseArray<>();
		array.put(23, new Object());

		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array, replacement);

		iterator.next();
		iterator.remove();

		Assert.assertEquals(replacement, array.valueAt(0));

	}

	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeWithoutFallback() {

		SparseArray<Object> array = new SparseArray<>();
		array.put(23, new Object());

		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array);

		iterator.next();
		iterator.remove();

	}

}
