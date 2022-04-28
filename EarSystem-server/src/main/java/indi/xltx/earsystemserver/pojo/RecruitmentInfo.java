package indi.xltx.earsystemserver.pojo;

public class RecruitmentInfo {
    private Long id; // 唯一标识id
    private String jobname; // 工作名称
    private String company; // 用人单位名称
    private String degree; // 学历要求

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the jobname
     */
    public String getJobname() {
        return jobname;
    }

    /**
     * @param jobname the jobname to set
     */
    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return the degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree the degree to set
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

}
