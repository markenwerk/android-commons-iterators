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
import android.util.SparseBooleanArray;

import net.markenwerk.commons.datastructures.Entry;
import net.markenwerk.commons.iterators.ProtectedIterator;

import java.util.NoSuchElementException;


/**
 * An {@link SparseBooleanArrayIterator} is a {@link ProtectedIterator} that iterates over a given
 * {@link SparseBooleanArray}.
 * <p>
 * Calling {@link SparseBooleanArrayIterator#remove()} may set the array to the given
 * replacement value at the index that corresponds to the last value returned by
 * {@link SparseBooleanArrayIterator#next()}.
 * </p>
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public final class SparseBooleanArrayIterator implements ProtectedIterator<Entry<Integer, Boolean>> {

	private final SparseBooleanArray array;

	private int index = -1;

	/**
	 * Creates a new {@link SparseBooleanArrayIterator} that iterates over the given {@link SparseBooleanArray}.
	 *
	 * @param array The {@link SparseBooleanArray} to iterate over.
	 * @throws IllegalArgumentException If the given {@link SparseBooleanArray} is {@literal null}.
	 */
	public SparseBooleanArrayIterator(SparseBooleanArray array) throws IllegalArgumentException {
		if (null == array) {
			throw new IllegalArgumentException("array is null");
		}
		this.array = array;
	}

	public boolean hasNext() {
		return array.size() != index + 1;
	}

	public Entry<Integer, Boolean> next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("SparseBooleanArrayIterator has no further element");
		}
		index++;
		return new Entry<>(array.keyAt(index), array.valueAt(index));
	}

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Cannot remove from a SparseBooleanArrayIterator");
	}
}