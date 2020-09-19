package com.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * @author sheng
 */
public final class DateRange implements Serializable {
    private static final long serialVersionUID = -7071173065659002011L;
    /**
     * 开始时间
     */
    public Date start;
    /**
     * 结束时间
     */
    public Date end;

    public DateRange() {
    }

    public DateRange(Date start, Date end) {
        this.start = start;
        this.end = end(end);
    }

    public Date getStart() {
        return start;
    }

    public DateRange setStart(Date start) {
        this.start = start;
        return this;
    }

    public Date getEnd() {
        return end;
    }

    public DateRange setEnd(Date end) {
        this.end = end(end);
        return this;
    }

    public DateRange endDirect(Date end) {
        this.end = end;
        return this;
    }

    private Date end(Date date) {
        if (Objects.isNull(date)) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
