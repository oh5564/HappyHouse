package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.FavoriteDto;

public interface FavoriteService {
	void addFavorite(FavoriteDto favoriteDto) throws Exception;
	void deleteFavorite(int fvid) throws Exception;
	List<FavoriteDto> listFavorite(String fvuserid) throws Exception;

}
