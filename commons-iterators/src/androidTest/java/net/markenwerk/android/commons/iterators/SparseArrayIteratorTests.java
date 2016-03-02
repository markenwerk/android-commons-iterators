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
		array.put(0, new Object());
		array.put(1, new Object());

		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(array.get(0), iterator.next().getValue());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(array.get(1), iterator.next().getValue());
		Assert.assertFalse(iterator.hasNext());

	}


	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test
	public void removeWithFallback() {

		Object replacement = new Object();
		SparseArray<Object> array = new SparseArray<>();
		array.put(0, new Object());
		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array, replacement);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(array.get(0), iterator.next().getValue());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

		Assert.assertEquals(replacement, array.get(0));

	}

	/**
	 * Remove a value in a {@code short[]}.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void removeWithoutFallback() {

		SparseArray<Object> array = new SparseArray<>();
		array.put(0, new Object());

		Iterator<Entry<Integer, Object>> iterator = new SparseArrayIterator<>(array);

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(array.get(0), iterator.next().getValue());
		Assert.assertFalse(iterator.hasNext());

		iterator.remove();

	}

}
