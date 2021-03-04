package exercise.all.lvl2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PrinterTest {

	Printer printer = new Printer();
	
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
	void priorities배열은_null일수_없음() {
		int[] priorities = null;
		int location = 1;
		int orderByPrint = printer.getOrderMyPrint(priorities, location);
		assertEquals(-1, orderByPrint);
	}
	
	@Test
	void priorities배열은_빈_배열일_수_없음() {
		int[] priorities = {};
		int location = 1;
		int orderByPrint = printer.getOrderMyPrint(priorities, location);
		assertEquals(-1, orderByPrint);
	}
	
	@Test
	void priorities배열은_최대사이즈_100() {
		int[] priorities1 = new int[100];
		for (int i = 0; i < 100; i++) {
			priorities1[i] = i;
		}
		int[] priorities2 = new int[101];
		for (int j = 0; j < 101; j++) {
			priorities2[j] = j;
		}
		int location = 1;
		int orderByPrint1 = printer.getOrderMyPrint(priorities1, location);
		int orderByPrint2 = printer.getOrderMyPrint(priorities2, location);
		assertTrue(orderByPrint1 > -1);
		assertEquals(-1, orderByPrint2);
	}
	
	@Test
	void location은_최소0이상() {
		int[] priorities = new int[100];
		for (int i = 0; i < 100; i++) {
			priorities[i] = i;
		}

		int location = -1;
		int orderByPrint = printer.getOrderMyPrint(priorities, location);
		assertEquals(-1, orderByPrint);
	}
	
	@Test
	void location은_최대_priorities_크기보다_1작다() {
		int[] priorities = new int[100];
		for (int i = 0; i < 100; i++) {
			priorities[i] = i;
		}
		
		int location1 = 99;
		int location2 = 100;
		int orderByPrint1 = printer.getOrderMyPrint(priorities, location1);
		int orderByPrint2 = printer.getOrderMyPrint(priorities, location2);
		assertTrue(orderByPrint1 > -1);
		assertEquals(-1, orderByPrint2);
	}
	
	@Test
	void getOrderMyPrintTest01() {
		int[] priorities = {2, 1, 3, 2};
		int location = 2;
		int orderByPrint = printer.getOrderMyPrint(priorities, location);
		assertEquals(1, orderByPrint);
	}
	
	@Test
	void getOrderMyPrintTest02() {
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		int orderByPrint = printer.getOrderMyPrint(priorities, location);
		assertEquals(5, orderByPrint);
	}
}
