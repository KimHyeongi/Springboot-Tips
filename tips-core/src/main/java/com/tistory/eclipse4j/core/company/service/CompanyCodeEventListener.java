package com.tistory.eclipse4j.core.company.service;

import com.tistory.eclipse4j.core.jpa.db1.entity.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class CompanyCodeEventListener {

    @TransactionalEventListener(condition = "#event.updated", phase = TransactionPhase.AFTER_COMMIT)
    public void eventListenerForUpdateCompanyCode(CompanyCodeUpdateEvent event) {
        Company company = (Company)event.getSource();
        log.info("[EventListener] Update Company.id {}, Company.code {}, Message {}", company.getId(), company.getCode(), event.getMessage());
    }
}
