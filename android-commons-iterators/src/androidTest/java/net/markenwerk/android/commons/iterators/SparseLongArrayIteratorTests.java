/*
 * Copyright (c) 2016 Torsten Krause, Markenwerk GmbH
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


import android.annotation.TargetApi;
import android.os.Build;
import android.util.SparseLongArray;

import net.markenwerk.commons.datastructures.Entry;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class SparseLongArrayIteratorTests {

	@Test(expected = IllegalArgumentException.class)
	public void create_nullArrayy() {

		new SparseLongArrayIterator(null);

	}

	@Test
	public void iterate() {

		SparseLongArray array = new SparseLongArray();
		array.put(23, 23l);
		array.put(42, 42l);

		Iterator<Entry<Integer, Long>> iterator = new SparseLongArrayIterator(array);
		Assert.assertTrue(iterator.hasNext());

		Entry<Integer, Long> first = iterator.next();
		Assert.assertEquals(Integer.valueOf(23), first.getKey());
		Assert.assertSame(array.valueAt(0), first.getValue());
		Assert.assertTrue(iterator.hasNext());

		Entry<Integer, Long> second = iterator.next();
		Assert.assertEquals(Integer.valueOf(42), second.getKey());
		Assert.assertSame(array.valueAt(1), second.getValue());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test(expected = NoSuchElementException.class)
	public void iterate_noNext() {

		Iterator<Entry<Integer, Long>> iterator = new SparseLongArrayIterator(new SparseLongArray());

		iterator.next();

	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {

		SparseLongArray array = new SparseLongArray();
		array.put(23, 23l);

		Iterator<Entry<Integer, Long>> iterator = new SparseLongArrayIterator(array);

		iterator.remove();

	}

}
