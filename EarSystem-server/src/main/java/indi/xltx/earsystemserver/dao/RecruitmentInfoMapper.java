package indi.xltx.earsystemserver.dao;

import java.util.List;
import java.util.Map;

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

    // 获取最新招聘信息
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
            @Param("city") String city,
            @Param("workTime") Integer workTime,
            @Param("industry") String industry,
            @Param("minSalary") Integer minSalary,
            @Param("maxSalary") Integer maxSalary,
            @Param("offset") Integer offset,
            @Param("rows") Integer rows);

    // 按行业分的需求人数
    @SelectProvider(RecruitmentInfoSqlProvider.class)
    List<Map<String, ?>> getRequirementGroupByIndustry();

    // 按工作经验分的招聘信息条目
    @Select("select worktime, count(*) AS value from job_info group by worktime")
    List<Map<String, ?>> getRequirementGroupByWorkTime();

    // 按工作经验查询薪资：max min avg
    @SelectProvider(RecruitmentInfoSqlProvider.class)
    List<Map<String, ?>> getSalaryGroupByWorkTime(String mode);

    // 按专业分析
    @Select("SELECT profession,COUNT(*) AS VALUE FROM job_info INNER JOIN industry_profession WHERE	job_info.industry = industry_profession.industry GROUP BY industry_profession.profession")
    List<Map<String, ?>> getRequirementGroupByProfession();

    // 分行业按经验统计
    @Select("select count(*) from job_info where industry=#{industry} and worktime=#{workTime}")
    Integer getRequirementByIndustryAndWorkTime(@Param("industry") String industry,
            @Param("workTime") Integer workTime);

    // 按行业工资分
    @SelectProvider(RecruitmentInfoSqlProvider.class)
    List<Map<String, ?>> getSalaryGroupByIndustry();

    // 按照专业分析薪资
    @SelectProvider(RecruitmentInfoSqlProvider.class)
    List<Map<String, ?>> getSalaryGroupByProfession();

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
                @Param("city") String city,
                @Param("workTime") Integer workTime,
                @Param("industry") String industry,
                @Param("minSalary") Integer minSalary,
                @Param("maxSalary") Integer maxSalary,
                @Param("offset") Integer offset,
                @Param("rows") Integer rows) {
            return new SQL() {
                {
                    SELECT("id");
                    SELECT("jobname");
                    SELECT("company");
                    SELECT("degree");
                    FROM("job_info");
                    if (workTime != null && workTime > 0) {
                        WHERE("worktime<#{workTime}");
                    }
                    if (industry != null) {
                        WHERE("industry=#{industry}");
                    }
                    if (city != null) {
                        WHERE("city=#{city}");
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

        public static String getRequirementGroupByIndustry() {
            return new SQL() {
                {
                    SELECT("industry");
                    SELECT("SUM(quantity)");
                    FROM("job_info");
                    GROUP_BY("industry");
                }
            }.toString();
        }

        public static String getSalaryGroupByWorkTime(String mode) {
            return new SQL() {
                {
                    SELECT("worktime");
                    switch (mode) {
                        case "max":
                            SELECT("max(maxsalary) as value");
                            break;
                        case "avg":
                            SELECT("(avg(minsalary)+avg(maxsalary))/2 as value");
                            break;
                        case "min":
                            SELECT("min(minsalary) as value");
                        default:
                            break;
                    }
                    FROM("job_info");
                    GROUP_BY("worktime");
                }
            }.toString();
        }

        public static String getSalaryGroupByIndustry() {
            return new SQL() {
                {
                    SELECT("industry");
                    SELECT("round(AVG(maxsalary)) as max");

                    SELECT("round(AVG(minsalary)) as min");

                    SELECT("round((AVG(maxsalary)+AVG(minsalary))/2) as avg");

                    FROM("job_info");
                    GROUP_BY("industry");
                }
            }.toString();
        }

        public static String getSalaryGroupByProfession() {
            return new SQL() {
                {
                    SELECT("profession");
                    SELECT("round(AVG(maxsalary)) as max");

                    SELECT("round(AVG(minsalary)) as min");

                    SELECT("round((AVG(maxsalary)+AVG(minsalary))/2) as avg");

                    FROM("job_info");
                    INNER_JOIN("industry_profession");
                    WHERE("job_info.industry=industry_profession.industry");
                    GROUP_BY("profession");
                }
            }.toString();
        }
    }
}
