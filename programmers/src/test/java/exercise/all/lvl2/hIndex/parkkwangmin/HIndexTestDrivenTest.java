package exercise.all.lvl2.hIndex.parkkwangmin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 논문 인용 횟수를 역순으로 정렬 및 map에 key(인용 횟수), value(인용 횟수가 동일한 논문 수) 형태로 담는다  
 * 맨 첫번째 부터 key <= (해당 키값보다 크거나 같은 key에 해당하는 value들의 합) 조건을 만족하는지 체크한다
 * 가장 처음 만족하는 key를 H-Index로 정한다. 
 * 
 * @author 박광민
 * @since 2021. 6. 17.
 */
class HIndexTestDrivenTest {
	
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
		HIndexTestDriven hIndex = new HIndexTestDriven(citations);
		assertTrue(hIndex.getHIndex() >= 0, "숫자를 응답함");
	}
}
