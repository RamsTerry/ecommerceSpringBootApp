package com.simplilearn.shoes.dto;


import lombok.Data;

@Data
public class ProductDTO {
	private Long id;
	private String name;
	private int categoryId;
	
	private double price;
	private String description;
	private String image_Name;

	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage_Name() {
		return image_Name;
	}

	public void setImage_Name(String image_Name) {
		this.image_Name = image_Name;
	}

	public ProductDTO(Long id, String name, int categoryId, double price, String description, String image_Name) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.description = description;
		this.image_Name = image_Name;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", price=" + price
				+ ", description=" + description + ", image_Name=" + image_Name + "]";
	}

	
}
