package exercise.all.lvl2.newsClustering.parkkwangmin;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 자카드 유사도(Jaccard Index)를 구하자
 * 자카드 유사도를 측정할 두 문자열을 집합(배열)로 반들자
 * 두 집합의 교집합을 구하자
 * 두 집합의 합집합을 구하자
 * 두 집합의 교집합과 합집합 갯수를 기준으로 자카드 유사도 값을 계산하자
 * @author 박광민
 * @since 2021. 6. 14.
 */
class NewsClusteringTest {
	
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
	void calculateJaccardIndexTest() {
		NewsClustering nc = new NewsClustering();
		String newsTitle1 = "aa1+aa2";
		String newsTitle2 = "AAAA12";
		int jaccardIndex = nc.calculateJaccardIndex(newsTitle1, newsTitle2);
		assertTrue(jaccardIndex >= 0, "숫자 리턴");
	}
	
	@Test
	void calculateJaccardIndexTest_1() {
		NewsClustering nc = new NewsClustering();
		String newsTitle1 = "FRANCE";
		String newsTitle2 = "french";
		int jaccardIndex = nc.calculateJaccardIndex(newsTitle1, newsTitle2);
		assertEquals(16384, jaccardIndex, "FRANCE, french");
	}
	
	@Test
	void calculateJaccardIndexTest_2() {
		NewsClustering nc = new NewsClustering();
		String newsTitle1 = "handshake";
		String newsTitle2 = "shake hands";
		int jaccardIndex = nc.calculateJaccardIndex(newsTitle1, newsTitle2);
		assertEquals(65536, jaccardIndex, "handshake, shake hands");
	}
	
	@Test
	void calculateJaccardIndexTest_3() {
		NewsClustering nc = new NewsClustering();
		String newsTitle1 = "aa1+aa2";
		String newsTitle2 = "AAAA12";
		int jaccardIndex = nc.calculateJaccardIndex(newsTitle1, newsTitle2);
		assertEquals(43690, jaccardIndex, "aa1+aa2, AAAA12");
	}
	
	@Test
	void calculateJaccardIndexTest_4() {
		NewsClustering nc = new NewsClustering();
		String newsTitle1 = "E=M*C^2";
		String newsTitle2 = "e=m*c^2";
		int jaccardIndex = nc.calculateJaccardIndex(newsTitle1, newsTitle2);
		assertEquals(65536, jaccardIndex, "E=M*C^2, e=m*c^2");
	}
	
	@Test
	void generateJaccardMapTest_Map_컬랙션을_응답() {
		NewsClustering nc = new NewsClustering();
		String str = "ABABE";
		Map<String, Integer> jaccardMap = nc.generateJaccardMap(str);
		assertTrue(jaccardMap.size() >= 0, "Map 컬렉션배열을 응답");
		//assertEquals(4, jaccardArray.length, "문자열크기5이면 총 4개 생성");
	}
	
	@Test
	void generateJaccardArrayTest_연속된_2개의문자열로_구성된_배열() {
		NewsClustering nc = new NewsClustering();
		String str = "ABCDE";
		Map<String, Integer> jaccardMap = nc.generateJaccardMap(str);
		assertEquals(4, jaccardMap.size(), "문자열길이 5 - 1 = 4");
		assertEquals(1, jaccardMap.get("ab"), "첫번째 ab");
		assertEquals(1, jaccardMap.get("bc"), "첫번째 bc");
		assertEquals(1, jaccardMap.get("cd"), "첫번째 cd");
		assertEquals(1, jaccardMap.get("de"), "첫번째 de");
	}
	
	@Test
	void generateJaccardArrayTest_연속된_2개의문자열중_영문자외_문자가_포함된것은_제외() {
		NewsClustering nc = new NewsClustering();
		String str = "AB-DE";
		Map<String, Integer> jaccardMap = nc.generateJaccardMap(str);
		assertEquals(2, jaccardMap.size(), "AB, DE 총 2개");
		assertEquals(1, jaccardMap.get("ab"), "첫번째 ab");
		assertEquals(1, jaccardMap.get("de"), "첫번째 de");
	}
	
	@Test
	void generateJaccardArrayTest_중복된_문자열이면_VALUE에_1추가() {
		NewsClustering nc = new NewsClustering();
		String str = "ABABE";
		Map<String, Integer> jaccardMap = nc.generateJaccardMap(str);
		assertEquals(3, jaccardMap.size(), "AB, BA, BE");
		assertEquals(2, jaccardMap.get("ab"), "ab 2개");
		assertEquals(1, jaccardMap.get("ba"), "ba 1개");
		assertEquals(1, jaccardMap.get("be"), "be 1개");
	}
	
	@Test
	void getIntersectionTest() {
		NewsClustering nc = new NewsClustering();
		Map<String, Integer> strMap1 = new HashMap<String, Integer>();
		strMap1.put("ab", 3);
		strMap1.put("bc", 1);
		strMap1.put("de", 1);
		Map<String, Integer> strMap2 = new HashMap<String, Integer>();
		strMap2.put("ab", 2);
		strMap2.put("cd", 1);
		strMap2.put("de", 2);
		Map<String, Integer> intersectionMap = nc.getIntersection(strMap1, strMap2);
		assertEquals(2, intersectionMap.size(), "AB, DE");
		assertEquals(2, intersectionMap.get("ab"), "ab");
		assertEquals(1, intersectionMap.get("de"), "de");
	}
	
	@Test
	void getUnionTest() {
		NewsClustering nc = new NewsClustering();
		Map<String, Integer> strMap1 = new HashMap<String, Integer>();
		strMap1.put("ab", 3);
		strMap1.put("bc", 1);
		strMap1.put("de", 1);
		Map<String, Integer> strMap2 = new HashMap<String, Integer>();
		strMap2.put("ab", 2);
		strMap2.put("cd", 1);
		strMap2.put("de", 2);
		Map<String, Integer> unionMap = nc.getUnion(strMap1, strMap2);
		assertEquals(4, unionMap.size(), "AB, DE");
		assertEquals(3, unionMap.get("ab"), "ab");
		assertEquals(1, unionMap.get("bc"), "bc");
		assertEquals(1, unionMap.get("cd"), "cd");
		assertEquals(2, unionMap.get("de"), "de");
	}
	
	@Test
	void getJaccardIndexTest() {
		NewsClustering nc = new NewsClustering();
		Map<String, Integer> strMap1 = new HashMap<String, Integer>();
		strMap1.put("ab", 3);
		strMap1.put("bc", 1);
		strMap1.put("de", 1);
		Map<String, Integer> strMap2 = new HashMap<String, Integer>();
		strMap2.put("ab", 2);
		strMap2.put("cd", 1);
		strMap2.put("de", 2);
		Map<String, Integer> intersectionMap = nc.getIntersection(strMap1, strMap2);
		Map<String, Integer> unionMap = nc.getUnion(strMap1, strMap2);
		
		int jaccardIndex = nc.getJaccardIndex(intersectionMap, unionMap);
		assertEquals((int)Math.floor((3/(double)7) * 65536), jaccardIndex, "3/7 * 65536");
	}
	
	
}
