package com.ssafy.happyhouse.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.AvgListDto;
import com.ssafy.happyhouse.model.HouseInfoDto;
import com.ssafy.happyhouse.model.SidoGugunCodeDto;
import com.ssafy.happyhouse.model.dongDto;
import com.ssafy.happyhouse.model.mapper.HouseMapMapper;

@Service
public class HouseMapServiceImpl implements HouseMapService {

	@Autowired
	private HouseMapMapper houseMapMapper;

	@Override
	public List<SidoGugunCodeDto> getSido() throws Exception {
		return houseMapMapper.getSido();
	}

	@Override
	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
		return houseMapMapper.getGugunInSido(sido);
	}

	@Override
	public List<dongDto> getDongInGugun(String gugun) throws Exception {
		return houseMapMapper.getDongInGugun(gugun);
	}

	@Override
	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
		return houseMapMapper.getAptInDong(dong);
	}

	@Override
	public List<HouseInfoDto> getAptInName(String apt, String state) throws Exception {
		return houseMapMapper.getAptInName(apt, state);
	}

	@Override
	public ArrayList<HouseInfoDto> getAptInMax(int max) throws Exception {

		List<HouseInfoDto> list = houseMapMapper.getAptInMax(max);
		// System.out.println((ArrayList<HouseInfoDto>) list);
		list = mergeSort((ArrayList<HouseInfoDto>) list);
		int sum = 0;
		ArrayList<HouseInfoDto> res = new ArrayList<HouseInfoDto>();
		for (HouseInfoDto dto : list) {
			sum += Integer.parseInt(dto.getRecentPrice().replace(",", "").trim());
			if (sum > max)
				break;
			res.add(dto);
		}
		return res;
	}

	private ArrayList<HouseInfoDto> mergeSort(ArrayList<HouseInfoDto> list) {
		if (list.size() <= 1)
			return list;

		ArrayList<HouseInfoDto> left = new ArrayList<HouseInfoDto>();
		ArrayList<HouseInfoDto> right = new ArrayList<HouseInfoDto>();
		int mid = list.size() / 2;
		for (int i = 0; i < mid; i++) {
			if (list.get(i).getRecentPrice() != null) {
				left.add(list.get(i));
			}

		}

		for (int i = mid; i < list.size(); i++) {
			if (list.get(i).getRecentPrice() != null) {
				right.add(list.get(i));
			}
		}

		left = mergeSort(left);
		right = mergeSort(right);

		return merge(left, right);

	}

	private ArrayList<HouseInfoDto> merge(ArrayList<HouseInfoDto> left, ArrayList<HouseInfoDto> right) {
		ArrayList<HouseInfoDto> res = new ArrayList<HouseInfoDto>(left.size() + right.size());
		int l = 0, r = 0;
		while (l < left.size() && r < right.size()) {
			int a = Integer.parseInt(left.get(l).getRecentPrice().replace(",", "").trim());
			int b = Integer.parseInt(right.get(r).getRecentPrice().replace(",", "").trim());
			if (a < b) {
				res.add(left.get(l++));
			} else {
				res.add(right.get(r++));
			}
		}
		while (l < left.size()) {
			res.add(left.get(l++));
		}
		while (r < right.size()) {
			res.add(right.get(r++));
		}
		return res;
	}

	@Override
	public HouseInfoDto getAptbyId(int apt) throws Exception {
		// TODO Auto-generated method stub
		return houseMapMapper.getAptbyId(apt);
	}

	@Override
	public List<AvgListDto> getAvgList(String dong, int min, int max) {
		double a = min * 3.305785;
		double b = max * 3.305785;
		return houseMapMapper.getAvgList(dong, a, b);
	}

	@Override
	public List<AvgListDto> getAvgListSinCity(double min, double max) {
		double a = min * 3.305785;
		double b = max * 3.305785;
		return houseMapMapper.getAvgListSinCity(a, b);
	}

}
