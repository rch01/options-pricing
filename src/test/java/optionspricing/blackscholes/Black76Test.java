package optionspricing.blackscholes;

import static org.junit.Assert.*;

import org.junit.Test;

public class Black76Test {

	@Test
	public void testCalculateTheoreticalOptionPrice() {
		assertEquals(88.3736, Black76.calculateTheoreticalOptionPrice('c', 1240, 1200, 0.5, 0.05, 0.2), 0.01);
		assertEquals(49.3612, Black76.calculateTheoreticalOptionPrice('p', 1240, 1200, 0.5, 0.05, 0.2), 0.01);
		
		assertEquals(1.9392, Black76.calculateTheoreticalOptionPrice('c', 49, 50, 0.3846153846, 0.05, 0.2), 0.01);
		assertEquals(2.9201, Black76.calculateTheoreticalOptionPrice('p', 49, 50, 0.3846153846, 0.05, 0.2), 0.01);
		
		assertEquals(120.6182, Black76.calculateTheoreticalOptionPrice('c', 2006, 2100, ((double) 122) / 365, 0.05, 0.35), 0.01);
		assertEquals(213.0603, Black76.calculateTheoreticalOptionPrice('p', 2006, 2100, ((double) 122) / 365, 0.05, 0.35), 0.01);
		
		assertEquals(0.9732, Black76.calculateTheoreticalOptionPrice('c', 20, 20, 0.25, 0.09, 0.25), 0.01);
		assertEquals(0.9732, Black76.calculateTheoreticalOptionPrice('p', 20, 20, 0.25, 0.09, 0.25), 0.01);
		
		assertEquals(2.7485, Black76.calculateTheoreticalOptionPrice('c', 1567, 2000, ((double) 100) / 365, 0.07, 0.25), 0.05);
		assertEquals(427.4897, Black76.calculateTheoreticalOptionPrice('p', 1567, 2000, ((double) 100) / 365, 0.07, 0.25), 0.05);
	}

	@Test
	public void testCalculateD1() {
		assertEquals(0.3026, Black76.calculateD1(1240, 1200, 0.5, 0.2), 0.01);
		assertEquals(-0.1070, Black76.calculateD1(49, 50, 0.3846153846, 0.2), 0.01);
		assertEquals(-0.1251, Black76.calculateD1(2006, 2100, ((double) 122) / 365, 0.35), 0.01);
		assertEquals(0.0625, Black76.calculateD1(20, 20, 0.25, 0.25), 0.01);
		assertEquals(-1.7991, Black76.calculateD1(1567, 2000, ((double) 100) / 365, 0.25), 0.01);
	}

	@Test
	public void testCalculateD2() {
		assertEquals(0.1611, Black76.calculateD2(1240, 1200, 0.5, 0.2), 0.01);
		assertEquals(-0.2311, Black76.calculateD2(49, 50, 0.3846153846, 0.2), 0.01);
		assertEquals(-0.3275, Black76.calculateD2(2006, 2100, ((double) 122) / 365, 0.35), 0.01);
		assertEquals(-0.0625, Black76.calculateD2(20, 20, 0.25, 0.25), 0.01);
		assertEquals(-1.9299, Black76.calculateD2(1567, 2000, ((double) 100) / 365, 0.25), 0.01);
	}

}
