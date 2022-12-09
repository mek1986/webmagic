package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtEnum;
import us.codecraft.webmagic.samples.tdt.entity.TdtMethod;
import us.codecraft.webmagic.samples.tdt.entity.TdtMethodExample;


public interface TdtMethodDAO {
    long countByExample(TdtMethodExample example);

    int deleteByExample(TdtMethodExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtMethod record);

    int insertSelective(TdtMethod record);

    List<TdtMethod> selectByExample(TdtMethodExample example);

    TdtMethod selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtMethod record, @Param("example") TdtMethodExample example);

    int updateByExample(@Param("record") TdtMethod record, @Param("example") TdtMethodExample example);

    int updateByPrimaryKeySelective(TdtMethod record);

    int updateByPrimaryKey(TdtMethod record);

    int batchInsert(List<TdtMethod> list);
}