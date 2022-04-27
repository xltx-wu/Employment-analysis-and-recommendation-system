package indi.xltx.earsystemserver.pojo;

import java.sql.Date;

public class RecruitmentInfoCommon extends RecruitmentInfo {
    private float maxSalary;
    private float minSalary;
    private String city;
    private int workTime;
    private Date releaseTime;
    private String industry;
    private String quantity;
    private long userId;
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
     * @return the workTime
     */
    public int getWorkTime() {
        return workTime;
    }
    /**
     * @param workTime the workTime to set
     */
    public void setWorkTime(int workTime) {
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
    public String getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    
    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }


}
