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

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An {@link SparseBooleanArrayIterator} is a {@link Iterator} that iterates over a given
 * {@link SparseBooleanArray}.
 * <p>
 * Calling {@link SparseBooleanArrayIterator#remove()} may set the array to the given
 * replacement value at the index that corresponds to the last value returned by
 * {@link SparseBooleanArrayIterator#next()}.
 * </p>
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.0.0
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public final class SparseBooleanArrayIterator implements Iterator<Entry<Integer, Boolean>> {

	private final SparseBooleanArray array;

	private final boolean removable;

	private final Boolean replacement;

	private int index = -1;

	/**
	 * Creates a new {@link SparseBooleanArrayIterator} that iterates over the given {@link SparseBooleanArray}.
	 *
	 * @param array The {@link SparseBooleanArray} to iterate over.
	 * @throws IllegalArgumentException If the given {@link SparseBooleanArray} is {@literal null}.
	 */
	public SparseBooleanArrayIterator(SparseBooleanArray array) throws IllegalArgumentException {
		this(array, false, null);
	}

	/**
	 * Creates a new {@link SparseBooleanArrayIterator} that iterates over the given {@link SparseBooleanArray}.
	 *
	 * @param array       The {@link SparseBooleanArray} to iterate over.
	 * @param replacement The value to replace removed values with.
	 * @throws IllegalArgumentException If the given {@link SparseBooleanArray} is {@literal null}.
	 */
	public SparseBooleanArrayIterator(SparseBooleanArray array, boolean replacement) throws
		IllegalArgumentException {
		this(array, true, replacement);
	}

	private SparseBooleanArrayIterator(SparseBooleanArray array, boolean removable, Boolean replacement) throws
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

	public Entry<Integer, Boolean> next() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("SparseBooleanArrayIterator has no further element");
		}
		index++;
		return new Entry<>(array.keyAt(index), array.valueAt(index));
	}

	public void remove() {
		if (removable) {
			array.put(index, replacement);
		} else {
			throw new UnsupportedOperationException("Cannot remove from SparseBooleanArrayIterator");
		}
	}
}
