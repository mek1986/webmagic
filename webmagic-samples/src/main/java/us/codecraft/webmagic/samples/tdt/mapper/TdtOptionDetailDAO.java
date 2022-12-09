package us.codecraft.webmagic.samples.tdt.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import us.codecraft.webmagic.samples.tdt.entity.TdtOption;
import us.codecraft.webmagic.samples.tdt.entity.TdtOptionDetail;
import us.codecraft.webmagic.samples.tdt.entity.TdtOptionDetailExample;


public interface TdtOptionDetailDAO {
    long countByExample(TdtOptionDetailExample example);

    int deleteByExample(TdtOptionDetailExample example);

    int deleteByPrimaryKey(String id);

    int insert(TdtOptionDetail record);

    int insertSelective(TdtOptionDetail record);

    List<TdtOptionDetail> selectByExample(TdtOptionDetailExample example);

    TdtOptionDetail selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TdtOptionDetail record, @Param("example") TdtOptionDetailExample example);

    int updateByExample(@Param("record") TdtOptionDetail record, @Param("example") TdtOptionDetailExample example);

    int updateByPrimaryKeySelective(TdtOptionDetail record);

    int updateByPrimaryKey(TdtOptionDetail record);

    int batchInsert(List<TdtOptionDetail> list);
}