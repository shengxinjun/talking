/**
 * This class is generated by jOOQ
 */
package sxj.db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.4"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VipUser implements Serializable {

    private static final long serialVersionUID = -2138777547;

    private Integer id;
    private String  name;
    private Integer age;
    private String  telephone;
    private Integer integrate;
    private Integer level;
    private Integer sex;
    private Double  money;

    public VipUser() {}

    public VipUser(VipUser value) {
        this.id = value.id;
        this.name = value.name;
        this.age = value.age;
        this.telephone = value.telephone;
        this.integrate = value.integrate;
        this.level = value.level;
        this.sex = value.sex;
        this.money = value.money;
    }

    public VipUser(
        Integer id,
        String  name,
        Integer age,
        String  telephone,
        Integer integrate,
        Integer level,
        Integer sex,
        Double  money
    ) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
        this.integrate = integrate;
        this.level = level;
        this.sex = sex;
        this.money = money;
    }

    public Integer getId() {
        return this.id;
    }

    public VipUser setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public VipUser setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return this.age;
    }

    public VipUser setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public VipUser setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Integer getIntegrate() {
        return this.integrate;
    }

    public VipUser setIntegrate(Integer integrate) {
        this.integrate = integrate;
        return this;
    }

    public Integer getLevel() {
        return this.level;
    }

    public VipUser setLevel(Integer level) {
        this.level = level;
        return this;
    }

    public Integer getSex() {
        return this.sex;
    }

    public VipUser setSex(Integer sex) {
        this.sex = sex;
        return this;
    }

    public Double getMoney() {
        return this.money;
    }

    public VipUser setMoney(Double money) {
        this.money = money;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final VipUser other = (VipUser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        }
        else if (!name.equals(other.name))
            return false;
        if (age == null) {
            if (other.age != null)
                return false;
        }
        else if (!age.equals(other.age))
            return false;
        if (telephone == null) {
            if (other.telephone != null)
                return false;
        }
        else if (!telephone.equals(other.telephone))
            return false;
        if (integrate == null) {
            if (other.integrate != null)
                return false;
        }
        else if (!integrate.equals(other.integrate))
            return false;
        if (level == null) {
            if (other.level != null)
                return false;
        }
        else if (!level.equals(other.level))
            return false;
        if (sex == null) {
            if (other.sex != null)
                return false;
        }
        else if (!sex.equals(other.sex))
            return false;
        if (money == null) {
            if (other.money != null)
                return false;
        }
        else if (!money.equals(other.money))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((age == null) ? 0 : age.hashCode());
        result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
        result = prime * result + ((integrate == null) ? 0 : integrate.hashCode());
        result = prime * result + ((level == null) ? 0 : level.hashCode());
        result = prime * result + ((sex == null) ? 0 : sex.hashCode());
        result = prime * result + ((money == null) ? 0 : money.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("VipUser (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(age);
        sb.append(", ").append(telephone);
        sb.append(", ").append(integrate);
        sb.append(", ").append(level);
        sb.append(", ").append(sex);
        sb.append(", ").append(money);

        sb.append(")");
        return sb.toString();
    }
}
