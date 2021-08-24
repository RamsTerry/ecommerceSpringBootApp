package com.simplilearn.shoes.controllers;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.simplilearn.shoes.dto.ProductDTO;
import com.simplilearn.shoes.model.Category;
import com.simplilearn.shoes.model.Product;
import com.simplilearn.shoes.model.User;
import com.simplilearn.shoes.repository.UserRepository;
import com.simplilearn.shoes.service.CategoryService;
import com.simplilearn.shoes.service.ProductService;
import com.simplilearn.shoes.service.UserService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCatAdd(@ModelAttribute ("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCat(@PathVariable int id) {
	categoryService.removeCategoryById(id);
	return "redirect:/admin/categories";
	}
	@GetMapping("/admin/categories/update/{id}")
	public String updateCat(@PathVariable int id, Model model) {
		Optional<Category> category=categoryService.getCategoryById(id);
		if(category.isPresent()){
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	//product Section
	@GetMapping("/admin/products")
	public String products(Model model) {
	model.addAttribute("products", productService.getAllProduct());
	return "products";
	}
	@GetMapping("/admin/products/add")
	public String productAddGet(Model model) {
	model.addAttribute("productDTO", new ProductDTO());
	model.addAttribute("categories", categoryService.getAllCategory());
	return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String  productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,@RequestParam("productImage") MultipartFile file,@RequestParam("imgName")String imgName) throws IOException {
		
		Product product = new Product();
		
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		product.setPrice(productDTO.getPrice());
		product.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
			Files.write(fileNameAndPath, file.getBytes()); 
		}else {
			imageUUID=imgName;
		}
		
		product.setImage_Name(imageUUID);
		productService.addProduct(product);
		
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
	productService.removeProductById(id);
	return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable long id,Model model){
		
		Product product= productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage_Name(product.getImage_Name());
		
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		
		return "productsAdd";
	}
	////user
	@GetMapping("/admin/users")
	public String getUser(Model model) {
		model.addAttribute("users", userService.getAllUser());
		return "users";
	}
	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable long id) {
	userService.removeUsertById(id);
	return "redirect:/admin/users";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@PostMapping("/login")
	public String loginp(String email,String password) {
		
		if(email.equals("terry@gmail.com") || password.equals("passwords")) {
			System.out.print("Welcome");
			
			return "redirect:/admin/";
		}else {
			
			return "login";
		}
	}
	
	@GetMapping("/register")
	public String registartion(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String processRegistratin(User user) {
		repo.save(user);
		return "registersuccess";
	}
	

}
