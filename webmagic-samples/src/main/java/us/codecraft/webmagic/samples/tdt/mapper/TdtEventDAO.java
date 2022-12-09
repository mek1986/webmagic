package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtEvent;
import us.codecraft.webmagic.samples.tdt.entity.TdtEventExample;
import us.codecraft.webmagic.samples.tdt.entity.TdtMethod;


public interface TdtEventDAO {
    long countByExample(TdtEventExample example);

    int deleteByExample(TdtEventExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtEvent record);

    int insertSelective(TdtEvent record);

    List<TdtEvent> selectByExample(TdtEventExample example);

    TdtEvent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtEvent record, @Param("example") TdtEventExample example);

    int updateByExample(@Param("record") TdtEvent record, @Param("example") TdtEventExample example);

    int updateByPrimaryKeySelective(TdtEvent record);

    int updateByPrimaryKey(TdtEvent record);

    int batchInsert(List<TdtEvent> list);
}