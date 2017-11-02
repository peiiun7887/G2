package com.product.model;

import java.util.List;

public class ProductService {

	private ProductDAO_interface dao;
	
	public ProductService(){
		dao = new ProductDAO();
	}
	
	
	public ProductVO addProduct(ProductVO productVO){
//		ProductVO productVO = new ProductVO();
//		productVO.setSto_num(sto_num);
//		productVO.setCom_name(com_name);
//		productVO.setM_price(new Double(m_price));
//		productVO.setL_price(new Double(l_price));
//		productVO.setDiscribe(discribe);
//		productVO.setImg(img);
//		productVO.setPt_num(pt_num);
//		productVO.setStatus(status);
//		productVO.setMercom_num(mercom_num);
		dao.insert(productVO);
		
		return productVO;	
	}
	
	public ProductVO updateProduct(ProductVO productVO){
//		ProductVO productVO = new ProductVO();
//		productVO.setCom_name(com_name);
//		productVO.setM_price(new Double(m_price));
//		productVO.setL_price(new Double(l_price));
//		productVO.setDiscribe(discribe);
//		productVO.setImg(img);
//		productVO.setPt_num(pt_num);
//		productVO.setStatus(status);
//		productVO.setMercom_num(mercom_num);
//		productVO.setCom_num(com_num);
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
