/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
/**
 * @author takunoji
 *
 * 2020/04/20
 */
public class CSVReaderTest {
	private CSVReader target;

	@Before
	public void init() {
	}

	@Test
	public void testNewInstance() {
		target = new CSVReader();
		assertNotNull(target);
	}

	@Test
	public void testReadFile() {
		String path = "resources/csv/materialCsv/1.Asia.csv";
		target = new CSVReader();
		List<String> list = null;
		try {
			list = target.readCSVAddID(path, null);
		} catch(Exception e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(list);
		assertEquals(25, list.size());
		assertEquals(2, list.get(0).split(",").length);
	}

	@Test
	public void testOutputFile() {
		String path = "resources/csv/materialCsv/1.Asia.csv";
		target = new CSVReader();
		try {
			target.outputCsvFile(target.readCSVAddID(path, 1), "resources/csv/output/out_Asia.csv");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testOutputFile2() {
		String path = "resources/csv/materialCsv/2.Oseania.csv";
		target = new CSVReader();
		try {
			target.outputCsvFile(target.readCSVAddID(path, 2), "resources/csv/output/out_Oseania.csv");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testOutputFile3() {
		String path = "resources/csv/materialCsv/3.America.csv";
		target = new CSVReader();
		try {
			target.outputCsvFile(target.readCSVAddID(path, 3), "resources/csv/output/out_America.csv");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testOutputFile4() {
		String path = "resources/csv/materialCsv/4.Europe.csv";
		target = new CSVReader();
		try {
			target.outputCsvFile(target.readCSVAddID(path, 4), "resources/csv/output/out_Europe.csv");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testOutputFile5() {
		String path = "resources/csv/materialCsv/5.Africa.csv";
		target = new CSVReader();
		try {
			target.outputCsvFile(target.readCSVAddID(path, 5), "resources/csv/output/out_Africa.csv");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMakeMd1() {
		String path = "resources/csv/materialCsv/1.Asia.csv";
		target = new CSVReader();
		try {
			target.convertCsvToMdTable(path, "resources/csv/output/md/Asia.md", new Integer(1));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMakeMd2() {
		String path = "resources/csv/materialCsv/2.Oseania.csv";
		target = new CSVReader();
		try {
			target.convertCsvToMdTable(path, "resources/csv/output/md/Oseania.md", new Integer(2));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMakeMd3() {
		String path = "resources/csv/materialCsv/3.America.csv";
		target = new CSVReader();
		try {
			target.convertCsvToMdTable(path, "resources/csv/output/md/America.md", new Integer(3));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMakeMd4() {
		String path = "resources/csv/materialCsv/4.Europe.csv";
		target = new CSVReader();
		try {
			target.convertCsvToMdTable(path, "resources/csv/output/md/Europe.md", new Integer(4));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testMakeMd5() {
		String path = "resources/csv/materialCsv/5.Africa.csv";
		target = new CSVReader();
		try {
			target.convertCsvToMdTable(path, "resources/csv/output/md/Africa.md", new Integer(5));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}
