package com;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		for (;;) {
			Files.copy(Paths.get("c:\\Windows\\System32\\drivers\\etc\\hosts"), new FileOutputStream("hosts"));

			GregorianCalendar cal = new GregorianCalendar();
			try {
				cal = DateUtils.getAtomicTime();
			} catch (Exception e) {

			}

			Files.copy(Paths.get("hosts"), new FileOutputStream("hosts1"));
			List<String> social = Files.readAllLines(Paths.get("social.txt"));
			List<String> lines = Files.readAllLines(Paths.get("hosts1"));
			System.out.println(cal.getTime());

			if (cal.get(Calendar.HOUR_OF_DAY) >= 22) {

				Files.write(Paths.get("hosts1"), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

				for (String line : lines) {
					if (!social.contains(line)) {
						Files.write(Paths.get("hosts1"), line.getBytes(), StandardOpenOption.APPEND);
						Files.write(Paths.get("hosts1"), "\r\n".getBytes(), StandardOpenOption.APPEND);
					}
				}

				Files.write(Paths.get("hosts1"), Files.readAllBytes(Paths.get("social.txt")),
						StandardOpenOption.APPEND);

				Files.copy(Paths.get("hosts1"), new FileOutputStream("c:\\Windows\\System32\\drivers\\etc\\hosts"));
			} else if (cal.get(Calendar.HOUR_OF_DAY) >= 7) {

				Files.write(Paths.get("hosts1"), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

				for (String line : lines) {
					if (!social.contains(line)) {
						Files.write(Paths.get("hosts1"), line.getBytes(), StandardOpenOption.APPEND);
						Files.write(Paths.get("hosts1"), "\r\n".getBytes(), StandardOpenOption.APPEND);
					}
				}

				Files.copy(Paths.get("hosts1"), new FileOutputStream("c:\\Windows\\System32\\drivers\\etc\\hosts"));
			}

			System.out.println("done");

			Thread.sleep(60 * 1000 * 10);
		}
	}

}
