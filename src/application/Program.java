package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the path of the file: ");
		String strPath = sc.nextLine();
		
		List<Product> product = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))) {
			
			String line = br.readLine();
			File file = new File(strPath);
			boolean success = new File(file.getParent() + "\\out").mkdir();
			String newPath = file.getParent() + "\\out\\summary.csv";
			while (line != null) {
				String vect[] = line.split(",");
				String name = vect[0];
				double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				product.add(new Product(name, price, quantity));
				line = br.readLine();
			}
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(newPath))) {
				for(Product p : product) {
					bw.write(p.toString());
					bw.newLine();
				}
			}
			catch(IOException e) {
				System.out.println("Error Writing File: " + e.getMessage());
			}
			
		}
		catch(IOException e) {
			System.out.println("Error Reading File: " + e.getMessage());
		}
		finally {
			sc.close();
		}

	}

}
