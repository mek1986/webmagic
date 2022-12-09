package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtEnum;
import us.codecraft.webmagic.samples.tdt.entity.TdtEnumExample;
import us.codecraft.webmagic.samples.tdt.entity.TdtOption;


public interface TdtEnumDAO {
    long countByExample(TdtEnumExample example);

    int deleteByExample(TdtEnumExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtEnum record);

    int insertSelective(TdtEnum record);

    List<TdtEnum> selectByExample(TdtEnumExample example);

    TdtEnum selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtEnum record, @Param("example") TdtEnumExample example);

    int updateByExample(@Param("record") TdtEnum record, @Param("example") TdtEnumExample example);

    int updateByPrimaryKeySelective(TdtEnum record);

    int updateByPrimaryKey(TdtEnum record);

    int batchInsert(List<TdtEnum> list);
}