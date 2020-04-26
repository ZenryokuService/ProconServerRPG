/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * @author takunoji
 *
 * 2020/04/20
 */
public class CSVReader {
	/** ファイル読み込みを行う部品 */
	private BufferedReader buf;

	/**
	 * コンストラクタ
	 */
	public CSVReader() {
		// Mapの初期化
		Map<String, String> areaMap = new HashMap<String, String>();
		areaMap.put("1.Asia.csv", null);
		areaMap.put("2.Oseania.csv", null);
		areaMap.put("3.America.csv", null);
		areaMap.put("4.Europe.csv", null);
		areaMap.put("5.Africa.csv", null);

	}

	/**
	 * 読み込んだデータに対しIDを追加してCSVに修正する。
	 * 
	 * @param path 読み込むファイルのパス
	 * @param index CSVのデータに追加するINDEX, NULLの場合は追加しない
	 * @return 
	 */
	public List<String> readCSVAddID(String path, Integer index) throws Exception{
		List<String> lineList = new ArrayList<>();
		// ファイル読み込み
		File csv = new File(path);
		try {
			buf = new BufferedReader(new FileReader(csv));
		} catch (FileNotFoundException e) {
			buf = null;
			e.printStackTrace();
			throw e;
		}
		String line = null;
		// １行ずつ取得、編集
		Integer id = new Integer(0);
		try {
			String safix = index != null ? ", " + index.toString() : "";
			while((line = buf.readLine()) != null) {
				lineList.add(id.toString() + ", " + line + safix);
				id++;
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return lineList;
	}

	public void outputCsvFile(List<String> csvData, String outFile) throws Exception {
		PrintWriter write = null;
		try {
			write = new PrintWriter(new FileWriter(outFile));
			String sep = System.lineSeparator();
			for (String str : csvData) {
				write.write(str + sep);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			write.close();
		}
	}

	public void convertCsvToMdTable(String path, String outPath, Integer index) throws Exception {
		List<String> csvList = this.readCSVAddID(path, index);
		PrintWriter write = null;
		try {
			write = new PrintWriter(new FileWriter(outPath));
			String sep = System.lineSeparator();
			for (String str : csvList) {
				str = "| " + str.replace(", ", " |") + " |";
				write.write(str + sep);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			write.close();
		}
	}
}
