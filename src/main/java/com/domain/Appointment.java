package com.domain;

import java.io.Serializable;
import java.util.Date;



public class Appointment implements Serializable {
	 private static final long serialVersionUID = -1963711733;

	    private Integer id;
	    private String  context;
	    private Double  money;
	    private Integer flag;
	    private Integer vipId;
	    private Date    appointTime;
	    
	    private String name;//姓名
	    
	    private Double leftMoney;//余额
	    
	    private String telephone;//电话
	    
	    
	    
	    

	    public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Double getLeftMoney() {
			return leftMoney;
		}

		public void setLeftMoney(Double leftMoney) {
			this.leftMoney = leftMoney;
		}

		public Appointment() {}

	    public Appointment(Appointment value) {
	        this.id = value.id;
	        this.context = value.context;
	        this.money = value.money;
	        this.flag = value.flag;
	        this.vipId = value.vipId;
	        this.appointTime = value.appointTime;
	    }

	    public Appointment(
	        Integer id,
	        String  context,
	        Double  money,
	        Integer flag,
	        Integer vipId,
	        Date    appointTime
	    ) {
	        this.id = id;
	        this.context = context;
	        this.money = money;
	        this.flag = flag;
	        this.vipId = vipId;
	        this.appointTime = appointTime;
	    }

	    public Integer getId() {
	        return this.id;
	    }

	    public Appointment setId(Integer id) {
	        this.id = id;
	        return this;
	    }

	    public String getContext() {
	        return this.context;
	    }

	    public Appointment setContext(String context) {
	        this.context = context;
	        return this;
	    }

	    public Double getMoney() {
	        return this.money;
	    }

	    public Appointment setMoney(Double money) {
	        this.money = money;
	        return this;
	    }

	    public Integer getFlag() {
	        return this.flag;
	    }

	    public Appointment setFlag(Integer flag) {
	        this.flag = flag;
	        return this;
	    }

	    public Integer getVipId() {
	        return this.vipId;
	    }

	    public Appointment setVipId(Integer vipId) {
	        this.vipId = vipId;
	        return this;
	    }

	    public Date getAppointTime() {
	        return this.appointTime;
	    }

	    public Appointment setAppointTime(Date appointTime) {
	        this.appointTime = appointTime;
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
	        final Appointment other = (Appointment) obj;
	        if (id == null) {
	            if (other.id != null)
	                return false;
	        }
	        else if (!id.equals(other.id))
	            return false;
	        if (context == null) {
	            if (other.context != null)
	                return false;
	        }
	        else if (!context.equals(other.context))
	            return false;
	        if (money == null) {
	            if (other.money != null)
	                return false;
	        }
	        else if (!money.equals(other.money))
	            return false;
	        if (flag == null) {
	            if (other.flag != null)
	                return false;
	        }
	        else if (!flag.equals(other.flag))
	            return false;
	        if (vipId == null) {
	            if (other.vipId != null)
	                return false;
	        }
	        else if (!vipId.equals(other.vipId))
	            return false;
	        if (appointTime == null) {
	            if (other.appointTime != null)
	                return false;
	        }
	        else if (!appointTime.equals(other.appointTime))
	            return false;
	        return true;
	    }

	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((id == null) ? 0 : id.hashCode());
	        result = prime * result + ((context == null) ? 0 : context.hashCode());
	        result = prime * result + ((money == null) ? 0 : money.hashCode());
	        result = prime * result + ((flag == null) ? 0 : flag.hashCode());
	        result = prime * result + ((vipId == null) ? 0 : vipId.hashCode());
	        result = prime * result + ((appointTime == null) ? 0 : appointTime.hashCode());
	        return result;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder("Appointment (");

	        sb.append(id);
	        sb.append(", ").append(context);
	        sb.append(", ").append(money);
	        sb.append(", ").append(flag);
	        sb.append(", ").append(vipId);
	        sb.append(", ").append(appointTime);

	        sb.append(")");
	        return sb.toString();
	    }
	}
