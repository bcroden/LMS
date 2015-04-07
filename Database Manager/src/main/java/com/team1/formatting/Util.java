package com.team1.formatting;

import java.util.ArrayList;

import com.team1.books.Book;
import com.team1.books.InvalidISBNException;

public class Util {
	public static String DELIMITER = "@#@";
	public static String serializeBookArrayList(ArrayList<Book> books) {
		if(books == null)
			return null;
		if(books.size() == 0)
			return " ";
		
		String str = "";
		int i = 0;
		for(; i < books.size()-1; i++) {
			str = str.concat(books.get(i).getSerialized());
			str = str.concat(DELIMITER);
		}
		str = str.concat(books.get(i).getSerialized());
		return str;
	}
	
	public static ArrayList<Book> deserializeBookArrayList(String serialized) {
		if(serialized == null)
			return null;
		ArrayList<Book> books = new ArrayList<Book>();
		String[] strs = serialized.split(DELIMITER);
		if(strs.length == 0)
			return books;
		
		for(String s : strs) {
			books.add(new Book(s));
		}
		
		return books;
	}
	
	public static void main(String[] args) throws InvalidISBNException {
		ArrayList<Book> books = new ArrayList<Book>();
		for(int i = 0; i < 5; i++) {
			Book b = new Book("a" , "b", "c", "d", "e", "f");
			books.add(b);
		}
		
		String str = serializeBookArrayList(books);
		System.out.println(str);
		
		ArrayList<Book> books2 = deserializeBookArrayList(str);
		System.out.println(books2.size());
		for(Book b : books2)
			System.out.println(b.toString());
	}
}