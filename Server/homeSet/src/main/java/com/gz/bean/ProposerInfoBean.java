package com.gz.bean;

import javax.persistence.Column;
import javax.persistence.Id;

import com.gz.model.common.BaseBean;

public class ProposerInfoBean extends BaseBean{
	/**
     * 主键
     */
    @Id
    private Integer id;

    /**
     * 申请人姓名
     */
    private String proposer;

    /**
     * 申请人所在城市
     */
    @Column(name = "inCity")
    private Long incity;

    /**
     * 有无房产（0.有  1.无）
     */
    @Column(name = "isHouseProperty")
    private Integer ishouseproperty;

    /**
     * 贷款金额
     */
    @Column(name = "loanAmount")
    private Integer loanamount;

    /**
     * 申请人IP(暂时做记录，不限制)
     */
    @Column(name = "proposerIP")
    private String proposerip;
    
    private String email;
    
    

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    /**
     * 手机号
     */
    private String tel;

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
     * 获取申请人姓名
     *
     * @return proposer - 申请人姓名
     */
    public String getProposer() {
        return proposer;
    }

    /**
     * 设置申请人姓名
     *
     * @param proposer 申请人姓名
     */
    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    /**
     * 获取申请人所在城市
     *
     * @return inCity - 申请人所在城市
     */
    public Long getIncity() {
        return incity;
    }

    /**
     * 设置申请人所在城市
     *
     * @param incity 申请人所在城市
     */
    public void setIncity(Long incity) {
        this.incity = incity;
    }

    /**
     * 获取有无房产（0.有  1.无）
     *
     * @return isHouseProperty - 有无房产（0.有  1.无）
     */
    public Integer getIshouseproperty() {
        return ishouseproperty;
    }

    /**
     * 设置有无房产（0.有  1.无）
     *
     * @param ishouseproperty 有无房产（0.有  1.无）
     */
    public void setIshouseproperty(Integer ishouseproperty) {
        this.ishouseproperty = ishouseproperty;
    }

    /**
     * 获取贷款金额
     *
     * @return loanAmount - 贷款金额
     */
    public Integer getLoanamount() {
        return loanamount;
    }

    /**
     * 设置贷款金额
     *
     * @param loanamount 贷款金额
     */
    public void setLoanamount(Integer loanamount) {
        this.loanamount = loanamount;
    }

    /**
     * 获取申请人IP(暂时做记录，不限制)
     *
     * @return proposerIP - 申请人IP(暂时做记录，不限制)
     */
    public String getProposerip() {
        return proposerip;
    }

    /**
     * 设置申请人IP(暂时做记录，不限制)
     *
     * @param proposerip 申请人IP(暂时做记录，不限制)
     */
    public void setProposerip(String proposerip) {
        this.proposerip = proposerip;
    }

    /**
     * 获取手机号
     *
     * @return tel - 手机号
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置手机号
     *
     * @param tel 手机号
     */
    public void setTel(String tel) {
        this.tel = tel;
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
		return "ProposerInfoBean [id=" + id + ", proposer=" + proposer + ", incity=" + incity + ", ishouseproperty="
				+ ishouseproperty + ", loanamount=" + loanamount + ", proposerip=" + proposerip + ", email=" + email
				+ ", tel=" + tel + ", createtime=" + createtime + ", modifytime=" + modifytime + "]";
	}
    
}
