package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtModule;
import us.codecraft.webmagic.samples.tdt.entity.TdtModuleExample;


public interface TdtModuleDAO {
    long countByExample(TdtModuleExample example);

    int deleteByExample(TdtModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtModule record);

    int insertSelective(TdtModule record);

    List<TdtModule> selectByExample(TdtModuleExample example);

    TdtModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtModule record, @Param("example") TdtModuleExample example);

    int updateByExample(@Param("record") TdtModule record, @Param("example") TdtModuleExample example);

    int updateByPrimaryKeySelective(TdtModule record);

    int updateByPrimaryKey(TdtModule record);
}