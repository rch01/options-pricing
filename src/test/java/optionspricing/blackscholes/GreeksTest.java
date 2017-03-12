package optionspricing.blackscholes;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreeksTest {

	@Test
	public void testDelta() {
		assertEquals(0.6035, Greeks.delta('c', 0.05, 0.5, Black76.calculateD1(1240, 1200, ((double) 183) / 365, 0.2)), 0.01);
		assertEquals(-0.3717, Greeks.delta('p', 0.05, 0.5, Black76.calculateD1(1240, 1200, ((double) 183) / 365, 0.2)), 0.01);
		
		assertEquals(0.4509, Greeks.delta('c', 0.05, 0.3846153846, Black76.calculateD1(49, 50, ((double) 140) / 365, 0.2)), 0.01);
		assertEquals(-0.5300, Greeks.delta('p', 0.05, 0.3846153846, Black76.calculateD1(49, 50, ((double) 140) / 365, 0.2)), 0.01);
		
		assertEquals(0.4427, Greeks.delta('c', 0.05, ((double) 122) / 365, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35)), 0.01);
		assertEquals(-0.5406, Greeks.delta('p', 0.05, ((double) 122) / 365, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35)), 0.01);
		
		assertEquals(0.5132, Greeks.delta('c', 0.09, 0.25, Black76.calculateD1(20, 20, ((double) 92) / 365, 0.25)), 0.01);
		assertEquals(-0.4643, Greeks.delta('p', 0.09, 0.25, Black76.calculateD1(20, 20, ((double) 92) / 365, 0.25)), 0.01);
		
		assertEquals(0.0353, Greeks.delta('c', 0.07, ((double) 100) / 365, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25)), 0.01);
		assertEquals(-0.9456, Greeks.delta('p', 0.07, ((double) 100) / 365, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25)), 0.01);
		
		assertEquals(0.8319, Greeks.delta('c', 0.05, ((double) 1) / 4, Black76.calculateD1(1.1, 1, ((double) 1) / 4, 0.2)), 0.01);
		assertEquals(-0.1556, Greeks.delta('p', 0.05, ((double) 1) / 4, Black76.calculateD1(1.1, 1, ((double) 1) / 4, 0.2)), 0.01);
	}

	@Test
	public void testTheta() {
		assertEquals(-0.1662, Greeks.theta('c', 1240, 1200, 0.05, ((double) 183) / 365, 0.2, Black76.calculateD1(1240, 1200, ((double) 183) / 365, 0.2), Black76.calculateD2(1240, 1200, ((double) 183) / 365, 0.2)), 0.01);
		assertEquals(-0.1715, Greeks.theta('p', 1240, 1200, 0.05, ((double) 183) / 365, 0.2, Black76.calculateD1(1240, 1200, ((double) 183) / 365, 0.2), Black76.calculateD2(1240, 1200, ((double) 183) / 365, 0.2)), 0.01);
		
		assertEquals(-0.0082, Greeks.theta('c', 49, 50, 0.05, ((double) 140) / 365, 0.2, Black76.calculateD1(49, 50, ((double) 140) / 365, 0.2), Black76.calculateD2(49, 50, ((double) 140) / 365, 0.2)), 0.01);
		assertEquals(-0.0080, Greeks.theta('p', 49, 50, 0.05, ((double) 140) / 365, 0.2, Black76.calculateD1(49, 50, ((double) 140) / 365, 0.2), Black76.calculateD2(49, 50, ((double) 140) / 365, 0.2)), 0.01);
		
		assertEquals(-0.6309, Greeks.theta('c', 2006, 2100, 0.05, ((double) 122) / 365, 0.35, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35), Black76.calculateD2(2006, 2100, ((double) 122) / 365, 0.35)), 0.01);
		assertEquals(-0.6182, Greeks.theta('p', 2006, 2100, 0.05, ((double) 122) / 365, 0.35, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35), Black76.calculateD2(2006, 2100, ((double) 122) / 365, 0.35)), 0.01);
		
		assertEquals(-0.0051, Greeks.theta('c', 20, 20, 0.09, ((double) 92) / 365, 0.25, Black76.calculateD1(20, 20, ((double) 92) / 365, 0.25), Black76.calculateD2(20, 20, ((double) 92) / 365, 0.25)), 0.01);
		assertEquals(-0.0051, Greeks.theta('p', 20, 20, 0.09, ((double) 92) / 365, 0.25, Black76.calculateD1(20, 20, ((double) 92) / 365, 0.25), Black76.calculateD2(20, 20, ((double) 92) / 365, 0.25)), 0.01);
		
		assertEquals(-0.0790, Greeks.theta('c', 1567, 2000, 0.07, ((double) 100) / 365, 0.25, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25), Black76.calculateD2(1567, 2000, ((double) 100) / 365, 0.25)), 0.01);
		assertEquals(0.0, Greeks.theta('p', 1567, 2000, 0.07, ((double) 100) / 365, 0.25, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25), Black76.calculateD2(1567, 2000, ((double) 100) / 365, 0.25)), 0.01);
		
		assertEquals(-0.0001, Greeks.theta('c', 1.1, 1, 0.05, ((double) 1) / 4, 0.2, Black76.calculateD1(1.1, 1, ((double) 1) / 4, 0.25), Black76.calculateD2(1.1, 1, ((double) 1) / 4, 0.2)), 0.01);
		assertEquals(0.0001, Greeks.theta('p', 1.1, 1, 0.05, ((double) 1) / 4, 0.2, Black76.calculateD1(1.1, 1, ((double) 1) / 4, 0.25), Black76.calculateD2(1.1, 1, ((double) 1) / 4, 0.2)), 0.01);
	}

	@Test
	public void testGamma() {
		assertEquals(0.0021, Greeks.gamma(1240, 0.05, ((double) 183) / 365, 0.2, Black76.calculateD1(1240, 1200, ((double) 183) / 365, 0.2)), 0.01);
		assertEquals(0.0641, Greeks.gamma(49, 0.05, ((double) 140) / 365, 0.2, Black76.calculateD1(49, 50, ((double) 140) / 365, 0.2)), 0.01);
		assertEquals(0.0010, Greeks.gamma(2006, 0.05, ((double) 122) / 365, 0.35, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35)), 0.01);
		assertEquals(0.1550, Greeks.gamma(20, 0.09, ((double) 92) / 365, 0.25, Black76.calculateD1(20, 20, ((double) 92) / 365, 0.25)), 0.01);
		assertEquals(0.0004, Greeks.gamma(1567, 0.07, ((double) 100) / 365, 0.25, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25)), 0.01);
		assertEquals(2.1658, Greeks.gamma(1.1, 0.05, ((double) 1) / 4, 0.2, Black76.calculateD1(1.1, 1, ((double) 1) / 4, 0.2)), 0.01);
	}
}
