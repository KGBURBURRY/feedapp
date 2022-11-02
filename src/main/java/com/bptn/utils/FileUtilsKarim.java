package com.bptn.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtilsKarim {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils1.class);

	// Reading the File
	public static void readFile(Path k) {
		String home = System.getProperty("user.home");
		System.out.println(home);

		Path p = Paths.get(home);
		if (Files.exists(k)) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(new File(k.toUri()));
				System.out.println("File has been read.");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("file wasn't found");
			}
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println("File in text reads: " + data);
				
			}
		}
	}

	// deleting the file
	public static void deleteFile(Path k) throws IOException, FileSystemException {
		String home = System.getProperty("user.home");
		System.out.println(home);

		Path p = Paths.get(home + "/test.txt");
		if (!Files.exists(p)) {
			Files.createFile(p);
		}
		System.out.println(Files.exists(p));
		try {
			Files.delete(p);
			System.out.println("file was successfully deleted.");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("file was successfully deleted.");
		}
		System.out.println(Files.exists(p));
	}

	// Writing on the file

	public static void writeFile(Path k) throws IOException  {
		String home = System.getProperty("user.home");
		System.out.println(home);
		Path p = Paths.get(home + "/test.txt");
		if (!Files.exists(p)) {
			try {
				Files.createFile(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (Files.exists(p)) {
				FileWriter writer = new FileWriter(new File(p.toUri()));
				writer.write("This is what I'm writing onto the file");
				try {
					writer.close();
				} catch (IOException e) {
					System.out.println("this is the issue");
					e.printStackTrace();
				}

			}
		}
	}

	// Update File
	public static void updateFile(Path k) throws IOException {
		String home = System.getProperty("user.home");
		System.out.println(home);
		Path p = Paths.get(home + "/test.txt");
		if (!Files.exists(p)) {
			Files.createFile(p);

			try (FileWriter fw = new FileWriter("NewTest.txt")) {
			}



		}

	}

	// isFileExist method
	public static void isFileExist(Path k) {

		if (Files.exists(k))
			System.out.println("The file: " + k + ", exists.");

		else
			System.out.println("The specified file does not exist.");
	}

	// isFileExistsWithExceptionHandling method
	public static void isFileExistsWithExceptionHandling(Path k) throws FileNotFoundException {
		Scanner scanner;

		try {
			scanner = new Scanner(new File(k.toUri()));
			while (scanner.hasNextLine()) {
				String str = scanner.nextLine();
				logger.debug(str);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// createFileIfNotExist method
	public static void createFileIfNotExist(Path k) throws IOException {

		if (!Files.exists(k)) {
			Files.createFile(k);
			System.out.println("The file: " + k + " has been created.");
		} else
			System.out.println("The file already exists");
	}

	public static void main(String[] args) throws IOException {

		String home = System.getProperty("user.home");
		System.out.println(home);

		Path p = Paths.get(home);
		System.out.println("Check if a file exists: " + home + ", " + Files.exists(p));

		Path p1 = Paths.get(home + "/test.txt");

		if (!Files.exists(p1)) {
			Files.createFile(p1);

		}

		try (FileWriter writer = new FileWriter(new File(p1.toUri()))) {

			for (int i = 0; i < 3; i++) {
				writer.write("Karim's file\n");
			}
			System.out.println("File writing has been executed.");
		}

		readFile(p1);
		writeFile(p1);
		isFileExist(p1);
		isFileExistsWithExceptionHandling(p1);
		createFileIfNotExist(p1);
		updateFile(p1);
		deleteFile(p1);

	}

}