package com.product.model;

import java.util.List;


public interface ProductDAO_interface {
	public String insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public List<ProductVO> getAll();
	public ProductVO findByPrimaryKey(String com_num);
	public List<ProductVO> stoFindbyProductName(String com_name , String sto_num);
	public List<ProductVO> stoGetAll(String sto_num);
}
