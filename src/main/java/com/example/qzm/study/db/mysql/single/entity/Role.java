package com.example.qzm.study.db.mysql.single.entity;

/**
 * @ClassName Role
 * @Description TODO
 * @Version 1.0
 **/
public class Role {
    private long id;
    private long username;
    private String roleName;
    private int gender;
    private long gold;
    private long gp;
    private int exp;
    private int level;
    private long modifyTime;
    private long createTime = System.currentTimeMillis();

    public Role() {
    }

    public Role(long username, String roleName) {
        this.username = username;
        this.roleName = roleName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsername() {
        return username;
    }

    public void setUsername(long username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
    }

    public long getGp() {
        return gp;
    }

    public void setGp(long gp) {
        this.gp = gp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
