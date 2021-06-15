package exercise.all.lvl2.hIndex.parkkwangmin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HIndexTest {
	
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
	void getHIndex_숫자리턴() {
		int[] citations = {3, 0, 6, 1, 5};
		HIndex hIndex = new HIndex();
		assertTrue(hIndex.getHIndex(citations) >= 0, "숫자를 응답함");
	}
	
}
