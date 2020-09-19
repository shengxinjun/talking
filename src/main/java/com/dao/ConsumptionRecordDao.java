package com.dao;

import static sxj.db.Tables.CONSUMPTION_RECORD;

import java.util.ArrayList;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.ConsumptionRecord;

import sxj.db.tables.records.ConsumptionRecordRecord;


@Repository
public class ConsumptionRecordDao extends DAOImpl<ConsumptionRecordRecord,ConsumptionRecord, Integer> {

	@Autowired
	private DSLContext dslContext;
    @Autowired
    public ConsumptionRecordDao(Configuration configuration) {
        super(CONSUMPTION_RECORD, ConsumptionRecord.class, configuration);
    }
    @Override
    protected Integer getId(ConsumptionRecord consumptionRecord) {
        return consumptionRecord.getId();
    }
    
    public List<ConsumptionRecord> findVipConsumptionRecords(Integer vipId){
    	List<ConsumptionRecord> list = new ArrayList<ConsumptionRecord>();
    	Condition condition = CONSUMPTION_RECORD.VIP_ID.eq(vipId);
    	list = dslContext.select().from(CONSUMPTION_RECORD).where(condition).fetchInto(ConsumptionRecord.class);
    	return list;
    }
    
}
