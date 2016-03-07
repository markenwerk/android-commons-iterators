# Some common iterators for Java

[![Build Status](https://travis-ci.org/markenwerk/java-commons-iterators.svg?branch=master)](https://travis-ci.org/markenwerk/java-commons-iterators)
[![Coverage Status](https://coveralls.io/repos/markenwerk/java-commons-iterators/badge.svg?branch=master&service=github)](https://coveralls.io/github/markenwerk/java-commons-iterators?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/56d813f1d716950040a0ef21/badge.svg?style=flat)](https://www.versioneye.com/user/projects/56d813f1d716950040a0ef21)[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/commons-iterators)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/android-commons-iterators/badge.svg)](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/android-commons-iterators)
[![API Level 8](https://img.shields.io/badge/android-API_8-brightgreen.svg)](https://developer.android.com/reference/packages.html)
[![MIT License](https://img.shields.io/badge/license-MIT-brightgreen.svg)](https://github.com/markenwerk/android-commons-iterators/blob/master/LICENSE)

## Overview

This is a collection of companion iterators for [some commonly useful `Iterable` implementations](https://github.com/markenwerk/android-commons-iterables) for the following use cases:

- [Iterating over sparse arrays](#iterating-over-sparse-arrays)
- [Iterating over other objects](#iterating-over-other-objects)

This library is an extended version of [some commonly useful `Iterator` implementations](https://github.com/markenwerk/java-commons-iterators) for Java, that contains `Iterators` for Android specific data structures.

This library is hosted in the [Maven Central Repository](https://maven-badges.herokuapp.com/maven-central/net.markenwerk/android-commons-iterators). You can use it with the following coordinates:

```gradle
compile 'net.markenwerk:android-commons-iterators:2.2.0'
```

Consult the [usage description](#usage) and [Javadoc](https://markenwerk.github.io/android-commons-iterators/index.html) for further information.

## Usage

### Iterating over sparse arrays

This library provides the following [`Iterators`][Iterator] that take existing sparse arrays and yield all elements of
 the given array wrapped in an [`Entry`][Entry]:

- [`LongSparseArrayIterator`][LongSparseArrayIterator]
- [`SparseArrayIterator`][SparseArrayIterator]
- [`SparseBooleanArrayIterator`][SparseBooleanArrayIterator]
- [`SparseIntegerArrayIterator`][SparseIntegerArrayIterator]
- [`SparseLongArrayIterator`][SparseLongArrayIterator]

```java
SparseArray<Foo> sparseArray = ...

// yields every foo from sparseArray
Iterator<Entry<Integer, Foo>> arraytIterator = new SparseArrayIterator(sparseArray);
```

### Iterating over other objects

This library provides the generic [`PairIterator`][PairIterator] that takes and existing
[`Pair`][Pair] and yields both contained values.

```java
Pair<Foo, Foo> pair = ...

// yields both foos from pair
Iterator<Foo> pairIterator = new PairIterator<>(pair);
```

[LongSparseArrayIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/LongSparseArrayIterator.html
[PairIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/PairIterator.html
[SparseArrayIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/SparseArrayIterator.html
[SparseBooleanArrayIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/SparseBooleanArrayIterator.html
[SparseIntegerArrayIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/SparseIntegerArrayIterator.html
[SparseLongArrayIterator]: https://markenwerk.github.io/android-commons-iterators/index.html?net/markenwerk/android/commons/iterators/SparseLongArrayIterator.html

[Entry]: https://markenwerk.github.io/java-commons-datastructures/index.html?net/markenwerk/commons/datastructures/Entry.html

[Pair]
