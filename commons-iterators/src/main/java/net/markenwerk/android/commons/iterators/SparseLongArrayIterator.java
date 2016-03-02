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

import net.markenwerk.commons.iterators.Entry;

import java.util.Iterator;


/**
 * An {@link SparseLongArrayIterator} is a {@link Iterator} that iterates over a given
 * {@link SparseLongArray}.
 * <p>
 * Calling {@link SparseLongArrayIterator#remove()} may set the array to the given
 * replacement value at the index that corresponds to the last value returned by
 * {@link SparseLongArrayIterator#next()}.
 * </p>
 *
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.0.0
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public final class SparseLongArrayIterator implements Iterator<Entry<Integer, Long>> {

	private final SparseLongArray array;

	private final boolean removable;

	private final Long replacement;

	private int index = -1;

	/**
	 * Creates a new {@link SparseLongArrayIterator} that iterates over the given {@link SparseLongArray}.
	 *
	 * @param array The {@link SparseLongArray} to iterate over.
	 * @throws IllegalArgumentException If the given {@link SparseLongArray} is {@literal null}.
	 */
	public SparseLongArrayIterator(SparseLongArray array) throws IllegalArgumentException {
		this(array, false, null);
	}

	/**
	 * Creates a new {@link SparseLongArrayIterator} that iterates over the given {@link SparseLongArray}.
	 *
	 * @param array       The {@link SparseLongArray} to iterate over.
	 * @param replacement The value to replace removed values with.
	 * @throws IllegalArgumentException If the given {@link SparseLongArray} is {@literal null}.
	 */
	public SparseLongArrayIterator(SparseLongArray array, long replacement) throws
		IllegalArgumentException {
		this(array, true, replacement);
	}

	private SparseLongArrayIterator(SparseLongArray array, boolean removable, Long replacement) throws
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

	public Entry<Integer, Long> next() {
		index++;
		return new Entry<>(array.keyAt(index), array.valueAt(index));
	}

	public void remove() {
		if (removable) {
			array.put(index, replacement);
		} else {
			throw new UnsupportedOperationException("cannot remove from SparseLongArrayIterator");
		}
	}
}
