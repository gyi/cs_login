package com.login.db.model.platform;

public class Platform {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cs_platform.id
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cs_platform.platformName
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    private String platformname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cs_platform.describle
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    private String describle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cs_platform.isDeleted
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    private Integer isdeleted;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column cs_platform.platformKey
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    private String platformkey;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_platform.id
     *
     * @return the value of cs_platform.id
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_platform.id
     *
     * @param id the value for cs_platform.id
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_platform.platformName
     *
     * @return the value of cs_platform.platformName
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public String getPlatformname() {
        return platformname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_platform.platformName
     *
     * @param platformname the value for cs_platform.platformName
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public void setPlatformname(String platformname) {
        this.platformname = platformname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_platform.describle
     *
     * @return the value of cs_platform.describle
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public String getDescrible() {
        return describle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_platform.describle
     *
     * @param describle the value for cs_platform.describle
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public void setDescrible(String describle) {
        this.describle = describle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_platform.isDeleted
     *
     * @return the value of cs_platform.isDeleted
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public Integer getIsdeleted() {
        return isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_platform.isDeleted
     *
     * @param isdeleted the value for cs_platform.isDeleted
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public void setIsdeleted(Integer isdeleted) {
        this.isdeleted = isdeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column cs_platform.platformKey
     *
     * @return the value of cs_platform.platformKey
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public String getPlatformkey() {
        return platformkey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column cs_platform.platformKey
     *
     * @param platformkey the value for cs_platform.platformKey
     *
     * @mbggenerated Wed Aug 13 10:21:42 CST 2014
     */
    public void setPlatformkey(String platformkey) {
        this.platformkey = platformkey;
    }
}