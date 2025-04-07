package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.FavoriteDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.mapper.FavoriteMapper;

@Service
public class FavoriteServiceImpl implements FavoriteService{
	
	@Autowired
	private FavoriteMapper favoriteMapper;
	
	@Override
	public void addFavorite(FavoriteDto favoriteDto) throws Exception{
		favoriteMapper.addFavorite(favoriteDto);
	}
	
	@Override
	public void deleteFavorite(int fvid) throws Exception{
		favoriteMapper.deleteFavorite(fvid);
	}
	
	@Override
	public List<FavoriteDto> listFavorite(String fvuserid) throws Exception{
		return favoriteMapper.listFavorite(fvuserid);
	}
	

}
