package com.dao;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.CodeMessage;
import sxj.db.tables.records.CodeMessageRecord;
import static sxj.db.Tables.CODE_MESSAGE;
@Repository
public class CodeMessageDao extends DAOImpl<CodeMessageRecord,CodeMessage, Integer> {

    @Autowired
    public CodeMessageDao(Configuration configuration) {
        super(CODE_MESSAGE, CodeMessage.class, configuration);
    }
    @Override
    protected Integer getId(CodeMessage codeMessage) {
        return codeMessage.getCode();
    }

    
}
