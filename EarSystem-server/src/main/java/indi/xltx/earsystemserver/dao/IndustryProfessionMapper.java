package indi.xltx.earsystemserver.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

@Mapper
public interface IndustryProfessionMapper {

    @SelectProvider(IndustryProfessionSqlProvider.class)
    List<String> getProfessionByIndustry(List<String> industryList);

    class IndustryProfessionSqlProvider implements ProviderMethodResolver {
        public static String getProfessionByIndustry(List<String> industryList) {
            return new SQL() {
                {
                    SELECT_DISTINCT("profession");
                    FROM("industry_profession");

                }
            }.toString();
        }
    }
}
