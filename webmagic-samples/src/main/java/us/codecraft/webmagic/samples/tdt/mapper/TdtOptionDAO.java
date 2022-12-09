package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtClass;
import us.codecraft.webmagic.samples.tdt.entity.TdtOption;
import us.codecraft.webmagic.samples.tdt.entity.TdtOptionExample;


public interface TdtOptionDAO {
    long countByExample(TdtOptionExample example);

    int deleteByExample(TdtOptionExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtOption record);

    int insertSelective(TdtOption record);

    List<TdtOption> selectByExample(TdtOptionExample example);

    TdtOption selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtOption record, @Param("example") TdtOptionExample example);

    int updateByExample(@Param("record") TdtOption record, @Param("example") TdtOptionExample example);

    int updateByPrimaryKeySelective(TdtOption record);

    int updateByPrimaryKey(TdtOption record);

    int batchInsert(List<TdtOption> list);
}