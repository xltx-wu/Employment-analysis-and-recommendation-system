package indi.xltx.earsystemserver.pojo;

import java.sql.Date;

public class RecruitmentInfoCommon extends RecruitmentInfo {
    private float maxSalary;
    private float minSalary;
    private String city;
    private String district;
    private String workTime;
    private Date releaseTime;
    private String url;

    /**
     * @return the maxSalary
     */
    public float getMaxSalary() {
        return maxSalary;
    }

    /**
     * @param maxSalary the maxSalary to set
     */
    public void setMaxSalary(float maxSalary) {
        this.maxSalary = maxSalary;
    }

    /**
     * @return the minSalary
     */
    public float getMinSalary() {
        return minSalary;
    }

    /**
     * @param minSalary the minSalary to set
     */
    public void setMinSalary(float minSalary) {
        this.minSalary = minSalary;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the workTime
     */
    public String getWorkTime() {
        return workTime;
    }

    /**
     * @param workTime the workTime to set
     */
    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    /**
     * @return the releaseTime
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * @param releaseTime the releaseTime to set
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
