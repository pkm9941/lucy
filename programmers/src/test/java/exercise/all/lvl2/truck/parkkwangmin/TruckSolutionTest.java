package exercise.all.lvl2.truck.parkkwangmin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import exercise.all.lvl2.truck.parkkwangmin.TruckSolution.Bridge;

class TruckSolutionTest {
	
	public TruckSolution truck = new TruckSolution();
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	@BeforeEach
	void setUp() throws Exception {
	}
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void 케이스1() {
		int bridgeLength = 2;
		int weight = 10;
		int[] truckWeights = {7,4,5,6};
		Bridge bridge = new Bridge(bridgeLength, weight);
		assertEquals(8, bridge.elapsedTime(truckWeights));
	}
	
	@Test
	void 케이스2() {
		int bridgeLength = 100;
		int weight = 100;
		int[] truckWeights = {10};
		Bridge bridge = new Bridge(bridgeLength, weight);
		assertEquals(101, bridge.elapsedTime(truckWeights));
	}
	
	@Test
	void 케이스3() {
		int bridgeLength = 100;
		int weight = 100;
		int[] truckWeights = {10,10,10,10,10,10,10,10,10,10};
		Bridge bridge = new Bridge(bridgeLength, weight);
		assertEquals(110, bridge.elapsedTime(truckWeights));
	}
	
}
