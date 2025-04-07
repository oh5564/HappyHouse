package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.FavoriteDto;

@Mapper
public interface FavoriteMapper {
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	void deleteFavorite(int fvid) throws Exception;
	List<FavoriteDto> listFavorite(String fvuserid) throws Exception;

}
