package com.kh.springchap2.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.springchap2.model.Product;

@Mapper
public interface ProductMapper {
	//src/main/resource/mapper/ProductMapper.xml
	//밑에 작성해준  sql id만 작성할 것 jpa처럼 모두 작성하면 에러가 발생한다.
	
	List<Product> getAllProduct();
}
