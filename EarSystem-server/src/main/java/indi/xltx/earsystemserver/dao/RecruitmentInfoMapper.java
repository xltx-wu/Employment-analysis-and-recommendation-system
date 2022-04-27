package indi.xltx.earsystemserver.dao;

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
    Cursor<RecruitmentInfo> getLatestInfo(@Param("limit") int limit);

    @Select("select * from job_info where id = #{id}")
    RecruitmentInfoCommon getCommonInfoById(long id);

    @SelectProvider(RecruitmentInfoSqlProvider.class)
    Cursor<RecruitmentInfo> getInfoByKeyWord(String city, int workTime, String industry, int offset, int rows);

    Cursor<RecruitmentInfo> getInfoByCity();

    class RecruitmentInfoSqlProvider implements ProviderMethodResolver {
        // 获取最新职业信息（简略）的SQL语句生成器
        public static String getLatestInfo(@Param("limit") int limit) {
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

        public static String getInfoByKeyWord(String city, int workTime, String industry, int offset, int rows) {
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
                    LIMIT("#{offset},#{rows}");
                }
            }.toString();
        }
    }
}
