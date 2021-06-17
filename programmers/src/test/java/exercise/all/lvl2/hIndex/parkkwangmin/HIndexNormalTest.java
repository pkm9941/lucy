package exercise.all.lvl2.hIndex.parkkwangmin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.util.StopWatch;

@TestMethodOrder(OrderAnnotation.class)
class HIndexNormalTest {
	
	private static int[] citations;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		citations = new int[1000000];
		for (int i = 0; i < 1000000; i++) {
			citations[i] = (int) (Math.random()*10000);
			//citations[i] = i;
		}
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
	@Order(1)
	void test() {
		int hIndexValue = 0;
		assertTrue(hIndexValue >= 0);
	}
	
	@Test
	@Order(6)
	void getHIndexMap() {
		HIndexNormal hIndex = new HIndexNormal();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int hIndexValue = hIndex.getHIndex(citations);
		stopWatch.stop(); 
		System.out.println(stopWatch.getTotalTimeSeconds() + " : getHIndexMap");
		System.out.println(hIndexValue);
		assertTrue(hIndexValue >= 0);
	}
	
	@Test
	@Order(5)
	void getHIndexList() {
		HIndexNormal hIndex = new HIndexNormal();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int hIndexValue = hIndex.getHIndexList(citations);
		stopWatch.stop(); 
		System.out.println(stopWatch.getTotalTimeSeconds() + " : getHIndexList");
		System.out.println(hIndexValue);
		assertTrue(hIndexValue >= 0);
	}
	
	@Test
	@Order(4)
	void getHIndexArray() {
		HIndexNormal hIndex = new HIndexNormal();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int hIndexValue = hIndex.getHIndexArray(citations);
		stopWatch.stop(); 
		System.out.println(stopWatch.getTotalTimeSeconds() + " : getHIndexArray");
		System.out.println(hIndexValue);
		assertTrue(hIndexValue >= 0);
	}
	
	@Test
	@Order(3)
	void getHIndexIntArray() {
		HIndexNormal hIndex = new HIndexNormal();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		int hIndexValue = hIndex.getHIndexIntArray(citations);
		stopWatch.stop(); 
		System.out.println(stopWatch.getTotalTimeSeconds() + " : getHIndexIntArray");
		System.out.println(hIndexValue);
		assertTrue(hIndexValue >= 0);
	}
	
	
	@Test
	@Order(10)
	void test2() {
		int hIndexValue = 0;
		assertTrue(hIndexValue >= 0);
	}
}
