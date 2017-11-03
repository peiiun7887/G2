package com.product_type.model;

import java.util.List;

public class ProductTypeService {
	private ProductType_interface dao;
	
	public ProductTypeService(){
		dao = new ProductTypeDAO();
	}
	
	public ProductTypeVO add(ProductTypeVO productTypeVO){
		dao.insert(productTypeVO);
		return productTypeVO;
	}
	public List<ProductTypeVO> getAll(){
		return dao.getAll();
	}
	
	public ProductTypeVO getOnePdcT(String pt_num){
		return dao.getOnePdcT(pt_num);
	}
	
}
