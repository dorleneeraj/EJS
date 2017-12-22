package com.algorithms.sorting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Random;

import data.structure.heaps.Heap;
import data.structure.heaps.MinHeap;

public class ExternalMergeSort {

	public static void main(String[] args) throws IOException {

		int numWays = 10;
		int runSize = 1000;

		String file = preprocess(numWays, runSize);
		String outputFile = "ouputFile";
		externalSort(file, outputFile, numWays, runSize);
	}

	private static String preprocess(int numWays, int runSize)
			throws IOException {
		String inputFile = "input.txt";
		String outputFile = "output.txt";

		Files.deleteIfExists(new File(inputFile).toPath());
		Files.deleteIfExists(new File(outputFile).toPath());

		Random random = new Random();

		for (int i = 0; i < numWays; i++) {
			Integer array[] = new Integer[runSize];
			for (int j = 0; j < runSize; j++) {
				array[j] = random.nextInt(100);

			}

			storeNumsToFile(inputFile, array);

		}
		return inputFile;

	}

	private static void externalSort(String inputFile, String outputFile,
			int numWays, int runSize) throws IOException {

		String[] intermediateFiles = createInitialRuns(inputFile, numWays,
				runSize);
		mergerFiles(intermediateFiles, outputFile, numWays, runSize);
	}

	private static void mergerFiles(String[] intermediateFiles,
			String outputFile, int numWays, int runSize) {
		File outF = new File(outputFile);
		Heap<Integer, Integer> minHeap = new MinHeap<Integer, Integer>();

		for (int i = 0; i < numWays; i++) {

		}

	}

	private static String[] createInitialRuns(String inputFile, int numWays,
			int runSize) throws IOException {

		String[] sortedPartFiles = new String[numWays];
		for (int i = 0; i < numWays; i++) {
			Integer[] nums = readNumsFromFile(inputFile, 0);
			Mergesort.sort(nums, 0, nums.length - 1);
			String fName = i + "";
			File f = new File(fName);
			Files.deleteIfExists(f.toPath());
			sortedPartFiles[i] = fName;
			storeNumsToFile(fName, nums);
		}
		return sortedPartFiles;
	}

	private static void storeNumsToFile(String fName, Integer[] array)
			throws IOException {
		File file = new File(fName);
		Files.deleteIfExists(file.toPath());
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		for (int i = 0; i < array.length; i++) {
			writer.write(array[i] + " ");
		}
		writer.write('\n');
		writer.flush();
		writer.close();

	}

	private static Integer[] readNumsFromFile(String fName, int offset)
			throws IOException {
		File file = new File(fName);
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		randomFile.seek(offset);
		String line = randomFile.readLine();
		String[] numStrs = line.split(" ");
		Integer[] nums = new Integer[numStrs.length];
		for (int j = 0; j < nums.length; j++) {
			nums[j] = Integer.parseInt(numStrs[j]);
		}
		randomFile.close();
		return nums;
	}

}
