package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtClass;
import us.codecraft.webmagic.samples.tdt.entity.TdtClassExample;

@Mapper
public interface TdtClassDAO {
    long countByExample(TdtClassExample example);

    int deleteByExample(TdtClassExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtClass record);

    int insertSelective(TdtClass record);

    List<TdtClass> selectByExample(TdtClassExample example);

    TdtClass selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtClass record, @Param("example") TdtClassExample example);

    int updateByExample(@Param("record") TdtClass record, @Param("example") TdtClassExample example);

    int updateByPrimaryKeySelective(TdtClass record);

    int updateByPrimaryKey(TdtClass record);

    int batchInsert(List<TdtClass> list);
}