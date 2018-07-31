package com.gz.model;

import javax.persistence.*;

import com.gz.model.common.BaseBean;

public class City{
    /**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 城市唯一标识码
     */
    private Long code;

    /**
     * 城市名称
     */
    private String city;

    /**
     * 省
     */
    private String province;

    /**
     * 城市级别(1,2,3对应省市区)
     */
    private Integer level;

    /**
     * 城市备注
     */
    private String remark;

    /**
     * 该城市是否在本公司业务范围内（0.是    1.否   ）
     */
    @Column(name = "isBusiness")
    private Integer isbusiness;

    /**
     * 记录生成时间
     */
    @Column(name = "createTime")
    private Long createtime;

    /**
     * 记录修改时间
     */
    @Column(name = "modifyTime")
    private Long modifytime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取城市唯一标识码
     *
     * @return code - 城市唯一标识码
     */
    public Long getCode() {
        return code;
    }

    /**
     * 设置城市唯一标识码
     *
     * @param code 城市唯一标识码
     */
    public void setCode(Long code) {
        this.code = code;
    }

    /**
     * 获取城市名称
     *
     * @return city - 城市名称
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市名称
     *
     * @param city 城市名称
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取省
     *
     * @return province - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取城市级别(1,2,3对应省市区)
     *
     * @return level - 城市级别(1,2,3对应省市区)
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * 设置城市级别(1,2,3对应省市区)
     *
     * @param level 城市级别(1,2,3对应省市区)
     */
    public void setLevel(Integer level) {
        this.level = level;
    }

    /**
     * 获取城市备注
     *
     * @return remark - 城市备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置城市备注
     *
     * @param remark 城市备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取该城市是否在本公司业务范围内（0.是    1.否   ）
     *
     * @return isBusiness - 该城市是否在本公司业务范围内（0.是    1.否   ）
     */
    public Integer getIsbusiness() {
        return isbusiness;
    }

    /**
     * 设置该城市是否在本公司业务范围内（0.是    1.否   ）
     *
     * @param isbusiness 该城市是否在本公司业务范围内（0.是    1.否   ）
     */
    public void setIsbusiness(Integer isbusiness) {
        this.isbusiness = isbusiness;
    }

    /**
     * 获取记录生成时间
     *
     * @return createTime - 记录生成时间
     */
    public Long getCreatetime() {
        return createtime;
    }

    /**
     * 设置记录生成时间
     *
     * @param createtime 记录生成时间
     */
    public void setCreatetime(Long createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取记录修改时间
     *
     * @return modifyTime - 记录修改时间
     */
    public Long getModifytime() {
        return modifytime;
    }

    /**
     * 设置记录修改时间
     *
     * @param modifytime 记录修改时间
     */
    public void setModifytime(Long modifytime) {
        this.modifytime = modifytime;
    }

	@Override
	public String toString() {
		return "City [id=" + id + ", code=" + code + ", city=" + city + ", province=" + province + ", level=" + level
				+ ", remark=" + remark + ", isbusiness=" + isbusiness + ", createtime=" + createtime + ", modifytime="
				+ modifytime + "]";
	}
    
}