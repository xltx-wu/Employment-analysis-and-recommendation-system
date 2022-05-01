package indi.xltx.earsystemserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.jdbc.SQL;

import indi.xltx.earsystemserver.pojo.RecruitmentInfo;
import indi.xltx.earsystemserver.pojo.RecruitmentInfoCommon;

@Mapper
public interface RecruitmentInfoMapper {

    @SelectProvider(RecruitmentInfoSqlProvider.class)
    Cursor<RecruitmentInfo> getLatestInfo(@Param("limit") Integer limit);

    @Select("select * from job_info where id = #{id}")
    RecruitmentInfoCommon getCommonInfoById(Long id);

    @Select("select distinct industry from job_info")
    List<String> getIndustryList();

    @Select("select distinct city from job_info")
    List<String> getCityList();

    @SelectProvider(RecruitmentInfoSqlProvider.class)
    Cursor<RecruitmentInfo> getInfoByKeyWord(
            String city,
            Integer workTime,
            String industry,
            Integer minSalary,
            Integer maxSalary,
            Integer offset,
            Integer rows);

    class RecruitmentInfoSqlProvider implements ProviderMethodResolver {
        // 获取最新职业信息（简略）的SQL语句生成器
        public static String getLatestInfo(@Param("limit") Integer limit) {
            return new SQL() {
                {
                    SELECT("id");
                    SELECT("jobname");
                    SELECT("company");
                    SELECT("degree");
                    SELECT("releasetime");
                    FROM("job_info");
                    ORDER_BY("releasetime");
                    LIMIT(limit);
                }
            }.toString();
        }

        // 根据筛选条件获取职业信息的SQL语句生成器
        public static String getInfoByKeyWord(
                String city,
                Integer workTime,
                String industry,
                Integer minSalary,
                Integer maxSalary,
                Integer offset,
                Integer rows) {
            return new SQL() {
                {
                    SELECT("id");
                    SELECT("jobname");
                    SELECT("company");
                    SELECT("degree");
                    FROM("job_info");
                    if (industry != null) {
                        WHERE("industry=#{industry}");
                    }
                    if (city != null) {
                        WHERE("city=#{city}");
                    }
                    if (workTime > 0) {
                        WHERE("worktime<#{workTime}");
                    }
                    if (minSalary != null) {
                        WHERE("minsalary>#{minSalary}");
                        WHERE("maxsalary>#{minSalary}");
                    }
                    if (maxSalary != null) {
                        WHERE("maxsalary<#{maxSalary}");

                    }
                    if (rows != null && offset == null) {
                        LIMIT("#{rows}");
                    } else if (rows != null && offset != null) {
                        LIMIT("#{offset},#{rows}");
                    }
                }
            }.toString();
        }
    }
}