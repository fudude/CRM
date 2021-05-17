package com.dude.crm.workbench.service;

import com.dude.crm.workbench.domain.Clue;
import com.dude.crm.workbench.domain.Tran;

public interface ClueService {
    boolean save(Clue c);

    Clue detail(String id);

    boolean unbund(String id);

    boolean bund(String cid, String[] aids);


    boolean convert(String clueId, Tran t, String createBy);
}
