package com.xiongdiyibeizi.mapper;

import com.xiongdiyibeizi.bean.Img;
import com.xiongdiyibeizi.bean.ImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ImgMapper {
    int countByExample(ImgExample example);

    int deleteByExample(ImgExample example);

    int deleteByPrimaryKey(String imgId);

    int insert(Img record);

    int insertSelective(Img record);

    List<Img> selectByExampleWithBLOBs(ImgExample example);

    List<Img> selectByExample(ImgExample example);

    Img selectByPrimaryKey(String imgId);

    int updateByExampleSelective(@Param("record") Img record, @Param("example") ImgExample example);

    int updateByExampleWithBLOBs(@Param("record") Img record, @Param("example") ImgExample example);

    int updateByExample(@Param("record") Img record, @Param("example") ImgExample example);

    int updateByPrimaryKeySelective(Img record);

    int updateByPrimaryKeyWithBLOBs(Img record);

    int updateByPrimaryKey(Img record);

	List<Img> selectLatestFive();
}