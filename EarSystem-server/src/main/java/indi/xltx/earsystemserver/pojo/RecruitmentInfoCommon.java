package indi.xltx.earsystemserver.pojo;

import java.sql.Date;

public class RecruitmentInfoCommon extends RecruitmentInfo {
    private Float maxSalary;
    private Float minSalary;
    private String city;
    private Integer workTime;
    private Date releaseTime;
    private String industry;
    private Integer quantity;
    private Long userId;
    /**
     * @return the maxSalary
     */
    public Float getMaxSalary() {
        return maxSalary;
    }
    /**
     * @param maxSalary the maxSalary to set
     */
    public void setMaxSalary(Float maxSalary) {
        this.maxSalary = maxSalary;
    }
    /**
     * @return the minSalary
     */
    public Float getMinSalary() {
        return minSalary;
    }
    /**
     * @param minSalary the minSalary to set
     */
    public void setMinSalary(Float minSalary) {
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
     * @return the workTime
     */
    public Integer getWorkTime() {
        return workTime;
    }
    /**
     * @param workTime the workTime to set
     */
    public void setWorkTime(Integer workTime) {
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
     * @return the industry
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * @param industry the industry to set
     */
    public void setIndustry(String industry) {
        this.industry = industry;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }
    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
