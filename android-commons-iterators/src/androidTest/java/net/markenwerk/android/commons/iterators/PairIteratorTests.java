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


import android.util.Pair;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PairIteratorTests {

	@Test
	public void iterate() {

		Object first = new Object();
		Object second = new Object();

		Iterator<Object> iterator = new PairIterator<>(new Pair<>(first, second));

		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(first, iterator.next());
		Assert.assertTrue(iterator.hasNext());
		Assert.assertSame(second, iterator.next());
		Assert.assertFalse(iterator.hasNext());

	}

	@Test(expected = NoSuchElementException.class)
	public void iterateWithNoNext() {

		Iterator<Object> iterator = new PairIterator<>(new Pair<>(new Object(), new Object()));

		iterator.next();
		iterator.next();
		iterator.next();

	}

	@Test(expected = UnsupportedOperationException.class)
	public void remove() {


		Iterator<Object> iterator = new PairIterator<>(new Pair<>(new Object(), new Object()));

		iterator.next();
		iterator.remove();

	}

}
