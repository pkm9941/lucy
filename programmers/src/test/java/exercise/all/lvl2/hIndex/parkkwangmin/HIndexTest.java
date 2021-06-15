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
		HIndex hIndex = new HIndex(citations);
		assertTrue(hIndex.getHIndex() >= 0, "숫자를 응답함");
	}
	
	@Test
	void getHIndex_논문인용횟수기준_역순으로() {
		int[] citations = {3, 0, 6, 1, 5};
		HIndex hIndex = new HIndex(citations);
		assertEquals(6, hIndex.getcitations()[0], "논문인용횟수기준 역순으로 정렬 후 시작");
	}
	
	@Test
	void getHIndexOfAPaper_논문인용횟수가_논문수보다크면_hIndex_0() {
		int[] citations = {3, 0, 6, 1, 5};
		HIndex hIndex = new HIndex(citations);
		;
		assertEquals(0, hIndex.getHIndexOfAPaper(6), "논문인용횟수가 논문수를 초과하면 제외");
	}
	
}
