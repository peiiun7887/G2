package com.func_list.model;

import java.util.List;

public class FuncListService {
	private FuncListDAO_interface dao;
	
	public FuncListService (){
		dao = new FuncListDAO();
	}
	public List<FuncListVO> getAll(){
		return dao.getAll();
	}
}
