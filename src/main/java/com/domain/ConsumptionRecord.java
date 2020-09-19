package com.domain;

import java.io.Serializable;
import java.util.Date;



public class ConsumptionRecord implements Serializable {

    private static final long serialVersionUID = 829022669;

    private Integer id;
    private Double  money;
    private Date    time;
    private String  context;
    private Integer vipId;

    public ConsumptionRecord() {}

    public ConsumptionRecord(ConsumptionRecord value) {
        this.id = value.id;
        this.money = value.money;
        this.time = value.time;
        this.context = value.context;
        this.vipId = value.vipId;
    }

    public ConsumptionRecord(
        Integer id,
        Double  money,
        Date    time,
        String  context,
        Integer vipId
    ) {
        this.id = id;
        this.money = money;
        this.time = time;
        this.context = context;
        this.vipId = vipId;
    }

    public Integer getId() {
        return this.id;
    }

    public ConsumptionRecord setId(Integer id) {
        this.id = id;
        return this;
    }

    public Double getMoney() {
        return this.money;
    }

    public ConsumptionRecord setMoney(Double money) {
        this.money = money;
        return this;
    }

    public Date getTime() {
        return this.time;
    }

    public ConsumptionRecord setTime(Date time) {
        this.time = time;
        return this;
    }

    public String getContext() {
        return this.context;
    }

    public ConsumptionRecord setContext(String context) {
        this.context = context;
        return this;
    }

    public Integer getVipId() {
        return this.vipId;
    }

    public ConsumptionRecord setVipId(Integer vipId) {
        this.vipId = vipId;
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
        final ConsumptionRecord other = (ConsumptionRecord) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (money == null) {
            if (other.money != null)
                return false;
        }
        else if (!money.equals(other.money))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        }
        else if (!time.equals(other.time))
            return false;
        if (context == null) {
            if (other.context != null)
                return false;
        }
        else if (!context.equals(other.context))
            return false;
        if (vipId == null) {
            if (other.vipId != null)
                return false;
        }
        else if (!vipId.equals(other.vipId))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((money == null) ? 0 : money.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + ((context == null) ? 0 : context.hashCode());
        result = prime * result + ((vipId == null) ? 0 : vipId.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ConsumptionRecord (");

        sb.append(id);
        sb.append(", ").append(money);
        sb.append(", ").append(time);
        sb.append(", ").append(context);
        sb.append(", ").append(vipId);

        sb.append(")");
        return sb.toString();
    }
}
