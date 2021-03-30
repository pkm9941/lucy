package exercise.all.lvl2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.all.lvl2.stockPrice.parkkwangmin.StockPrice;

class StockPriceTest {

	private StockPrice stockPrice;
	private int size = 13000;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		int[] pricesPerSecond = StockPrice.generatePicesPerSecond(size);
		stockPrice = new StockPrice(pricesPerSecond);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void 중복for문() {
		stockPrice.solution(stockPrice.getPricesPerSecond());
		assertTrue(1 == 1);
	}
	
	@Test
	void 재귀() {
		stockPrice.calculateMaintainedTimeForEach();
		assertTrue(1 == 1);
	}
}
