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
import android.util.SparseIntArray;

import net.markenwerk.commons.datastructures.Entry;
import net.markenwerk.commons.iterators.AbstractProtectedIterator;

import java.util.NoSuchElementException;


/**
 * A {@link SparseIntegerArrayIterator} is an {@link AbstractProtectedIterator} that iterates over a given
 * {@link SparseIntArray}.
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public final class SparseIntegerArrayIterator extends AbstractProtectedIterator<Entry<Integer, Integer>> {

	private final SparseIntArray array;

	private int index;

	/**
	 * Creates a new {@link SparseIntegerArrayIterator}.
	 *
	 * @param array The {@link SparseIntArray} to iterate over.
	 * @throws IllegalArgumentException If the given {@link SparseIntArray} is {@literal null}.
	 */
	public SparseIntegerArrayIterator(SparseIntArray array) throws IllegalArgumentException {
		if (null == array) {
			throw new IllegalArgumentException("The given array is null");
		}
		this.array = array;
	}

	public boolean hasNext() {
		return index < array.size();
	}

	public Entry<Integer, Integer> next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("This iterator has no next element");
		}
		int index = this.index++;
		return new Entry<>(array.keyAt(index), array.valueAt(index));
	}


}
