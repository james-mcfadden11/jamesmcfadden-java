package com.techelevator;

import java.io.*;
import java.util.Scanner;

public class Lecture {

	public static void main(String[] args) throws IOException {

		Scanner userInput = new Scanner(System.in);

		/*
		 * The java.io.File class is a representation of file and directory path names.  It provides methods to inspect and
		 * modify file system objects.
		 *
		 * One benefit is that it compensates for differences in Windows and Unix use of '/' and '\' as directory delimiters.
		 *
		 * A new instance of File can be created from a String that contains a file system path
		 */
		System.out.println("Enter the path to a directory: ");
		String path = userInput.nextLine();

		File file = new File(path);

//		if(directory.exists()) {
//			directory.delete();
//		} else {
//			directory.mkdir();
//		}



		/*


				Data   ->    Buffer (Array)    ->    Disk

		 */





		try(FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter)) {

			printWriter.println("Walt");
			printWriter.println("Tom");
			printWriter.println("Tom");

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}



		/*if(file.exists()) {
			System.out.println("File does exist!");


			if (file.isFile()) {
				System.out.println("This is a file!");
			} else if(file.isDirectory()) {
				System.out.println("This is a directory!");

				for(File f : file.listFiles()) {
					System.out.println(f.getAbsolutePath());
				}
			}

			System.out.println("The size of the file is: " + file.length());

			System.out.println("The full path to the file is: " + file.getAbsolutePath());

		} else {
			System.out.println("File does not exist!");
			file.createNewFile();
		}*/


		System.out.println("What is the path to the original file: ");
		String filePath = userInput.nextLine();

		/*

		    Create the file object
		    Open the file for reading
			Open a new file for writing

			Read line by line of the original, while keeping track of the line count
			if the line count is even
			  write the line to the the other file

		 */
		boolean hasWritten = false;
		File evens = new File("evens.txt");
		File original = new File(filePath);
		try(Scanner fileReader = new Scanner(original);
		    PrintWriter writer = new PrintWriter(evens)) {

			int count = 1;
			while(fileReader.hasNextLine()) {
				String line = fileReader.nextLine();

				if(count % 2 == 0) {
					if(hasWritten) {
						writer.println();
					}
					writer.print(line);
					hasWritten = true;
				}


				count++;
			}

		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
		}

	}

}
