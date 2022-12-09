package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtAttr;
import us.codecraft.webmagic.samples.tdt.entity.TdtAttrExample;


public interface TdtAttrDAO {
    long countByExample(TdtAttrExample example);

    int deleteByExample(TdtAttrExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtAttr record);

    int insertSelective(TdtAttr record);

    List<TdtAttr> selectByExample(TdtAttrExample example);

    TdtAttr selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtAttr record, @Param("example") TdtAttrExample example);

    int updateByExample(@Param("record") TdtAttr record, @Param("example") TdtAttrExample example);

    int updateByPrimaryKeySelective(TdtAttr record);

    int updateByPrimaryKey(TdtAttr record);
}