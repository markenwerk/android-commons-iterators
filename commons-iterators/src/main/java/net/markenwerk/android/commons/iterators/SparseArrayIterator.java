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
import android.util.SparseArray;

import java.util.Iterator;


/**
 * An {@link SparseArrayIterator} is a {@link Iterator} that iterates over a given
 * {@link SparseArray}.
 * <p>
 * Calling {@link SparseArrayIterator#remove()} may set the array to the given
 * replacement value at the index that corresponds to the last value returned by
 * {@link SparseArrayIterator#next()}.
 * </p>
 *
 * @param <Payload> The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.0.0
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public final class SparseArrayIterator<Payload> implements Iterator<Entry<Integer, Payload>> {

	private final SparseArray<Payload> array;

	private final boolean removable;

	private final Payload replacement;

	private int index = -1;

	/**
	 * Creates a new {@link SparseArrayIterator} that iterates over the given {@link SparseArray}.
	 *
	 * @param array The {@link SparseArray} to iterate over.
	 * @throws IllegalArgumentException If the given {@link SparseArray} is {@literal null}.
	 */
	public SparseArrayIterator(SparseArray<Payload> array) throws IllegalArgumentException {
		this(array, false, null);
	}

	/**
	 * Creates a new {@link SparseArrayIterator} that iterates over the given {@link SparseArray}.
	 *
	 * @param array       The {@link SparseArray} to iterate over.
	 * @param replacement The value to replace removed values with.
	 * @throws IllegalArgumentException If the given {@link SparseArray} is {@literal null}.
	 */
	public SparseArrayIterator(SparseArray<Payload> array, Payload replacement) throws
		IllegalArgumentException {
		this(array, true, replacement);
	}

	private SparseArrayIterator(SparseArray<Payload> array, boolean removable, Payload replacement) throws
		IllegalArgumentException {
		if (null == array) {
			throw new IllegalArgumentException("array is null");
		}
		this.array = array;
		this.removable = removable;
		this.replacement = replacement;
	}

	public boolean hasNext() {
		return array.size() != index + 1;
	}

	public Entry<Integer, Payload> next() {
		index++;
		int key = array.keyAt(index);
		return new Entry<Integer, Payload>(key, array.valueAt(key));
	}

	public void remove() {
		if (removable) {
			array.setValueAt(index, replacement);
		} else {
			throw new UnsupportedOperationException("cannot remove from SparseArrayIterator");
		}
	}
}
