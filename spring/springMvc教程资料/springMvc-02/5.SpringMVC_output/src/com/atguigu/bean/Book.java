package com.atguigu.bean;

public class Book {
	
	private Integer id;
	private String bookName;
	private String author;
	private Integer stock;
	private Integer sales;
	private Double price;
	
	
	
	public Book(Integer id, String bookName, String author, Integer stock,
			Integer sales, Double price) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.author = author;
		this.stock = stock;
		this.sales = sales;
		this.price = price;
	}
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @param bookName the bookName to set
	 */
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return the stock
	 */
	public Integer getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	/**
	 * @return the sales
	 */
	public Integer getSales() {
		return sales;
	}
	/**
	 * @param sales the sales to set
	 */
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", bookName=" + bookName + ", author="
				+ author + ", stock=" + stock + ", sales=" + sales + ", price="
				+ price + "]";
	}
	
	
	
	

}
