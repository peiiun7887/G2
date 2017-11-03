package com.product_type.model;

import java.util.List;

public interface ProductType_interface {
	public void insert(ProductTypeVO productTypeVO);
	public List<ProductTypeVO> getAll();
	public ProductTypeVO getOnePdcT(String pt_num);
}
