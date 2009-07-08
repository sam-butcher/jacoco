/*******************************************************************************
 * Copyright (c) 2009 Mountainminds GmbH & Co. KG and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Marc R. Hoffmann - initial API and implementation
 *    
 * $Id: $
 *******************************************************************************/
package org.jacoco.core.analysis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit tests for {@link AbstractCounter}.
 * 
 * @author Marc R. Hoffmann
 * @version $Revision: $
 */
public class AbstractCounterTest {

	private AbstractCounter getInstance(int total, int covered) {
		return new AbstractCounter(total, covered) {
		};
	}

	@Test
	public void testGetTotal() {
		AbstractCounter c = CounterImpl.getInstance(33, 12);
		assertEquals(33, c.getTotalCount(), 0.0);
	}

	@Test
	public void testGetCovered() {
		AbstractCounter c = CounterImpl.getInstance(33, 15);
		assertEquals(15, c.getCoveredCount(), 0.0);
	}

	@Test
	public void testGetNotCovered() {
		AbstractCounter c = CounterImpl.getInstance(22, 15);
		assertEquals(7, c.getNotCoveredCount(), 0.0);
	}

	@Test
	public void testGetCoveredRatio1() {
		AbstractCounter c = getInstance(20, 10);
		assertEquals(0.5, c.getCoveredRatio(), 0.0);
	}

	@Test
	public void testGetCoveredRatio2() {
		AbstractCounter c = getInstance(20, 0);
		assertEquals(0.0, c.getCoveredRatio(), 0.0);
	}

	@Test
	public void testGetCoveredRatio3() {
		AbstractCounter c = getInstance(0, 0);
		assertTrue(Double.isNaN(c.getCoveredRatio()));
	}

	@Test
	public void testGetNotCoveredRatio1() {
		AbstractCounter c = getInstance(20, 10);
		assertEquals(0.5, c.getNotCoveredRatio(), 0.0);
	}

	@Test
	public void testGetNotCoveredRatio2() {
		AbstractCounter c = getInstance(20, 20);
		assertEquals(0.0, c.getNotCoveredRatio(), 0.0);
	}

	@Test
	public void testGetNotCoveredRatio3() {
		AbstractCounter c = getInstance(0, 0);
		assertTrue(Double.isNaN(c.getNotCoveredRatio()));
	}

	@Test
	public void testEquals1() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(300, 123);
		assertEquals(c1, c2);
	}

	@Test
	public void testEquals2() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(400, 123);
		assertFalse(c1.equals(c2));
	}

	@Test
	public void testEquals3() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(300, 124);
		assertFalse(c1.equals(c2));
	}

	@Test
	public void testEquals4() {
		AbstractCounter c = getInstance(300, 123);
		assertFalse(c.equals(new Integer(123)));
	}

	@Test
	public void testHashCode1() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(300, 123);
		assertEquals(c1.hashCode(), c2.hashCode(), 0.0);
	}

	@Test
	public void testHashCode2() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(400, 123);
		assertFalse(c1.hashCode() == c2.hashCode());
	}

	@Test
	public void testHashCode3() {
		AbstractCounter c1 = getInstance(300, 123);
		AbstractCounter c2 = getInstance(300, 124);
		assertFalse(c1.hashCode() == c2.hashCode());
	}

	@Test
	public void testToString() {
		AbstractCounter c = getInstance(300, 123);
		assertEquals("Counter[123/300]", c.toString());
	}

}