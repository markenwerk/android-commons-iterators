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

import net.markenwerk.commons.iterators.AbstractProtectedIterator;

import java.util.NoSuchElementException;


/**
 * A {@link PairIterator} is an {@link AbstractProtectedIterator} that iterates over a given
 * {@link Pair}, where both components have a common super type.
 *
 * @param <Payload> The payload type.
 * @author Torsten Krause (tk at markenwerk dot net)
 * @since 2.2.0
 */
public final class PairIterator<Payload> extends AbstractProtectedIterator<Payload> {

	private final Pair<? extends Payload, ? extends Payload> pair;

	private int index;

	/**
	 * Creates a new {@link PairIterator}.
	 *
	 * @param pair The {@link Pair} to iterate over.
	 * @throws IllegalArgumentException If the given {@link Pair} is {@literal null}.
	 */
	public PairIterator(Pair<? extends Payload, ? extends Payload> pair) throws
		IllegalArgumentException {
		if (null == pair) {
			throw new IllegalArgumentException("The given pair is null");
		}
		this.pair = pair;
	}

	public boolean hasNext() {
		return index < 2;
	}

	public Payload next() throws NoSuchElementException {
		switch (index++) {
			case 0:
				return pair.first;
			case 1:
				return pair.second;
			default:
				throw new NoSuchElementException("This iterator has no next element");
		}
	}

}
