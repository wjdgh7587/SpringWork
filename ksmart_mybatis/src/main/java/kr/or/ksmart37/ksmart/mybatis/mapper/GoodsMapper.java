package kr.or.ksmart37.ksmart.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.ksmart37.ksmart.mybatis.dto.Goods;

//GoodsMapper

@Mapper
public interface GoodsMapper {
	public List<Goods> getGoodsList();
	
	public Goods getGoodsByCode(String goodsCode);
	
	public int modifyGoods(Goods goods);
	
	public int removeGoods(String goodsCode);

	
}
