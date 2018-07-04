package com.scp.maven.jdbc.Demo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

public class OracleDemo {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Driver loaded successfully...");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");
		System.out.println("DB connection is successfully done...");
		
		Statement st=con.createStatement();
		//st.executeUpdate("create table book(bookId number(3),bookName varchar2(20),authorName varchar2(30))");
		//System.out.println("table is created...");
		
		/*st.executeUpdate("insert into book values(101,'Mruthyunjay','Shivaji Savant')");
		st.executeUpdate("insert into book values(102,'Wings of fire','A.P.J.Abdul Kalam')");
		st.executeUpdate("insert into book values(103,'Hi Vat Ekatichi','V.P.Kale')");
		st.executeUpdate("insert into book values(104,'The Kiss of My Life','Imarn Hashmi')");
		st.executeUpdate("insert into book values(105,'Mein Kampf','Adolf Hitler')");*/
		
		//int no=st.executeUpdate("alter table book modify(bookName varchar2(30))");
		//System.out.println(no+"  Table is altered...");
		//no=st.executeUpdate("insert into book values(102,'The Story of My Life1111','Helen Keler')");
		//System.out.println(no+" record is inserted...");
		//st.executeUpdate("delete from book where bookName='The Story of My Life'");
		//System.out.println("record is deleted...");
		//st.executeQuery("drop table book");
		//System.out.println("table is droped");
		int no=st.executeUpdate("update book set bookId=106 where bookName='The Story of My Life1111'");
		System.out.println(no+ " record is updated...");
		
				
		ResultSet rs=st.executeQuery("select * from book");
		
		ResultSetMetaData rsMetadata=rs.getMetaData();
		int noOfColms=rsMetadata.getColumnCount();
		System.out.println(noOfColms);
		System.out.println("Table Description...");
		for(int i=1;i<=noOfColms;i++){
			System.out.println(rsMetadata.getColumnName(i)+"\t"+rsMetadata.getColumnTypeName(i));
		}
		List<Book>listOfBooks=new ArrayList<>();
		while(rs.next()){
			int bId=rs.getInt("bookId");
			String bName=rs.getString("bookName");
			String authorName=rs.getString("authorName");
			Book b1=new Book(bId, bName, authorName);
			listOfBooks.add(b1);
		}
		System.out.println("List of Books : ");
		System.out.println(listOfBooks);
		
		//st.executeUpdate("insert into student values(50,'Bill')");
		//System.out.println("record is inserted...");
		
		//st.executeUpdate("update student set studId=50 where studName='Stev'");
		//System.out.println("record is updated...");
		
		//st.executeUpdate("delete from student where studId=50");
		//System.out.println("record  is deleted...");
		/*ResultSet rs=st.executeQuery("select * from student ");//where studId>=20");
		List<Student>listOfStud=new ArrayList<>();
		while(rs.next()){
			int id=rs.getInt("studId");
			String name=rs.getString("studName");
			Student s1=new Student(id, name);
			listOfStud.add(s1);
		}
		System.out.println(listOfStud);*/
		//rs.close();
		st.close();
		con.close();
		System.out.println("Connection successfully closed...");
	}
}
class Book{
	int bookId;
	String bookName;
	String authorName;
	public Book(int bookId, String bookName, String authorName) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.authorName = authorName;
	}
	@Override
	public String toString() {
		return "\nBook [bookId=" + bookId + ", bookName=" + bookName + ", authorName=" + authorName + "]";
	}
	
}
class Student{
	int studId;
	String studName;
	public Student(int studId, String studName) {
		super();
		this.studId = studId;
		this.studName = studName;
	}
	@Override
	public String toString() {
		return "\nStudent [studId=" + studId + ", studName=" + studName + "]";
	}
	
}