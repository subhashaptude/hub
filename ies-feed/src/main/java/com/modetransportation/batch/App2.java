package com.modetransportation.batch;

import com.modetransportation.batch.core.FlatFileWriter;

public class App2 {

	public static void main(String[] args) {
		FlatFileWriter f = new FlatFileWriter();
		try {
			f.writeReversals();
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
