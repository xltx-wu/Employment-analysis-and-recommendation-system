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

    @Select("select * from qcwy where id = #{id}")
    RecruitmentInfoCommon getCommonInfoById(long id);

    @SelectProvider(RecruitmentInfoSqlProvider.class)
    Cursor<RecruitmentInfo> getInfoByKeyWord();

    Cursor<RecruitmentInfo> getInfoByAddress();

    class RecruitmentInfoSqlProvider implements ProviderMethodResolver {
        // 获取最新职业信息（简略）的SQL语句生成器
        public static String getLatestInfo(@Param("limit") int limit) {

            String sql0 = new SQL() {
                {

                    SELECT("id");
                    SELECT("name");
                    SELECT("company");
                    SELECT("releasetime");
                    FROM("qcwy");
                    ORDER_BY("releasetime");
                    LIMIT(limit);
                }
            }.toString();

            String sql1 = new SQL() {
                {
                    SELECT("id");
                    SELECT("jobname AS name");
                    SELECT("jobunit AS company");
                    SELECT("releasetime");
                    FROM("gwy");
                    ORDER_BY("releasetime");
                    LIMIT(limit);
                }
            }.toString();

            String sql = "(%s) UNION (%s)";
            return String.format(sql, sql0, sql1);
        }
    }
}
