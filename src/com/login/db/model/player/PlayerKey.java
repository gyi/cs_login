package com.login.db.model.player;

public class PlayerKey {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cs_player.platformId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	private Integer platformid;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column cs_player.userId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	private String userid;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cs_player.platformId
	 * @return  the value of cs_player.platformId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	public Integer getPlatformid() {
		return platformid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cs_player.platformId
	 * @param platformid  the value for cs_player.platformId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	public void setPlatformid(Integer platformid) {
		this.platformid = platformid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column cs_player.userId
	 * @return  the value of cs_player.userId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column cs_player.userId
	 * @param userid  the value for cs_player.userId
	 * @mbggenerated  Thu Aug 21 18:39:13 CST 2014
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
}