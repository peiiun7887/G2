package com.product.model;

import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;
	
	public ProductService(){
		dao = new ProductDAO();
	}
	
	
	public ProductVO addProduct(ProductVO productVO){
		String com_num = dao.insert(productVO);
		productVO.setCom_num(com_num);
		return productVO;	
	}
	
	public ProductVO updateProduct(ProductVO productVO){
		dao.update(productVO);
		return productVO;
	}
	
	public List<ProductVO> stoFindProductbyName(String com_name, String sto_num){
		return dao.stoFindbyProductName(com_name, sto_num);
	}
	
	public List<ProductVO> stoFindAllProduct(String sto_num){
		return dao.stoGetAll(sto_num);		
	}
	
	public ProductVO getOneProduct(String com_num){
		return dao.findByPrimaryKey(com_num);
	}
	
	public List<ProductVO> getAll(){
		return dao.getAll();
	}
	
}
