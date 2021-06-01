package exercise.all.lvl2;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PairRemoveTest {

	private static String s;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		s = "baaasewrdbsadfbwerrbaaasdbaaasewrdbsadfbwerrbaaasdbaaasewrdbsadfbwerrbaaasdbaaasewrdbsadfbwerrbaaasdbaaasewrdbsadfbwerrbaaasd";
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 50000; i++) {
			b.append(s);
		}
		
		s = b.toString();
		System.out.println("size : " + s.length());
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
	void 박소현() {
		exercise.all.lvl2.pairRemove.parksohyun.PairRemove.solution(s);
	}
	
	@Test
	void 노형래() {
		exercise.all.lvl2.pairRemove.nohhyungrae.PairRemove.solution(s);
	}
	
	@Test
	void 박광민() {
		exercise.all.lvl2.pairRemove.parkkwangmin.PairRemove.removePairs(s);
	}
	
}
