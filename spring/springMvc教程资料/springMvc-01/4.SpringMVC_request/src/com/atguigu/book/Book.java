package com.atguigu.book;

/**
 * 书名:<input type="text" name="bookName"/><br/>
	作者:<input type="text" name="author"/><br/>
	价格:<input type="text" name="price"/><br/>
	库存:<input type="text" name="stock"/><br/>
	销量:<input type="text" name="sales"/><br/>
 * @author lfy
 *
 */
public class Book {
	
	private String bookName;
	private String author;
	private Double price;
	private Integer stock;
	private Integer sales;
	private Address address;
	
	//一定有无参构造器
	
	
	
	/**
	 * @return the bookName
	 */
	public String getBookName() {
		return bookName;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", author=" + author + ", price="
				+ price + ", stock=" + stock + ", sales=" + sales
				+ ", address=" + address + "]";
	}
	
	
	
	

}
